package dev.pasqua.ordertaking.ep01

// Command — intenção, não fato
data class PlaceOrderCommand(
    val orderId: OrderId,
    val customerInfo: CustomerInfo,
    val shippingAddress: Address,
    val billingAddress: Address,
    val lines: List<OrderLine>
)
