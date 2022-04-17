package nl.hu.bep3.kees.stock.core.application.command;

public class AddNewIngredient {
    private final String name;
    private final Integer quantity;

    public AddNewIngredient(String name, Integer quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
