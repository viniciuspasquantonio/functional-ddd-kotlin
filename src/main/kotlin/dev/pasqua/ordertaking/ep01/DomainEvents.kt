package dev.pasqua.ordertaking.ep01

// Domain Events — sempre no passado, sempre imutáveis
sealed interface PlaceOrderEvent

data class OrderPlaced(
    val orderId: OrderId,
    val customerInfo: CustomerInfo,
    val shippingAddress: Address,
    val billingAddress: Address,
    val amountToBill: BillingAmount,
    val shippableItems: List<ShippableOrderLine>
) : PlaceOrderEvent

data class AcknowledgmentSent(
    val orderId: OrderId,
    val emailAddress: EmailAddress
) : PlaceOrderEvent

data class BillableOrderPlaced(
    val orderId: OrderId,
    val billingAddress: Address,
    val amountToBill: BillingAmount
) : PlaceOrderEvent
