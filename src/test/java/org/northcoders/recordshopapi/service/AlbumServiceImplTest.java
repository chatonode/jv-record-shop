package org.northcoders.recordshopapi.service;

import java.util.*;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.mapper.response.AlbumResponseMapper;
import org.northcoders.recordshopapi.model.Currency;
import org.northcoders.recordshopapi.repository.ArtistRepository;
import org.northcoders.recordshopapi.repository.GenreRepository;
import org.northcoders.recordshopapi.mapper.request.album.AlbumCreateMapper;
import org.northcoders.recordshopapi.util.service.TestEntityFactory;
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
class AlbumServiceImplTest {

    @Mock
    private AlbumRepository albumRepository;

    @Mock
    private ArtistRepository artistRepository;

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    private AlbumServiceImpl albumService;

    private TestEntityFactory tef;

    @BeforeEach
    void setUp() {
        tef = new TestEntityFactory();
        tef.initialiseAllEntities();

    }

//    @Test
//    void createAlbum_ShouldReturnSavedAlbum_WhenAttributesAreValid() {
//        Date createdUpdatedDate = new Date();
//
//        tef.elvisPresley.setId(9L);
//        tef.elvisPresley.setCreatedDate(createdUpdatedDate);
//        tef.elvisPresley.setUpdatedDate(createdUpdatedDate);
//
//        tef.rockRoll.setId(8L);
//        tef.rockRoll.setCreatedDate(createdUpdatedDate);
//        tef.rockRoll.setUpdatedDate(createdUpdatedDate);
//
//        tef.country.setId(9L);
//        tef.country.setCreatedDate(createdUpdatedDate);
//        tef.country.setUpdatedDate(createdUpdatedDate);
//
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
//        System.out.println("expectedAlbum: " + expectedAlbum);
//
//        when(artistRepository.findById(9L)).thenReturn(Optional.of(tef.elvisPresley));
//        when(genreRepository.findById(8L)).thenReturn(Optional.of(tef.rockRoll));
//        when(genreRepository.findById(9L)).thenReturn(Optional.of(tef.country));
//        when(albumRepository.save(expectedAlbum)).thenReturn(expectedAlbum);
////                .thenAnswer(invocationOnMock -> {
////            Date createdUpdatedDate = new Date();
////
////            Album album = invocationOnMock.getArgument(0);
////            album.setId(9L);
////            album.setQuantityInStock(0);
////            album.setCreatedDate(createdUpdatedDate);
////            album.setUpdatedDate(createdUpdatedDate);
////
////            System.out.println("invocated album: " + album);
////            return album;
////        });
//
//        System.out.println("albumCreateDTO: " + albumCreateDTO);
//
//        AlbumResponseDTO createdAlbumDto = albumService.createAlbum(albumCreateDTO);
//
//        // After creation, let new album has (null) attributes defined
//        expectedAlbum.setId(9L);
//        expectedAlbum.setQuantityInStock(0);
//        expectedAlbum.setCreatedDate(createdUpdatedDate);
//        expectedAlbum.setUpdatedDate(createdUpdatedDate);
//
//        System.out.println(createdAlbumDto);
//
//        assertNotNull(createdAlbumDto);
//        verify(genreRepository).findById(tef.rockRoll.getId());
//        verify(genreRepository).findById(tef.country.getId());
//        verify(artistRepository).findById(tef.elvisPresley.getId());
//        verify(albumRepository).save(expectedAlbum);
//
//        assertEquals(10L, createdAlbumDto.id());
//        assertEquals(albumCreateDTO.getTitle(), createdAlbumDto.title());
//        assertEquals(1, createdAlbumDto.artists().size());
//        assertEquals(tef.elvisPresley.getFullName(), createdAlbumDto.artists().stream().toList().getFirst().fullName());
//        assertEquals(2, createdAlbumDto.genres().size());
//        assertEquals(tef.rockRoll.getName(), createdAlbumDto.genres().stream().toList().get(0).name());
//        assertEquals(tef.country, createdAlbumDto.genres().stream().toList().get(1));
//        assertEquals(new HashSet<>(genres), createdAlbumDto.genres());
//        assertEquals(albumCreateDTO.getDurationInSeconds(), createdAlbumDto.durationInSeconds());
//        assertEquals(albumCreateDTO.getImageUrl(), createdAlbumDto.imageUrl());
//        assertEquals(albumCreateDTO.getReleaseYear(), createdAlbumDto.releaseYear());
//        assertEquals(albumCreateDTO.getPublisher(), createdAlbumDto.publisher());
//        assertEquals(albumCreateDTO.getPriceInPences(), createdAlbumDto.priceInPences());
//        assertEquals(albumCreateDTO.getCurrency(), createdAlbumDto.currency());
//        assertEquals(albumCreateDTO.getFormat(), createdAlbumDto.format());
//        assertEquals(0, createdAlbumDto.quantityInStock());
//    }

    @Test
    void getAlbumByTitle_ShouldReturnAlbums_WhenAlbumsExistForGivenTitle() {
        when(albumRepository.findAllByTitle("Goodbye Yellow Brick Road")).thenReturn(List.of(tef.goodbyeYellowBrickRoad));

        List<AlbumResponseDTO> albums = albumService.getAlbumsByTitle("Goodbye Yellow Brick Road");

        assertNotNull(albums);
        assertEquals(1, albums.size());
        assertTrue(albums.contains(AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)));
    }

    @Test
    void getAlbumByTitle_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenTitle() {
        when(albumRepository.findAllByTitle("Nonexistent Album")).thenReturn(List.of());

        List<AlbumResponseDTO> albums = albumService.getAlbumsByTitle("Nonexistent Album");


        assertNotNull(albums);
        assertEquals(0, albums.size());
    }

    @Test
    void getAlbumById_ShouldReturnAlbum_WhenAlbumExists() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));

        AlbumResponseDTO foundAlbum = albumService.getAlbumById(1L);

        assertNotNull(foundAlbum);
        assertEquals(AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad), foundAlbum);
    }

    @Test
    void getAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.getAlbumById(1L));

        assertEquals("NotFound: Album", thrown.getMessage());
    }

    @Test
    void updateAlbum_ShouldReturnUpdatedAlbum_WhenAlbumExists() {
        AlbumUpdateDTO updateDTO = new AlbumUpdateDTO();
        updateDTO.setTitle("Updated Title");

        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
        when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

        assertNotNull(updatedAlbum);
        assertEquals("Updated Title", updatedAlbum.title());
        verify(albumRepository).save(any(Album.class));
    }

    @Test
    void updateAlbum_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
        AlbumUpdateDTO updateDTO = new AlbumUpdateDTO();
        updateDTO.setTitle("Updated Title");

        when(albumRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.updateAlbum(1L, updateDTO));

        assertEquals("NotFound: Album", thrown.getMessage());
        verify(albumRepository, never()).save(any(Album.class));
    }

    @Test
    void updateAlbum_ShouldThrowNotFoundException_WhenArtistDoesNotExist() {
        AlbumUpdateDTO updateDTO = new AlbumUpdateDTO();
        updateDTO.setArtistIds(List.of(1L));

        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
        when(artistRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.updateAlbum(1L, updateDTO));

        assertEquals("NotFound: Artist", thrown.getMessage());
        verify(albumRepository, never()).save(any(Album.class));
    }

    @Test
    void updateAlbum_ShouldThrowNotFoundException_WhenGenreDoesNotExist() {
        AlbumUpdateDTO updateDTO = new AlbumUpdateDTO();
        updateDTO.setGenreIds(List.of(1L));

        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
        when(genreRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.updateAlbum(1L, updateDTO));

        assertEquals("NotFound: Genre", thrown.getMessage());
        verify(albumRepository, never()).save(any(Album.class));
    }

    @Test
    void updateAlbum_ShouldUpdateAlbum_WhenArtistAndGenreArePresent() {
        AlbumUpdateDTO updateDTO = new AlbumUpdateDTO();
        updateDTO.setTitle("Updated Title");
        updateDTO.setArtistIds(List.of(1L));
        updateDTO.setGenreIds(List.of(1L));

        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
        when(artistRepository.findById(1L)).thenReturn(Optional.of(tef.eltonJohn));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(tef.pop));
        when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

        assertNotNull(updatedAlbum);
        assertEquals("Updated Title", updatedAlbum.title());
        assertEquals(1, updatedAlbum.artists().size());
        assertEquals(1, updatedAlbum.genres().size());
        verify(albumRepository).save(any(Album.class));
    }

    @Test
    void updateAlbum_ShouldHandleNullArtistsAndGenres() {
        AlbumUpdateDTO updateDTO = new AlbumUpdateDTO();
        updateDTO.setTitle("Updated Title");
        updateDTO.setArtistIds(null);
        updateDTO.setGenreIds(null);

        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
        when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

        AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

        assertNotNull(updatedAlbum);
        assertEquals("Updated Title", updatedAlbum.title());
        verify(albumRepository).save(any(Album.class));
    }

    @Test
    void deleteAlbumById_ShouldDeleteAlbum_WhenAlbumExists() {
        when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));

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
        when(albumRepository.findAll()).thenReturn(List.of(tef.goodbyeYellowBrickRoad, tef.heroes, tef.bad, tef.britney, tef.karma, tef.rayOfLight, tef.whenWeAllFallAsleep, tef.futureNostalgia));

        List<AlbumResponseDTO> albums = albumService.getAllAlbums();

        assertNotNull(albums);
        assertEquals(8, albums.size());
    }

    @Test
    void getAlbumsByReleaseYear_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenYear() {
        when(albumRepository.findAllByReleaseYear(2001)).thenReturn(List.of(tef.britney, tef.karma));

        List<AlbumResponseDTO> albums = albumService.getAlbumsByReleaseYear(2001);

        assertNotNull(albums);
        assertEquals(2, albums.size());
        assertTrue(albums.containsAll(List.of(AlbumResponseMapper.toDTO(tef.britney), AlbumResponseMapper.toDTO(tef.karma))));
    }

    @Test
    void getAlbumsByReleaseYear_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenYear() {
        when(albumRepository.findAllByReleaseYear(1952)).thenReturn(List.of());

        List<AlbumResponseDTO> albums = albumService.getAlbumsByReleaseYear(1952);

        assertNotNull(albums);
        assertEquals(0, albums.size());
    }

    @Test
    void getAlbumsByGenre_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenGenres() {
        Set<Genre> popGenres = Set.of(tef.pop);
        when(genreRepository.findByName(tef.pop.getName())).thenReturn(Optional.of(tef.pop));
        when(albumRepository.findAllByGenreSet(popGenres)).thenReturn(List.of(tef.britney, tef.karma, tef.whenWeAllFallAsleep, tef.rayOfLight, tef.futureNostalgia, tef.bad, tef.goodbyeYellowBrickRoad));

        List<AlbumResponseDTO> albums = albumService.getAlbumsByGenre(GenreType.POP);

        assertNotNull(albums);
        assertEquals(7, albums.size());
        assertTrue(albums.containsAll(List.of(
                AlbumResponseMapper.toDTO(tef.britney),
                AlbumResponseMapper.toDTO(tef.karma),
                AlbumResponseMapper.toDTO(tef.whenWeAllFallAsleep),
                AlbumResponseMapper.toDTO(tef.rayOfLight),
                AlbumResponseMapper.toDTO(tef.futureNostalgia),
                AlbumResponseMapper.toDTO(tef.bad),
                AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)))
        );
    }

    @Test
    void getAlbumsByGenre_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenGenres() {
        Set<Genre> jazzGenres = Set.of(tef.jazz);
        when(genreRepository.findByName(tef.jazz.getName())).thenReturn(Optional.of(tef.jazz));
        when(albumRepository.findAllByGenreSet(jazzGenres)).thenReturn(List.of());

        List<AlbumResponseDTO> albums = albumService.getAlbumsByGenre(GenreType.JAZZ);

        assertNotNull(albums);
        assertEquals(0, albums.size());
        assertTrue(albums.containsAll(List.of()));
    }

    @Test
    void getAlbumsByFormat_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenFormat() {
        when(albumRepository.findAllByFormat(Format.CD)).thenReturn(List.of(tef.goodbyeYellowBrickRoad, tef.bad, tef.rayOfLight, tef.futureNostalgia));

        List<AlbumResponseDTO> albums = albumService.getAlbumsByFormat(Format.CD);

        assertNotNull(albums);
        assertEquals(4, albums.size());
        assertTrue(albums.containsAll(List.of(
                AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad),
                AlbumResponseMapper.toDTO(tef.bad),
                AlbumResponseMapper.toDTO(tef.rayOfLight),
                AlbumResponseMapper.toDTO(tef.futureNostalgia)))
        );
    }

    @Test
    void getAlbumsByFormat_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenFormat() {
        when(albumRepository.findAllByFormat(Format.DVD)).thenReturn(List.of());

        List<AlbumResponseDTO> albums = albumService.getAlbumsByFormat(Format.CD);

        assertNotNull(albums);
        assertEquals(0, albums.size());
    }


}
