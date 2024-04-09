package com.mower.domain.model;

import com.mower.domain.Orientation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OrientationTest {

    @Test
    @DisplayName("turn left when orientated to North should return West")
    void turnLeftWhenOrientatedToNorthShouldReturnWest() {
        Assertions.assertThat(Orientation.N.turnLeft()).isEqualTo(Orientation.W);
    }

    @Test
    @DisplayName("turn right when orientated to North should return East")
    void turnRightWhenOrientatedToNorthShouldReturnEast() {
        Assertions.assertThat(Orientation.N.turnRight()).isEqualTo(Orientation.E);
    }

    @Test
    @DisplayName("turn left when orientated to South should return East")
    void turnLeftWhenOrientatedToSouthShouldReturnEast() {
        Assertions.assertThat(Orientation.S.turnLeft()).isEqualTo(Orientation.E);
    }

    @Test
    @DisplayName("turn right when orientated to South should return West")
    void turnRightWhenOrientatedToSouthShouldReturnWest() {
        Assertions.assertThat(Orientation.S.turnRight()).isEqualTo(Orientation.W);
    }

    @Test
    @DisplayName("turn left when orientated to East should return East")
    void turnLeftWhenOrientatedToEastShouldReturnNorth() {
        Assertions.assertThat(Orientation.E.turnLeft()).isEqualTo(Orientation.N);
    }

    @Test
    @DisplayName("turn right when orientated to East should return South")
    void turnRightWhenOrientatedToEastShouldReturnSouth() {
        Assertions.assertThat(Orientation.E.turnRight()).isEqualTo(Orientation.S);
    }

    @Test
    @DisplayName("turn left when orientated to West should return South")
    void turnLeftWhenOrientatedToWestShouldReturnSouth() {
        Assertions.assertThat(Orientation.W.turnLeft()).isEqualTo(Orientation.S);
    }

    @Test
    @DisplayName("turn right when orientated to West should return North")
    void turnRightWhenOrientatedToWestShouldReturnNorth() {
        Assertions.assertThat(Orientation.W.turnRight()).isEqualTo(Orientation.N);
    }
}