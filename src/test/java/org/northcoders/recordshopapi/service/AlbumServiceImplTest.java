package org.northcoders.recordshopapi.service;

import java.util.*;

import org.northcoders.recordshopapi.model.Currency;
import org.northcoders.recordshopapi.repository.GenreRepository;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.northcoders.recordshopapi.exception.service.InvalidParameterException;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.model.*;
import org.northcoders.recordshopapi.repository.AlbumRepository;

@DataJpaTest
class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

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
    }

    @Test
    void createAlbum_ShouldReturnSavedAlbum_WhenIdIsNull() {
        when(albumRepository.save(goodbyeYellowBrickRoad)).thenReturn(goodbyeYellowBrickRoad);

        Album savedAlbum = albumService.createAlbum(goodbyeYellowBrickRoad);

        assertNotNull(savedAlbum);
        assertEquals(goodbyeYellowBrickRoad, savedAlbum);
        verify(albumRepository).save(goodbyeYellowBrickRoad);
    }

    @Test
    void createAlbum_ShouldThrowInvalidParameterException_WhenIdIsNotNull() {
        goodbyeYellowBrickRoad.setId(1L);

        InvalidParameterException thrown = assertThrows(InvalidParameterException.class, () -> albumService.createAlbum(goodbyeYellowBrickRoad));

        assertEquals("Invalid parameter '%s' provided for entity '%s'.".formatted("id", Album.class.getSimpleName()), thrown.getMessage());
    }

    @Test
    void getAlbumByTitle_ShouldReturnAlbums_WhenAlbumsExistForGivenTitle() {
        when(albumRepository.findAllByTitle("Goodbye Yellow Brick Road")).thenReturn(List.of(goodbyeYellowBrickRoad));

        List<Album> albums = albumService.getAlbumsByTitle("Goodbye Yellow Brick Road");

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertTrue(albums.containsAll(List.of(goodbyeYellowBrickRoad)));
    }

    @Test
    void getAlbumByTitle_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenTitle() {
        when(albumRepository.findAllByTitle("Nonexistent Album")).thenReturn(List.of());

        List<Album> albums = albumService.getAlbumsByTitle("Nonexistent Album");


        assertNotNull(albums);
        assertEquals(0, albums.size());
    }

    @Test
    void getAlbumById_ShouldReturnAlbum_WhenAlbumExists() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(goodbyeYellowBrickRoad));

        Album foundAlbum = albumService.getAlbumById(1L);

        assertNotNull(foundAlbum);
        assertEquals(goodbyeYellowBrickRoad, foundAlbum);
    }

    @Test
    void getAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.getAlbumById(1L));

        assertEquals("Entity '%s' not found.".formatted(Album.class.getSimpleName()), thrown.getMessage());
    }

    @Test
    void replaceAlbum_ShouldReturnUpdatedAlbum_WhenAlbumExists() {
        Album updatedAlbum = goodbyeYellowBrickRoad;
        updatedAlbum.setTitle("Updated Title");
        when(albumRepository.findById(1L)).thenReturn(Optional.of(goodbyeYellowBrickRoad));
        when(albumRepository.save(updatedAlbum)).thenReturn(updatedAlbum);

        Album resultAlbum = albumService.replaceAlbum(1L, updatedAlbum);

        assertNotNull(resultAlbum);
        assertEquals("Updated Title", resultAlbum.getTitle());
        verify(albumRepository).save(updatedAlbum);
    }

    @Test
    void replaceAlbum_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.replaceAlbum(1L, goodbyeYellowBrickRoad));

        assertEquals("Entity '%s' not found.".formatted(Album.class.getSimpleName()), thrown.getMessage());
    }

    @Test
    void deleteAlbumById_ShouldDeleteAlbum_WhenAlbumExists() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(goodbyeYellowBrickRoad));

        albumService.deleteAlbumById(1L);

        verify(albumRepository).deleteById(1L);
    }

    @Test
    void deleteAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.deleteAlbumById(1L));

        assertEquals("Entity '%s' not found.".formatted(Album.class.getSimpleName()), thrown.getMessage());
    }

    @Test
    void getAllAlbums_ShouldReturnListOfAlbums() {
        when(albumRepository.findAll()).thenReturn(List.of(goodbyeYellowBrickRoad, heroes, bad, britney, karma, rayOfLight, whenWeAllFallAsleep, futureNostalgia));

        List<Album> albums = albumService.getAllAlbums();

        assertNotNull(albums);
        assertEquals(8, albums.size());
    }

    @Test
    void getAlbumsByReleaseYear_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenYear() {
        when(albumRepository.findAllByReleaseYear(2001)).thenReturn(List.of(britney, karma));

        List<Album> albums = albumService.getAlbumsByReleaseYear(2001);

        assertNotNull(albums);
        assertEquals(2, albums.size());
        assertTrue(albums.containsAll(List.of(britney, karma)));
    }

    @Test
    void getAlbumsByReleaseYear_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenYear() {
        when(albumRepository.findAllByReleaseYear(1952)).thenReturn(List.of());

        List<Album> albums = albumService.getAlbumsByReleaseYear(1952);

        assertNotNull(albums);
        assertEquals(0, albums.size());
    }

    @Test
    void getAlbumsByGenre_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenGenres() {
        Set<Genre> popGenres = Set.of(pop);
        when(albumRepository.findAllByGenreSet(popGenres)).thenReturn(List.of(britney, karma, whenWeAllFallAsleep, rayOfLight, futureNostalgia, bad, goodbyeYellowBrickRoad));

        List<Album> albums = albumService.getAlbumsByGenre(GenreType.POP);

        assertNotNull(albums);
        assertEquals(7, albums.size());
        assertTrue(albums.containsAll(List.of(britney, karma, whenWeAllFallAsleep, rayOfLight, futureNostalgia, bad, goodbyeYellowBrickRoad)));
    }

    @Test
    void getAlbumsByGenre_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenGenres() {
        Set<Genre> jazzGenres = Set.of(jazz);
        when(genreRepository.save(jazz)).thenReturn(jazz);
        when(albumRepository.findAllByGenreSet(jazzGenres)).thenReturn(List.of());

        List<Album> albums = albumService.getAlbumsByGenre(GenreType.POP);

        assertNotNull(albums);
        assertEquals(0, albums.size());
        assertTrue(albums.containsAll(List.of()));
    }

    @Test
    void getAlbumsByFormat_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenFormat() {
        when(albumRepository.findAllByFormat(Format.CD)).thenReturn(List.of(goodbyeYellowBrickRoad, bad, rayOfLight, futureNostalgia));

        List<Album> albums = albumService.getAlbumsByFormat(Format.CD);

        assertNotNull(albums);
        assertEquals(4, albums.size());
        assertTrue(albums.containsAll(List.of(goodbyeYellowBrickRoad, bad, rayOfLight, futureNostalgia)));
    }

    @Test
    void getAlbumsByFormat_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenFormat() {
        when(albumRepository.findAllByFormat(Format.DVD)).thenReturn(List.of());

        List<Album> albums = albumService.getAlbumsByFormat(Format.CD);

        assertNotNull(albums);
        assertEquals(0, albums.size());
    }
}
