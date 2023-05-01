package ru.beerwaroff.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_hotel")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_address")
    private Address address;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_image")
    private Image image;

    @Column(name = "number_of_stars")
    private Integer numberOfStars;

    @NotEmpty(message = "Название отеля не может быть пустым")
    @Size(max = 32, message = "Имя не может содержать больше 32 символов")
    @Column(name = "name_of_the_hotel")
    private String name;

    public Hotel() {

    }
    public Hotel(Address address, Integer numberOfStars, String name) {
        this.numberOfStars = numberOfStars;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getNumberOfStars() {
        return numberOfStars;
    }

    public void setNumberOfStars(Integer numberOfStars) {
        this.numberOfStars = numberOfStars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
