package nl.hu.bep3.kees.stock.infrastructure.driver.messaging.event;

import java.time.Instant;
import java.util.Hashtable;
import java.util.UUID;

public class OrderPlacedEvent {
    public UUID eventId;
    public String eventKey;
    public Instant eventDate;
    public String order;
    public Hashtable<String, Integer> meals;
}
