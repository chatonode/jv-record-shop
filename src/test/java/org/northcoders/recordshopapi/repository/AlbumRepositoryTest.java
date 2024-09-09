package org.northcoders.recordshopapi.repository;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.util.TestEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.northcoders.recordshopapi.model.*;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private GenreRepository genreRepository;

    private TestEntityFactory tef;

    @BeforeEach
    void setUp() {
        tef = new TestEntityFactory();
        tef.initialiseAllEntities();

        // Saves all nested artists and genres altogether thanks to CascadeType.ALL
        albumRepository.saveAll(List.of(
                tef.goodbyeYellowBrickRoad, tef.heroes, tef.bad, tef.britney, tef.karma, tef.rayOfLight, tef.whenWeAllFallAsleep, tef.futureNostalgia
        ));
    }

    @AfterEach
    void tearDown() {
        albumRepository.deleteAll();
    }

    @Test
    void save_setsNullAttributesToValues() {
        Album album = Album.builder()
                .title("Elvis Presley")
                .artists(List.of(tef.elvisPresley)) // Predefined artist object
                .genres(List.of(tef.rockRoll, tef.country)) // Predefined genres
                .durationInSeconds(2400) // Approx. 40 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/a/a8/Elvis_Presley_LPM-1254_Album_Cover.jpg/220px-Elvis_Presley_LPM-1254_Album_Cover.jpg")
                .releaseYear(1956)
                .format(Format.Vinyl) // 1950s format
                .publisher("RCA Victor")
                .priceInPences(1299) // £12.99
                .currency(Currency.GBP)
                .build();

        Album actualAlbum = albumRepository.save(album);

        assertNotNull(actualAlbum);

        assertNotEquals(album.getId(), actualAlbum.getId());
        assertNotEquals(null, actualAlbum.getId());
        assertNotEquals(tef.currentAlbumId.get(), actualAlbum.getId());
        assertEquals(tef.currentAlbumId.intValue() + 1, actualAlbum.getId().intValue());

        assertNotEquals(album.getCreatedDate(), actualAlbum.getCreatedDate());
        assertNotEquals(null, actualAlbum.getCreatedDate());
//        assertEquals(1, actualAlbum.getCreatedDate()); // TODO
    }

    @Test
    void findAllByReleaseYear_ShouldReturnAlbums_WhenAlbumsExistForGivenYear() {
        List<Album> expectedAlbumsFrom2001 = List.of(tef.britney, tef.karma);

        List<Album> actualAlbumsFrom2001 = albumRepository.findAllByReleaseYear(2001);

        assertNotNull(actualAlbumsFrom2001);
        assertEquals(expectedAlbumsFrom2001.size(), actualAlbumsFrom2001.size());
        assertEquals(expectedAlbumsFrom2001, actualAlbumsFrom2001);
    }

    @Test
    void findAllByReleaseYear_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenYear() {
        List<Album> expectedAlbumsFrom1975 = List.of();

        List<Album> actualAlbumsFrom1975 = albumRepository.findAllByReleaseYear(1975);
        assertNotNull(actualAlbumsFrom1975);
        assertEquals(expectedAlbumsFrom1975.size(), actualAlbumsFrom1975.size());
        assertEquals(expectedAlbumsFrom1975, actualAlbumsFrom1975);
    }

    @Test
    void findAllByTitle_ShouldReturnAlbums_WhenAlbumExists() {
        List<Album> expectedAlbums = List.of(tef.goodbyeYellowBrickRoad);


        List<Album> actualAlbums = albumRepository.findAllByTitle("Goodbye Yellow Brick Road");

        assertNotNull(expectedAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertEquals(expectedAlbums, actualAlbums);
    }

    @Test
    void findAllByTitle_ShouldReturnEmptyAlbums_WhenAlbumDoesNotExist() {
        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByTitle("Nonexistent Album");
        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertEquals(expectedAlbums, actualAlbums);
    }

    @Test
    void findAllByGenreSet_ShouldReturnAlbums_WhenAlbumsExistForGivenGenres() {
        Set<Genre> popGenreSet = Set.of(tef.pop);
        List<Album> expectedAlbums = List.of(tef.britney, tef.karma, tef.whenWeAllFallAsleep, tef.rayOfLight, tef.futureNostalgia, tef.bad, tef.goodbyeYellowBrickRoad);

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(popGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.containsAll(expectedAlbums));
    }

    @Test
    void findAllByGenreSet_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenGenres() {
        Set<Genre> nonexistentGenreSet = Set.of(tef.jazz);
        // since it is not saved within an album
        //   CascadeType.PERSIST or any other CascadeType won't work.
        //   So, we need to save it manually before actually searching with it.
        genreRepository.save(tef.jazz);

        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(nonexistentGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.isEmpty());
    }

    @Test
    void findAllByGenreSet_ShouldReturnEmptyAlbums_WhenGenreSetIsEmpty() {
        Set<Genre> emptyGenreSet = Set.of();
        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(emptyGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
    }

    @Test
    void findAllByGenreSet_ShouldReturnEmptyAlbums_WhenNoAlbumsExistAndGenreSetIsEmpty() {
        // Clear out the albums
        albumRepository.deleteAll();
        Set<Genre> emptyGenreSet = Set.of();

        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(emptyGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.isEmpty());
    }

    @Test
    void findAllByFormat_ShouldReturnAlbums_WhenAlbumsExistForGivenFormat() {
        Format cdFormat = Format.CD;
        List<Album> expectedAlbums = List.of(tef.goodbyeYellowBrickRoad, tef.bad, tef.rayOfLight, tef.futureNostalgia);

        List<Album> actualAlbums = albumRepository.findAllByFormat(cdFormat);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.containsAll(expectedAlbums));
    }

    @Test
    void findAllByFormat_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenFormat() {
        Format nonexistentFormat = Format.DVD;
        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByFormat(nonexistentFormat);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.isEmpty());
    }

}