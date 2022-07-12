package com.example.cognizantapp

import junit.framework.TestCase
import org.junit.Before
import org.junit.BeforeClass

class CalculatorTest : TestCase() {
    companion object {
        lateinit var calculator : Calculator
        @BeforeClass
        fun setUp() {
            calculator = Calculator()
        }
    }





    fun testAdd() {
        val expected = 20;
        assertEquals(expected, calculator.add(10, 10))
    }

    fun testMultiply() {
        val expected = 100;
        var calculator = Calculator()
        assertEquals(expected, calculator.multiply(10, 10))
    }
}