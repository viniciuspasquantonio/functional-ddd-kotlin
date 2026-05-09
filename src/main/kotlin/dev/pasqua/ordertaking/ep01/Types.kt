package dev.pasqua.ordertaking.ep01

// ─────────────────────────────────────────────────────────────────────────────
// Value Types
// ─────────────────────────────────────────────────────────────────────────────

@JvmInline
value class OrderId private constructor(val value: String) {
    companion object {
        fun create(value: String): OrderId? =
            if (value.isNotBlank()) OrderId(value) else null
        fun unsafeCreate(value: String) = OrderId(value)
    }
}

@JvmInline
value class BillingAmount private constructor(val value: Double) {
    companion object {
        fun create(value: Double): BillingAmount? =
            if (value >= 0) BillingAmount(value) else null
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// Address
// ─────────────────────────────────────────────────────────────────────────────

data class Address(
    val street: String,
    val city: String,
    val state: String,
    val zipCode: String,
    val country: String,
)

// ─────────────────────────────────────────────────────────────────────────────
// Order Lines
// ─────────────────────────────────────────────────────────────────────────────

data class ShippableOrderLine(
    val productCode: ProductCode,
    val quantity: OrderQuantity,
)

data class ValidatedOrderLine(
    val productCode: ProductCode,
    val quantity: OrderQuantity,
)

data class PricedOrderLine(
    val productCode: ProductCode,
    val quantity: OrderQuantity,
    val price: Double,
)

// ─────────────────────────────────────────────────────────────────────────────
// Order States (state machine — cada estado só tem o que faz sentido nele)
// ─────────────────────────────────────────────────────────────────────────────

data class UnvalidatedOrder(
    val orderId: String,
    val customerInfo: CustomerInfo,
    val shippingAddress: Address,
    val billingAddress: Address,
    val lines: List<OrderLine>,
)

data class ValidatedOrder(
    val orderId: OrderId,
    val customerInfo: CustomerInfo,
    val shippingAddress: Address,
    val billingAddress: Address,
    val lines: List<ValidatedOrderLine>,
)

data class PricedOrder(
    val orderId: OrderId,
    val customerInfo: CustomerInfo,
    val shippingAddress: Address,
    val billingAddress: Address,
    val lines: List<PricedOrderLine>,
    val amountToBill: BillingAmount,
)

data class AcknowledgedOrder(
    val pricedOrder: PricedOrder,
    val acknowledgmentSent: AcknowledgmentSent,
) {
    fun toEvents(): List<PlaceOrderEvent> = listOf(
        OrderPlaced(
            orderId = pricedOrder.orderId,
            customerInfo = pricedOrder.customerInfo,
            shippingAddress = pricedOrder.shippingAddress,
            billingAddress = pricedOrder.billingAddress,
            amountToBill = pricedOrder.amountToBill,
            shippableItems = pricedOrder.lines.map {
                ShippableOrderLine(it.productCode, it.quantity)
            }
        ),
        acknowledgmentSent,
        BillableOrderPlaced(
            orderId = pricedOrder.orderId,
            billingAddress = pricedOrder.billingAddress,
            amountToBill = pricedOrder.amountToBill,
        )
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// Extensions
// ─────────────────────────────────────────────────────────────────────────────

fun PlaceOrderCommand.toUnvalidatedOrder() = UnvalidatedOrder(
    orderId = orderId.value,
    customerInfo = customerInfo,
    shippingAddress = shippingAddress,
    billingAddress = billingAddress,
    lines = lines,
)
