package com.example.coverage

import org.junit.Test
import org.junit.Assert.assertEquals

class SimpleKotlinTest {
    @Test
    fun testAdd() {
        val lib = SimpleKotlinLib()
        assertEquals(3, lib.add(1, 2))
    }
}
