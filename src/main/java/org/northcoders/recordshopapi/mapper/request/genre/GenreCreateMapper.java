package org.northcoders.recordshopapi.mapper.request.genre;


import org.northcoders.recordshopapi.dto.request.genre.GenreCreateDTO;
import org.northcoders.recordshopapi.model.Genre;

public class GenreCreateMapper {
    public static Genre toEntity(GenreCreateDTO genreCreateDTO) {
        return Genre.builder()
                .name(genreCreateDTO.getGenreType())
                .build();
    }
}
