package com.noranekoit.challenge4.Service;

import com.noranekoit.challenge4.repository.SeatsRepository;
import com.noranekoit.challenge4.service.impl.SeatsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SeatsServiceTest {
    @Mock
    SeatsRepository seatsRepository;

    private SeatsServiceImpl seatsService;

    @BeforeEach void setup(){
        this.seatsService = new SeatsServiceImpl(this.seatsRepository);
    }

    @Test void getAllSeatsAvailable(){
        seatsService.getAllSeatsAvailable();
        Mockito.verify(seatsRepository).findAvailableSeats();
    }
}
