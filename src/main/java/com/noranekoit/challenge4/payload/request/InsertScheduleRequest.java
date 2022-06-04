package com.noranekoit.challenge4.payload.request;

import com.noranekoit.challenge4.models.entity.Films;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
public class InsertScheduleRequest {
    private String scheduleId;

    private Time jamMulai;

    private Time jamSelesai;

    private LocalDate tanggalTayang;

    private Float hargaTiket;

    private Films films;

}