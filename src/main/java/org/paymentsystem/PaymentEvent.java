package org.paymentsystem;

public record PaymentEvent (EventType eventType, int amount) {
}
