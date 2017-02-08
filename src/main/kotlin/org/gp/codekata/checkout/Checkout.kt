package org.gp.codekata.checkout

import java.math.BigDecimal

interface Checkout {
    fun scan(itemSku: String)
    fun total(): Int
    fun size(): Int

    //removeItem
    //applyCoupon
    //applyTax
    //payment
}