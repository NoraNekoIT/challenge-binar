package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.models.entity.Films;
import com.noranekoit.challenge4.payload.request.InsertFilmRequest;
import com.noranekoit.challenge4.payload.request.UpdateFilmRequest;
import com.noranekoit.challenge4.payload.response.FilmResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public interface FilmsService {
    FilmResponse addFilm(InsertFilmRequest insertFilmRequest);
    FilmResponse updateFilm(String filmCode, UpdateFilmRequest updateFilmRequest) throws NotFoundException;
    void deleteFilmByFilmCode(String filmCode);
    Stream<FilmResponse> getAllFilmTayang();
    CompletableFuture<List<Films>> getAllFilmsAsnyc();
}