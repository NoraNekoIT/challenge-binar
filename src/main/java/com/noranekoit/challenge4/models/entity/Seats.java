package com.noranekoit.challenge4.models.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Getter
@Setter
@Entity(name = "seats")
public class Seats {

    @EmbeddedId
    private SeatsIdentity seatsIdentity;

    @Column(name = "seat_status")
    private Boolean seatStatus;

    @ManyToOne()
    @JoinColumn(name = "id_reservation",
            referencedColumnName = "id_reservation"
    )
    private Reservation reservation;

}