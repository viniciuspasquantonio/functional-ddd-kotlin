package dev.pasqua.ordertaking.ep01

// ─────────────────────────────────────────────
// Ep 03 — Pensar em Tipos: do F# para Kotlin
// ─────────────────────────────────────────────

// AND types → data class
data class CustomerInfo(
    val name: PersonalName,
    val emailAddress: EmailAddress,
)

data class PersonalName(
    val firstName: String,
    val lastName: String,
)

// OR types → sealed class
sealed class OrderLine {
    data class Product(val productCode: ProductCode, val quantity: OrderQuantity) : OrderLine()
    data class Comment(val text: String) : OrderLine()
}

// ─────────────────────────────────────────────
// Ep 05 — Invariantes no Type System
// ─────────────────────────────────────────────

@JvmInline
value class EmailAddress private constructor(val value: String) {
    companion object {
        fun create(value: String): EmailAddress? =
            if (value.contains("@")) EmailAddress(value) else null
    }
}

@JvmInline
value class OrderQuantity private constructor(val value: Int) {
    companion object {
        fun create(value: Int): OrderQuantity? =
            if (value in 1..1000) OrderQuantity(value) else null
    }
}

sealed interface ProductCode {
    @JvmInline value class Widget(val value: String) : ProductCode
    @JvmInline value class Gizmo(val value: String) : ProductCode
}
