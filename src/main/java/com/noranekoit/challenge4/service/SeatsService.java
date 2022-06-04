package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.payload.response.SeatResponse;

import java.util.stream.Stream;

public interface SeatsService {
    Stream<SeatResponse> getAllSeatsAvailable();
}
