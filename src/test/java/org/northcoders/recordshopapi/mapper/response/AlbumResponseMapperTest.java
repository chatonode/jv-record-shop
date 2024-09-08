package org.northcoders.recordshopapi.mapper.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Date;

@SpringBootTest
class AlbumResponseMapperTest {

    @Autowired
    private AlbumResponseMapper albumResponseMapper;

    @BeforeEach
    void setUp() {

    }

    @Test
    void toDTO_mapsAlbumEntityToAlbumResponseDTO() {
        // Arrange
        Artist artist = Artist.builder()
                .fullName("Artist Name")
                .albums(List.of())
                .build();
        artist.setId(1L);

        Genre genre = Genre.builder()
                .name(GenreType.ROCK)
                .build();
        genre.setId(1L);

        Album album = Album.builder()
                .title("Test Album")
                .artists(List.of(artist))
                .genres(List.of(genre))
                .durationInSeconds(300)
                .imageUrl("http://example.com/image.jpg")
                .releaseYear(2022)
                .publisher("Test Publisher")
                .priceInPences(999)
                .currency(Currency.GBP)
                .format(Format.Vinyl)
                .build();

        album.setId(1L);  // Set the ID after building
        album.setQuantityInStock(10);
        album.setCreatedDate(new Date());
        album.setUpdatedDate(new Date());

        // Act: Call the toDTO method
        AlbumResponseDTO albumResponseDTO = albumResponseMapper.toDTO(album);

        // Assert: Verify that the mapping is correct
        assertEquals(album.getId(), albumResponseDTO.id());
        assertEquals(album.getTitle(), albumResponseDTO.title());
        assertEquals(1, albumResponseDTO.artists().size());
        assertEquals("Artist Name", albumResponseDTO.artists().get(0).fullName());
        assertEquals(1, albumResponseDTO.genres().size());
        assertEquals(GenreType.ROCK.name(), albumResponseDTO.genres().get(0).name().name()); // Assuming GenreType is enum
        assertEquals(album.getDurationInSeconds(), albumResponseDTO.durationInSeconds());
        assertEquals(album.getPriceInPences(), albumResponseDTO.priceInPences());
        assertEquals(album.getCurrency(), albumResponseDTO.currency());
        assertEquals(album.getQuantityInStock(), albumResponseDTO.quantityInStock());
        assertEquals(album.getFormat(), albumResponseDTO.format());
    }
}