package org.northcoders.recordshopapi.controller;


import org.northcoders.recordshopapi.dto.request.genre.GenreCreateDTO;
import org.northcoders.recordshopapi.dto.response.success.SuccessPayload;
import org.northcoders.recordshopapi.dto.response.enums.SuccessResultType;
import org.northcoders.recordshopapi.dto.response.success.genre.GenreResponseDTO;

import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @GetMapping
    public ResponseEntity<SuccessPayload<List<GenreResponseDTO>>> getGenres() {
        List<GenreResponseDTO> genres = new ArrayList<>();

        List<GenreResponseDTO> allGenres = genreService.getAllGenres();
        genres.addAll(allGenres);

        SuccessPayload<List<GenreResponseDTO>> successPayload = new SuccessPayload<>(SuccessResultType.Fetched, Genre.class, genres);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessPayload<GenreResponseDTO>> getGenreById(@PathVariable String id) {
        GenreResponseDTO genre = genreService.getGenreById(Long.parseLong(id));

        SuccessPayload<GenreResponseDTO> successPayload = new SuccessPayload<>(SuccessResultType.Fetched, Genre.class, genre);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessPayload<GenreResponseDTO>> createGenre(@RequestBody @Validated GenreCreateDTO genreCreateDTO) {
        GenreResponseDTO createdGenre = genreService.createGenre(genreCreateDTO);

        SuccessPayload<GenreResponseDTO> successPayload = new SuccessPayload<>(SuccessResultType.Created, Genre.class, createdGenre);

        return new ResponseEntity<>(successPayload, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessPayload<String>> deleteGenre(@PathVariable String id) {
        genreService.deleteGenreById(Long.parseLong(id));

        SuccessPayload<String> successPayload = new SuccessPayload<>(SuccessResultType.Deleted, Genre.class, id);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }
}
