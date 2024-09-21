package org.northcoders.recordshopapi.mapper.request.album;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class AlbumUpdateMapperTest {

    private Album existingAlbum;
    private List<Artist> artists;
    private List<Genre> genres;

    @BeforeEach
    void setup() {
        Artist artistOne = Artist.builder()
                .fullName("Artist One")
                .albums(List.of())
                .build();
        artistOne.setId(1L);
        artistOne.setCreatedDate(new Date());
        artistOne.setUpdatedDate(new Date());

        Artist artistTwo = Artist.builder()
                .fullName("Artist Two")
                .albums(List.of())
                .build();
        artistTwo.setId(2L);
        artistTwo.setCreatedDate(new Date());
        artistTwo.setUpdatedDate(new Date());

        artists = List.of(
                artistOne,
                artistTwo
        );
        Genre rock = Genre.builder()
                .name(GenreType.ROCK)
                .build();
        rock.setId(1L);
        rock.setCreatedDate(new Date());
        rock.setUpdatedDate(new Date());

        Genre pop = Genre.builder()
                .name(GenreType.POP)
                .build();
        pop.setId(2L);
        pop.setCreatedDate(new Date());
        pop.setUpdatedDate(new Date());

        genres = List.of(rock, pop);


        // Mocking an existing album
        existingAlbum = Album.builder()
                .title("Old Title")
                .artists(artists)
                .genres(genres)
                .durationInSeconds(240)
                .imageUrl("https://old-image.url")
                .releaseYear(2000)
                .publisher("Old Publisher")
                .format(Format.Vinyl)
                .priceInPences(1500)
                .currency(Currency.GBP)
                .build();
        existingAlbum.setId(1L);
        existingAlbum.setCreatedDate(new Date());
        existingAlbum.setUpdatedDate(new Date());
        existingAlbum.setQuantityInStock(10);

        artists.forEach(artist -> artist.setAlbumSet(Set.of(existingAlbum)));
        genres.forEach(genre -> genre.setAlbumSet(Set.of(existingAlbum)));
    }

    @Test
    void testToEntity_UpdateTitleAndDurationOnly() {
        AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                .title("New Title")
                .durationInSeconds(300)
                .build();

        Album updatedAlbum = AlbumUpdateMapper.toEntity(existingAlbum, updateDTO, artists, genres);

        // Fields updated
        assertEquals("New Title", updatedAlbum.getTitle());
        assertEquals(300, updatedAlbum.getDurationInSeconds());

        // Fields unchanged
        assertEquals(existingAlbum.getReleaseYear(), updatedAlbum.getReleaseYear());
        assertEquals(existingAlbum.getPriceInPences(), updatedAlbum.getPriceInPences());
        assertEquals(existingAlbum.getArtistSet(), updatedAlbum.getArtistSet());
        assertEquals(existingAlbum.getGenreSet(), updatedAlbum.getGenreSet());
    }

    @Test
    void testToEntity_UpdateArtistsAndGenresOnly() {
        Artist artistThree = Artist.builder()
                .fullName("New Artist")
                .albums(List.of())
                .build();
        artistThree.setId(3L);
        artistThree.setCreatedDate(new Date());
        artistThree.setUpdatedDate(new Date());

        List<Artist> newArtists = List.of(artistThree);

        Genre jazz = Genre.builder()
                .name(GenreType.JAZZ)
                .build();
        jazz.setId(3L);
        jazz.setCreatedDate(new Date());
        jazz.setUpdatedDate(new Date());

        List<Genre> newGenres = List.of(jazz);


        AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                .artistIds(List.of(3L))
                .genreIds(List.of(3L))
                .build();

        Album updatedAlbum = AlbumUpdateMapper.toEntity(existingAlbum, updateDTO, newArtists, newGenres);

        // Artists updated
        assertNotNull(updatedAlbum.getArtistSet());
        assertNotEquals(artists.size(), updatedAlbum.getArtistSet().size());
        assertEquals(newArtists.size(), updatedAlbum.getArtistSet().size());
        artists.forEach(artist -> assertFalse(updatedAlbum.getArtistSet().contains(artist)));
        assertTrue(updatedAlbum.getArtistSet().contains(artistThree));

        // Genres updated
        assertNotNull(updatedAlbum.getGenreSet());
        assertNotEquals(genres.size(), updatedAlbum.getGenreSet().size());
        assertEquals(newGenres.size(), updatedAlbum.getGenreSet().size());
        genres.forEach(genre -> assertFalse(updatedAlbum.getGenreSet().contains(genre)));
        assertTrue(updatedAlbum.getGenreSet().contains(jazz));

        // Fields unchanged
        assertEquals(existingAlbum.getTitle(), updatedAlbum.getTitle());
        assertEquals(existingAlbum.getReleaseYear(), updatedAlbum.getReleaseYear());
        assertEquals(existingAlbum.getPriceInPences(), updatedAlbum.getPriceInPences());
    }

    @Test
    void testToEntity_UpdatePriceAndCurrencyOnly() {
        AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                .priceInPences(2500)
                .currency(Currency.EUR)
                .build();

        Album updatedAlbum = AlbumUpdateMapper.toEntity(existingAlbum, updateDTO, artists, genres);

        // Fields updated
        assertEquals(2500, updatedAlbum.getPriceInPences());
        assertEquals(Currency.EUR, updatedAlbum.getCurrency());

        // Fields unchanged
        assertEquals(existingAlbum.getTitle(), updatedAlbum.getTitle());
        assertEquals(existingAlbum.getArtistSet(), updatedAlbum.getArtistSet());
        assertEquals(existingAlbum.getReleaseYear(), updatedAlbum.getReleaseYear());
    }

    @Test
    void testToEntity_PartialUpdateDoesNotAffectUnchangedFields() {
        AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                .title("Partially Updated Title")
                .build();

        Album updatedAlbum = AlbumUpdateMapper.toEntity(existingAlbum, updateDTO, artists, genres);

        // Fields updated
        assertEquals("Partially Updated Title", updatedAlbum.getTitle());

        // Fields unchanged
        assertEquals(existingAlbum.getReleaseYear(), updatedAlbum.getReleaseYear());
        assertEquals(existingAlbum.getPriceInPences(), updatedAlbum.getPriceInPences());
        assertEquals(existingAlbum.getCurrency(), updatedAlbum.getCurrency());
    }

    @Test
    void testToEntity_NullValuesDoNotOverwriteExistingFields() {
        AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                .title(null)  // Explicitly setting to null
                .build();

        Album updatedAlbum = AlbumUpdateMapper.toEntity(existingAlbum, updateDTO, artists, genres);

        // Fields unchanged because title is null
        assertEquals(existingAlbum.getTitle(), updatedAlbum.getTitle());

        // Other fields remain unchanged
        assertEquals(existingAlbum.getReleaseYear(), updatedAlbum.getReleaseYear());
        assertEquals(existingAlbum.getPriceInPences(), updatedAlbum.getPriceInPences());
        assertEquals(existingAlbum.getArtistSet(), updatedAlbum.getArtistSet());
    }
}
