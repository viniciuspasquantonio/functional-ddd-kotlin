package dev.pasqua.ordertaking.ep01

// ─────────────────────────────────────────────────────────────────────────────
// Domain Errors — explícitos no tipo, nunca exceções implícitas
// ─────────────────────────────────────────────────────────────────────────────

sealed interface ValidationError {
    data class InvalidOrderId(val value: String) : ValidationError
    data class InvalidProductCode(val code: String) : ValidationError
    data class InvalidQuantity(val value: Int) : ValidationError
    data object EmptyOrderLines : ValidationError
}

sealed interface PricingError {
    data class ProductNotFound(val code: ProductCode) : PricingError
    data class InvalidPrice(val value: Double) : PricingError
}

sealed interface PlaceOrderError {
    data class Validation(val errors: List<ValidationError>) : PlaceOrderError
    data class Pricing(val error: PricingError) : PlaceOrderError
    data class AcknowledgmentFailed(val reason: String) : PlaceOrderError
    data class RemoteServiceError(val cause: Throwable) : PlaceOrderError
}
