package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.artist.ArtistCreateDTO;
import org.northcoders.recordshopapi.dto.request.genre.GenreCreateDTO;
import org.northcoders.recordshopapi.dto.response.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.dto.response.genre.GenreResponseDTO;

import java.util.List;

public interface GenreService {
    List<GenreResponseDTO> getAllGenres();

//    List<GenreResponseDTO> getGenresByName(String name);

    GenreResponseDTO getGenreById(Long id); // OR NotFoundException

    GenreResponseDTO createGenre(GenreCreateDTO genreCreateDTO);

    void deleteGenreById(Long id);
}
