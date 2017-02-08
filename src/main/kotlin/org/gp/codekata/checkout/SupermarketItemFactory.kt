package org.gp.codekata.checkout

/**
 * Created by gregpalmer on 1/29/17.
 */
class SupermarketItemFactory {
    val INVENTORY = listOf("A", "B", "C", "D") //TODO fix this to be more dynamic?

    fun findSupermarketItem(sku: String) : SupermarketItem {
        validateSku(sku)
        return SupermarketItem(sku, 1)
    }

    private fun validateSku(sku: String) {
        if (!INVENTORY.contains(sku)) {
            throw UnknownSkuException(sku)
        }
    }
}