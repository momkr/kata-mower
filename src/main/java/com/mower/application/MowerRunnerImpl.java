package com.mower.application;

import com.mower.domain.MowerRepository;
import com.mower.domain.command.CommandHandler;
import com.mower.domain.model.Mower;
import com.mower.domain.model.MowerCommands;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MowerRunnerImpl implements ApplicationRunner {

    private final MowerRepository mowerRepository;

    private final CommandHandler commandHandler;

    @Value("${fileName}")
    private String fileName;

    @Override
    public void run(ApplicationArguments args) throws IOException {
        log.info(""" 
                ---------------------------------------------  MOWITNOW BY MOW  ---------------------------------------------
                            __
                             \\ \\
                              \\ \\
                               \\ \\
                                \\/`\\
                                |   \\   _+,_
                                 \\   (_[____]_   /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                  '._|.-._.-._] /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                 ^^^^^^^^^^^^^^^^^^'-''-'^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\
                """);
        List<MowerCommands> mowerCommands = mowerRepository.readFile(fileName);
        if (!CollectionUtils.isEmpty(mowerCommands)) {
            mowerCommands.forEach(commandHandler);
            log.info(">>> End of file. Mowers are in final positions: {}", mowerCommands.stream().map(MowerCommands::getMower).map(Mower::toString).toList());
            log.info("""
                ---------------------------------------------  END  ---------------------------------------------
                                                                                                                                                                            __
                                                                                                                                                                             \\ \\
                                                                                                                                                                              \\ \\
                                                                                                                                                                               \\ \\
                                                                                                                                                                                \\/`\\
                                                                                                                                                                                |   \\   _+,_
                                                                                                                                                                                 \\   (_[____]_
                                                                                                                                                                                  '._|.-._.-._]
                 ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^\
               \s""");
        }

    }
}
