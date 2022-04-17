package nl.hu.bep3.kees.stock.core.application.query;

public class GetIngredientsByMeal {
    private final String meal;

    public GetIngredientsByMeal(String meal) {
        this.meal = meal;
    }

    public String getMeal() {
        return meal;
    }
}
