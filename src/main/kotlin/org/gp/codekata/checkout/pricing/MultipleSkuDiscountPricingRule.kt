package org.gp.codekata.checkout.pricing

import org.gp.codekata.checkout.pricing.PricingRule
import org.gp.codekata.checkout.Receipt
import org.gp.codekata.checkout.ReceiptLineItem
import org.gp.codekata.checkout.SupermarketItem

class MultipleSkuDiscountPricingRule : AbstractSkuPricingRule {
    val discount : Int
    val quantityRequired : Int
    var count : Int = 0

    constructor(sku: String, quantityRequired: Int, discount: Int) : super(sku) {
        this.discount = discount
        this.quantityRequired = quantityRequired
    }

    override fun applyHelper(item: SupermarketItem, receipt: Receipt) {
        count += 1

        if (shouldApplyDiscount()) {
            applyDiscount(item, receipt)
        }
    }
    private fun shouldApplyDiscount() = count % quantityRequired == 0

    private fun applyDiscount(item: SupermarketItem, receipt: Receipt) {
        val lineItem = ReceiptLineItem(item, this)
        receipt.addLineItem(lineItem)
    }

    override fun getDescription(item: SupermarketItem): String {
        return "Multi SKU Discount: $item.sku"
    }

    override fun getPrice(): Int {
        return discount
    }

}
