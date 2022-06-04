package com.noranekoit.challenge4.Service;

import com.noranekoit.challenge4.repository.FilmsRepository;
import com.noranekoit.challenge4.service.impl.FilmsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class FilmsServiceTest {
    @Mock FilmsRepository filmsRepository;

    private FilmsServiceImpl filmsService;

    @BeforeEach void setUp() {
        this.filmsService = new FilmsServiceImpl(this.filmsRepository);
    }

    @Test void getAllFilmTayang(){
        filmsService.getAllFilmTayang();
        Mockito.verify(filmsRepository).findAllByStatusTayang();
    }

}
