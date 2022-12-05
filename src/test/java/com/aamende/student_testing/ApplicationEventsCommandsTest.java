package com.aamende.student_testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import org.springframework.test.annotation.DirtiesContext;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Тест команд shell")
@SpringBootTest
public class ApplicationEventsCommandsTest {

    private static String COMMAND_LOGIN1 = "login";
    private static String COMMAND_LOGIN2 = "l";
    private static String COMMAND_EXAM = "exam";
    private static String GREEETINGS_PATTERN = "Добро пожаловать: %s";
    private static String DEFAULT_NAME = "Unknown User";
    @Autowired
    private Shell shell;

    @DisplayName(" должен возвращать приветствие для всех форм команды логина")
    @Test
    void shouldReturnGreetingsAfterLogin() {
        String res1 = (String) shell.evaluate(() -> COMMAND_LOGIN1);
        assertThat(res1).isEqualTo(String.format(GREEETINGS_PATTERN, DEFAULT_NAME));

        String res2 = (String) shell.evaluate(() -> COMMAND_LOGIN2);
        assertThat(res2).isEqualTo(String.format(GREEETINGS_PATTERN, DEFAULT_NAME));
    }

    @DisplayName(" должен возвращать CommandNotCurrentlyAvailable при попытке пользователя "
            + "пройти экзамен, не залогинившись")
    @Test
    // переоткрывает контекст перед методом
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    void shouldReturnCommandNotCurrentlyAvailableAfterCommandExam() {
        Object result = shell.evaluate(() -> COMMAND_EXAM);
        assertThat(result).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }
}
