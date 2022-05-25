package com.noranekoit.challenge4.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.noranekoit.challenge4.dto.ReservationData;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@SqlResultSetMapping(
        name = "reservationMapping",
        classes = {
                @ConstructorResult(
                        targetClass = ReservationData.class,
                        columns = {
                                @ColumnResult(name = "id_reservation"),
                                @ColumnResult(name = "username"),
//                                @ColumnResult(name = "jam_mulai", type = Time.class) ,
//                                @ColumnResult(name = "jam_selesai", type = Time.class),
                                @ColumnResult(name = "tanggal_tayang", type = LocalDate.class),
                                @ColumnResult(name = "film_name"),
                                @ColumnResult(name = "no"),
                                @ColumnResult(name= "studio_name")

                        }
                )
        }
)
@NamedNativeQuery(
        name = "Reservation.getAllReservation",
        query = "SELECT DISTINCT r.id_reservation, u.username, s.jam_mulai, s.jam_selesai, s.tanggal_tayang, f.film_name, se.no, se.studio_name  " +
                "from reservation r, users u , schedules s, films f,  seats se " +
                "WHERE r.schedule_id = s.schedule_id AND s.film_code = f.film_code and u.username= r.username and r.id_reservation = se.id_reservation",
        resultSetMapping = "reservationMapping",
        resultClass = ReservationData.class
)
@ToString
@Getter
@Setter
@Entity(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id_reservation")
    private String idReservation;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "username")
    private Users users;

    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "schedule_id")
    private Schedules schedules;

    @JsonIgnore
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private Set<Seats> seats ;

}