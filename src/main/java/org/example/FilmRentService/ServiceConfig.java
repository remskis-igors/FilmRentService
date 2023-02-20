package org.example.FilmRentService;


import org.example.FilmRentService.dao.FilmWareHouse;
import org.example.FilmRentService.dao.PersonRegister;
import org.example.FilmRentService.model.Film;
import org.example.FilmRentService.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public Film first() {
        return new Film("GoodBlessLatvia", "Fantasy movie");
    }

    @Bean
    public Film second() {
        return new Film("GodBlessRiga", "Horror movie");
    }

    @Bean
    public Film third() {
        return new Film("Perfect evening", "Porno movie");
    }

    @Bean
    public Person maris() {
        return new Person("Maris");
    }

    @Bean
    public Person peteris() {
        return new Person("Peteris");
    }

    @Bean
    public Person mira() {
        return new Person("Mira");

    }

    @Bean
    public PersonRegister personRegister() {
        return new PersonRegister();
    }

    @Bean
    public FilmWareHouse filmWareHouse() { return new FilmWareHouse(); }

    @Bean
    public FilmRentService filmRentService() {
        return new FilmRentService("FilmRentService init");
    }
}
