package com.aamende.student_testing.dao;

import com.aamende.student_testing.dto.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonDaoImpl implements PersonDao {

    @Override
    public Person findByName(String name) {
        return new Person(name, 22);
    }
}
