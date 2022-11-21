package com.aamende.student_testing.dao;

import com.aamende.student_testing.dto.Person;

public interface PersonDao {
    Person findByName(String name);
}

