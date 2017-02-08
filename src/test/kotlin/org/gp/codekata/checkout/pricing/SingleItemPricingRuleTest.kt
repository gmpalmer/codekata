package org.gp.codekata.checkout

import org.gp.codekata.checkout.pricing.SingleSkuPricingRule
import org.junit.Assert
import org.junit.Test

class SingleItemPricingRuleTest() {
    val receipt = ReceiptImpl()
    val underTest = SingleSkuPricingRule("A", 50)
    val goodItem = SupermarketItem("A",1)
    val badItem = SupermarketItem("B",1)

    @Test fun testMatchingItem() {
        underTest.apply(goodItem, receipt)

        Assert.assertEquals(1, receipt.size())
        Assert.assertEquals(50, receipt.total())
    }

    @Test fun testMatchingItemAddMultiple() {
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)

        Assert.assertEquals(3, receipt.size())
        Assert.assertEquals(150, receipt.total())
    }

    @Test fun testNonMatchingItem() {
        underTest.apply(badItem, receipt)

        Assert.assertEquals(0, receipt.size())
        Assert.assertEquals(0, receipt.total())
    }
}