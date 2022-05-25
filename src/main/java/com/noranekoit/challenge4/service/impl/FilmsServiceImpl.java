package com.noranekoit.challenge4.service.impl;

import com.noranekoit.challenge4.models.entity.Films;
import com.noranekoit.challenge4.models.entity.Schedules;
import com.noranekoit.challenge4.models.entity.Seats;
import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertFilmRequest;
import com.noranekoit.challenge4.payload.request.InsertScheduleRequest;
import com.noranekoit.challenge4.payload.request.UpdateFilmRequest;
import com.noranekoit.challenge4.payload.response.FilmResponse;
import com.noranekoit.challenge4.payload.response.ScheduleResponse;
import com.noranekoit.challenge4.payload.response.SeatResponse;
import com.noranekoit.challenge4.repository.FilmsRepository;
import com.noranekoit.challenge4.repository.SchedulesRepository;
import com.noranekoit.challenge4.repository.SeatsRepository;
import com.noranekoit.challenge4.service.FilmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class FilmsServiceImpl implements FilmsService {

    private final FilmsRepository filmsRepository;
    private final SchedulesRepository schedulesRepository;
    private final SeatsRepository seatsRepository;

    @Autowired
    public FilmsServiceImpl(FilmsRepository filmsRepository, SchedulesRepository schedulesRepository, SeatsRepository seatsRepository) {
        this.filmsRepository = filmsRepository;
        this.schedulesRepository = schedulesRepository;
        this.seatsRepository = seatsRepository;
    }

    //insert film
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

/*
    @Override
    public Films addFilm(Films film) {
        return filmsRepository.save(film);
    }
*/

    //update film
    @Override
    public FilmResponse updateFilm(String filmCode, UpdateFilmRequest updateFilmRequest) throws NotFoundException {
        Films filmsDB = filmsRepository.findById(filmCode).orElseThrow(NotFoundException::new);
        filmsDB.setFilmName(updateFilmRequest.getFilmName());
        filmsDB.setStatusTayang(updateFilmRequest.isStatusTayang());
        filmsRepository.save(filmsDB);
        return convertFilmToFilmResponse(filmsDB);
    }

//    @Override
//    public void updateFilm(Films film) {
//        Films filmsDB = filmsRepository.findById(String.valueOf(film.getFilmCode())).orElseThrow();
//        filmsRepository.save(film);
//    }

    //delete film by filmcode

    @Override
    public void deleteFilmByFilmCode(String filmCode) throws NotFoundException {
        Films filmsDB = filmsRepository.findById(filmCode).orElseThrow(NotFoundException::new);
        filmsRepository.deleteById(filmCode);
    }


/*
    @Override
    public void deleteFilmByFilmCode(String filmCode) {
        try {
            filmsRepository.deleteById(filmCode);
        }catch (DataAccessException e){
            throw new RuntimeException(e.getMessage());
        }
    }
*/

    //get all film tayang
    @Override
    public Stream<FilmResponse> getAllFilmTayang() {
        List<Films> films = filmsRepository.findAllByStatusTayang();
        return films.stream().map(this::convertFilmToFilmResponse);
    }

    //insert schedule
    @Override
    public ScheduleResponse addSchedules(InsertScheduleRequest insertScheduleRequest) {
        Schedules schedule = new Schedules(
                insertScheduleRequest.getScheduleId(),
                insertScheduleRequest.getJamMulai(),
                insertScheduleRequest.getJamSelesai(),
                insertScheduleRequest.getTanggalTayang(),
                insertScheduleRequest.getHargaTiket(),
                insertScheduleRequest.getFilms()
        );
        schedulesRepository.save(schedule);
        return convertScheduleToScheduleResponse(schedule);
    }



    //get all date film by name
    @Override
    public Stream<ScheduleResponse> getAllFilmDateByFilm(String schedules) {
        List<Schedules> schedule = schedulesRepository.findAllDateByName(schedules);
        System.out.println(schedules);
        return schedule.stream().map(this::convertScheduleToScheduleResponse);
    }

    //get seats available
    @Override
    public Stream<SeatResponse> getAllSeatsAvailable() {
        List<Seats> seats = seatsRepository.findAvailableSeats();
        return seats.stream().map(this::convertSeatsToSeatsResponse);
    }


    //convert Response
    private FilmResponse convertFilmToFilmResponse(Films films){
       return new FilmResponse(
               films.getFilmCode(),
               films.getFilmName(),
               films.isStatusTayang(),
               films.getSchedules()
       );
    }


    //convert response
    private ScheduleResponse convertScheduleToScheduleResponse(Schedules schedules){
        return new ScheduleResponse(
                schedules.getScheduleId(),
                schedules.getJamMulai(),
                schedules.getJamSelesai(),
                schedules.getTanggalTayang(),
                schedules.getHargaTiket(),
                schedules.getFilms()
        );
    }



    //convert response
    private SeatResponse convertSeatsToSeatsResponse(Seats seats){
        return new SeatResponse(
                seats.getSeatsIdentity(),
                seats.getSeatStatus()
        );
    }
}