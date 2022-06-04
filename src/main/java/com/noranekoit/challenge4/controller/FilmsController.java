package com.noranekoit.challenge4.controller;

import com.noranekoit.challenge4.error.NotFoundException;
import com.noranekoit.challenge4.models.entity.Films;
import com.noranekoit.challenge4.payload.request.InsertFilmRequest;
import com.noranekoit.challenge4.payload.request.UpdateFilmRequest;
import com.noranekoit.challenge4.payload.response.FilmResponse;
import com.noranekoit.challenge4.payload.response.WebResponse;
import com.noranekoit.challenge4.service.FilmsService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/film")
public class FilmsController {

    private final FilmsService filmsService;

    @Autowired
    public FilmsController(FilmsService filmsService) {
        this.filmsService = filmsService;
    }

    @GetMapping("/tayang")
    public WebResponse<Stream<FilmResponse>> getAllFilmTayang() {
        Stream<FilmResponse> filmResponse = filmsService.getAllFilmTayang();
        return new WebResponse<>(
                200,
                "OK",
                filmResponse
        );
    }

    @GetMapping("/allFilmsAsnyc")
    public CompletableFuture<ResponseEntity<List<Films>>> getAllFilmTayangAsync() {
            return filmsService.getAllFilmsAsnyc().thenApply(ResponseEntity::ok);
    }

    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<FilmResponse> addFilm(@RequestBody InsertFilmRequest insertFilmRequest) {
        FilmResponse filmResponse = filmsService.addFilm(insertFilmRequest);
        return new WebResponse<>(
                200,
                "OK",
                filmResponse
        );
    }


    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @PutMapping("/updateFilm/{filmCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<FilmResponse> updateUser(
            @PathVariable String filmCode,
            @RequestBody UpdateFilmRequest updateFilmRequest) throws NotFoundException {
        FilmResponse filmResponse = filmsService.updateFilm(filmCode, updateFilmRequest);
        return new WebResponse<>(
                200,
                "OK",
                filmResponse
        );
    }

    @Operation(description = "perlu Role admin silahkan login admin untuk dapat cookies barer token")
    @DeleteMapping("/deleteFilm/{filmCode}")
    @PreAuthorize("hasRole('ADMIN')")
    public WebResponse<String> deleteUser(@PathVariable String filmCode) {
        filmsService.deleteFilmByFilmCode(filmCode);
        return new WebResponse<>(
                200,
                "OK",
                filmCode + " Berhasil di Hapus"
        );
    }

}