package org.northcoders.recordshopapi.mapper.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.response.success.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.List;
import java.util.Date;

class AlbumResponseMapperTest {
    @Test
    void toDTO_mapsAlbumEntityToAlbumResponseDTO() {
        // Arrange
        Artist artist = Artist.builder()
                .fullName("Artist Name")
                .albums(List.of())
                .build();
        artist.setId(1L);
        artist.setCreatedDate(new Date());
        artist.setUpdatedDate(new Date());

        Genre genre = Genre.builder()
                .name(GenreType.ROCK)
                .build();
        genre.setId(1L);
        genre.setCreatedDate(new Date());
        genre.setUpdatedDate(new Date());

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

        album.setId(1L);
        album.setQuantityInStock(10);
        album.setCreatedDate(new Date());
        album.setUpdatedDate(new Date());

        // Act: Call the toDTO method
        AlbumResponseDTO albumResponseDTO = AlbumResponseMapper.toDTO(album);

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