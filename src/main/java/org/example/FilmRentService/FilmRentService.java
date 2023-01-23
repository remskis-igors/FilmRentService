package org.example.FilmRentService;

import org.example.FilmRentService.dao.FilmDao;
import org.example.FilmRentService.dao.FilmWareHouse;
import org.example.FilmRentService.dao.PersonDao;
import org.example.FilmRentService.dao.PersonRegister;
import org.example.FilmRentService.model.Film;
import org.example.FilmRentService.model.Person;


import java.util.List;


public class FilmRentService implements FilmDao, PersonDao {

    private String name;
    private FilmWareHouse filmWareHouse;
    private PersonRegister personRegister;

    /**
     * Cunstructor
     *
     * @param name
     */
    public FilmRentService(String name) {
        this.name = name;
        filmWareHouse = new FilmWareHouse();
        personRegister = new PersonRegister();
    }

    public String getName() {
        return name;
    }

    @Override
    public void removeFilm(Film film) {
        this.filmWareHouse.removeFilm(film);
    }

    @Override
    public void addFilm(Film film) {
        this.filmWareHouse.addFilm(film);
    }

    @Override
    public boolean checkOut(Film film, Person person) {
        return filmWareHouse.checkOut(film, person);
    }

    @Override
    public boolean checkIn(Film film) {
        return filmWareHouse.checkIn(film);
    }

    @Override
    public List<Film> getFilmsPerPerson(Person p1) {
        return filmWareHouse.getFilmsPerPerson(p1);
    }

    @Override
    public List<Film> getFilms() {
        return filmWareHouse.getFilms();
    }

    @Override
    public List<Film> getAvailableFilms() {
        return filmWareHouse.getAvailableFilms();
    }

    @Override
    public List<Film> getUnAvailableFilms() {
        return filmWareHouse.getUnAvailableFilms();
    }

    @Override
    public List<Person> getPeople() {
        return personRegister.getPeople();
    }

    @Override
    public void addPeople(Person p1) {
        personRegister.addPeople(p1);
    }

    @Override
    public void removePeople(Person p1) {
        personRegister.removePeople(p1);

    }
    @Override
    public String toString() {
        return this.getName() + " " + this.getFilms().size() + "  films  " + this.getPeople().size() + " films ";
    }

    public static void main(String[] args) {
        FilmRentService filmRentService = new FilmRentService("SuperCinemaKosherRent");
        Film first = initFilm(filmRentService, "GoodBlessLatvia", "Fantasy movie");
        Film second = initFilm(filmRentService, "GodBlessRiga", "Horror movie");
        Film third = initFilm(filmRentService, "Perfect evening", "Porno movie");

        Person maris = initPerson(filmRentService, "Maris");
        Person peteris = initPerson(filmRentService, "Peteris");
        Person mara = initPerson(filmRentService, "Mara");

        System.out.println("LOGGER FilmService " + filmRentService.getName() + " was created");
        filmRentService.printStatus();

        System.out.println("LOGGER CheckIn of films ");
        filmRentService.checkIn(first);
        filmRentService.checkIn(second);
        filmRentService.checkIn(third);

        System.out.println("LOGGER all films checked in ");
        filmRentService.printStatus();

        filmRentService.checkOut(first, peteris);
        filmRentService.printStatus();


        filmRentService.checkOut(third, mara);
        filmRentService.printStatus();

        filmRentService.checkOut(second, maris);
        filmRentService.printStatus();

        filmRentService.getAvailableFilms();
        filmRentService.getUnAvailableFilms();
        filmRentService.getPeople();


        filmRentService.checkIn(first);
        filmRentService.printStatus();



    }

    /**
     * helper class to init person to film warehouse
     */
    public static Person initPerson(FilmRentService filmRentService, String personName) {
        Person person = new Person();
        person.setName(personName);
        filmRentService.addPeople(person);
        return person;
    }

    /**
     * helper class to init film to film warehouse
     */
    public static Film initFilm(FilmRentService filmRentService, String filmName, String genre) {
        Film film = new Film(filmName);
        film.setGenre(genre);
        filmRentService.addFilm(film);
        return film;
    }

    /**
     * helper class to print the status of the film warehouse
     */
    private void printStatus() {
        System.out.println(" Status print of FilmService \n" + this.toString());
        for (Film thisFilms : this.getFilms()) {
            System.out.println(thisFilms);
        }
        for (Person thisPeople : this.getPeople()) {
            int count = this.getFilmsPerPerson(thisPeople).size();
            System.out.println(thisPeople + " (has " + count + " of films)");
        }
        System.out.println("Films are available: " + this.getAvailableFilms().size() + this.getAvailableFilms());
        System.out.println("Films are not available: " + this.getUnAvailableFilms());
        System.out.println("-= EOF =-");
    }
}