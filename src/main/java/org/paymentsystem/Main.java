package org.paymentsystem;

import java.util.List;

public class Main {
    public static void main(String[] args) {
            List<PaymentEvent> eventsPartiallyPaid = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1)
        );

        List<PaymentEvent> eventsCancelled = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.PAYMENT_CANCELLED, 1)
        );

        List<PaymentEvent> eventIsPaid = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 2)
        );

        List<PaymentEvent> eventIsNew = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.PAYMENT_CREATED, 3)

        );

        System.out.println("State of event: " + PaymentStateService.determinePaymentState(eventIsPaid));
        System.out.println("State of event: " + PaymentStateService.determinePaymentState(eventsCancelled));
        System.out.println("State of event : " + PaymentStateService.determinePaymentState(eventsPartiallyPaid));
        System.out.println("State of event: " + PaymentStateService.determinePaymentState(eventIsNew));
    }
}