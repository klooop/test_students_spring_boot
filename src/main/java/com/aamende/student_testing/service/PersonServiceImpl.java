package com.aamende.student_testing.service;

import com.aamende.student_testing.configs.AppProps;
import com.aamende.student_testing.dao.PersonDao;
import com.aamende.student_testing.dao.PersonDaoImpl;
import com.aamende.student_testing.dto.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);
    AppProps props;
    PersonDao dao;
    private final static String COMMA_DELIMITER = ";";
    private static int COUNT = 0;

    public PersonServiceImpl(AppProps props,
                             PersonDao dao) {
        this.props = props;
        this.dao = dao;
    }

    @Override
    public Person getByName(String name) {
        LOGGER.info("finding person by name");
        return dao.findByName(name);
    }

    @Override
    public int examinePerson(Person person) {
        LOGGER.info("----STARTED EXAMINING PERSON----");
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(props.getFileName()))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                records.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (List<String> record : records) {
            System.out.println(record.get(0));
            System.out.println(
                    "Type right answer: " + "\n" + record.get(1) + "\n" + record.get(2));
            Scanner scanner = new Scanner(System.in);
            String response = scanner.nextLine().trim();
            if (response.equals(record.get(3))) {
                COUNT++;
            }
        }
        person.setScore(COUNT);
        LOGGER.info(
                "Количество баллов у студента " + person.getName() + " : " + COUNT + "/"
                        + +records.size());
        return COUNT;
    }
}
