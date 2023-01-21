package org.example.FilmRentService.dao;

import org.example.FilmRentService.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRegister implements PersonDao {

    private List<Person> people;


    public PersonRegister() {
        people = new ArrayList<>();
    }


    @Override
    public List<Person> getPeople() {
        return people;
    }

    @Override
    public void addPeople(Person p1) {
        this.people.add(p1);
    }

    @Override
    public void removePeople(Person p1) {
        this.people.remove(p1);

    }
}
