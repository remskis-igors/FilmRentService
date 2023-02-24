package src.tests.org.example.FilmRentService;

import org.example.FilmRentService.FilmRentService;
import org.example.FilmRentService.ServiceConfig;
import org.example.FilmRentService.model.Film;
import org.example.FilmRentService.model.Person;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ServiceConfig.class })
public class FilmServiceTest implements ApplicationContextAware {
    private ApplicationContext context;

    private final Film film  = new Film("XXXX","YYY");
    private final Film filmTwo = new Film ("ZZZ","WWW");
    private final Film filmThree =new Film("111","222");

    private final Person person = new Person("AAAA");
    private final Person personTwo = new Person("BBBB");
    private final Person personThree = new Person("CCCC");

    @Autowired
    private FilmRentService filmRentService;

    @Before
    public void setUp(){
        filmRentService.getFilms().clear();
        filmRentService.getPeople().clear();

        filmRentService.addPeople(person);
        filmRentService.addPeople(personTwo);
        filmRentService.addPeople(personThree);

        filmRentService.addFilm(film);
        filmRentService.addFilm(filmTwo);
        filmRentService.addFilm(filmThree);

    }

    @Test
    public void FilmServiceTest(){

        assertEquals("FilmRentService init", filmRentService.getName());
        assertTrue(filmRentService.getFilms() != null);
        assertTrue(filmRentService.getPeople() != null);

    }

    @Test
    public void testRemoveFilm() {

        assertEquals(3, filmRentService.getFilms().size());
        assertEquals(0, filmRentService.getFilms().indexOf(film));
        assertEquals(1, filmRentService.getFilms().indexOf(filmTwo));
        assertEquals(2, filmRentService.getFilms().indexOf(filmThree));

        filmRentService.removeFilm(film);;
        assertEquals(2, filmRentService.getFilms().size());
        assertEquals(0, filmRentService.getFilms().indexOf(filmTwo));
        assertEquals(1,  filmRentService.getFilms().indexOf(filmThree));

        filmRentService.removeFilm(filmTwo);
        assertEquals(1, filmRentService.getFilms().size());
        assertEquals(0, filmRentService.getFilms().indexOf(filmThree));

        filmRentService.removeFilm(filmThree);
        assertEquals(0, filmRentService.getFilms().size());

    }

    @Test
    public void testCheckOut() {

        assertTrue("Film did not check out correctly", filmRentService.checkOut(film, person));

        assertEquals("AAAA",film.getPerson().getName());

        assertFalse("film been Check Out ", filmRentService.checkOut(film, personTwo));

        assertTrue("Film check in failed", filmRentService.checkIn(film));

        assertFalse("Film was already checked in", filmRentService.checkIn(film));

        assertFalse("Film was never checked out", filmRentService.checkIn(filmThree));

    }

    @Test
    public void testGetFilmsPerPerson() {
        assertEquals(0, filmRentService.getFilmsPerPerson(person).size());

        filmRentService.checkOut(film, person);

        List<Film> testFilms = filmRentService.getFilmsPerPerson(person);
        assertEquals(1, testFilms.size());

        filmRentService.checkOut(filmTwo, personThree);
        filmRentService.checkOut(filmThree, personThree);


        testFilms = filmRentService.getFilmsPerPerson(personThree);
        assertEquals(2, testFilms.size());

        filmRentService.checkOut(filmThree, person);
        filmRentService.checkOut(filmTwo, person);
        filmRentService.checkOut(film, person);

        testFilms = filmRentService.getFilmsPerPerson(person);
        assertEquals(1, testFilms.size());

    }

    @Test
    public void testGetAvailableFilms() {
        List<Film> testFilms = filmRentService.getAvailableFilms();
        assertEquals(3, testFilms.size());
        assertEquals(2, testFilms.indexOf(filmThree));

        filmRentService.checkOut(film, person);


        testFilms = filmRentService.getAvailableFilms();
        assertEquals(2, testFilms.size());
        assertEquals(1, testFilms.indexOf(filmThree));

        filmRentService.checkOut(filmTwo, personTwo);

        testFilms = filmRentService.getAvailableFilms();
        assertEquals(1, testFilms.size());
        assertEquals(0, testFilms.indexOf(filmThree));

        filmRentService.checkOut(filmThree, personThree);

        testFilms = filmRentService.getAvailableFilms();
        assertEquals(0, testFilms.size());

    }

    @Test
    public void testGetUnavailableFilms() {
        assertEquals(0, filmRentService.getUnAvailableFilms().size());

        filmRentService.checkOut(film, person);

        List<Film> testFilms = filmRentService.getUnAvailableFilms();
        assertEquals(1, testFilms.size());
        assertEquals(0, testFilms.indexOf(film));

        filmRentService.checkOut(filmTwo, personTwo);
        testFilms = filmRentService.getUnAvailableFilms();
        assertEquals(2, testFilms.size());
        assertEquals(1, testFilms.indexOf(filmTwo));

        filmRentService.checkOut(filmThree, personThree);
        testFilms = filmRentService.getUnAvailableFilms();
        assertEquals(3, testFilms.size());
        assertEquals(2, testFilms.indexOf(filmThree));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
