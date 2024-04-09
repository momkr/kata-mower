package com.mower.domain;

import com.mower.domain.model.Coordinates;
import com.mower.domain.model.Mower;
import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@UtilityClass
public class MowerReaderUtils {

    public static Optional<Coordinates> readLawnCoordinates(String[] elements) {
        if (elements.length == 2) {

            Integer x = getPosition(elements[0]);
            Integer y = getPosition(elements[1]);

            if (x != null && y != null && x > 0 && y > 0) {
                return Optional.of(Coordinates.builder()
                        .x(x)
                        .y(y)
                        .build());
            }
        }
        return Optional.empty();
    }

    public static Optional<Coordinates> readMowerCoordinates(String[] elements) {
        if (elements.length == 3) {
            Integer x = getPosition(elements[0]);
            Integer y = getPosition(elements[1]);

            if (x != null && y != null && x >= 0 && y >= 0) {
                return Optional.of(Coordinates.builder()
                        .x(x)
                        .y(y)
                        .build());
            }
        }
        return Optional.empty();
    }

    public static Optional<Orientation> readMowerOrientation(String[] elements) {
        if (elements.length == 3) {
            List<String> validOrientations = Arrays.stream(Orientation.values()).map(Objects::toString).toList();
            if (validOrientations.contains(elements[2])) {
                return Optional.of(Orientation.valueOf(elements[2]));
            }
        }
        return Optional.empty();
    }

    public static Optional<Mower> readMower(String[] elements) {
        if (elements.length == 3) {
            Optional<Coordinates> optionalCoordinates = readMowerCoordinates(elements);
            Optional<Orientation> optionalOrientation = readMowerOrientation(elements);

            if (optionalCoordinates.isPresent() && optionalOrientation.isPresent()) {
                return Optional.of(Mower.builder()
                        .coordinates(optionalCoordinates.orElse(null))
                        .orientation(optionalOrientation.orElse(null))
                        .build());
            }
        }
        return Optional.empty();
    }

    public static String getDigit(String element) {
        return (element.matches("\\d+")) ? element : null;
    }

    public static Integer getPosition(String element) {
        return Optional.ofNullable(getDigit(element)).map(Integer::parseInt).orElse(null);
    }

}
