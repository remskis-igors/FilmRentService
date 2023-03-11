package org.example.filmRentService;

import org.example.filmRentService.model.Film;
import org.example.filmRentService.model.FilmRentService;
import org.example.filmRentService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/filmRentService")
public class FilmRentServiceController {

    @Autowired
    private FilmRentService filmRentService;

    @PostConstruct
    public void init(){
        filmRentService.addFilm( new Film("GoodBlessLatvia","Fantasy movie"));
        filmRentService.addFilm(new Film("GodBlessRiga", "Horror movie"));
        filmRentService.addFilm(new Film("Perfect evening", "Porno movie"));
        filmRentService.addPeople(new Person("Maris"));
        filmRentService.addPeople(new Person("Peteris"));
        filmRentService.addPeople(new Person("Mira"));
        Film porn = new Film("HornyBoops","Pron");
        Person janis = new Person();
        filmRentService.addFilm(porn);
        filmRentService.addPeople(janis);
        filmRentService.checkOut(porn,janis);
    }

    @RequestMapping(value="", produces= MimeTypeUtils.TEXT_PLAIN_VALUE)
        public String filmRentServiceStatus(){
        return filmRentService.getStatusInfo();
    }
    @RequestMapping(value = "/films", method = RequestMethod.GET, produces = MimeTypeUtils.TEXT_PLAIN_VALUE)
    public String getFilmsAsString(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "personName", required = false) String personName) {
        List<Film> books = getFilms(status, personName);
        return books.stream().map (Film::toString).collect(Collectors.joining("\n"));
    }

    private List<Film> getFilms(@RequestParam(value = "status", required = false) String status, @RequestParam(value = "personName", required = false) String personName) {
        if (personName != null && !personName.isEmpty()) {
            Person person = filmRentService.findByName(personName);
            return filmRentService.getFilmsPerPerson(person);
        }

        if ("available".equals(status)) {
            return filmRentService.getAvailableFilms();
        }
        if ("unavailable".equals(status)) {
            return filmRentService.getUnAvailableFilms();
        }
        return filmRentService.getFilms();
    }

    @RequestMapping(value = "/films/add", method = RequestMethod.POST)
    public String addFilm(@RequestParam(value = "title") String title) {
        if (title == null) {
            return "Cannot add film with absent title value";
        }
        Film newFilm = new Film(title);
        filmRentService.addFilm(newFilm);
        return "Film " + title + " Added";
    }

    @RequestMapping(value = "/films/remove", method = RequestMethod.DELETE)
    public String removeFilm(@RequestParam(value = "title") String title) {
        if (title == null) {
            return "Cannot remove film if title unknown/absent";
        }
        Film filmToRemove = new Film(title);
        filmRentService.removeFilm(filmToRemove);
        return "Film " + title + " is removed";
    }

    @RequestMapping(value = "/films/checkOut", method = RequestMethod.PUT)
    public String checkOutFilm(@RequestParam(value = "title") String title, @RequestParam(value = "person") String personName) {
        if (title == null) {
            return "Cannot remove film if title unknown/absent";
        }
        Film film = filmRentService.findByTitle(title);
        Person person = filmRentService.findByName(personName);
        boolean checkedOut = filmRentService.checkOut(film, person);
        return "Film " + title + (checkedOut ? "" : "not") + " checked out to " + personName;
    }

    @RequestMapping(value = "/films/checkIn", method = RequestMethod.PUT)
    public String checkInFilm(@RequestParam(value = "title") String title) {
        if (title == null) {
            return "Cannot remove film if title unknown/absent";
        }
        Film film = filmRentService.findByTitle(title);
        boolean checkedIn = filmRentService.checkIn(film);
        return "Film " + title + (checkedIn ? "" : "not") + " checked in";
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET, produces = MimeTypeUtils.TEXT_PLAIN_VALUE)
    public String getPersons() {
        List<Person> persons = filmRentService.getPeople();
        return persons.stream().map (Person::toString).collect(Collectors.joining("\n"));
    }

    @RequestMapping(value = "/persons/add", method = RequestMethod.POST)
    public String addPerson(@RequestParam(value = "name") String personName) {
        if (personName == null) {
            return "Cannot create person without name!";
        }
        Person person = new Person(personName);
        filmRentService.addPeople(person);
        return "Person " + personName + " created";
    }

    @RequestMapping(value = "/persons/remove", method = RequestMethod.DELETE)
    public String removePerson(@RequestParam(value = "name") String personName) {
        if (personName == null) {
            return "Cannot remove person without name!" ;
        }
        Person person = filmRentService.findByName(personName);
        filmRentService.removePeople(person);
        return "Person " + personName + " removed";
    }


}
