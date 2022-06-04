package com.noranekoit.challenge4.controller;

import com.noranekoit.challenge4.payload.request.InsertScheduleRequest;
import com.noranekoit.challenge4.payload.response.ScheduleResponse;
import com.noranekoit.challenge4.payload.response.WebResponse;
import com.noranekoit.challenge4.service.SchedulesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/schedule")
public class SchedulesController {
    private final SchedulesService schedulesService;

    @Autowired
    public SchedulesController(SchedulesService schedulesService) {
        this.schedulesService = schedulesService;
    }

    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @PostMapping("/addSchedule")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<ScheduleResponse> addSchedule(@RequestBody InsertScheduleRequest insertScheduleRequest) {
        ScheduleResponse scheduleResponse = schedulesService.addSchedules(insertScheduleRequest);
        return new WebResponse<>(
                200,
                "OK",
                scheduleResponse
        );
    }

    @GetMapping("/jadwal/{film}")
    public WebResponse<Stream<ScheduleResponse>> getAllFilmDateByName(
            @PathVariable("film") String film) {
        Stream<ScheduleResponse> scheduleResponse = schedulesService.getAllFilmDateByFilm(film);
        return new WebResponse<>(
                200,
                "OK",
                scheduleResponse
        );
    }
}
