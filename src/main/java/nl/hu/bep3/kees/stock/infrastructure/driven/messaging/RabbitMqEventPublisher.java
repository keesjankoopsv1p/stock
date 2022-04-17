package nl.hu.bep3.kees.stock.infrastructure.driven.messaging;

import nl.hu.bep3.kees.stock.core.domain.event.IngredientEvent;
import nl.hu.bep3.kees.stock.core.port.messaging.IngredientEventPublisher;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

public class RabbitMqEventPublisher implements IngredientEventPublisher {
    private final RabbitTemplate rabbitTemplate;
    private final String restaurantExchange;

    public RabbitMqEventPublisher(
            RabbitTemplate rabbitTemplate,
            String restaurantExchange
    ) {
        this.rabbitTemplate = rabbitTemplate;
        this.restaurantExchange = restaurantExchange;
    }

    public void publish(IngredientEvent event) {
        this.rabbitTemplate.convertAndSend(restaurantExchange, event.getEventKey(), event);
    }
}
