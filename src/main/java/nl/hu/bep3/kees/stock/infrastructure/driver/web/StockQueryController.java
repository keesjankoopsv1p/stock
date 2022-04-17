package nl.hu.bep3.kees.stock.infrastructure.driver.web;

import nl.hu.bep3.kees.stock.core.application.StockQueryHandler;
import nl.hu.bep3.kees.stock.core.application.query.GetIngredient;
import nl.hu.bep3.kees.stock.core.application.query.GetIngredientsByMeal;
import nl.hu.bep3.kees.stock.core.domain.Ingredient;
import nl.hu.bep3.kees.stock.infrastructure.driver.web.response.IngredientQuantityResponse;
import nl.hu.bep3.kees.stock.infrastructure.driver.web.response.StockInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/ingredients")
public class StockQueryController {
    private final StockQueryHandler queryHandler;

    public StockQueryController(StockQueryHandler queryHandler) {
        this.queryHandler = queryHandler;
    }

    @GetMapping()
    public StockInfo showStock(){
        List<Ingredient> ingredientList = this.queryHandler.handle();
        return new StockInfo(ingredientList);
    }

    @GetMapping("/{id}")
    public IngredientQuantityResponse getIngredient(@PathVariable UUID id){
        Ingredient ingredient = queryHandler.handle(new GetIngredient(id));
        IngredientQuantityResponse responseObject = new IngredientQuantityResponse(ingredient.getId(), ingredient.getQuantity());
        return responseObject;
    }

    @GetMapping("/meal/{id}")
    public List<IngredientQuantityResponse> getIngredientsByMeal(@PathVariable String id){
        List<IngredientQuantityResponse> response = new ArrayList<>();
        List<Ingredient> ingredients = queryHandler.handle(new GetIngredientsByMeal(id));
        for (Ingredient ingredient: ingredients) {
            response.add(new IngredientQuantityResponse(ingredient.getId(), ingredient.getQuantity()));
        }
        return response;
    }
}
