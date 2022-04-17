package nl.hu.bep3.kees.stock.infrastructure.driver.messaging;

import nl.hu.bep3.kees.stock.core.application.StockCommandHandler;
import nl.hu.bep3.kees.stock.core.application.command.AddMealToIngredients;
import nl.hu.bep3.kees.stock.core.application.command.DeductMealIngredients;
import nl.hu.bep3.kees.stock.infrastructure.driver.messaging.event.MealAddedEvent;
import nl.hu.bep3.kees.stock.infrastructure.driver.messaging.event.OrderPlacedEvent;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqEventListener {
    private final StockCommandHandler commandHandler;

    public RabbitMqEventListener(StockCommandHandler commandHandler) {
        this.commandHandler = commandHandler;
    }

    @RabbitListener(queues = "#{'${messaging.queue.meals-stock}'}")
    void listen(MealAddedEvent event) {
        switch (event.eventKey) {
            case "meals.stock.added":
                this.commandHandler.handle(
                        new AddMealToIngredients(event.meal)
                );
        }
    }

    @RabbitListener(queues = "#{'${messaging.queue.orders-stock}'}")
    void listen(OrderPlacedEvent event) {
        switch (event.eventKey) {
            case "orders.stock.placed":
                this.commandHandler.handle(
                        new DeductMealIngredients(event.meals)
                );
        }
    }
}
