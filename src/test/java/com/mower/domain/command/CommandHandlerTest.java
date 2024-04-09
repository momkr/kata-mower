package com.mower.domain.command;

import com.mower.domain.Orientation;
import com.mower.domain.model.Coordinates;
import com.mower.domain.model.LeftCommand;
import com.mower.domain.model.Mower;
import com.mower.domain.model.MowerCommands;
import com.mower.domain.model.RightCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


@ExtendWith(MockitoExtension.class)
class CommandHandlerTest {

    @InjectMocks
    CommandHandler commandHandler;


    @Test
    @DisplayName("should call forward command when command is a forward type")
    void shouldCallForwardCommandWhenCommandIsAForwardType() {

        LeftCommand leftCommand = Mockito.mock(LeftCommand.class);
        RightCommand rightCommand = Mockito.mock(RightCommand.class);

        Mower mower = Mower.builder()
                .orientation(Orientation.N)
                .coordinates(Coordinates.builder().x(1).y(2).build())
                .build();

        commandHandler.accept(MowerCommands.builder()
                .mower(mower)
                .commands(List.of(leftCommand, rightCommand))
                .build());

        ArgumentCaptor<Mower> mowerArgumentCaptor = ArgumentCaptor.forClass(Mower.class);
        Mockito.verify(leftCommand).execute(mowerArgumentCaptor.capture());
        Mockito.verify(rightCommand).execute(mowerArgumentCaptor.capture());
        Assertions.assertThat(mowerArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(mower);
    }

}