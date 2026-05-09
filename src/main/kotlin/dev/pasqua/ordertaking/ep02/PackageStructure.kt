package dev.pasqua.ordertaking.ep02

// Bounded contexts viram pacotes — ou módulos, se quiser mais isolamento
//
// com.widgetsinc/
// ├── ordertaking/          ← bounded context Order-Taking
// │   ├── domain/
// │   │   ├── Order.kt      ← Order no contexto de Order-Taking
// │   │   ├── Customer.kt
// │   │   └── Events.kt
// │   ├── application/
// │   │   └── PlaceOrderWorkflow.kt
// │   └── infrastructure/
// │       └── ...
// ├── billing/              ← bounded context Billing
// │   ├── domain/
// │   │   ├── Order.kt      ← Order no contexto de Billing (diferente!)
// │   │   └── Events.kt
// │   ├── application/
// │   │   └── BillOrderWorkflow.kt
// │   └── infrastructure/
// │       └── ...
// └── shipping/             ← bounded context Shipping
//     ├── domain/
//     │   ├── Order.kt      ← Order no contexto de Shipping (diferente ainda!)
//     │   └── Events.kt
//     ├── application/
//     │   └── ShipOrderWorkflow.kt
//     └── infrastructure/
//         └── ...
