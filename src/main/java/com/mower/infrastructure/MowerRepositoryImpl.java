package com.mower.infrastructure;

import com.mower.domain.MowerReaderUtils;
import com.mower.domain.MowerRepository;
import com.mower.domain.command.CommandFactory;
import com.mower.domain.model.Coordinates;
import com.mower.domain.model.Lawn;
import com.mower.domain.model.Mower;
import com.mower.domain.model.MowerCommands;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@Slf4j
@Repository
public class MowerRepositoryImpl implements MowerRepository {

    public static final String SEPARATOR = " ";

    @Override
    public List<MowerCommands> readFile(String fileName) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName))))) {
            List<String> lines = reader.lines().toList();
            initLawn(lines);
            return getMowerCommands(lines);

        } catch (RuntimeException e) {
            log.error("Error reading file: {}", fileName, e);
            throw e;
        }
    }

    private void initLawn(List<String> lines) throws IOException {
        String lawnSizeLine = lines.getFirst();
        String[] lawnSize = lawnSizeLine.split(SEPARATOR);
        Coordinates coordinates = MowerReaderUtils.readLawnCoordinates(lawnSize).orElseThrow(() -> new IOException("Error reading file. Lawn size is missing"));
        Lawn.init(coordinates);
        log.info("> Creating Lawn with length: {} and height: {}, ", coordinates.getX(), coordinates.getY());
    }

    private List<MowerCommands> getMowerCommands(List<String> lines) {
        List<MowerCommands> commands = new ArrayList<>();

        for (int i = 1; i < lines.size(); i++) {
            String[] mowerInformation = lines.get(i).split(SEPARATOR);
            String[] movesInformation = lines.get(++i).split("");

            Mower mower = MowerReaderUtils.readMower(mowerInformation).orElse(null);

            if (Objects.nonNull(mower)) {
                log.info("> Creating a new Mower at {}", mower);
                commands.add(MowerCommands.builder()
                        .mower(mower)
                        .commands(Arrays.stream(movesInformation).map(CommandFactory::of).toList())
                        .build());
            }
        }
        return commands;
    }
}
