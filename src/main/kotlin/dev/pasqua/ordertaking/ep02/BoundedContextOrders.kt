package dev.pasqua.ordertaking.ep02

// ─────────────────────────────────────────────────────────────────────────────
// ordertaking/domain/Order.kt
// Order no contexto de Order-Taking: entrada crua do sistema
// ─────────────────────────────────────────────────────────────────────────────

data class UnvalidatedOrder(
    val orderId: String,                       // ainda não é um OrderId tipado
    val customerInfo: UnvalidatedCustomerInfo,
    val shippingAddress: UnvalidatedAddress,
    val billingAddress: UnvalidatedAddress,
    val lines: List<UnvalidatedOrderLine>
)

data class ValidatedOrder(
    val orderId: OrderId,                      // agora é tipado
    val customerInfo: CustomerInfo,
    val shippingAddress: ValidatedAddress,
    val billingAddress: ValidatedAddress,
    val lines: List<ValidatedOrderLine>
)

// ─────────────────────────────────────────────────────────────────────────────
// billing/domain/Order.kt
// Order no contexto de Billing: documento financeiro
// ─────────────────────────────────────────────────────────────────────────────

data class BillableOrder(
    val orderId: OrderId,
    val billingAddress: ValidatedAddress,
    val amountToBill: BillingAmount   // o que importa pro Billing
    // endereço de entrega? não existe aqui. itens físicos? não existe aqui.
)

// ─────────────────────────────────────────────────────────────────────────────
// shipping/domain/Order.kt
// Order no contexto de Shipping: lista de itens para entregar
// ─────────────────────────────────────────────────────────────────────────────

data class ShippableOrder(
    val orderId: OrderId,
    val shippingAddress: ValidatedAddress,
    val shipmentLines: List<ShippableLine>  // somente itens físicos
    // preço? não existe aqui. dados de pagamento? não existe aqui.
)

// ─────────────────────────────────────────────────────────────────────────────
// Tipos de suporte
// ─────────────────────────────────────────────────────────────────────────────

@JvmInline value class OrderId(val value: String)
@JvmInline value class BillingAmount(val value: Double)
@JvmInline value class ProductCode(val value: String)
@JvmInline value class OrderQuantity(val value: Int)

data class UnvalidatedCustomerInfo(val name: String, val email: String)
data class CustomerInfo(val name: String, val email: String)
data class UnvalidatedAddress(val street: String, val city: String, val zipCode: String)
data class ValidatedAddress(val street: String, val city: String, val zipCode: String)
data class UnvalidatedOrderLine(val productCode: String, val quantity: Int)
data class ValidatedOrderLine(val productCode: ProductCode, val quantity: OrderQuantity)
data class ShippableLine(val productCode: ProductCode, val quantity: OrderQuantity)
