package org.northcoders.recordshopapi.mapper.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.response.genre.GenreResponseDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenreResponseMapperTest {

    @Test
    void toDTO_mapsGenreEntityToGenreResponseDTO() {
        // Arrange
        Album album = Album.builder()
                .title("Test Album")
                .artists(List.of())
                .genres(List.of())
                .durationInSeconds(300)
                .imageUrl("http://example.com/image.jpg")
                .releaseYear(2022)
                .publisher("Test Publisher")
                .priceInPences(999)
                .currency(Currency.GBP)
                .format(Format.Vinyl)
                .build();
        album.setId(1L);
        album.setCreatedDate(new Date());
        album.setUpdatedDate(new Date());
        album.setQuantityInStock(10);

        Genre genre = Genre.builder()
                .name(GenreType.ROCK)
                .build();
        genre.setId(1L);
        genre.setAlbumSet(new HashSet<>(List.of(album)));
        genre.setCreatedDate(new Date());
        genre.setUpdatedDate(new Date());

        // Act: Call the toDTO method
        GenreResponseDTO genreResponseDTO = GenreResponseMapper.toDTO(genre);

        // Assert: Verify that the mapping is correct
        assertEquals(genre.getId(), genreResponseDTO.id());
        assertEquals(genre.getName(), genreResponseDTO.name());
        assertEquals(1, genreResponseDTO.albums().size());
        assertEquals(1L, genreResponseDTO.albums().get(0).id());
        assertEquals("Test Album", genreResponseDTO.albums().get(0).title());
        assertEquals(genre.getCreatedDate(), genreResponseDTO.createdDate());
        assertEquals(genre.getUpdatedDate(), genreResponseDTO.updatedDate());
    }
}
