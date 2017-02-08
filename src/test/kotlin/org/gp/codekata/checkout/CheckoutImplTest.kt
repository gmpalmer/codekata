package org.gp.codekata.checkout

import org.gp.codekata.checkout.pricing.MultipleSkuDiscountPricingRule
import org.gp.codekata.checkout.pricing.PricingRuleSet
import org.gp.codekata.checkout.pricing.SingleSkuPricingRule
import org.junit.Assert
import org.junit.Test

class CheckoutImplTest() {
    var receipt = ReceiptImpl()
    var itemFactory = SupermarketItemFactory()
    var pricingRules = PricingRuleSet()
    var underTest = CheckoutImpl(receipt, itemFactory, pricingRules)

    init {
        pricingRules.addRule(SingleSkuPricingRule("A", 50))
        pricingRules.addRule(SingleSkuPricingRule("B", 30))
        pricingRules.addRule(SingleSkuPricingRule("C", 20))
        pricingRules.addRule(SingleSkuPricingRule("D", 15))

        // A 3 for 130 (20 discount on 3)
        pricingRules.addRule(MultipleSkuDiscountPricingRule("A",3, -20))

        // B 2 for 45 (15 discount on 2)
        pricingRules.addRule(MultipleSkuDiscountPricingRule("A",2, -15))
    }

    fun setup() {
     receipt = ReceiptImpl()
     itemFactory = SupermarketItemFactory()
     pricingRules = PricingRuleSet()
     underTest = CheckoutImpl(receipt, itemFactory, pricingRules)

        pricingRules.addRule(SingleSkuPricingRule("A", 50))
        pricingRules.addRule(SingleSkuPricingRule("B", 30))
        pricingRules.addRule(SingleSkuPricingRule("C", 20))
        pricingRules.addRule(SingleSkuPricingRule("D", 15))

        // A 3 for 130
        pricingRules.addRule(MultipleSkuDiscountPricingRule("A",3, -20))

        // B 2 for 45
        pricingRules.addRule(MultipleSkuDiscountPricingRule("B",2, -15))
    }

    @Test fun checkoutEmptyTotal() {
        Assert.assertEquals(0, underTest.total())
    }

    @Test fun sizeInitial() {
        Assert.assertEquals(0, underTest.size())
    }

    @Test fun sizeAddFourItems() {
        underTest.scan("A")
        underTest.scan("B")
        underTest.scan("C")
        underTest.scan("D")

        Assert.assertEquals(4, underTest.size())
    }

    @Test fun scanA() {
        underTest.scan("A")
        Assert.assertEquals(50, underTest.total())
    }

    @Test fun scanB() {
        underTest.scan("B")
        Assert.assertEquals(30, underTest.total())
    }

    @Test fun scanC() {
        underTest.scan("C")
        Assert.assertEquals(20, underTest.total())
    }

    @Test fun scanD() {
        underTest.scan("D")
        Assert.assertEquals(15, underTest.total())
    }

    fun price(goods:String) : Int {
        setup()

        goods.toCharArray().forEach {
            underTest.scan(it.toString())
        }

        return receipt.total()
    }

    @Test fun testTotals() {
        Assert.assertEquals(0, price(""))
        Assert.assertEquals(50, price("A"))
        Assert.assertEquals(80, price("AB"))
        Assert.assertEquals(115, price("CDBA"))

        Assert.assertEquals(100, price("AA"))
        Assert.assertEquals(130, price("AAA"))
        Assert.assertEquals(180, price("AAAA"))
        Assert.assertEquals(230, price("AAAAA"))
        Assert.assertEquals(260, price("AAAAAA"))

        Assert.assertEquals(160, price("AAAB"))
        Assert.assertEquals(175, price("AAABB"))
        Assert.assertEquals(190, price("AAABBD"))
        Assert.assertEquals(190, price("DABABA"))
    }
}