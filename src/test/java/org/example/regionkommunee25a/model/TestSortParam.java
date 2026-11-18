package org.example.regionkommunee25a.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestSortParam {

    @DisplayName("paramtest med 3 arrays")
    @ParameterizedTest
    @MethodSource("sortnumbersArgument")
    void testArrParm(int[] numbers, int[] expected) {
        Arrays.sort(numbers);
        assertArrayEquals(expected, numbers, () -> "der er fejl " + Arrays.toString(numbers) + " i array");
    }

    private static Stream<Arguments> sortnumbersArgument() {
        return Stream.of(
                Arguments.of(new int[]{12,3,14,1}, new int[]{1,3,14,12}),
                Arguments.of(new int[]{1,2,3,4,5,6,7,8,9,10}, new int[]{1,2,3,4,5,6,7,8,9,10}),
                Arguments.of( new int[]{9,8,-4}, new int[]{-4,8,9})
        );
    }



}
