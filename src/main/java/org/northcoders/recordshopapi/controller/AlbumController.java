package org.northcoders.recordshopapi.controller;

import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Format;
import org.northcoders.recordshopapi.model.GenreType;
import org.northcoders.recordshopapi.model.response.OperationType;
import org.northcoders.recordshopapi.model.response.SuccessResponse;
import org.northcoders.recordshopapi.service.AlbumService;
import org.northcoders.recordshopapi.service.AlbumServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/album")
public class AlbumController {

    @Autowired
    AlbumService albumService;

    @GetMapping
    public ResponseEntity<SuccessResponse> getAllAlbums(
            @RequestParam(required = false, name = "year") Integer releaseYear,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "genre") GenreType genreType,
            @RequestParam(required = false, name = "format") Format format
    ) {
        List<Album> albums = new ArrayList<>();

        if (releaseYear != null) {
            List<Album> albumsWithReleaseYear = albumService.getAlbumsByReleaseYear(releaseYear);

            albums.addAll(albumsWithReleaseYear);
        } else if (title != null) {
            List<Album> albumsWithTitle = albumService.getAlbumsByTitle(title);

            albums.addAll(albumsWithTitle);
        } else if (genreType != null) {
            List<Album> albumsWithGenreType = albumService.getAlbumsByGenre(genreType);

            albums.addAll(albumsWithGenreType);
        } else if (format != null) {
            List<Album> albumsWithFormat = albumService.getAlbumsByFormat(format);
            albums.addAll(albumsWithFormat);
        }

        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK, Album.class, OperationType.FETCH, albums);

        return new ResponseEntity<>(successResponse, successResponse.getStatus());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SuccessResponse> getAlbumById(@PathVariable String id) {
        Album album = albumService.getAlbumById(Long.parseLong(id));

        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK, Album.class, OperationType.FETCH, album);

        return new ResponseEntity<>(successResponse, successResponse.getStatus());
    }

    @PostMapping
    public ResponseEntity<SuccessResponse> createAlbum(@RequestBody Album album) {
        Album createdAlbum = albumService.createAlbum(album);

        SuccessResponse successResponse = new SuccessResponse(HttpStatus.CREATED, Album.class, OperationType.INSERT, createdAlbum);

        return new ResponseEntity<>(successResponse, successResponse.getStatus());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuccessResponse> replaceAlbum(@PathVariable String id, @RequestBody Album album) {
        Album replacedAlbum = albumService.replaceAlbum(Long.parseLong(id), album);

        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK, Album.class, OperationType.MODIFY, replacedAlbum);

        return new ResponseEntity<>(successResponse, successResponse.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<SuccessResponse> deleteAlbum(@PathVariable String id) {
        albumService.deleteAlbumById(Long.parseLong(id));

        SuccessResponse successResponse = new SuccessResponse(HttpStatus.OK, Album.class, OperationType.REMOVE, id);

        return new ResponseEntity<>(successResponse, successResponse.getStatus());
    }
}
