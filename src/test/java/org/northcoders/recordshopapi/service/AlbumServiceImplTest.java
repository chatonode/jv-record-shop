//package org.northcoders.recordshopapi.service;
//
//import java.util.*;
//
//import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
//import org.northcoders.recordshopapi.model.Currency;
//import org.northcoders.recordshopapi.repository.ArtistRepository;
//import org.northcoders.recordshopapi.repository.GenreRepository;
//import org.northcoders.recordshopapi.mapper.request.album.AlbumCreateMapper;
//import org.northcoders.recordshopapi.util.TestEntityFactory;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//import org.northcoders.recordshopapi.exception.service.NotFoundException;
//import org.northcoders.recordshopapi.model.*;
//import org.northcoders.recordshopapi.repository.AlbumRepository;
//
//@DataJpaTest
//class AlbumServiceImplTest {
//
//    @Mock
//    private AlbumRepository albumRepository;
//
//    @Mock
//    private ArtistRepository artistRepository;
//
//    @Mock
//    GenreRepository genreRepository;
//
//    @InjectMocks
//    private AlbumServiceImpl albumService;
//
//    private TestEntityFactory tef;
//
//    @BeforeEach
//    void setUp() {
//        tef = new TestEntityFactory();
//        tef.initialiseAllEntities();
//    }
//
//    @Test
//    void createAlbum_ShouldReturnSavedAlbum_WhenAttributesAreValid() {
//        List<Long> artistIds = List.of(tef.elvisPresley.getId());
//        List<Long> genreIds = List.of(tef.rockRoll.getId(), tef.country.getId());
//
//        AlbumCreateDTO albumCreateDTO = AlbumCreateDTO.builder()
//                .title("Elvis Presley")
//                .artistIds(artistIds)
//                .genreIds(genreIds)
//                .durationInSeconds(2400) // Approx. 40 minutes
//                .imageUrl("https://upload.wikimedia.org/wikipedia/en/thumb/a/a8/Elvis_Presley_LPM-1254_Album_Cover.jpg/220px-Elvis_Presley_LPM-1254_Album_Cover.jpg")
//                .releaseYear(1956)
//                .format(Format.Vinyl) // 1950s format
//                .publisher("RCA Victor")
//                .priceInPences(1299) // £12.99
//                .currency(Currency.GBP)
//                .build();
//
//        List<Artist> artists = List.of(tef.elvisPresley);
//        List<Genre> genres = List.of(tef.rockRoll, tef.country);
//
//        Album expectedAlbum = AlbumCreateMapper.toEntity(albumCreateDTO, artists, genres);
//
//        when(artistRepository.findById(tef.elvisPresley.getId())).thenReturn(Optional.of(tef.elvisPresley));
//        when(genreRepository.findById(tef.rockRoll.getId())).thenReturn(Optional.of(tef.rockRoll));
//        when(genreRepository.findById(tef.country.getId())).thenReturn(Optional.of(tef.country));
//        when(albumRepository.save(expectedAlbum)).thenAnswer(invocationOnMock -> {
//            Album album = invocationOnMock.getArgument(0);
//            album.setId(10L);
//            album.setQuantityInStock(0);
//            return album;
//        });
//
//        Album createdAlbum = albumService.createAlbum(albumCreateDTO);
//
//        System.out.println(createdAlbum);
//
//        assertNotNull(createdAlbum);
//        verify(genreRepository).findById(tef.rockRoll.getId());
//        verify(genreRepository).findById(tef.country.getId());
//        verify(artistRepository).findById(tef.elvisPresley.getId());
//        verify(albumRepository).save(createdAlbum);
//
//        assertEquals(10L, createdAlbum.getId());
//        assertEquals(albumCreateDTO.getTitle(), createdAlbum.getTitle());
//        assertEquals(1, createdAlbum.getArtistSet().size());
//        assertEquals(tef.elvisPresley, createdAlbum.getArtistSet().stream().toList().getFirst());
//        assertEquals(2, createdAlbum.getGenreSet().size());
//        assertEquals(tef.rockRoll, createdAlbum.getGenreSet().stream().toList().get(0));
//        assertEquals(tef.country, createdAlbum.getGenreSet().stream().toList().get(1));
//        assertEquals(new HashSet<>(genres), createdAlbum.getGenreSet());
//        assertEquals(albumCreateDTO.getDurationInSeconds(), createdAlbum.getDurationInSeconds());
//        assertEquals(albumCreateDTO.getImageUrl(), createdAlbum.getImageUrl());
//        assertEquals(albumCreateDTO.getReleaseYear(), createdAlbum.getReleaseYear());
//        assertEquals(albumCreateDTO.getPublisher(), createdAlbum.getPublisher());
//        assertEquals(albumCreateDTO.getPriceInPences(), createdAlbum.getPriceInPences());
//        assertEquals(albumCreateDTO.getCurrency(), createdAlbum.getCurrency());
//        assertEquals(albumCreateDTO.getFormat(), createdAlbum.getFormat());
//        assertEquals(0, createdAlbum.getQuantityInStock());
//    }
//
//    @Test
//    void getAlbumByTitle_ShouldReturnAlbums_WhenAlbumsExistForGivenTitle() {
//        when(albumRepository.findAllByTitle("Goodbye Yellow Brick Road")).thenReturn(List.of(tef.goodbyeYellowBrickRoad));
//
//        List<Album> albums = albumService.getAlbumsByTitle("Goodbye Yellow Brick Road");
//
//        assertNotNull(albums);
//        assertEquals(1, albums.size());
//        assertTrue(albums.containsAll(List.of(tef.goodbyeYellowBrickRoad)));
//    }
//
//    @Test
//    void getAlbumByTitle_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenTitle() {
//        when(albumRepository.findAllByTitle("Nonexistent Album")).thenReturn(List.of());
//
//        List<Album> albums = albumService.getAlbumsByTitle("Nonexistent Album");
//
//
//        assertNotNull(albums);
//        assertEquals(0, albums.size());
//    }
//
//    @Test
//    void getAlbumById_ShouldReturnAlbum_WhenAlbumExists() {
//        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
//
//        Album foundAlbum = albumService.getAlbumById(1L);
//
//        assertNotNull(foundAlbum);
//        assertEquals(tef.goodbyeYellowBrickRoad, foundAlbum);
//    }
//
//    @Test
//    void getAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
//        when(albumRepository.findById(1L)).thenReturn(Optional.empty());
//
//        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.getAlbumById(1L));
//
//        assertEquals("NotFound: Album", thrown.getMessage());
//    }
//
//    @Test
//    void replaceAlbum_ShouldReturnUpdatedAlbum_WhenAlbumExists() {
//        Album updatedAlbum = tef.goodbyeYellowBrickRoad;
//        updatedAlbum.setId(null);
//        updatedAlbum.setTitle("Updated Title");
//        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
//        when(albumRepository.save(updatedAlbum)).thenReturn(updatedAlbum);
//
//        Album resultAlbum = albumService.replaceAlbum(1L, updatedAlbum);
//
//        assertNotNull(resultAlbum);
//        assertEquals("Updated Title", resultAlbum.getTitle());
//        verify(albumRepository).save(updatedAlbum);
//    }
//
//    @Test
//    void replaceAlbum_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
//        when(albumRepository.findById(1L)).thenReturn(Optional.empty());
//
//        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.replaceAlbum(1L, tef.goodbyeYellowBrickRoad));
//
//        assertEquals("NotFound: Album", thrown.getMessage());
//    }
//
//    @Test
//    void deleteAlbumById_ShouldDeleteAlbum_WhenAlbumExists() {
//        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
//
//        albumService.deleteAlbumById(1L);
//
//        verify(albumRepository).deleteById(1L);
//    }
//
//    @Test
//    void deleteAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
//        when(albumRepository.findById(1L)).thenReturn(Optional.empty());
//
//        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.deleteAlbumById(1L));
//
//        assertEquals("NotFound: Album", thrown.getMessage());
//    }
//
//    @Test
//    void getAllAlbums_ShouldReturnListOfAlbums() {
//        when(albumRepository.findAll()).thenReturn(List.of(tef.goodbyeYellowBrickRoad, tef.heroes, tef.bad, tef.britney, tef.karma, tef.rayOfLight, tef.whenWeAllFallAsleep, tef.futureNostalgia));
//
//        List<Album> albums = albumService.getAllAlbums();
//
//        assertNotNull(albums);
//        assertEquals(8, albums.size());
//    }
//
//    @Test
//    void getAlbumsByReleaseYear_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenYear() {
//        when(albumRepository.findAllByReleaseYear(2001)).thenReturn(List.of(tef.britney, tef.karma));
//
//        List<Album> albums = albumService.getAlbumsByReleaseYear(2001);
//
//        assertNotNull(albums);
//        assertEquals(2, albums.size());
//        assertTrue(albums.containsAll(List.of(tef.britney, tef.karma)));
//    }
//
//    @Test
//    void getAlbumsByReleaseYear_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenYear() {
//        when(albumRepository.findAllByReleaseYear(1952)).thenReturn(List.of());
//
//        List<Album> albums = albumService.getAlbumsByReleaseYear(1952);
//
//        assertNotNull(albums);
//        assertEquals(0, albums.size());
//    }
//
//    @Test
//    void getAlbumsByGenre_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenGenres() {
//        Set<Genre> popGenres = Set.of(tef.pop);
//        when(genreRepository.findByName(tef.pop.getName())).thenReturn(Optional.of(tef.pop));
//        when(albumRepository.findAllByGenreSet(popGenres)).thenReturn(List.of(tef.britney, tef.karma, tef.whenWeAllFallAsleep, tef.rayOfLight, tef.futureNostalgia, tef.bad, tef.goodbyeYellowBrickRoad));
//
//        List<Album> albums = albumService.getAlbumsByGenre(GenreType.POP);
//
//        assertNotNull(albums);
//        assertEquals(7, albums.size());
//        assertTrue(albums.containsAll(List.of(tef.britney, tef.karma, tef.whenWeAllFallAsleep, tef.rayOfLight, tef.futureNostalgia, tef.bad, tef.goodbyeYellowBrickRoad)));
//    }
//
//    @Test
//    void getAlbumsByGenre_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenGenres() {
//        Set<Genre> jazzGenres = Set.of(tef.jazz);
//        when(genreRepository.findByName(tef.jazz.getName())).thenReturn(Optional.of(tef.jazz));
//        when(albumRepository.findAllByGenreSet(jazzGenres)).thenReturn(List.of());
//
//        List<Album> albums = albumService.getAlbumsByGenre(GenreType.JAZZ);
//
//        assertNotNull(albums);
//        assertEquals(0, albums.size());
//        assertTrue(albums.containsAll(List.of()));
//    }
//
//    @Test
//    void getAlbumsByFormat_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenFormat() {
//        when(albumRepository.findAllByFormat(Format.CD)).thenReturn(List.of(tef.goodbyeYellowBrickRoad, tef.bad, tef.rayOfLight, tef.futureNostalgia));
//
//        List<Album> albums = albumService.getAlbumsByFormat(Format.CD);
//
//        assertNotNull(albums);
//        assertEquals(4, albums.size());
//        assertTrue(albums.containsAll(List.of(tef.goodbyeYellowBrickRoad, tef.bad, tef.rayOfLight, tef.futureNostalgia)));
//    }
//
//    @Test
//    void getAlbumsByFormat_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenFormat() {
//        when(albumRepository.findAllByFormat(Format.DVD)).thenReturn(List.of());
//
//        List<Album> albums = albumService.getAlbumsByFormat(Format.CD);
//
//        assertNotNull(albums);
//        assertEquals(0, albums.size());
//    }
//}
