package org.gp.codekata.checkout.pricing

import org.gp.codekata.checkout.Receipt
import org.gp.codekata.checkout.SupermarketItem

/**
 * Created by gregpalmer on 1/29/17.
 */
interface PricingRule {
    fun apply(item: SupermarketItem, receipt: Receipt)
    fun getPrice() : Int
    fun  getDescription(item: SupermarketItem): String
}