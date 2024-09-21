package org.northcoders.recordshopapi.mapper.response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.response.success.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.Date;
import java.util.List;

class ArtistResponseMapperTest {

    @Test
    void toDTO_mapsArtistEntityToArtistResponseDTO() {
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

        Artist artist = Artist.builder()
                .fullName("Artist Name")
                .albums(List.of(album))
                .build();
        artist.setId(1L);
        artist.setCreatedDate(new Date());
        artist.setUpdatedDate(new Date());

        // Act: Call the toDTO method
        ArtistResponseDTO artistResponseDTO = ArtistResponseMapper.toDTO(artist);

        // Assert: Verify that the mapping is correct
        assertEquals(artist.getId(), artistResponseDTO.id());
        assertEquals(artist.getFullName(), artistResponseDTO.fullName());
        assertEquals(1, artistResponseDTO.albums().size());
        assertEquals(1L, artistResponseDTO.albums().get(0).id());
        assertEquals("Test Album", artistResponseDTO.albums().get(0).title());
        assertEquals(artist.getCreatedDate(), artistResponseDTO.createdDate());
        assertEquals(artist.getUpdatedDate(), artistResponseDTO.updatedDate());
    }
}
