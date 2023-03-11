package org.example.filmRentService;

import junit.framework.TestCase;
import org.example.filmRentService.model.Person;

public class PersonRegisterTest extends TestCase {

    public void testPerson() {
        Person p = new Person();
        assertEquals("name", p.getName());
        assertEquals(10, p.getMaximumFilmsOnPerson());
    }

    public void testSetMaximumFilmsFail() {
        Person p = new Person();
        p.setMaximumFilmsOnPerson(10);
        assertEquals(10, p.getMaximumFilmsOnPerson());
    }

    public void testSetName() {
        Person p = new Person();
        p.setName("Marcis");
        assertEquals("Marcis", p.getName());
    }
    public void testToString(){
        Person p = new Person();
        p.setName("Ilgonis Spekjrausis");
        p.setMaximumFilmsOnPerson(10000);
        String testString = "Ilgonis Spekjrausis can rent 10000 films";
        assertEquals(testString, p.toString());
    }
}
