package dev.pasqua.ordertaking.ep01

import java.math.BigDecimal
import java.sql.Timestamp

// ❌ Database-Driven Design
data class OrderEntity(
    val id: Long,
    val customerId: Long,
    val status: String,       // "pending", "confirmed", "shipped"? quem sabe
    val total: BigDecimal?,   // nullable por quê?
    val createdAt: Timestamp,
    val updatedAt: Timestamp,
    val shippingAddressId: Long?,
    val billingAddressId: Long?,
    val promoCode: String?,
    val notes: String?,
    // ... mais 30 colunas que só o banco entende
)

// ─────────────────────────────────────────────────────────────────────────────

// ❌ Class-Driven Design
class OrderService(
    private val orderRepository: OrderRepository,
    private val customerRepository: CustomerRepository,
    private val inventoryService: InventoryService,
    private val emailService: EmailService,
    private val paymentService: PaymentService
) {
    fun validateOrder(order: Order): Boolean { TODO() }
    fun priceOrder(order: Order): Order { TODO() }
    fun processOrder(order: Order): Order { TODO() }
    fun acknowledgeOrder(order: Order): Unit { TODO() }
    fun submitOrder(order: Order): Unit { TODO() }
    fun cancelOrder(orderId: Long): Unit { TODO() }
    // ... mais 15 métodos que compartilham estado interno
}

// stubs para compilar — não são o domínio
class Order
interface OrderRepository
interface CustomerRepository
interface InventoryService
interface EmailService
interface PaymentService
