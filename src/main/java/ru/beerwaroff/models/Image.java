package ru.beerwaroff.models;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_image")
    private Integer id;

    @Column(name = "path_image")
    private String path;

    public Image() {
    }

    public Image(String path) {
        this.path = path;
    }

    public Integer getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
