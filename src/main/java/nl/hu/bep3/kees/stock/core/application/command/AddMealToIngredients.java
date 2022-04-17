package nl.hu.bep3.kees.stock.core.application.command;

public class AddMealToIngredients {
    private final String meal;

    public AddMealToIngredients(String meal) {
        this.meal = meal;
    }

    public String getMeal() {
        return meal;
    }
}
