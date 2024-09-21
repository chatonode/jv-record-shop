package org.northcoders.recordshopapi.controller;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.dto.response.success.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Format;
import org.northcoders.recordshopapi.model.GenreType;
import org.northcoders.recordshopapi.dto.response.enums.SuccessResultType;
import org.northcoders.recordshopapi.dto.response.success.SuccessPayload;
import org.northcoders.recordshopapi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<SuccessPayload<List<AlbumResponseDTO>>> getAlbums(
            @RequestParam(required = false, name = "year") Integer releaseYear,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "genre") GenreType genreType,
            @RequestParam(required = false, name = "format") Format format
    ) {
        List<AlbumResponseDTO> albums = new ArrayList<>();

        if (releaseYear != null) {
            List<AlbumResponseDTO> albumsWithReleaseYear = albumService.getAlbumsByReleaseYear(releaseYear);

            albums.addAll(albumsWithReleaseYear);
        } else if (title != null) {
            List<AlbumResponseDTO> albumsWithTitle = albumService.getAlbumsByTitle(title);

            albums.addAll(albumsWithTitle);
        } else if (genreType != null) {
            List<AlbumResponseDTO> albumsWithGenreType = albumService.getAlbumsByGenre(genreType);

            albums.addAll(albumsWithGenreType);
        } else if (format != null) {
            List<AlbumResponseDTO> albumsWithFormat = albumService.getAlbumsByFormat(format);
            albums.addAll(albumsWithFormat);
        } else {
            List<AlbumResponseDTO> allAlbums = albumService.getAllAlbums();
            albums.addAll(allAlbums);
        }

        SuccessPayload<List<AlbumResponseDTO>> successPayload = new SuccessPayload<>(SuccessResultType.Fetched, Album.class, albums);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessPayload<AlbumResponseDTO>> getAlbumById(@PathVariable String id) {
        AlbumResponseDTO album = albumService.getAlbumById(Long.parseLong(id));

        SuccessPayload<AlbumResponseDTO> successPayload = new SuccessPayload<>(SuccessResultType.Fetched, Album.class, album);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SuccessPayload<AlbumResponseDTO>> createAlbum(@RequestBody @Validated AlbumCreateDTO albumCreateDTO) {
        AlbumResponseDTO createdAlbum = albumService.createAlbum(albumCreateDTO);

        SuccessPayload<AlbumResponseDTO> successPayload = new SuccessPayload<>(SuccessResultType.Created, Album.class, createdAlbum);

        return new ResponseEntity<>(successPayload, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessPayload<AlbumResponseDTO>> replaceAlbum(@PathVariable String id, @Validated @RequestBody AlbumUpdateDTO albumUpdateDTO) {
        AlbumResponseDTO replacedAlbum = albumService.updateAlbum(Long.parseLong(id), albumUpdateDTO);

        SuccessPayload<AlbumResponseDTO> successPayload = new SuccessPayload<>(SuccessResultType.Updated, Album.class, replacedAlbum);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessPayload<String>> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbumById(Long.parseLong(id));

        SuccessPayload<String> successPayload = new SuccessPayload<>(SuccessResultType.Deleted, Album.class, id);

        return new ResponseEntity<>(successPayload, HttpStatus.OK);
    }
}
