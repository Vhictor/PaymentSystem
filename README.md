# Payment System

The Payment System is a Java program that manages payments represented as a series of events. Each payment event can be one of three kinds:

- **Payment Created**: Represents the creation of a payment with an expected amount.
- **Transfer Received**: Indicates an incoming transfer to cover the payment. This event can occur multiple times.
- **Payment Cancelled**: Indicates the cancellation of the payment.

The system provides a function, `determinePaymentState`, which accepts a list of payment events (sorted in chronological order) and determines the overall state of the payment based on the events:

- If the payment has been requested and no other events occurred, the state is `NEW`.
- If transfers received cover the full requested amount, the state is `PAID`.
- If transfers received cover less than the requested amount, the state is `PARTIALLY_PAID`.
- If a payment cancellation event is present, the state is `CANCELLED`.

## Assumptions

- Each payment event has an associated amount.
- Events are sorted in chronological order.
- A payment can only be cancelled once.
- The system doesn't handle fractional amounts or currency conversions. It assumes that the amounts provided in the payment events are integers representing the same currency.
- The system doesn't consider events other than payment created, transfer received, and payment cancelled.

## Usage

To use the Payment System, follow these steps:

1. Create instances of `PaymentEvent` representing the payment events.
2. Add these events to a list and ensure they are sorted in chronological order.
3. Call the `determinePaymentState` method of the `PaymentSystem` class, passing the list of events as an argument.
4. The method will return the state of the payment based on the events.
