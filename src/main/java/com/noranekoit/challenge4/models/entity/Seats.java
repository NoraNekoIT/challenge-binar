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

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_reservation",nullable = false,
            referencedColumnName = "id_reservation"
    )
    private Reservation reservation;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "schedule_id",nullable = false,
//        referencedColumnName = "schedule_id"
//    )private Schedules schedules;

//    @ManyToOne(optional = false)
//    @JoinColumn(name = "film_code",nullable = false,
//        referencedColumnName = "film_code"
//    )private Films films;
}