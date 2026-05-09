package dev.pasqua.ordertaking.ep02

// ordertaking/domain/Order.kt
// internal = só visível dentro do pacote ordertaking

internal data class PricingCalculation(
    val basePrice: Double,
    val discount: Double?,
    val finalPrice: Double
)

// ordertaking/domain/Events.kt
// sem internal = contrato público com outros contextos

// data class OrderPlaced(...)  ← outros contextos podem ver isso
