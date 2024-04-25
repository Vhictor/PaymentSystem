package org.paymentsystem;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentStateServiceTest {

    @Test
    public void testPartiallyPaidPaymentState() {
        List<PaymentEvent> eventIsPartiallyPaid = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1)
        );

       assertEquals(PaymentStateService.determinePaymentState(eventIsPartiallyPaid), PaymentState.PARTIALLY_PAID);

    }

    @Test
    public void testCancelledPaymentState() {
        List<PaymentEvent> eventIsCancelled = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.PAYMENT_CANCELLED, 1)
        );

        assertEquals(PaymentStateService.determinePaymentState(eventIsCancelled), PaymentState.CANCELLED);

    }

    @Test
    public void testNewPaymentState() {
        List<PaymentEvent> eventIsNew = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3)
        );
        assertEquals(PaymentStateService.determinePaymentState(eventIsNew), PaymentState.NEW);

    }

    @Test
    public void testPaidPaymentState() {
        List<PaymentEvent> eventIsPaid = List.of(
                new PaymentEvent(EventType.PAYMENT_CREATED, 3),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 1),
                new PaymentEvent(EventType.TRANSFER_RECEIVED, 2)
        );

        assertEquals(PaymentStateService.determinePaymentState(eventIsPaid), PaymentState.PAID);

    }
}