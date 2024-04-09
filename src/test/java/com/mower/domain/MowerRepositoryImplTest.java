package com.mower.domain;

import com.mower.domain.model.Coordinates;
import com.mower.domain.model.ForwardCommand;
import com.mower.domain.model.LeftCommand;
import com.mower.domain.model.Mower;
import com.mower.domain.model.MowerCommands;
import com.mower.domain.model.RightCommand;
import com.mower.infrastructure.MowerRepositoryImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class MowerRepositoryImplTest {

    @InjectMocks
    MowerRepositoryImpl mowerRepository;


    @Test
    @DisplayName("File reader should not treat a file without lawn size")
    void fileReaderShouldFailWhenLawnSizeMissing() {
        String fileName = "unknownFile.txt";

        Assertions.assertThatThrownBy(() -> mowerRepository.readFile(fileName))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("File reader should read file and return mowers and their commands")
    void fileReaderShouldReadFileAndReturnMowersWithTheirCommands() throws IOException {
        String fileName = "mowers-sample.txt";
        List<MowerCommands> expectedMowers = List.of(
                MowerCommands.builder()
                        .mower(Mower.builder()
                                .orientation(Orientation.N)
                                .coordinates(Coordinates.builder().x(1).y(2).build())
                                .build())
                        .commands(List.of(new LeftCommand(), new ForwardCommand(), new LeftCommand(), new ForwardCommand(), new LeftCommand(), new ForwardCommand(), new LeftCommand(), new ForwardCommand(), new ForwardCommand())).build(),
                MowerCommands.builder()
                        .mower(Mower.builder()
                                .orientation(Orientation.E)
                                .coordinates(Coordinates.builder().x(3).y(3).build())
                                .build())
                        .commands(List.of(new ForwardCommand(), new ForwardCommand(), new RightCommand(), new ForwardCommand(), new ForwardCommand(), new RightCommand(), new ForwardCommand(), new RightCommand(), new RightCommand(), new ForwardCommand())).build());

        Assertions.assertThat(mowerRepository.readFile(fileName)).usingRecursiveComparison().isEqualTo(expectedMowers);
    }


}