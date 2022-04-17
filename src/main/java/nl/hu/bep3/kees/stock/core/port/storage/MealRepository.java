package nl.hu.bep3.kees.stock.core.port.storage;

import java.util.List;

public interface MealRepository {
    List<String> getIngredientsByMeal(String id);
}
