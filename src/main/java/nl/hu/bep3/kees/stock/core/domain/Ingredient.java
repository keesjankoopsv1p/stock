package nl.hu.bep3.kees.stock.core.domain;

import nl.hu.bep3.kees.stock.core.domain.event.IngredientEvent;
import nl.hu.bep3.kees.stock.core.domain.event.IngredientOutOfStock;
import nl.hu.bep3.kees.stock.core.domain.exception.IngredientQuantityToLow;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document
public class Ingredient {
    @Id
    private UUID id;
    @Indexed(unique = true)
    private String name;
    @Indexed
    private Integer quantity;
    @Indexed
    private Set<String> meals;
    @Transient
    private List<IngredientEvent> events = new ArrayList<>();

    public Ingredient(String name, Integer quantity){
        this.id = UUID.randomUUID();
        this.name = name;
        this.quantity = quantity;
        this.meals = new HashSet<>();
    }

    // methods
    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Set<String> getMeals() {
        return meals;
    }

    public void lowerQuantity(Integer amount){
        if (amount > this.quantity){
            throw new IngredientQuantityToLow(this.name);
        }
        this.quantity  = this.quantity - amount;
        if (this.quantity == 0){
            events.add(new IngredientOutOfStock(this.id));
        }
    }

    public void addMeal(String meal) {
        this.meals.add(meal);
    }

    public void clearEvents() {
        this.events.clear();
    }

    public List<IngredientEvent> listEvents() { return events; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Ingredient other = (Ingredient) obj;
        if (!this.id.equals(((Ingredient) obj).id)) {
            return false;
        }

        return true;
    }
}
