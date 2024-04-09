package com.mower.domain.command;

import com.mower.domain.model.ForwardCommand;
import com.mower.domain.model.LeftCommand;
import com.mower.domain.model.RightCommand;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommandFactoryTest {

    @Test
    @DisplayName("command factory should return valid command")
    void shouldReturnValidCommand() {
        Assertions.assertThat(CommandFactory.of("A")).usingRecursiveComparison().isEqualTo(new ForwardCommand());
        Assertions.assertThat(CommandFactory.of("G")).usingRecursiveComparison().isEqualTo(new LeftCommand());
        Assertions.assertThat(CommandFactory.of("D")).usingRecursiveComparison().isEqualTo(new RightCommand());
    }
}