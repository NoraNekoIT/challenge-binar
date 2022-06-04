package com.noranekoit.challenge4.service.impl;

import com.noranekoit.challenge4.models.entity.Films;
import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertFilmRequest;
import com.noranekoit.challenge4.payload.request.UpdateFilmRequest;
import com.noranekoit.challenge4.payload.response.FilmResponse;
import com.noranekoit.challenge4.repository.FilmsRepository;
import com.noranekoit.challenge4.service.FilmsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class FilmsServiceImpl implements FilmsService {


    private static final Logger LOGGER = LoggerFactory.getLogger(FilmsServiceImpl.class);
    private final FilmsRepository filmsRepository;

    @Autowired
    public FilmsServiceImpl(FilmsRepository filmsRepository) {
        this.filmsRepository = filmsRepository;
    }

    @Override
    public FilmResponse addFilm(InsertFilmRequest insertFilmRequest) {
        Films films = new Films(
                insertFilmRequest.getFilmCode(),
                insertFilmRequest.getFilmName(),
                insertFilmRequest.isStatusTayang()
        );
        filmsRepository.save(films);
        return convertFilmToFilmResponse(films);
    }

    @Override
    public FilmResponse updateFilm(String filmCode, UpdateFilmRequest updateFilmRequest) throws NotFoundException {
        Films filmsDB = filmsRepository.findById(filmCode).orElseThrow(NotFoundException::new);
        filmsDB.setFilmName(updateFilmRequest.getFilmName());
        filmsDB.setStatusTayang(updateFilmRequest.isStatusTayang());
        filmsRepository.save(filmsDB);
        return convertFilmToFilmResponse(filmsDB);
    }

    @Override
    public void deleteFilmByFilmCode(String filmCode){
        if (filmsRepository.findById(filmCode).isPresent()){
            filmsRepository.deleteById(filmCode);
        }

    }

    @Override
    public Stream<FilmResponse> getAllFilmTayang() {
        List<Films> films = filmsRepository.findAllByStatusTayang();
        return films.stream().map(this::convertFilmToFilmResponse);
    }

    @Override
    @Async
    public CompletableFuture<List<Films>> getAllFilmsAsnyc() {
        LOGGER.info("Request to get a list of films");
        final List<Films> films = filmsRepository.findAllByStatusTayang();
        return CompletableFuture.completedFuture(films);
    }

    private FilmResponse convertFilmToFilmResponse(Films films){
       return new FilmResponse(
               films.getFilmCode(),
               films.getFilmName(),
               films.isStatusTayang(),
               films.getSchedules()
       );
    }


}