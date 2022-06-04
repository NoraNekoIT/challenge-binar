package com.noranekoit.challenge4.controller;

import com.noranekoit.challenge4.payload.response.SeatResponse;
import com.noranekoit.challenge4.payload.response.WebResponse;
import com.noranekoit.challenge4.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/seat")
public class SeatsController {
    private final SeatsService seatsService;

    @Autowired
    public SeatsController(SeatsService seatsService) {
        this.seatsService = seatsService;
    }

    @GetMapping("/available")
    public WebResponse<Stream<SeatResponse>> getAllAvailableSeats() {
        Stream<SeatResponse> seatResponse = seatsService.getAllSeatsAvailable();
        return new WebResponse<>(
                200,
                "OK",
                seatResponse
        );
    }
 }
