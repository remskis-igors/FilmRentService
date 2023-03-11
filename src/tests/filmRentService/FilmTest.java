package org.example.filmRentService;

import org.example.filmRentService.model.Film;
import org.example.filmRentService.model.Person;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class})
public class FilmTest implements ApplicationContextAware {
    private ApplicationContext context;

    @Test
    public void testFilm(){
    Film first = (Film)context.getBean("first");
    assertEquals("GoodBlessLatvia",first.getTitle());
    assertEquals("Fantasy movie",first.getGenre());
    }

    @Test
    public void testGetPerson(){
    Film third = (Film) context.getBean("third");
    Person a = new Person();
    a.setName("Mira");

    third.setPerson(a);
    String testName =  third.getPerson().getName();
    assertEquals("Mira",testName);

    }

    @Test
    public void testToString() {
        Film second = new Film("MegoPorno", "fantasy");
        Person person = new Person();
        person.setName("Alvids");

        assertEquals("MegoPorno is: fantasy genre;", second.toString());
        second.setPerson(person);
        assertEquals("MegoPorno is genre; booked with Customer : Alvids", second.toString(),second.toString());
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context =  applicationContext;
    }
}
