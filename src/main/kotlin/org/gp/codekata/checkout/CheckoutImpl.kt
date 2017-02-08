package org.gp.codekata.checkout

import org.gp.codekata.checkout.pricing.PricingRule

class CheckoutImpl(val receipt : Receipt, val itemFactory : SupermarketItemFactory, val pricingRule: PricingRule) : Checkout {

    override fun scan(itemSku: String) {
        val superMarketItem = itemFactory.findSupermarketItem(itemSku)

        pricingRule.apply(superMarketItem, receipt)
    }

    override fun total(): Int {
        return receipt.total()
    }

    override fun size(): Int {
        return receipt.size()
    }
}