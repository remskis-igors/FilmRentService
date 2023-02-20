package org.example.FilmRentService.model;

import org.apache.commons.lang3.StringEscapeUtils;

public class Film  {

    private String title;

    private String genre;

    private Person person;

    public Film(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }


    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person p2) {
        this.person = p2;
    }

    public String toString() {
        String available;
        if (this.getPerson() == null) {
            available = "Available";
        } else {
            available = " booked with Customer : " + this.getPerson().getName();
        }
        return StringEscapeUtils.unescapeXml(this.getTitle()) + " is: " +this.getGenre() + ";";
    }
}