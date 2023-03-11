package org.example.filmRentService.model;

import org.example.filmRentService.dao.FilmDao;
import org.example.filmRentService.dao.FilmWareHouse;
import org.example.filmRentService.dao.PersonDao;
import org.example.filmRentService.dao.PersonRegister;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class FilmRentService implements FilmDao, PersonDao {

    private String name;
    private FilmWareHouse filmWareHouse;
    private PersonRegister personRegister;

    public FilmRentService() {
        this.name = "name";
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


    public String getStatusInfo(){
        StringBuilder statusInfo  =  new StringBuilder();
        statusInfo.append("FilmRentService status \n").append(this.toString()).append("\n");
        this.getFilms().forEach(film -> statusInfo.append("Film :").append(film).append("\n"));

        this.getPeople().forEach(person -> {
            int count = this.getFilmsPerPerson(person).size();
            statusInfo.append(person).append(" (has ").append(count).append(" of films)").append("\n");
        });
        statusInfo.append(" Available: ").append(this.getAvailableFilms().size()).append("\n");
        statusInfo.append("--- EOF ---").append("\n");
        return statusInfo.toString();
    }
    @Override
    public Person findByName(String personName) {
        return personRegister.findByName(personName);
    }


    public Film  findByTitle(String title) { return filmWareHouse.findByTitle(title);}
}