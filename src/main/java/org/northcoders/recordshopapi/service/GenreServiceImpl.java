package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.genre.GenreCreateDTO;
import org.northcoders.recordshopapi.dto.response.success.genre.GenreResponseDTO;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.mapper.request.genre.GenreCreateMapper;
import org.northcoders.recordshopapi.mapper.response.GenreResponseMapper;

import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<GenreResponseDTO> getAllGenres() {
        List<Genre> genres = new ArrayList<>();

        genreRepository.findAll().forEach(genres::add);

        List<GenreResponseDTO> genreResponseDTOs = genres.stream().map(GenreResponseMapper::toDTO).toList();

        return genreResponseDTOs;
    }

    @Override
    public GenreResponseDTO getGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(Genre.class));

        GenreResponseDTO genreResponseDTO = GenreResponseMapper.toDTO(genre);

        return genreResponseDTO;
    }

    @Override
    public GenreResponseDTO createGenre(GenreCreateDTO genreCreateDTO) {
        Genre genre = GenreCreateMapper.toEntity(genreCreateDTO);

        Genre createdGenre = genreRepository.save(genre);

        GenreResponseDTO genreResponseDTO = GenreResponseMapper.toDTO(createdGenre);

        return genreResponseDTO;
    }

    @Override
    public void deleteGenreById(Long id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new NotFoundException(Genre.class));

        genreRepository.deleteById(id);
    }
}
