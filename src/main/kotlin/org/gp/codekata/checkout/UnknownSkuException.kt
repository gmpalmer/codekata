package org.gp.codekata.checkout

/**
 * Created by gregpalmer on 1/29/17.
 */
class UnknownSkuException : RuntimeException {
    constructor(unknownSku: String) : super("Unknown SKU: " + unknownSku)
}