package dev.pasqua.ordertaking.ep02

// ordertaking/domain/Events.kt
// Eventos emitidos pelo contexto Order-Taking
// Esses são os contratos com o mundo externo

sealed interface PlaceOrderEvent

data class OrderPlaced(
    val orderId: OrderId,
    val customerInfo: CustomerInfo,
    val shippingAddress: ValidatedAddress,
    val billingAddress: ValidatedAddress,
    val amountToBill: BillingAmount,
    val shippableItems: List<ShippableLine>
) : PlaceOrderEvent

data class AcknowledgmentSent(
    val orderId: OrderId,
    val emailAddress: String
) : PlaceOrderEvent

data class BillableOrderPlaced(
    val orderId: OrderId,
    val billingAddress: ValidatedAddress,
    val amountToBill: BillingAmount
) : PlaceOrderEvent
