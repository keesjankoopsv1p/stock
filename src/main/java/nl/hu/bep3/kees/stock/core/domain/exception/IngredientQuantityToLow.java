package nl.hu.bep3.kees.stock.core.domain.exception;

public class IngredientQuantityToLow extends RuntimeException{
    public IngredientQuantityToLow(String message) { super(message); }
}
