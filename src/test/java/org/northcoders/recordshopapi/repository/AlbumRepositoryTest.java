package org.northcoders.recordshopapi.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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

    private Genre rock, pop, dancePop, jazz, electronic, funk, world;

    private Artist eltonJohn, davidBowie, michaelJackson, britneySpears, tarkan, madonna, billieEilish, duaLipa;

    private Album goodbyeYellowBrickRoad, heroes, bad, britney, karma, rayOfLight, whenWeAllFallAsleep, futureNostalgia;

    void initialiseGenres() {
        rock = Genre.builder()
                .name(GenreType.ROCK)
                .build();
        pop = Genre.builder()
                .name(GenreType.POP)
                .build();
        dancePop = Genre.builder()
                .name(GenreType.DANCE_POP)
                .build();
        jazz = Genre.builder()
                .name(GenreType.JAZZ)
                .build();
        electronic = Genre.builder()
                .name(GenreType.ELECTRONIC)
                .build();
        funk = Genre.builder()
                .name(GenreType.FUNK)
                .build();
        world = Genre.builder()
                .name(GenreType.WORLD)
                .build();
    }

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
                .genres(List.of(rock, pop))
                .durationInSeconds(4000) // Approx. 66 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/8/86/Elton_John_-_Goodbye_Yellow_Brick_Road.jpg")
                .releaseYear(1973)
                .format(Format.CD)
                .publisher("DJM Records")
                .priceInPences(1999) // £19.99
                .currency(Currency.GBP)
                .build();

        heroes = Album.builder()
                .title("Heroes")
                .artists(List.of(davidBowie))
                .genres(List.of(rock, electronic))
                .durationInSeconds(2600) // Approx. 43 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/7/7b/David_Bowie_-_Heroes.png")
                .releaseYear(1977)
                .format(Format.Vinyl)
                .publisher("RCA Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();

        bad = Album.builder()
                .title("Bad")
                .artists(List.of(michaelJackson))
                .genres(List.of(pop, funk))
                .durationInSeconds(3200) // Approx. 53 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png")
                .releaseYear(1987)
                .format(Format.CD)
                .publisher("Epic Records")
                .priceInPences(1899) // £18.99
                .currency(Currency.GBP)
                .build();

        britney = Album.builder()
                .title("Britney")
                .artists(List.of(britneySpears))
                .genres(List.of(pop))
                .durationInSeconds(2600) // Approx. 43 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/0/0c/Britney_Spears_-_Britney.png")
                .releaseYear(2001)
                .format(Format.Cassette)
                .publisher("Jive Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();

        karma = Album.builder()
                .title("Karma")
                .artists(List.of(tarkan))
                .genres(List.of(pop, world))
                .durationInSeconds(3100) // Approx. 51 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/1/11/Tarkan_-_Karma_%28Tarkan_album%29.jpg")
                .releaseYear(2001)
                .format(Format.Vinyl)
                .publisher("Universal Music Turkey")
                .priceInPences(1499) // £14.99
                .currency(Currency.GBP)
                .build();

        rayOfLight = Album.builder()
                .title("Ray of Light")
                .artists(List.of(madonna))
                .genres(List.of(pop, electronic))
                .durationInSeconds(3200) // Approx. 53 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/d/dd/Ray_of_Light_Madonna.png")
                .releaseYear(1998)
                .format(Format.CD)
                .publisher("Warner Bros. Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();

        whenWeAllFallAsleep = Album.builder()
                .title("When We All Fall Asleep, Where Do We Go?")
                .artists(List.of(billieEilish))
                .genres(List.of(pop))
                .durationInSeconds(2600) // Approx. 43 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/3/38/When_We_All_Fall_Asleep%2C_Where_Do_We_Go%3F.png")
                .releaseYear(2019)
                .format(Format.Vinyl)
                .publisher("Interscope Records")
                .priceInPences(1499) // £14.99
                .currency(Currency.GBP)
                .build();

        futureNostalgia = Album.builder()
                .title("Future Nostalgia")
                .artists(List.of(duaLipa))
                .genres(List.of(pop, dancePop))
                .durationInSeconds(2300) // Approx. 38 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/f/f5/Dua_Lipa_-_Future_Nostalgia_%28Official_Album_Cover%29.png")
                .releaseYear(2020)
                .format(Format.CD)
                .publisher("Warner Records")
                .priceInPences(1599) // £15.99
                .currency(Currency.GBP)
                .build();

    }

    @BeforeEach
    void setUp() {
        this.initialiseGenres();
        this.initialiseArtists();
        this.initialiseAlbums();


        // Saves all nested artists and genres altogether thanks to CascadeType.PERSIST
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
        Album expectedAlbum = goodbyeYellowBrickRoad;
        Optional<Album> actualAlbum = albumRepository.findByTitle("Goodbye Yellow Brick Road");
        assertFalse(actualAlbum.isEmpty());
        assertEquals(expectedAlbum, actualAlbum.get());
    }

    @Test
    void findByTitle_ReturnNullAlbum() {
        Optional<Album> actualAlbum = albumRepository.findByTitle("Nonexistent Album");
        assertTrue(actualAlbum.isEmpty());
    }

    @Test
    void findAllByGenreSet_ReturnsAlbums() {
        Set<Genre> popGenreSet = Set.of(pop);
        List<Album> expectedAlbums = List.of(britney, karma, whenWeAllFallAsleep, rayOfLight, futureNostalgia, bad, goodbyeYellowBrickRoad);

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(popGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.containsAll(expectedAlbums));
    }

    @Test
    void findAllByGenreSet_ReturnsEmptyAlbums() {
        Set<Genre> nonexistentGenreSet = Set.of(jazz);
        // since it is not saved within an album
        //   CascadeType.PERSIST or any other CascadeType won't work.
        //   So, we need to save it manually before actually searching with it.
        genreRepository.save(jazz);

        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(nonexistentGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.isEmpty());
    }

    @Test
    void findAllByGenreSet_EmptyGenreSet_ReturnsEmptyAlbums() {
        Set<Genre> emptyGenreSet = Set.of();
        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByGenreSet(emptyGenreSet);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
    }

    @Test
    void findAllByGenreSet_EmptyGenreSet_ReturnsEmptyAlbumsWhenNoAlbumsExist() {
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
    void findAllByFormat_ReturnsAlbums() {
        Format cdFormat = Format.CD;
        List<Album> expectedAlbums = List.of(goodbyeYellowBrickRoad, bad, rayOfLight, futureNostalgia);

        List<Album> actualAlbums = albumRepository.findAllByFormat(cdFormat);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.containsAll(expectedAlbums));
    }

    @Test
    void findAllByFormat_ReturnsEmptyAlbums() {
        Format nonexistentFormat = Format.DVD;
        List<Album> expectedAlbums = List.of();

        List<Album> actualAlbums = albumRepository.findAllByFormat(nonexistentFormat);

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());
        assertTrue(actualAlbums.isEmpty());
    }


}