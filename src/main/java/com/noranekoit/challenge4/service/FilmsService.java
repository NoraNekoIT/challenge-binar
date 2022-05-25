package com.noranekoit.challenge4.service;

import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertFilmRequest;
import com.noranekoit.challenge4.payload.request.InsertScheduleRequest;
import com.noranekoit.challenge4.payload.request.UpdateFilmRequest;
import com.noranekoit.challenge4.payload.response.FilmResponse;
import com.noranekoit.challenge4.payload.response.ScheduleResponse;
import com.noranekoit.challenge4.payload.response.SeatResponse;

import java.util.stream.Stream;

public interface FilmsService {
    FilmResponse addFilm(InsertFilmRequest insertFilmRequest);
    FilmResponse updateFilm(String filmCode, UpdateFilmRequest updateFilmRequest) throws NotFoundException;
    void deleteFilmByFilmCode(String filmCode) throws NotFoundException;
    Stream<FilmResponse> getAllFilmTayang();

    ScheduleResponse addSchedules(InsertScheduleRequest insertScheduleRequest);
//    List<Schedules> getAllSchedules();

    Stream<ScheduleResponse> getAllFilmDateByFilm(String schedule);

    Stream<SeatResponse> getAllSeatsAvailable();

}