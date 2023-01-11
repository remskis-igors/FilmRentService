package org.example.dao;

import org.example.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> getPeople();

    void addPeople(Person p1);

    void removePeople(Person p1);
}

