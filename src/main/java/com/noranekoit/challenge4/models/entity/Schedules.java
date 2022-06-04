package com.noranekoit.challenge4.models.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity(name = "schedules")
public class Schedules {

    @Id
    @Column(name = "schedule_id",unique = true)
    private String scheduleId;

    @Column(name = "jam_mulai")
    private Time jamMulai;

    @Column(name = "jam_selesai")
    private Time jamSelesai;

    @Column(name = "tanggal_tayang")
    private LocalDate tanggalTayang;

    @Column(name = "harga_tiket")
    private Float hargaTiket;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="film_code",
            referencedColumnName = "film_code"
    )
    private Films films;

    @OneToMany(mappedBy = "schedules", cascade = CascadeType.ALL)
    private Set<Reservation> reservation;

    public Schedules(String scheduleId, Time jamMulai, Time jamSelesai, LocalDate tanggalTayang, Float hargaTiket, Films films) {
        this.scheduleId = scheduleId;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.tanggalTayang = tanggalTayang;
        this.hargaTiket = hargaTiket;
        this.films = films;
    }

}
