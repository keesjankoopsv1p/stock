package nl.hu.bep3.kees.stock.core.application;

import nl.hu.bep3.kees.stock.core.application.query.GetIngredient;
import nl.hu.bep3.kees.stock.core.application.query.GetIngredientsByMeal;
import nl.hu.bep3.kees.stock.core.domain.Ingredient;
import nl.hu.bep3.kees.stock.core.domain.exception.IngredientNotFound;
import nl.hu.bep3.kees.stock.core.port.storage.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockQueryHandler {
    private final IngredientRepository ingredientRepository;

    public StockQueryHandler(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    //get information of entire stock
    public List<Ingredient> handle() {
        return ingredientRepository.findAll();
    }

    // get ingredient by id
    public Ingredient handle(GetIngredient query) {
        return getIngredientById(query.getId());
    }

    // get ingredients by mealId
    public List<Ingredient> handle(GetIngredientsByMeal query) {
        return ingredientRepository.findIngredientsByMeal(query.getMeal());
    }

    private Ingredient getIngredientById(UUID id) {
        return this.ingredientRepository.findById(id)
                .orElseThrow(() -> new IngredientNotFound(id.toString()));
    }
}
