package org.northcoders.recordshopapi.service;

import java.util.*;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.model.Currency;
import org.northcoders.recordshopapi.repository.ArtistRepository;
import org.northcoders.recordshopapi.repository.GenreRepository;
import org.northcoders.recordshopapi.mapper.request.album.AlbumCreateMapper;
import org.northcoders.recordshopapi.util.TestDataFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.model.*;
import org.northcoders.recordshopapi.repository.AlbumRepository;

@DataJpaTest
class AlbumServiceImplTestDataFactory extends TestDataFactory {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    public AlbumServiceImplTestDataFactory() {
        super(); // To reach predefined sample data and their initialisers
    }

    @BeforeEach
    void setUp() {
        this.initialiseArtists();
        this.initialiseGenres();
        this.initialiseAlbums();
    }

    @Test
    void createAlbum_ShouldReturnSavedAlbum_WhenAttributesAreValid() {
        List<Long> artistIds = List.of(elvisPresley.getId());
        List<Long> genreIds = List.of(rockRoll.getId(), country.getId());

        AlbumCreateDTO albumCreateDTO = AlbumCreateDTO.builder()
                .title("Elvis Presley")
                .artistIds(artistIds)
                .genreIds(genreIds)
                .durationInSeconds(2400) // Approx. 40 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/a/a8/Elvis_Presley_LPM-1254_Album_Cover.jpg/220px-Elvis_Presley_LPM-1254_Album_Cover.jpg")
                .releaseYear(1956)
                .format(Format.Vinyl) // 1950s format
                .publisher("RCA Victor")
                .priceInPences(1299) // £12.99
                .currency(Currency.GBP)
                .build();

        List<Artist> artists = List.of(elvisPresley);
        List<Genre> genres = List.of(rockRoll, country);

        Album expectedAlbum = AlbumCreateMapper.toEntity(albumCreateDTO, artists, genres);

        when(artistRepository.findById(elvisPresley.getId())).thenReturn(Optional.of(elvisPresley));
        when(genreRepository.findById(rockRoll.getId())).thenReturn(Optional.of(rockRoll));
        when(genreRepository.findById(country.getId())).thenReturn(Optional.of(country));
        when(albumRepository.save(expectedAlbum)).thenAnswer(invocationOnMock -> {
            Album album = invocationOnMock.getArgument(0);
            album.setId(10L);
            album.setQuantityInStock(0);
            return album;
        });

        Album createdAlbum = albumService.createAlbum(albumCreateDTO);

        System.out.println(createdAlbum);

        assertNotNull(createdAlbum);
        verify(genreRepository).findById(rockRoll.getId());
        verify(genreRepository).findById(country.getId());
        verify(artistRepository).findById(elvisPresley.getId());
        verify(albumRepository).save(createdAlbum);

        assertEquals(10L, createdAlbum.getId());
        assertEquals(albumCreateDTO.getTitle(), createdAlbum.getTitle());
        assertEquals(1, createdAlbum.getArtistSet().size());
        assertEquals(elvisPresley, createdAlbum.getArtistSet().stream().toList().getFirst());
        assertEquals(2, createdAlbum.getGenreSet().size());
        assertEquals(rockRoll, createdAlbum.getGenreSet().stream().toList().get(0));
        assertEquals(country, createdAlbum.getGenreSet().stream().toList().get(1));
        assertEquals(new HashSet<>(genres), createdAlbum.getGenreSet());
        assertEquals(albumCreateDTO.getDurationInSeconds(), createdAlbum.getDurationInSeconds());
        assertEquals(albumCreateDTO.getImageUrl(), createdAlbum.getImageUrl());
        assertEquals(albumCreateDTO.getReleaseYear(), createdAlbum.getReleaseYear());
        assertEquals(albumCreateDTO.getPublisher(), createdAlbum.getPublisher());
        assertEquals(albumCreateDTO.getPriceInPences(), createdAlbum.getPriceInPences());
        assertEquals(albumCreateDTO.getCurrency(), createdAlbum.getCurrency());
        assertEquals(albumCreateDTO.getFormat(), createdAlbum.getFormat());
        assertEquals(0, createdAlbum.getQuantityInStock());
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

        assertEquals("NotFound: Album", thrown.getMessage());
    }

    @Test
    void replaceAlbum_ShouldReturnUpdatedAlbum_WhenAlbumExists() {
        Album updatedAlbum = goodbyeYellowBrickRoad;
        updatedAlbum.setId(null);
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

        assertEquals("NotFound: Album", thrown.getMessage());
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

        assertEquals("NotFound: Album", thrown.getMessage());
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
        when(genreRepository.findByName(pop.getName())).thenReturn(Optional.of(pop));
        when(albumRepository.findAllByGenreSet(popGenres)).thenReturn(List.of(britney, karma, whenWeAllFallAsleep, rayOfLight, futureNostalgia, bad, goodbyeYellowBrickRoad));

        List<Album> albums = albumService.getAlbumsByGenre(GenreType.POP);

        assertNotNull(albums);
        assertEquals(7, albums.size());
        assertTrue(albums.containsAll(List.of(britney, karma, whenWeAllFallAsleep, rayOfLight, futureNostalgia, bad, goodbyeYellowBrickRoad)));
    }

    @Test
    void getAlbumsByGenre_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenGenres() {
        Set<Genre> jazzGenres = Set.of(jazz);
        when(genreRepository.findByName(jazz.getName())).thenReturn(Optional.of(jazz));
        when(albumRepository.findAllByGenreSet(jazzGenres)).thenReturn(List.of());

        List<Album> albums = albumService.getAlbumsByGenre(GenreType.JAZZ);

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
