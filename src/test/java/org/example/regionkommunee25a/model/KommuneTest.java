package org.example.regionkommunee25a.model;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KommuneTest {

    @Test
    void test() {
        if (3>4)
        fail("Det går slet ikke");
    }

    @Test
    void test2() {
        assertEquals("ABC", "ABC");
    }

    @Test
    @DisplayName("Test division med 0")
    @Disabled
    void test3() {
        int i = 10;
        int y = 0;
        assertEquals(0, i/y);
    }

    @Test
    @DisplayName("Test sort metode")
    void testArr() {
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers, () -> "Array ikke sortereret korrekt" + Arrays.toString(numbers));
    }

}