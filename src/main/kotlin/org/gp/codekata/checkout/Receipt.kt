package org.gp.codekata.checkout

/**
 * Created by gregpalmer on 1/29/17.
 */
interface Receipt {
    fun addLineItem(receiptLineItem: ReceiptLineItem)
    fun total() : Int
    fun size() : Int
    //removeLineItem
    //print
 }