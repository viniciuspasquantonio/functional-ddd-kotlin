package dev.pasqua.ordertaking.ep01

import arrow.core.Either
import arrow.core.raise.either

// ✅ Domain-Driven, Functional
// Tipos expressam o domínio

sealed interface OrderStatus
object Pending : OrderStatus
data class Validated(val validatedLines: List<ValidatedOrderLine>) : OrderStatus
data class Priced(val amount: BillingAmount) : OrderStatus
data class Confirmed(val events: List<PlaceOrderEvent>) : OrderStatus

// Cada step é uma função pura com tipo claro
fun interface ValidateOrder {
    operator fun invoke(order: UnvalidatedOrder): Either<PlaceOrderError, ValidatedOrder>
}
fun interface PriceOrder {
    operator fun invoke(order: ValidatedOrder): Either<PlaceOrderError, PricedOrder>
}
fun interface AcknowledgeOrder {
    operator fun invoke(order: PricedOrder): AcknowledgedOrder
}

// O workflow compõe os steps
fun placeOrder(
    validate: ValidateOrder,
    price: PriceOrder,
    acknowledge: AcknowledgeOrder
): (PlaceOrderCommand) -> Either<PlaceOrderError, List<PlaceOrderEvent>> = { cmd ->
    either {
        val validated = validate(cmd.toUnvalidatedOrder()).bind()
        val priced = price(validated).bind()
        val acknowledged = acknowledge(priced)
        acknowledged.toEvents()
    }
}
