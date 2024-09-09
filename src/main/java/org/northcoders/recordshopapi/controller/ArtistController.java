package org.northcoders.recordshopapi.controller;

import org.northcoders.recordshopapi.dto.request.artist.ArtistCreateDTO;
import org.northcoders.recordshopapi.dto.response.SuccessPayload;
import org.northcoders.recordshopapi.dto.response.SuccessResultType;
import org.northcoders.recordshopapi.dto.response.artist.ArtistResponseDTO;

import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artist")
public class ArtistController {
    @Autowired
    ArtistService artistService;

    @GetMapping
    public ResponseEntity<SuccessPayload> getArtists(
            @RequestParam(required = false, name = "name") String name
    ) {
        List<ArtistResponseDTO> artists = new ArrayList<>();

        if (name != null) {
            List<ArtistResponseDTO> artistsWithTitle = artistService.getArtistsByName(name);

            artists.addAll(artistsWithTitle);
        } else {
            List<ArtistResponseDTO> allArtists = artistService.getAllArtists();
            artists.addAll(allArtists);
        }

        SuccessPayload successPayload = new SuccessPayload(SuccessResultType.Fetched, Artist.class, artists);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessPayload> getArtistById(@PathVariable String id) {
        ArtistResponseDTO artist = artistService.getArtistById(Long.parseLong(id));

        SuccessPayload successPayload = new SuccessPayload(SuccessResultType.Fetched, Artist.class, artist);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessPayload> createArtist(@RequestBody @Validated ArtistCreateDTO artistCreateDTO) {
        ArtistResponseDTO createdArtist = artistService.createArtist(artistCreateDTO);

        SuccessPayload successPayload = new SuccessPayload(SuccessResultType.Created, Artist.class, createdArtist);

        return new ResponseEntity<>(successPayload, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessPayload> deleteArtist(@PathVariable String id) {
        artistService.deleteArtistById(Long.parseLong(id));

        SuccessPayload successPayload = new SuccessPayload(SuccessResultType.Deleted, Artist.class, id);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }
}
