package dev.pasqua.ordertaking.ep02

// ordertaking/infrastructure/ShippingMapper.kt
// ACL: traduz do modelo de Order-Taking para o modelo de Shipping
// A bagunça fica aqui — não nos dois domínios

object OrderTakingToShippingMapper {

    fun toShippableOrder(event: OrderPlaced): ShippableOrder {
        return ShippableOrder(
            orderId = event.orderId,
            shippingAddress = event.shippingAddress,
            shipmentLines = event.shippableItems.map { item ->
                ShippableLine(
                    productCode = item.productCode,
                    quantity = item.quantity
                )
            }
        )
    }
}

// shipping/infrastructure/OrderReceiver.kt
// Shipping recebe o ShippableOrder — já traduzido
// Não sabe nada sobre como Order-Taking funciona internamente

fun interface ShipOrderWorkflow {
    fun execute(order: ShippableOrder)
}

class OrderReceiver(
    private val shipOrderWorkflow: ShipOrderWorkflow
) {
    fun receive(shippableOrder: ShippableOrder) {
        shipOrderWorkflow.execute(shippableOrder)
    }
}
