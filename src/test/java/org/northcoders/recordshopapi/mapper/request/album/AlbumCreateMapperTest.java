package org.northcoders.recordshopapi.mapper.request.album;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.model.Format;
import org.northcoders.recordshopapi.model.Currency;
import org.northcoders.recordshopapi.model.GenreType;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

class AlbumCreateMapperTest {

    @Test
    void toEntity_mapsAlbumCreateDTOToAlbum() {
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

        AlbumCreateDTO albumCreateDTO = AlbumCreateDTO.builder()
                .title("Test Album")
                .artistIds(List.of(1L))
                .genreIds(List.of(1L))
                .durationInSeconds(300)
                .imageUrl("http://example.com/image.jpg")
                .releaseYear(2022)
                .publisher("Test Publisher")
                .format(Format.Vinyl)
                .priceInPences(999)
                .currency(Currency.GBP)
                .build();

        // Convert IDs to actual entities
        List<Artist> artists = List.of(artist);
        List<Genre> genres = List.of(genre);

        // Act
        Album album = AlbumCreateMapper.toEntity(albumCreateDTO, artists, genres);

        // Assert
        assertEquals(albumCreateDTO.getTitle(), album.getTitle());
        assertEquals(artists, album.getArtistSet().stream().collect(Collectors.toList())); // Verify the list conversion
        assertEquals(genres, album.getGenreSet().stream().collect(Collectors.toList())); // Verify the list conversion
        assertEquals(albumCreateDTO.getDurationInSeconds(), album.getDurationInSeconds());
        assertEquals(albumCreateDTO.getImageUrl(), album.getImageUrl());
        assertEquals(albumCreateDTO.getReleaseYear(), album.getReleaseYear());
        assertEquals(albumCreateDTO.getPublisher(), album.getPublisher());
        assertEquals(albumCreateDTO.getFormat(), album.getFormat());
        assertEquals(albumCreateDTO.getPriceInPences(), album.getPriceInPences());
        assertEquals(albumCreateDTO.getCurrency(), album.getCurrency());
        assertEquals(Integer.valueOf(0), album.getQuantityInStock()); // Default value
    }
}
