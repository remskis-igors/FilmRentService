package org.example.FilmRentService;

import junit.framework.TestCase;
import org.example.FilmRentService.model.Person;

public class FilmTest extends TestCase {

    public void TestFilm() {
        Film flm1 = new Film("Latvia");
        assertEquals("Laassssssstvia", flm1.getTitle());
        assertEquals("genre", flm1.getGenre());
    }

    public void testPerson() {
        Film flm1 = new Film("Lithuania");
        Person person = new Person();
        person.setName("Edgars");
        flm1.setPerson(person);

        String personTestName = person.getName();
        assertEquals("Edgars", personTestName);
    }

    public void testTotest() {
        Film flm1 = new Film("MegoPorno");
        Person person = new Person();
        person.setName("Alvids");

        assertEquals("MegoPorno is genre; Available", flm1.toString(), flm1.toString());
        flm1.setPerson(person);
        assertEquals("MegoPorno is genre; booked with Customer : Alvids", flm1.toString(), flm1.toString());
    }

}
