package org.javarush.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.Type;

@Entity
@Table(schema = "movie", name = "film_text")
public class FilmText {

    @Id
    @Column(name = "film_id")
    private short id;

    @OneToOne
    @JoinColumn(name = "film_id")
    private Film film;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "description", columnDefinition = "text")
    @Type(type = "text")
    private String description;

    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
