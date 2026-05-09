package dev.pasqua.ordertaking.ep01

// ─────────────────────────────────────────────────────────────────────────────
// Domain Documentation — Order-Taking Workflow
// Texto simples que qualquer domain expert consegue ler e validar.
// ─────────────────────────────────────────────────────────────────────────────

// workflow "Place Order" =
//     input:  OrderForm
//     output:
//         OrderPlaced event
//         OR ValidationError
//
//     steps:
//         1. ValidateOrder
//            if validation fails -> return ValidationError
//
//         2. PriceOrder
//            calculate total price
//
//         3. AcknowledgeOrder
//            send confirmation to customer
//
//         4. CreateEvents
//            return OrderPlaced
//                 + AcknowledgmentSent
//                 + BillableOrderPlaced

// ─────────────────────────────────────────────────────────────────────────────
// Tradução para Kotlin: o contrato expresso no tipo
// ─────────────────────────────────────────────────────────────────────────────

typealias PlaceOrder = suspend (UnvalidatedOrder) -> List<PlaceOrderEvent>
