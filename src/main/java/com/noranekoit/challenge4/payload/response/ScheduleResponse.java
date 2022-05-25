package com.noranekoit.challenge4.payload.response;

import com.noranekoit.challenge4.models.entity.Films;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class ScheduleResponse {

        private String scheduleId;

        private Time jamMulai;

        private Time jamSelesai;

        private LocalDate tanggalTayang;

        private Float hargaTiket;

        private Films films;

//        private List<Seats> seats;
}
