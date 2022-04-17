package nl.hu.bep3.kees.stock.core.port.messaging;

import nl.hu.bep3.kees.stock.core.domain.event.IngredientEvent;

public interface IngredientEventPublisher {
    void publish(IngredientEvent event);
}
