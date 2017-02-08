package org.gp.codekata.checkout.pricing

import org.gp.codekata.checkout.Receipt
import org.gp.codekata.checkout.SupermarketItem

class PricingRuleSet : PricingRule {

    private val rules = mutableListOf<PricingRule>()

    fun addRule(rule:PricingRule) {
        rules.add(rule)
    }

    override fun apply(item: SupermarketItem, receipt: Receipt) = rules.forEach {
        it.apply(item, receipt)
    }

    override fun getPrice(): Int {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDescription(item: SupermarketItem): String {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}