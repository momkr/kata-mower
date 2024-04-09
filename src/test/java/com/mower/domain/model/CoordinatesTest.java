package com.mower.domain.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;


@ExtendWith(MockitoExtension.class)
class CoordinatesTest {

    @ParameterizedTest
    @DisplayName("increment X should update value correctly")
    @MethodSource("incrementXParameters")
    void incrementXShouldUpdateValueCorrectly(Coordinates startCoordinates, Coordinates endCoordinates) {
        Assertions.assertThat(startCoordinates.incrementX()).usingRecursiveComparison().isEqualTo(endCoordinates);
    }

    @ParameterizedTest
    @DisplayName("increment Y should update value correctly")
    @MethodSource("incrementYParameters")
    void incrementYShouldUpdateValueCorrectly(Coordinates startCoordinates, Coordinates endCoordinates) {
        Assertions.assertThat(startCoordinates.incrementY()).usingRecursiveComparison().isEqualTo(endCoordinates);
    }

    @ParameterizedTest
    @DisplayName("decrement X should update value correctly")
    @MethodSource("decrementXParameters")
    void decrementXShouldUpdateValueCorrectly(Coordinates startCoordinates, Coordinates endCoordinates) {
        Assertions.assertThat(startCoordinates.decrementX()).usingRecursiveComparison().isEqualTo(endCoordinates);
    }

    @ParameterizedTest
    @DisplayName("decrement Y should update value correctly")
    @MethodSource("decrementYParameters")
    void decrementYShouldUpdateValueCorrectly(Coordinates startCoordinates, Coordinates endCoordinates) {
        Assertions.assertThat(startCoordinates.decrementY()).usingRecursiveComparison().isEqualTo(endCoordinates);
    }

    public static Stream<Arguments> incrementXParameters() {

        return Stream.of(
                Arguments.of(
                        Coordinates.builder().x(0).y(0).build(), Coordinates.builder().x(1).y(0).build(),
                        Coordinates.builder().x(1).y(1).build(), Coordinates.builder().x(2).y(1).build()
                )
        );
    }

    public static Stream<Arguments> decrementXParameters() {

        return Stream.of(
                Arguments.of(
                        Coordinates.builder().x(1).y(2).build(), Coordinates.builder().x(0).y(2).build(),
                        Coordinates.builder().x(1).y(1).build(), Coordinates.builder().x(0).y(1).build()
                )
        );
    }

    public static Stream<Arguments> incrementYParameters() {

        return Stream.of(
                Arguments.of(
                        Coordinates.builder().x(0).y(0).build(), Coordinates.builder().x(0).y(1).build(),
                        Coordinates.builder().x(1).y(1).build(), Coordinates.builder().x(1).y(2).build()
                )
        );
    }

    public static Stream<Arguments> decrementYParameters() {

        return Stream.of(
                Arguments.of(
                        Coordinates.builder().x(1).y(2).build(), Coordinates.builder().x(1).y(1).build(),
                        Coordinates.builder().x(1).y(1).build(), Coordinates.builder().x(1).y(0).build()
                )
        );
    }

}