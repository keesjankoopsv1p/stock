package nl.hu.bep3.kees.stock.infrastructure.driver.web.response;

import nl.hu.bep3.kees.stock.core.domain.Ingredient;

import java.util.ArrayList;
import java.util.List;

public class StockInfo {
    public List<IngredientInfo> ingredients;

    public StockInfo(List<Ingredient> ingredients){
        this.ingredients = new ArrayList<>();
        for (Ingredient ingredient: ingredients) {
            this.ingredients.add(new IngredientInfo(ingredient));
        }
    }

    private class IngredientInfo{
        public String name;
        public Integer quantity;
        public IngredientInfo(Ingredient ingredient) {
            this.name = ingredient.getName();
            this.quantity = ingredient.getQuantity();
        }
    }

}
