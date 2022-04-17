package nl.hu.bep3.kees.stock.infrastructure.driver.web;

import nl.hu.bep3.kees.stock.core.application.StockCommandHandler;
import nl.hu.bep3.kees.stock.core.application.command.AddNewIngredient;
import nl.hu.bep3.kees.stock.core.domain.Ingredient;
import nl.hu.bep3.kees.stock.infrastructure.driver.web.request.AddNewIngredientRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/ingredients")
public class StockCommandController {
    private final StockCommandHandler commandHandler;

    public StockCommandController(StockCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @PostMapping
    public Ingredient addNewIngredient(@Valid @RequestBody AddNewIngredientRequest request) {
        return this.commandHandler.handle(
                new AddNewIngredient(request.name, request.quantity)
        );
    }
}
