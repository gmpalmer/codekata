package org.gp.codekata.checkout

import org.junit.Assert
import org.junit.Test

class SupermarketItemFactoryTest() {
    val underTest = SupermarketItemFactory()

    fun findSupermarketItemKnown(sku:String) {
        val actual = underTest.findSupermarketItem(sku)
        Assert.assertEquals(sku, actual.sku)
        Assert.assertEquals(1, actual.quantity)
    }

    @Test fun testFindKnownSku() {
        listOf("A", "B", "C", "D").forEach {
            findSupermarketItemKnown(it)
        }
    }

    @Test fun testFindUnknwonSku() {
        try {
            val actual = underTest.findSupermarketItem("0")
        } catch(e:UnknownSkuException) {
            Assert.assertEquals("Unknown SKU: 0", e.message)
        }
    }
}