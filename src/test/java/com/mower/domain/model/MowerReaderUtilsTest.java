package com.mower.domain.model;

import com.mower.domain.MowerReaderUtils;
import com.mower.domain.Orientation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;


class MowerReaderUtilsTest {

    @Test
    @DisplayName("should return empty when lawn coordinates is invalid")
    void shouldReturnEmptyWhenLawnCoordinatesIsInvalid() {
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"5", "5", "E"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"A", "A"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"1", "2", "3"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"0", "0"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"-5", "5"})).isEmpty();
    }

    @Test
    @DisplayName("should return coordinates when lawn coordinates is valid")
    void shouldReturnCoordinatesWhenLawnCoordinatesIsValid() {
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"5", "5"})).usingRecursiveComparison().isEqualTo(Optional.of(Coordinates.builder().x(5).y(5).build()));
        Assertions.assertThat(MowerReaderUtils.readLawnCoordinates(new String[]{"1", "1"})).usingRecursiveComparison().isEqualTo(Optional.of(Coordinates.builder().x(1).y(1).build()));
    }

    @Test
    @DisplayName("should return empty when mower position is invalid")
    void shouldReturnNullWhenMowerPositionIsInvalid() {
        Assertions.assertThat(MowerReaderUtils.readMowerCoordinates(new String[]{"5", "5"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMowerCoordinates(new String[]{"A", "A"})).isEmpty();
    }

    @Test
    @DisplayName("should return coordinates when mower position is valid")
    void shouldReturnCoordinatesWhenMowerPositionIsValid() {
        Assertions.assertThat(MowerReaderUtils.readMowerCoordinates(new String[]{"1", "2", "N"})).usingRecursiveComparison().isEqualTo(Optional.of(Coordinates.builder().x(1).y(2).build()));
        Assertions.assertThat(MowerReaderUtils.readMowerCoordinates(new String[]{"0", "0", "N"})).usingRecursiveComparison().isEqualTo(Optional.of(Coordinates.builder().x(0).y(0).build()));
    }

    @Test
    @DisplayName("should return orientation when mower orientation is valid")
    void shouldReturnOrientationWhenMowerOrientationIsValid() {
        String[] elements = {"1", "2", "N"};

        Assertions.assertThat(MowerReaderUtils.readMowerOrientation(elements)).usingRecursiveComparison().isEqualTo(Optional.of(Orientation.N));
    }

    @Test
    @DisplayName("should return empty orientation when mower orientation is invalid")
    void shouldReturnEmptyOrientationWhenMowerOrientationIsInvalid() {
        Assertions.assertThat(MowerReaderUtils.readMowerOrientation(new String[]{"N"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMowerOrientation(new String[]{"5", "5"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMowerOrientation(new String[]{"1", "2", "3"})).isEmpty();
    }

    @Test
    @DisplayName("should return mower when coordinates and orientation are valid")
    void shouldReturnMowerWhenCoordinatesAndOrientationAreValid() {
        Mower expectedMower = Mower.builder()
                .coordinates(Coordinates.builder().x(3).y(1).build())
                .orientation(Orientation.E)
                .build();

        Assertions.assertThat(MowerReaderUtils.readMower(new String[]{"3", "1", "E"})).usingRecursiveComparison().isEqualTo(Optional.of(expectedMower));
    }

    @Test
    @DisplayName("should return empty mower when coordinates or orientation is invalid")
    void shouldReturnEmptyMowerWhenMowerCoordinatesOrOrientationIsInvalid() {
        Assertions.assertThat(MowerReaderUtils.readMower(new String[]{"0", "0"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMower(new String[]{"0", "0", "0"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMower(new String[]{"A", "B", "3"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMower(new String[]{"A", "B", "C"})).isEmpty();
        Assertions.assertThat(MowerReaderUtils.readMower(new String[]{"1", "2", "3"})).isEmpty();
    }

}