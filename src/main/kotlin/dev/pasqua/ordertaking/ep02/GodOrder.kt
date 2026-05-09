package dev.pasqua.ordertaking.ep02

import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDate

// ❌ Anti-padrão: God Order que serve todos os contextos
data class Order(
    val orderId: String,
    val customerId: Long,
    val customerEmail: String,          // só Order-Taking precisa disso
    val shippingAddress: Address?,      // null em pedidos digitais
    val billingAddress: Address?,       // null se mesmo que shipping
    val paymentMethod: String?,         // null em pedidos internos
    val lines: List<OrderLine>,
    val pricedLines: List<PricedLine>?, // null antes do pricing
    val totalPrice: BigDecimal?,        // null antes do pricing
    val status: String,                 // "PENDING"? "PRICED"? "SHIPPED"? quem define?
    val shippingDate: LocalDate?,       // null em itens digitais
    val trackingNumber: String?,        // null até ser enviado
    val invoiceNumber: String?,         // null até faturar
    val billedAt: Instant?,             // null até faturar
    // ... mais 25 campos, cada um null "dependendo do contexto"
)

data class Address(val street: String, val city: String, val zipCode: String)
data class OrderLine(val productCode: String, val quantity: Int)
data class PricedLine(val productCode: String, val quantity: Int, val price: BigDecimal)
