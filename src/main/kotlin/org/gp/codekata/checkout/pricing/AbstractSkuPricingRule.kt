package org.gp.codekata.checkout.pricing

import org.gp.codekata.checkout.pricing.PricingRule
import org.gp.codekata.checkout.Receipt
import org.gp.codekata.checkout.SupermarketItem

/**
 * Created by gregpalmer on 1/29/17.
 */
abstract class AbstractSkuPricingRule(val sku: String) : PricingRule {

    override fun apply(item: SupermarketItem, receipt: Receipt) {
        if (item.sku == sku) {
            applyHelper(item, receipt)
        }
    }

    abstract fun applyHelper(item: SupermarketItem, receipt: Receipt)
}
