package org.example.FilmRentService.dao;

import org.example.FilmRentService.model.Film;
import org.example.FilmRentService.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilmWareHouse implements FilmDao {
    private List<Film> films = new ArrayList<>();

    @Override
    public List<Film> getFilms() {
        return films;
    }

    @Override
    public void removeFilm(Film film) {
        this.films.remove(film);
    }

    @Override
    public void addFilm(Film film) {
        this.films.add(film);
    }

    @Override
    public boolean checkOut(Film film, Person person) {
        if (film == null) { return false;}
        int filmsOnRentPerPerson = this.getFilmsPerPerson(person).size();
        boolean canCheckOut = (film.getPerson() == null) && (filmsOnRentPerPerson < person.getMaximumFilmsOnPerson());
        if (canCheckOut) {
            film.setPerson(person);
        }
        return canCheckOut;
    }

    @Override
    public boolean checkIn(Film film) {
        if (film == null) {
            return false;
        }
        boolean checkIn = film.getPerson() != null;
        if(checkIn){
            film.setPerson(null);
        }
        return checkIn;
    }

    public List<Film> getFilmsPerPerson(Person p1) {
        return this.getFilms().stream().filter(aFilm->{
            boolean filmCheckedOut = aFilm.getPerson() != null;
            return filmCheckedOut && aFilm.getPerson().getName().equals(p1.getName());
        }).collect(Collectors.toList());    }

    @Override
    public List<Film> getAvailableFilms() {
       return this.getFilms().stream().filter(film -> film.getPerson() == null).collect(Collectors.toList());
    }

    @Override
    public List<Film> getUnAvailableFilms() {
        return this.getFilms().stream().filter(film -> film.getPerson() != null).collect(Collectors.toList());    }
}
