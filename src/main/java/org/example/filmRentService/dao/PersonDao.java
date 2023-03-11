package org.example.filmRentService.dao;

import org.example.filmRentService.model.Person;

import java.util.List;

public interface PersonDao {

    List<Person> getPeople();

    void addPeople(Person p1);

    void removePeople(Person p1);

    Person findByName(String personName);
}

