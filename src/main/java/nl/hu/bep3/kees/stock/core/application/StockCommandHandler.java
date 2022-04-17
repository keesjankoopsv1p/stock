package nl.hu.bep3.kees.stock.core.application;

import nl.hu.bep3.kees.stock.core.application.command.AddMealToIngredients;
import nl.hu.bep3.kees.stock.core.application.command.AddNewIngredient;
import nl.hu.bep3.kees.stock.core.application.command.DeductMealIngredients;
import nl.hu.bep3.kees.stock.core.domain.Ingredient;
import nl.hu.bep3.kees.stock.core.domain.event.IngredientEvent;
import nl.hu.bep3.kees.stock.core.domain.exception.IngredientNotFound;
import nl.hu.bep3.kees.stock.core.port.messaging.IngredientEventPublisher;
import nl.hu.bep3.kees.stock.core.port.storage.IngredientRepository;
import nl.hu.bep3.kees.stock.core.port.storage.MealRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

@Service
public class StockCommandHandler {
    private final IngredientRepository ingredientsrepository;
    private final MealRepository mealRepository;
    private final IngredientEventPublisher eventPublisher;

    public StockCommandHandler(IngredientRepository ingredientsrepository, MealRepository mealRepository, IngredientEventPublisher eventPublisher){
        this.ingredientsrepository = ingredientsrepository;
        this.mealRepository = mealRepository;
        this.eventPublisher = eventPublisher;
    }

    // add new ingredient
    public Ingredient handle(AddNewIngredient command) {
        Ingredient newIngredient = new Ingredient(command.getName(), command.getQuantity());
        this.ingredientsrepository.save(newIngredient);
        return newIngredient;
    }

    // add the new meal to the ingredients it's made of
    public List<Ingredient> handle(AddMealToIngredients command) {
        List<String> ingredientsToUpdate = mealRepository.getIngredientsByMeal(command.getMeal());
        List<Ingredient> ingredients = new ArrayList<>();
        for (String id: ingredientsToUpdate) {
            ingredients.add(getIngredientById(UUID.fromString(id)));
        }
        for (Ingredient ingredient: ingredients) {
            ingredient.addMeal(command.getMeal());
            this.ingredientsrepository.save(ingredient);
        }
        return ingredients;
    }

    // adjust stock quantity according to the meals in the order
    // het was een lange dag..
    public void handle(DeductMealIngredients command) {
        Hashtable<Ingredient, Integer> ingredientsToDeduct = new Hashtable<>();
        Hashtable<String, Integer> orderedMeals = command.getMealsOrdered();
        for (String meal: orderedMeals.keySet()) {
            Integer quantity = orderedMeals.get(meal);
            List<Ingredient> currentMealIngredients = ingredientsrepository.findIngredientsByMeal(meal);
            for (Ingredient ingredient: currentMealIngredients) {
                ingredientsToDeduct = addAffectedIngredientToHashtable(ingredientsToDeduct, ingredient, quantity);
            }
        }
        for (Ingredient affectedIngredient: ingredientsToDeduct.keySet()) {
            Integer quantityToDeduct = ingredientsToDeduct.get(affectedIngredient);
            affectedIngredient.lowerQuantity(quantityToDeduct);
            this.ingredientsrepository.save(affectedIngredient);
            publishEventsFor(affectedIngredient);
        }
    }

    private Ingredient getIngredientById(UUID id) {
        return this.ingredientsrepository.findById(id)
                .orElseThrow(() -> new IngredientNotFound(id.toString()));
    }

    private void publishEventsFor(Ingredient ingredient){
        List<IngredientEvent> events = ingredient.listEvents();
        events.forEach(eventPublisher::publish);
        ingredient.clearEvents();
    }

    private Hashtable<Ingredient, Integer> addAffectedIngredientToHashtable(Hashtable<Ingredient, Integer> ingredientTable, Ingredient ingredient, Integer quantity){
        boolean alreadyInTable = false;
        for (Ingredient currentIngredient: ingredientTable.keySet()) {
            if (currentIngredient.equals(ingredient)) {
                ingredientTable.replace(currentIngredient, ingredientTable.get(currentIngredient) + quantity);
                alreadyInTable = true;
            }
        }
        if (!alreadyInTable) {
            ingredientTable.put(ingredient, quantity);
        }
        return ingredientTable;
    }
}
