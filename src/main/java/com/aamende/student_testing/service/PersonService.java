package com.aamende.student_testing.service;

import com.aamende.student_testing.dto.Person;

public interface PersonService {

    Person getByName(String name);

    int examinePerson(Person person);
}