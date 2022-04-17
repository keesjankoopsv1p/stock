package nl.hu.bep3.kees.stock.core.domain.event;

import java.util.UUID;

public class IngredientOutOfStock extends IngredientEvent{
    private final UUID ingredient;

    public IngredientOutOfStock(UUID ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String getEventKey() { return "stock.meals.out.of.stock"; }

    public UUID getIngredient() {
        return ingredient;
    }
}
