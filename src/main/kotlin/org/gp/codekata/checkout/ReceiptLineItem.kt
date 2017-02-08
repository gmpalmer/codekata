package org.gp.codekata.checkout

import org.gp.codekata.checkout.pricing.PricingRule

/**
 * Created by gregpalmer on 1/29/17.
 */
data class ReceiptLineItem(val item : SupermarketItem, val pricingRule : PricingRule) {
    val price = pricingRule.getPrice()
    val description = pricingRule.getDescription(item)


}
