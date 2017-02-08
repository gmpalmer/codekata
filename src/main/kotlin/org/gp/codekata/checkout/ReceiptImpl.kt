package org.gp.codekata.checkout

import org.gp.codekata.checkout.pricing.PricingRule

class ReceiptImpl : Receipt {
    private val items = mutableListOf<ReceiptLineItem>()

    override fun addLineItem(receiptLineItem: ReceiptLineItem) {
        items.add(receiptLineItem)
    }

    override fun total(): Int {
        return items.sumBy {it.price}
    }

    override fun size(): Int {
        return items.size
    }
}