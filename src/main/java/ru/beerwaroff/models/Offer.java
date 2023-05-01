package ru.beerwaroff.models;

import com.mysql.cj.xdevapi.Client;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_offer")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_room")
    private Room room;

    @Column(name = "check_in_date")
    private String checkInDate;

    @Column(name = "eviction_date")
    private String evictionDate;

    public Offer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getEvictionDate() {
        return evictionDate;
    }

    public void setEvictionDate(String evictionDate) {
        this.evictionDate = evictionDate;
    }
}
