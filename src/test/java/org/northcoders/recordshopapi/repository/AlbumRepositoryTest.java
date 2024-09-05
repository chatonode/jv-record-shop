package org.northcoders.recordshopapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
class AlbumRepositoryTest {

    @Autowired
    private AlbumRepository albumRepository;

    private Artist eltonJohn, davidBowie, michaelJackson, britneySpears, tarkan, madonna, billieEilish, duaLipa;

    private Album goodbyeYellowBrickRoad, heroes, bad, britney, karma, rayOfLight, whenWeAllFallAsleep, futureNostalgia;

    void initialiseArtists() {


        eltonJohn = Artist.builder()
                .fullName("Elton John")
                .albums(List.of())
                .build();
        davidBowie = Artist.builder()
                .fullName("David Bowie")
                .albums(List.of())
                .build();

        michaelJackson = Artist.builder()
                .fullName("Michael Jackson")
                .albums(List.of())
                .build();
        britneySpears = Artist.builder()
                .fullName("Britney Spears")
                .albums(List.of())
                .build();
        tarkan = Artist.builder()
                .fullName("Tarkan")
                .albums(List.of())
                .build();
        madonna = Artist.builder()
                .fullName("Madonna")
                .albums(List.of())
                .build();

        billieEilish = Artist.builder()
                .fullName("Billie Eilish")
                .albums(List.of())
                .build();

        duaLipa = Artist.builder()
                .fullName("Dua Lipa")
                .albums(List.of())
                .build();
    }

    void initialiseAlbums() {
        goodbyeYellowBrickRoad = Album.builder()
                .title("Goodbye Yellow Brick Road")
                .artists(List.of(eltonJohn))
                .genres(List.of(new Genre(GenreType.ROCK), new Genre(GenreType.POP)))
                .durationInSeconds(4000) // Approx. 66 minutes
                .releaseYear(1973)
                .format(Format.CD)
                .publisher("DJM Records")
                .priceInPences(1999) // £19.99
                .currency(Currency.GBP)
                .build();

        heroes = Album.builder()
                .title("Heroes")
                .artists(List.of(davidBowie))
                .genres(List.of(new Genre(GenreType.ROCK), new Genre(GenreType.ELECTRONIC)))
                .durationInSeconds(2600) // Approx. 43 minutes
                .releaseYear(1977)
                .format(Format.Vinyl)
                .publisher("RCA Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();

        bad = Album.builder()
                .title("Bad")
                .artists(List.of(michaelJackson))
                .genres(List.of(new Genre(GenreType.POP), new Genre(GenreType.FUNK)))
                .durationInSeconds(3200) // Approx. 53 minutes
                .releaseYear(1987)
                .format(Format.CD)
                .publisher("Epic Records")
                .priceInPences(1899) // £18.99
                .currency(Currency.GBP)
                .build();

        britney = Album.builder()
                .title("Britney")
                .artists(List.of(britneySpears))
                .genres(List.of(new Genre(GenreType.POP)))
                .durationInSeconds(2600) // Approx. 43 minutes
                .releaseYear(2001)
                .format(Format.Cassette)
                .publisher("Jive Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();

        karma = Album.builder()
                .title("Karma")
                .artists(List.of(tarkan))
                .genres(List.of(new Genre(GenreType.POP), new Genre(GenreType.WORLD)))
                .durationInSeconds(3100) // Approx. 51 minutes
                .releaseYear(2001)
                .format(Format.Vinyl)
                .publisher("Universal Music Turkey")
                .priceInPences(1499) // £14.99
                .currency(Currency.GBP)
                .build();

        rayOfLight = Album.builder()
                .title("Ray of Light")
                .artists(List.of(madonna))
                .genres(List.of(new Genre(GenreType.POP), new Genre(GenreType.ELECTRONIC)))
                .durationInSeconds(3200) // Approx. 53 minutes
                .releaseYear(1998)
                .format(Format.CD)
                .publisher("Warner Bros. Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();

        whenWeAllFallAsleep = Album.builder()
                .title("When We All Fall Asleep, Where Do We Go?")
                .artists(List.of(billieEilish))
                .genres(List.of(new Genre(GenreType.POP)))
                .durationInSeconds(2600) // Approx. 43 minutes
                .releaseYear(2019)
                .format(Format.Vinyl)
                .publisher("Interscope Records")
                .priceInPences(1499) // £14.99
                .currency(Currency.GBP)
                .build();

        futureNostalgia = Album.builder()
                .title("Future Nostalgia")
                .artists(List.of(duaLipa))
                .genres(List.of(new Genre(GenreType.POP), new Genre(GenreType.DANCE_POP)))
                .durationInSeconds(2300) // Approx. 38 minutes
                .releaseYear(2020)
                .format(Format.CD)
                .publisher("Warner Records")
                .priceInPences(1599) // £15.99
                .currency(Currency.GBP)
                .build();

    }

    @BeforeEach
    void setUp() {
        this.initialiseArtists();
        this.initialiseAlbums();

        albumRepository.saveAll(List.of(
                goodbyeYellowBrickRoad, heroes, bad, britney, karma, rayOfLight, whenWeAllFallAsleep, futureNostalgia
        ));
    }

    @Test
    void findAllByReleaseYear_ReturnsAlbums() {
        List<Album> expectedAlbumsFrom2001 = List.of(britney, karma);

        List<Album> actualAlbumsFrom2001 = albumRepository.findAllByReleaseYear(2001);

        assertNotNull(actualAlbumsFrom2001);
        assertEquals(expectedAlbumsFrom2001.size(), actualAlbumsFrom2001.size());
        assertEquals(expectedAlbumsFrom2001, actualAlbumsFrom2001);
    }

    @Test
    void findAllByReleaseYear_ReturnsEmptyAlbums() {
        List<Album> expectedAlbumsFrom1975 = List.of();

        List<Album> actualAlbumsFrom1975 = albumRepository.findAllByReleaseYear(1975);
        assertNotNull(actualAlbumsFrom1975);
        assertEquals(expectedAlbumsFrom1975.size(), actualAlbumsFrom1975.size());
        assertEquals(expectedAlbumsFrom1975, actualAlbumsFrom1975);
    }

    @Test
    void findByTitle_ReturnAlbum() {
        assertNotNull(null);
    }

    @Test
    void findByTitle_ReturnNullAlbum() {
        assertNotNull(null);
    }

    @Test
    void findAllByGenreSet_ReturnsAlbums() {
        assertNotNull(null);
    }

    @Test
    void findAllByGenreSet_ReturnsEmptyAlbums() {
        assertNotNull(null);
    }

    @Test
    void findAllByFormat_ReturnsAlbums() {
        assertNotNull(null);
    }

    @Test
    void findAllByFormat_ReturnsEmptyAlbums() {
        assertNotNull(null);
    }

}