package nl.hu.bep3.kees.stock.infrastructure.driver.web.response;

import java.util.UUID;

public class IngredientQuantityResponse {
    public UUID id;
    public Integer quantity;
    public IngredientQuantityResponse(UUID id, Integer quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
