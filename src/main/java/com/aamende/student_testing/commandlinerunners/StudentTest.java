package com.aamende.student_testing.commandlinerunners;

import com.aamende.student_testing.dto.Person;
import com.aamende.student_testing.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class StudentTest implements CommandLineRunner {

    private final Logger LOGGER = LoggerFactory.getLogger(StudentTest.class);
    private final PersonService personService;

    public StudentTest(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public void run(String... args)
            throws Exception {
        LOGGER.info("STARTING EXAM");
        Person artem = personService.getByName("Artem");
        personService.examinePerson(artem);

    }
}
