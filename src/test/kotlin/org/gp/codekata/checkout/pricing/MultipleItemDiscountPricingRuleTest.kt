package org.gp.codekata.checkout

import org.gp.codekata.checkout.pricing.MultipleSkuDiscountPricingRule
import org.gp.codekata.checkout.pricing.SingleSkuPricingRule
import org.junit.Assert
import org.junit.Test

class MultipleItemDiscountPricingRuleTest() {
    val receipt = ReceiptImpl()
    val underTest = MultipleSkuDiscountPricingRule("A", 3, -25)
    val goodItem = SupermarketItem("A",1)
    val badItem = SupermarketItem("B",1)

    @Test fun testMatchingItemUnderMinQuantity() {
        underTest.apply(goodItem, receipt)

        Assert.assertEquals(0, receipt.size())
        Assert.assertEquals(0, receipt.total())
    }

    @Test fun testMatchingItemEqualMinQuantity() {
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)

        Assert.assertEquals(1, receipt.size())
        Assert.assertEquals(-25, receipt.total())
    }

    @Test fun testMatchingItemOverMinQuantity() {
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)

        Assert.assertEquals(1, receipt.size())
        Assert.assertEquals(-25, receipt.total())
    }

    @Test fun testMatchingItemEqualMinQuantityDouble() {
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)
        underTest.apply(goodItem, receipt)

        Assert.assertEquals(2, receipt.size())
        Assert.assertEquals(-50, receipt.total())
    }

    @Test fun testNonMatchingItem() {
        underTest.apply(badItem, receipt)

        Assert.assertEquals(0, receipt.size())
        Assert.assertEquals(0, receipt.total())
    }
}