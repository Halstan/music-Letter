package com.musicletter.app

import org.assertj.core.api.AssertionsForClassTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

class MusicPlayerApplicationTests {

    val calculator = Calculator()

    @Test
    fun itShoulAddNumbers() {
        // GIVEN
        val numberOne = 20
        val numberTwo = 30

        // WHEN
        val result = calculator.add(numberOne, numberTwo)

        // THEN
        val expected =  50
        assertThat(result).isEqualTo(expected)
    }

    class Calculator {
        fun add(a: Int, b: Int): Int = a + b
    }

}
