package org.paymentsystem;

import java.util.List;

public class PaymentStateService {
   public static PaymentState determinePaymentState(List<PaymentEvent> events) {
      int requestedAmount = 0;
      int receivedAmount = 0;
      boolean cancelled = false;

      for (PaymentEvent event : events) {
         switch (event.eventType()) {
            case PAYMENT_CREATED -> requestedAmount = event.amount();
            case TRANSFER_RECEIVED -> receivedAmount += event.amount();
            case PAYMENT_CANCELLED -> cancelled = true;
         }
      }

      if (cancelled) {
         return PaymentState.CANCELLED;
      } else if (receivedAmount == requestedAmount) {
         return PaymentState.PAID;
      } else if (receivedAmount < requestedAmount && receivedAmount > 0) {
         return PaymentState.PARTIALLY_PAID;
      } else {
         return PaymentState.NEW;
      }
   }
}
