package org.example.filmRentService.dao;

import org.example.filmRentService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PersonRegister implements PersonDao {
    @Autowired
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
    public Person findByName( String personName){
        if(personName == null){
            return null;
        }
        return people.stream().filter(p -> Objects.equals(p.getName().toLowerCase(), personName.toLowerCase())).findFirst().orElse(null);
    }
}
