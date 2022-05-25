package com.noranekoit.challenge4.controller;
import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.payload.request.InsertFilmRequest;
import com.noranekoit.challenge4.payload.request.InsertScheduleRequest;
import com.noranekoit.challenge4.payload.request.UpdateFilmRequest;
import com.noranekoit.challenge4.payload.response.FilmResponse;
import com.noranekoit.challenge4.payload.response.ScheduleResponse;
import com.noranekoit.challenge4.payload.response.SeatResponse;
import com.noranekoit.challenge4.payload.response.WebResponse;
import com.noranekoit.challenge4.service.FilmsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@RequestMapping("api/film")
public class FilmsController {

    private final FilmsService filmsService;

    @Autowired
    public FilmsController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }


    //get all film tayang
    @GetMapping("/tayang")
    public WebResponse<Stream<FilmResponse>> getAllFilmTayang() {
        Stream<FilmResponse> filmResponse = filmsService.getAllFilmTayang();
        return new WebResponse(
                200,
                "OK",
                filmResponse
        );
    }

    //insert films
    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<FilmResponse> addFilm(@RequestBody InsertFilmRequest insertFilmRequest) {
        FilmResponse filmResponse = filmsService.addFilm(insertFilmRequest);
        return new WebResponse(
                200,
                "OK",
                filmResponse
        );
    }

    //insert schedules
    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @PostMapping("/addSchedule")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<ScheduleResponse> addSchedule(@RequestBody InsertScheduleRequest insertScheduleRequest){
        ScheduleResponse scheduleResponse = filmsService.addSchedules(insertScheduleRequest);
        return new WebResponse(
                200,
                "OK",
                scheduleResponse
        );
    }

    //insert schedules
//    @PostMapping("/addSchedule")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Schedules addSchedulues(@RequestBody Schedules schedules )
//    {
//        return filmsService.addSchedules(schedules);
//    }

    //update film
    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @PutMapping("/updateFilm/{filmCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<FilmResponse> updateUser(
            @PathVariable String filmCode,
            @RequestBody UpdateFilmRequest updateFilmRequest) throws NotFoundException {
        FilmResponse filmResponse = filmsService.updateFilm(filmCode, updateFilmRequest);
        return new WebResponse(
                200,
                "OK",
                filmResponse
        );
    }

    //delete film
    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @DeleteMapping("/deleteFilm/{film_code}")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<String> deleteUser(@PathVariable String film_code) throws NotFoundException {
        filmsService.deleteFilmByFilmCode(film_code);
        return new WebResponse(
                200,
                "OK",
                film_code + " Berhasil di Hapus"
        );
    }

    //get all film date by name
    @GetMapping("/jadwal/{film}")
    public WebResponse<Stream<ScheduleResponse>> getAllFilmDateByName(@PathVariable("film") String film) {
        Stream<ScheduleResponse> scheduleResponse = filmsService.getAllFilmDateByFilm(film);
        return  new WebResponse(
                200,
                "OK",
                scheduleResponse
        );
    }

    //get All Seats available
    @GetMapping("/seats")
    public WebResponse<Stream<SeatResponse>> getAllAvailableSeats() {
        Stream<SeatResponse> seatResponse = filmsService.getAllSeatsAvailable();
        return new WebResponse(
                200,
                "OK",
                seatResponse
        );
    }
}