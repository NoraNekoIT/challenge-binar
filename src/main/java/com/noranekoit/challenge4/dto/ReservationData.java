package com.noranekoit.challenge4.dto;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservationData {
    private String idReservation;
    private String username;
//    private Time jamMulai;
//    private Time jamSelesai;
    private LocalDate tanggalTayang;
    private String filmName;
    private String no;
    private String studioName;
}