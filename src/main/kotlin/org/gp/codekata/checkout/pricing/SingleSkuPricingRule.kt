package org.gp.codekata.checkout.pricing

import org.gp.codekata.checkout.pricing.PricingRule
import org.gp.codekata.checkout.Receipt
import org.gp.codekata.checkout.ReceiptLineItem
import org.gp.codekata.checkout.SupermarketItem

/**
 * Created by gregpalmer on 1/29/17.
 */
class SingleSkuPricingRule : AbstractSkuPricingRule {

    private val price : Int

    constructor(sku: String, price: Int) : super(sku) {
        this.price = price
    }

    override fun getDescription(item: SupermarketItem): String {
        return "SKU: $item.sku"
    }

    override fun getPrice(): Int {
        return price
    }

    override fun applyHelper(item: SupermarketItem, receipt: Receipt) {
        val lineItem = ReceiptLineItem(item, this)
        receipt.addLineItem(lineItem)
    }

}
