package com.noranekoit.challenge4.Service;

import com.noranekoit.challenge4.repository.SchedulesRepository;
import com.noranekoit.challenge4.service.impl.SchedulesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SchedulesServiceTest {
    @Mock
    SchedulesRepository schedulesRepository;

    private SchedulesServiceImpl schedulesService;

    @BeforeEach void setup(){
        this.schedulesService = new SchedulesServiceImpl(this.schedulesRepository);
    }

    @Test void getAllFilmDateByFilm(){
        schedulesService.getAllFilmDateByFilm("S001");
        Mockito.verify(schedulesRepository).findAllDateByName("S001");
    }

}
