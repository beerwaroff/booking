package ru.beerwaroff.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_room")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;

    private String comfort;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_image")
    private Image image;

    @Column(name = "room_number")
    private Integer roomNumber;

    @NotNull(message = "Количество мест не должно быть пустым")
    @Min(value = 1, message = "Количество мест должно быть числом от 1")
    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    @NotNull(message = "Цена не должна быть пустой")
    @Min(value = 0, message = "Цена должна быть числом")
    private Integer price;

    public Room() {
    }

    public Room(Hotel hotel, Integer roomNumber, Integer numberOfSeats, Integer price) {
        this.hotel = hotel;
        this.roomNumber = roomNumber;
        this.numberOfSeats = numberOfSeats;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
