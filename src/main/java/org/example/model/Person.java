package org.example.model;

public class Person {

    private String name;

    private int maximumFilmsOnPerson;

    public Person() {
        name = "Name";
        maximumFilmsOnPerson = 10;
    }

    public String getName() {
        return name;
    }

    public void setName(String customerName) {
        this.name = customerName;
    }

    public int getMaximumFilmsOnPerson() {
        return maximumFilmsOnPerson;
    }

    public void setMaximumFilmsOnPerson(int maximumFilmsOnPerson) {
        this.maximumFilmsOnPerson = maximumFilmsOnPerson;
    }

    public String toString() {
        return this.getName() + " can rent " + this.getMaximumFilmsOnPerson() + " films";

    }
}
