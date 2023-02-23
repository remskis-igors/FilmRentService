package org.example.FilmRentService;

import org.example.FilmRentService.dao.FilmWareHouse;
import org.example.FilmRentService.dao.PersonRegister;
import org.example.FilmRentService.model.Film;
import org.example.FilmRentService.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


import java.util.List;


public class FilmRentService {

    private String name;
    @Autowired
    private FilmWareHouse filmWareHouse;
    @Autowired
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


    public void removeFilm(Film film) {
        this.filmWareHouse.removeFilm(film);
    }


    public void addFilm(Film film) {
        this.filmWareHouse.addFilm(film);
    }

    public boolean checkOut(Film film, Person person) {
        return filmWareHouse.checkOut(film, person);
    }

    public boolean checkIn(Film film) {
        return filmWareHouse.checkIn(film);
    }

    public List<Film> getFilmsPerPerson(Person p) {
        return filmWareHouse.getFilmsPerPerson(p);
    }


    public List<Film> getFilms() {
        return filmWareHouse.getFilms();
    }

    public List<Film> getAvailableFilms() {
        return filmWareHouse.getAvailableFilms();
    }

    public List<Film> getUnAvailableFilms() {
        return filmWareHouse.getUnAvailableFilms();
    }

    public List<Person> getPeople() {
        return personRegister.getPeople();
    }

    public void addPeople(Person p1) {
        personRegister.addPeople(p1);
    }

    public void removePeople(Person p1) {
        personRegister.removePeople(p1);

    }
    public String toString() {
        return this.getName() + " " + this.getFilms().size() + "  films  " + this.getPeople().size() + " films ";
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ServiceConfig.class);

        FilmRentService filmRentService = (FilmRentService)context.getBean("filmRentService");
        filmRentService.printStatus();

        Film first = filmRentService.getFilms().get(0);
        Film second = filmRentService.getFilms().get(1);
        Film third = filmRentService.getFilms().get(2);

        Person maris = filmRentService.getPeople().get(0);
        Person peteris =  filmRentService.getPeople().get(1);
        Person mira = filmRentService.getPeople().get(2);

        System.out.println("FilmRentService created");
        filmRentService.printStatus();

        System.out.println("Check out first catalogue film to first person ");
        filmRentService.checkOut(first, maris);
        filmRentService.printStatus();


        System.out.println("check  in to third person  third film ");
        filmRentService.checkOut(third,mira);
        filmRentService.printStatus();
        System.out.println("check  out to third person  third film ");
        filmRentService.checkIn(third);
        filmRentService.printStatus();

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