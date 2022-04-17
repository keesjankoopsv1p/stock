package nl.hu.bep3.kees.stock.core.application.query;

import java.util.UUID;

public class GetIngredient {
    private final UUID id;

    public GetIngredient(UUID id) { this.id = id; }

    public UUID getId() { return this.id; }
}
