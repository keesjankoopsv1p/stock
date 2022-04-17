package nl.hu.bep3.kees.stock.core.application.command;

import java.util.Hashtable;

public class DeductMealIngredients {
    private final Hashtable<String, Integer> mealsOrdered;

    public DeductMealIngredients(Hashtable<String, Integer> mealsOrdered) {
        this.mealsOrdered = mealsOrdered;
    }

    public Hashtable<String, Integer> getMealsOrdered() {
        return mealsOrdered;
    }
}
