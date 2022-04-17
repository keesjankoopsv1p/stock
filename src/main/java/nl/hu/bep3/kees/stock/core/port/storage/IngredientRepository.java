package nl.hu.bep3.kees.stock.core.port.storage;

import nl.hu.bep3.kees.stock.core.domain.Ingredient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IngredientRepository extends MongoRepository<Ingredient, UUID> {

    //find all ingredients that are used in the given meal
    @Query(value = "{ 'meals' : ?0 }")
    List<Ingredient> findIngredientsByMeal(String mealId);
}
