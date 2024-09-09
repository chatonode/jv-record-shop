package org.northcoders.recordshopapi.mapper.request.genre;

import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.request.genre.GenreCreateDTO;
import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.model.GenreType;

import static org.junit.jupiter.api.Assertions.*;

class GenreCreateMapperTest {
    @Test
    void toEntity_mapsGenreCreateDTOToGenreEntity() {
        // Arrange: Prepare the GenreCreateDTO
        GenreCreateDTO genreCreateDTO = GenreCreateDTO.builder()
                .genreType(GenreType.ROCK)
                .build();

        // Act: Convert DTO to Entity using the mapper
        Genre genre = GenreCreateMapper.toEntity(genreCreateDTO);

        // Assert: Check if the mapping was done correctly
        assertNotNull(genre);
        assertEquals(GenreType.ROCK, genre.getName()); // Verify the GenreType is mapped correctly
    }
}