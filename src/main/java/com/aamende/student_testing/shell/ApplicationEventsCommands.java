package com.aamende.student_testing.shell;

import com.aamende.student_testing.dto.Person;
import com.aamende.student_testing.service.PersonService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ApplicationEventsCommands {
    private final PersonService personService;
    private String userName;
    Person person;
    @Autowired
    public ApplicationEventsCommands(PersonService personService) {
        this.personService = personService;
    }
    @ShellMethod(value = "Login command", key = {"login", "l"})
    public String login(@ShellOption(defaultValue = "Unknown User") String userName) {
        this.userName = userName;
        this.person = personService.getByName(userName);
        return String.format("Добро пожаловать: %s", userName);
    }
        @ShellMethod(value = "Start examining person", key = {"exam"})
        @ShellMethodAvailability(value = "isPersonAvailableForExam")
        public String examinePerson(){
            int points = personService.examinePerson(person);
            return String.format("Вы получили "+points+" за тест");
        }

    private Availability isPersonAvailableForExam() {
        return userName == null ? Availability.unavailable("Сначала нужно залогиниться") :
               Availability.available();
    }

}
