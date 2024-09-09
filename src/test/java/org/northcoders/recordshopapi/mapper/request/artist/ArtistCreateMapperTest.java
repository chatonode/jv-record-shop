package org.northcoders.recordshopapi.mapper.request.artist;

import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.request.artist.ArtistCreateDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArtistCreateMapperTest {

    @Test
    void toEntity_mapsArtistCreateDTOToArtist_WhenNoAlbumIsIncluded() {
        // Arrange
        ArtistCreateDTO artistCreateDTO = ArtistCreateDTO.builder()
                .fullName("Artist Name")
                .albumIds(List.of(1L))
                .build();

        // Convert empty
        List<Album> albums = List.of();

        // Act
        Artist artist = ArtistCreateMapper.toEntity(artistCreateDTO, albums);

        // Assert
        assertEquals(artistCreateDTO.getFullName(), artist.getFullName());
        assertEquals(albums, artist.getAlbumSet().stream().collect(Collectors.toList())); // Verify list conversion to Set
    }

    @Test
    void toEntity_mapsArtistCreateDTOToArtist_WhenOneAlbumIsIncluded() {
        // Arrange
        Album album = Album.builder()
                .title("Test Album")
                .artists(List.of())
                .genres(List.of())
                .build();
        album.setId(1L);
        album.setCreatedDate(new Date());
        album.setUpdatedDate(new Date());
        album.setQuantityInStock(10);

        ArtistCreateDTO artistCreateDTO = ArtistCreateDTO.builder()
                .fullName("Artist Name")
                .albumIds(List.of(1L))
                .build();

        // Convert IDs to actual Album entities
        List<Album> albums = List.of(album);

        // Act
        Artist artist = ArtistCreateMapper.toEntity(artistCreateDTO, albums);

        // Assert
        assertEquals(artistCreateDTO.getFullName(), artist.getFullName());
        assertEquals(albums, artist.getAlbumSet().stream().collect(Collectors.toList())); // Verify list conversion to Set
    }

    @Test
    void toEntity_mapsArtistCreateDTOToArtist_WhenMultipleAlbumsAreIncluded() {
        // Arrange
        Album album1 = Album.builder()
                .title("Test Album 1")
                .artists(List.of())
                .genres(List.of())
                .build();
        album1.setId(1L);
        album1.setCreatedDate(new Date());
        album1.setUpdatedDate(new Date());
        album1.setQuantityInStock(10);

        Album album2 = Album.builder()
                .title("Test Album 2")
                .artists(List.of())
                .genres(List.of())
                .build();
        album2.setId(2L);
        album2.setCreatedDate(new Date());
        album2.setUpdatedDate(new Date());
        album2.setQuantityInStock(3);

        ArtistCreateDTO artistCreateDTO = ArtistCreateDTO.builder()
                .fullName("Artist Name")
                .albumIds(List.of(1L))
                .build();

        // Convert IDs to actual Album entities
        List<Album> albums = List.of(album1, album2);

        // Act
        Artist artist = ArtistCreateMapper.toEntity(artistCreateDTO, albums);

        // Assert
        assertEquals(artistCreateDTO.getFullName(), artist.getFullName());
        assertTrue(artist.getAlbumSet().stream().collect(Collectors.toList()).contains(album1));
        assertTrue(artist.getAlbumSet().stream().collect(Collectors.toList()).contains(album2));
    }


}
