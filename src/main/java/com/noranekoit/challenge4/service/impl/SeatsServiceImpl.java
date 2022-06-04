package com.noranekoit.challenge4.service.impl;

import com.noranekoit.challenge4.models.entity.Seats;
import com.noranekoit.challenge4.payload.response.SeatResponse;
import com.noranekoit.challenge4.repository.SeatsRepository;
import com.noranekoit.challenge4.service.SeatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SeatsServiceImpl implements SeatsService {
    private final SeatsRepository seatsRepository;

    @Autowired
    public SeatsServiceImpl(SeatsRepository seatsRepository) {
        this.seatsRepository = seatsRepository;
    }

    @Override
    public Stream<SeatResponse> getAllSeatsAvailable() {
        List<Seats> seats = seatsRepository.findAvailableSeats();
        return seats.stream().map(this::convertSeatsToSeatsResponse);
    }

    private SeatResponse convertSeatsToSeatsResponse(Seats seats){
        return new SeatResponse(
                seats.getSeatsIdentity(),
                seats.getSeatStatus()
        );
    }
}
