package com.noranekoit.challenge4.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequest {
    private String idReservation;
    private String idSchedule;
    private Long idUser;
}
