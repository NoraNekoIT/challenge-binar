package com.noranekoit.challenge4.payload.response;

import com.noranekoit.challenge4.models.entity.SeatsIdentity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SeatResponse {

    private SeatsIdentity seatsIdentity;

    private Boolean seatStatus;

}
