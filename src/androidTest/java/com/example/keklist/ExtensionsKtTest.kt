package com.example.keklist

import org.junit.Test

import org.junit.Assert.*

class ExtensionsKtTest {

    @Test
    fun doesContain() {
        val test = " test "
        assertEquals(true, test.doesContain("test"))
    }

    @Test
    fun isGoods() {
        val test = "Не товары"
        assertEquals(false, test.isGoods())
    }

    @Test
    fun isServices() {
        val test = "Услуги"
        assertEquals(true, test.isServices())
    }
}