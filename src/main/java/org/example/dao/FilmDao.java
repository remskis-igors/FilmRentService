package org.example.dao;

import org.example.model.Film;
import org.example.model.Person;

import java.util.List;


public interface FilmDao {

    void removeFilm(Film film);

    void addFilm(Film film);

    boolean checkOut(Film film, Person person);

    boolean checkIn(Film film);


    List<Film> getFilmsPerPerson(Person p1);

    List<Film> getFilms();

    List<Film> getAvailableFilms();

    List<Film> getUnAvailableFilms();
}
