package nl.hu.bep3.kees.stock.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.UUID;

public class MealAddedEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public String meal;
}
