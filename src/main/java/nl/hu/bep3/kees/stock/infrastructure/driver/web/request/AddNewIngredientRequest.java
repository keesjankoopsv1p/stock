package nl.hu.bep3.kees.stock.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;

public class AddNewIngredientRequest {
    @NotBlank
    public String name;
    public Integer quantity;
}
