package org.northcoders.recordshopapi.service;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.dto.response.album.FlattenedArtistDTO;
import org.northcoders.recordshopapi.dto.response.album.FlattenedGenreDTO;
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
        tef.initializeAlbumResponseDTOs();

    }

    @AfterEach
    void tearDown() {
//        ...
    }


    @Nested
    class AlbumReadOps {
        @Test
        void getAllAlbums_ShouldReturnEmptyListOfAlbums_WhenNoAlbumExists() {
            when(albumRepository.findAll()).thenReturn(List.of());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                List<AlbumResponseDTO> albums = albumService.getAllAlbums();

                verify(albumRepository, times(1)).findAll();
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());

                assertNotNull(albums);
                assertEquals(0, albums.size());
            }
        }

        @Test
        void getAllAlbums_ShouldReturnListOfAlbums_WhenAlbumsExist() {
            when(albumRepository.findAll()).thenReturn(List.of(tef.goodbyeYellowBrickRoad, tef.heroes, tef.bad, tef.britney,
                    tef.karma, tef.rayOfLight, tef.whenWeAllFallAsleep, tef.futureNostalgia));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)).thenReturn(tef.goodbyeYellowBrickRoadResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.heroes)).thenReturn(tef.heroesResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.bad)).thenReturn(tef.badResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.britney)).thenReturn(tef.britneyResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.karma)).thenReturn(tef.karmaResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.rayOfLight)).thenReturn(tef.rayOfLightResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.whenWeAllFallAsleep)).thenReturn(tef.whenWeAllFallAsleepResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.futureNostalgia)).thenReturn(tef.futureNostalgiaResponseDTO);

                List<AlbumResponseDTO> albums = albumService.getAllAlbums();

                verify(albumRepository, times(1)).findAll();
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(8));

                assertNotNull(albums);
                assertEquals(8, albums.size());
                assertTrue(albums.containsAll(List.of(tef.goodbyeYellowBrickRoadResponseDTO, tef.heroesResponseDTO, tef.badResponseDTO, tef.britneyResponseDTO,
                        tef.karmaResponseDTO, tef.rayOfLightResponseDTO, tef.whenWeAllFallAsleepResponseDTO, tef.futureNostalgiaResponseDTO)));
            }
        }


        @Test
        void getAlbumsByReleaseYear_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenYear() {
            when(albumRepository.findAllByReleaseYear(1952)).thenReturn(List.of());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                List<AlbumResponseDTO> albums = albumService.getAlbumsByReleaseYear(1952);

                verify(albumRepository, times(1)).findAllByReleaseYear(1952);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());

                assertNotNull(albums);
                assertEquals(0, albums.size());
            }
        }

        @Test
        void getAlbumsByReleaseYear_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenYear() {
            when(albumRepository.findAllByReleaseYear(2001)).thenReturn(List.of(tef.britney, tef.karma));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.britney)).thenReturn(tef.britneyResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.karma)).thenReturn(tef.karmaResponseDTO);

                List<AlbumResponseDTO> albums = albumService.getAlbumsByReleaseYear(2001);

                verify(albumRepository, times(1)).findAllByReleaseYear(2001);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(2));

                assertNotNull(albums);
                assertEquals(2, albums.size());
                assertEquals(tef.britney.getId(), albums.get(0).id());
                assertEquals(tef.britney.getReleaseYear(), albums.get(0).releaseYear());
                assertEquals(tef.karma.getId(), albums.get(1).id());
                assertEquals(tef.karma.getReleaseYear(), albums.get(1).releaseYear());
            }
        }

        @Test
        void getAlbumByTitle_ShouldReturnEmptyAlbums_WhenNoAlbumsExistForGivenTitle() {
            when(albumRepository.findAllByTitle("Nonexistent Album")).thenReturn(List.of());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                List<AlbumResponseDTO> albums = albumService.getAlbumsByTitle("Nonexistent Album");

                verify(albumRepository, times(1)).findAllByTitle("Nonexistent Album");
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());

                assertNotNull(albums);
                assertEquals(0, albums.size());
            }
        }

        @Test
        void getAlbumByTitle_ShouldReturnAlbums_WhenAlbumsExistForGivenTitle() {
            when(albumRepository.findAllByTitle("Goodbye Yellow Brick Road")).thenReturn(List.of(tef.goodbyeYellowBrickRoad));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)).thenReturn(tef.goodbyeYellowBrickRoadResponseDTO);

                List<AlbumResponseDTO> albums = albumService.getAlbumsByTitle("Goodbye Yellow Brick Road");

                verify(albumRepository, times(1)).findAllByTitle("Goodbye Yellow Brick Road");
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(albums);
                assertEquals(1, albums.size());
                assertTrue(albums.contains(tef.goodbyeYellowBrickRoadResponseDTO));
            }
        }

        @Test
        void getAlbumsByGenre_ShouldThrowNotFoundException_WhenGenreDoesNotExist() {
            Set<Genre> rockRollGenres = Set.of(tef.rockRoll);
            when(genreRepository.findByName(tef.rockRoll.getName())).thenReturn(Optional.empty());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {

                NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.getAlbumsByGenre(GenreType.ROCK_ROLL));

                assertEquals("NotFound: Genre", thrown.getMessage());

                verify(albumRepository, never()).findAllByGenreSet(rockRollGenres);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());
            }
        }

        @Test
        void getAlbumsByGenre_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenGenres() {
            Set<Genre> jazzGenres = Set.of(tef.jazz);
            when(genreRepository.findByName(tef.jazz.getName())).thenReturn(Optional.of(tef.jazz));
            when(albumRepository.findAllByGenreSet(jazzGenres)).thenReturn(List.of());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                List<AlbumResponseDTO> albums = albumService.getAlbumsByGenre(GenreType.JAZZ);

                verify(genreRepository, times(1)).findByName(tef.jazz.getName());
                verify(albumRepository, times(1)).findAllByGenreSet(jazzGenres);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());

                assertNotNull(albums);
                assertEquals(0, albums.size());
            }
        }

        @Test
        void getAlbumsByGenre_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenGenres() {
            Set<Genre> popGenres = Set.of(tef.pop);
            when(genreRepository.findByName(tef.pop.getName())).thenReturn(Optional.of(tef.pop));
            when(albumRepository.findAllByGenreSet(popGenres)).thenReturn(List.of(tef.britney, tef.karma, tef.whenWeAllFallAsleep, tef.rayOfLight,
                    tef.futureNostalgia, tef.bad, tef.goodbyeYellowBrickRoad));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.britney)).thenReturn(tef.britneyResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.karma)).thenReturn(tef.karmaResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.whenWeAllFallAsleep)).thenReturn(tef.whenWeAllFallAsleepResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.rayOfLight)).thenReturn(tef.rayOfLightResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.futureNostalgia)).thenReturn(tef.futureNostalgiaResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.bad)).thenReturn(tef.badResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)).thenReturn(tef.goodbyeYellowBrickRoadResponseDTO);

                List<AlbumResponseDTO> albums = albumService.getAlbumsByGenre(GenreType.POP);

                verify(genreRepository, times(1)).findByName(tef.pop.getName());
                verify(albumRepository, times(1)).findAllByGenreSet(popGenres);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(7));

                assertNotNull(albums);
                assertEquals(7, albums.size());
                assertTrue(albums.containsAll(List.of(tef.britneyResponseDTO, tef.karmaResponseDTO, tef.whenWeAllFallAsleepResponseDTO, tef.rayOfLightResponseDTO,
                        tef.futureNostalgiaResponseDTO, tef.badResponseDTO, tef.goodbyeYellowBrickRoadResponseDTO)));
            }
        }

        @Test
        void getAlbumsByFormat_ShouldReturnEmptyListOfAlbums_WhenNoAlbumsExistForGivenFormat() {
            when(albumRepository.findAllByFormat(Format.DVD)).thenReturn(List.of());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                List<AlbumResponseDTO> albums = albumService.getAlbumsByFormat(Format.DVD);

                verify(albumRepository, times(1)).findAllByFormat(Format.DVD);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());

                assertNotNull(albums);
                assertEquals(0, albums.size());
            }
        }

        @Test
        void getAlbumsByFormat_ShouldReturnListOfAlbums_WhenAlbumsExistForGivenFormat() {
            when(albumRepository.findAllByFormat(Format.CD)).thenReturn(List.of(tef.goodbyeYellowBrickRoad, tef.bad, tef.rayOfLight, tef.futureNostalgia));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)).thenReturn(tef.goodbyeYellowBrickRoadResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.bad)).thenReturn(tef.badResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.rayOfLight)).thenReturn(tef.rayOfLightResponseDTO);
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.futureNostalgia)).thenReturn(tef.futureNostalgiaResponseDTO);

                List<AlbumResponseDTO> albums = albumService.getAlbumsByFormat(Format.CD);

                verify(albumRepository, times(1)).findAllByFormat(Format.CD);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(4));

                assertNotNull(albums);
                assertEquals(4, albums.size());
                assertTrue(albums.containsAll(List.of(tef.goodbyeYellowBrickRoadResponseDTO, tef.badResponseDTO,
                        tef.rayOfLightResponseDTO, tef.futureNostalgiaResponseDTO)));
            }

        }

        @Test
        void getAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
            when(albumRepository.findById(15L)).thenReturn(Optional.empty());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.getAlbumById(15L));

                assertEquals("NotFound: Album", thrown.getMessage());

                verify(albumRepository, times(1)).findById(15L);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());
            }
        }

        @Test
        void getAlbumById_ShouldReturnAlbum_WhenAlbumExists() {
            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(tef.goodbyeYellowBrickRoad)).thenReturn(tef.goodbyeYellowBrickRoadResponseDTO);

                AlbumResponseDTO foundAlbum = albumService.getAlbumById(1L);

                verify(albumRepository, times(1)).findById(1L);
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(foundAlbum);
                assertEquals(tef.goodbyeYellowBrickRoadResponseDTO, foundAlbum);
            }
        }
    }


    @Nested
    class AlbumWriteOps {

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
        void updateAlbum_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder().title("Updated Title").build();

            when(albumRepository.findById(15L)).thenReturn(Optional.empty());
            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.updateAlbum(15L, updateDTO));

                assertEquals("NotFound: Album", thrown.getMessage());

                verify(albumRepository, times(1)).findById(15L);
                verify(artistRepository, never()).findById(any(Long.class));
                verify(genreRepository, never()).findById(any(Long.class));
                verify(albumRepository, never()).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());
            }
        }

        @Test
        void updateAlbum_ShouldThrowNotFoundException_WhenArtistDoesNotExist() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder().artistIds(List.of(15L)).build();

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(artistRepository.findById(15L)).thenReturn(Optional.empty());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.updateAlbum(1L, updateDTO));

                assertEquals("NotFound: Artist", thrown.getMessage());

                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, times(1)).findById(15L);
                verify(genreRepository, never()).findById(any(Long.class));
                verify(albumRepository, never()).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());
            }
        }

        @Test
        void updateAlbum_ShouldThrowNotFoundException_WhenGenreDoesNotExist() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder().genreIds(List.of(15L)).build();

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(genreRepository.findById(15L)).thenReturn(Optional.empty());

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.updateAlbum(1L, updateDTO));

                assertEquals("NotFound: Genre", thrown.getMessage());

                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, never()).findById(any(Long.class));
                verify(genreRepository, times(1)).findById(15L);
                verify(albumRepository, never()).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), never());
            }
        }

        @Test
        void updateAlbum_ShouldReturnUpdatedAlbum_WhenAlbumExists() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder().title("Updated Title").build();
            Album updatedGoodbyeYellowBrickRoad = tef.goodbyeYellowBrickRoad;
            updatedGoodbyeYellowBrickRoad.setTitle(updateDTO.getTitle());
            AlbumResponseDTO updatedGoodbyeYellowBrickRoadResponseDTO = TestEntityFactory.createAlbumResponseDTO(updatedGoodbyeYellowBrickRoad);

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> {
                Album album = invocation.getArgument(0);
                album.setTitle("Updated Title");

                return album;
            });

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(updatedGoodbyeYellowBrickRoad)).thenReturn(updatedGoodbyeYellowBrickRoadResponseDTO);

                AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);


                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, never()).findById(1L);
                verify(genreRepository, never()).findById(any(Long.class));
                verify(albumRepository, times(1)).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(updatedAlbum);
                assertEquals("Updated Title", updatedAlbum.title());
            }
        }

        @Test
        void updateAlbum_ShouldUpdateMultipleFields_WhenAlbumExists() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                    .title("Updated Title")
                    .priceInPences(3000)
                    .releaseYear(1999)
                    .currency(Currency.EUR)
                    .quantityInStock(17)
                    .build();

            Album updatedGoodbyeYellowBrickRoad = tef.goodbyeYellowBrickRoad;
            updatedGoodbyeYellowBrickRoad.setTitle(updateDTO.getTitle());
            updatedGoodbyeYellowBrickRoad.setPriceInPences(updateDTO.getPriceInPences());
            updatedGoodbyeYellowBrickRoad.setReleaseYear(updateDTO.getReleaseYear());
            updatedGoodbyeYellowBrickRoad.setCurrency(updateDTO.getCurrency());
            updatedGoodbyeYellowBrickRoad.setQuantityInStock(updateDTO.getQuantityInStock());


            AlbumResponseDTO updatedGoodbyeYellowBrickRoadResponseDTO = TestEntityFactory.createAlbumResponseDTO(updatedGoodbyeYellowBrickRoad);

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(albumRepository.save(any(Album.class))).thenAnswer(invocation -> invocation.getArgument(0));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(updatedGoodbyeYellowBrickRoad)).thenReturn(updatedGoodbyeYellowBrickRoadResponseDTO);

                AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, never()).findById(any(Long.class));
                verify(genreRepository, never()).findById(any(Long.class));
                verify(albumRepository, times(1)).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(updatedAlbum);
                assertEquals(1L, updatedAlbum.id());
                assertEquals("Updated Title", updatedAlbum.title());
                assertEquals(3000, updatedAlbum.priceInPences());
                assertEquals(1999, updatedAlbum.releaseYear());
                assertEquals(Currency.EUR, updatedAlbum.currency());
                assertEquals(17, updatedAlbum.quantityInStock());
            }
        }

        @Test
        void updateAlbum_ShouldReturnUpdatedAlbum_WhenGivenArtistsGenresAreNull() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                    .title("Updated Title")
                    .artistIds(null)
                    .genreIds(null)
                    .build();
            Album updatedGoodbyeYellowBrickRoad = tef.goodbyeYellowBrickRoad;
            updatedGoodbyeYellowBrickRoad.setTitle(updateDTO.getTitle());
            AlbumResponseDTO updatedGoodbyeYellowBrickRoadResponseDTO = TestEntityFactory.createAlbumResponseDTO(updatedGoodbyeYellowBrickRoad);

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(albumRepository.save(any(Album.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(updatedGoodbyeYellowBrickRoad)).thenReturn(updatedGoodbyeYellowBrickRoadResponseDTO);

                AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, never()).findById(any(Long.class));
                verify(genreRepository, never()).findById(any(Long.class));
                verify(albumRepository, times(1)).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(updatedAlbum);
                assertEquals("Updated Title", updatedAlbum.title());
                assertEquals(1, updatedAlbum.artists().size());
                assertEquals(2, updatedAlbum.genres().size());
                assertTrue(updatedAlbum.artists().contains(FlattenedArtistDTO.builder().id(1L).fullName(tef.eltonJohn.getFullName()).build()));
                assertTrue(updatedAlbum.genres().containsAll(List.of(
                        FlattenedGenreDTO.builder().id(1L).name(GenreType.ROCK).build(),
                        FlattenedGenreDTO.builder().id(2L).name(GenreType.POP).build()
                )));
            }
        }

        @Test
        void updateAlbum_ShouldUpdateAlbum_WhenArtistAndGenreArePresent() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                    .title("Updated Title")
                    .artistIds(List.of(1L))
                    .genreIds(List.of(1L, 2L))
                    .build();
            Album updatedGoodbyeYellowBrickRoad = tef.goodbyeYellowBrickRoad;
            updatedGoodbyeYellowBrickRoad.setTitle(updateDTO.getTitle());
            AlbumResponseDTO updatedGoodbyeYellowBrickRoadResponseDTO = TestEntityFactory.createAlbumResponseDTO(updatedGoodbyeYellowBrickRoad);

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(artistRepository.findById(1L)).thenReturn(Optional.of(tef.eltonJohn));
            when(genreRepository.findById(1L)).thenReturn(Optional.of(tef.rock));
            when(genreRepository.findById(2L)).thenReturn(Optional.of(tef.pop));
            when(albumRepository.save(any(Album.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(updatedGoodbyeYellowBrickRoad)).thenReturn(updatedGoodbyeYellowBrickRoadResponseDTO);

                AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, times(1)).findById(1L);
                verify(genreRepository, times(2)).findById(any(Long.class));
                verify(albumRepository, times(1)).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(updatedAlbum);
                assertEquals("Updated Title", updatedAlbum.title());
                assertEquals(1, updatedAlbum.artists().size());
                assertEquals(2, updatedAlbum.genres().size());
            }
        }


        @Test
        void updateAlbum_ShouldUpdateAlbumArtistsAndGenres_WhenArtistAndGenreArePresent() {
            AlbumUpdateDTO updateDTO = AlbumUpdateDTO.builder()
                    .artistIds(List.of(2L, 3L))
                    .genreIds(List.of(3L, 4L))
                    .build();
            Album updatedGoodbyeYellowBrickRoad = tef.goodbyeYellowBrickRoad;
            updatedGoodbyeYellowBrickRoad.setArtistSet(Set.of(tef.davidBowie, tef.michaelJackson));
            updatedGoodbyeYellowBrickRoad.setGenreSet(Set.of(tef.dancePop, tef.electronic));
            AlbumResponseDTO updatedGoodbyeYellowBrickRoadResponseDTO = TestEntityFactory.createAlbumResponseDTO(updatedGoodbyeYellowBrickRoad);

            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));
            when(artistRepository.findById(2L)).thenReturn(Optional.of(tef.davidBowie));
            when(artistRepository.findById(3L)).thenReturn(Optional.of(tef.michaelJackson));
            when(genreRepository.findById(3L)).thenReturn(Optional.of(tef.dancePop));
            when(genreRepository.findById(4L)).thenReturn(Optional.of(tef.electronic));
            when(albumRepository.save(any(Album.class))).thenAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

            try (MockedStatic<AlbumResponseMapper> utilities = Mockito.mockStatic(AlbumResponseMapper.class)) {
                utilities.when(() -> AlbumResponseMapper.toDTO(updatedGoodbyeYellowBrickRoad)).thenReturn(updatedGoodbyeYellowBrickRoadResponseDTO);

                AlbumResponseDTO updatedAlbum = albumService.updateAlbum(1L, updateDTO);

                verify(albumRepository, times(1)).findById(1L);
                verify(artistRepository, never()).findById(1L);
                verify(artistRepository, times(1)).findById(2L);
                verify(artistRepository, times(1)).findById(3L);
                verify(genreRepository, never()).findById(1L);
                verify(genreRepository, never()).findById(2L);
                verify(genreRepository, times(1)).findById(3L);
                verify(genreRepository, times(1)).findById(4L);
                verify(albumRepository, times(1)).save(any(Album.class));
                utilities.verify(() -> AlbumResponseMapper.toDTO(any(Album.class)), times(1));

                assertNotNull(updatedAlbum);
                assertEquals(1L, updatedAlbum.id());

                assertEquals(2, updatedAlbum.artists().size());
                assertFalse(updatedAlbum.artists().contains(FlattenedArtistDTO.builder().id(1L).fullName(tef.eltonJohn.getFullName()).build()));
                assertTrue(updatedAlbum.artists().containsAll(List.of(
                        FlattenedArtistDTO.builder().id(2L).fullName(tef.davidBowie.getFullName()).build(),
                        FlattenedArtistDTO.builder().id(3L).fullName(tef.michaelJackson.getFullName()).build()
                )));

                assertEquals(2, updatedAlbum.genres().size());
                assertFalse(updatedAlbum.genres().contains(FlattenedGenreDTO.builder().id(1L).name(GenreType.ROCK).build()));
                assertFalse(updatedAlbum.genres().contains(FlattenedGenreDTO.builder().id(2L).name(GenreType.POP).build()));
                assertTrue(updatedAlbum.genres().containsAll(List.of(
                        FlattenedGenreDTO.builder().id(3L).name(GenreType.DANCE_POP).build(),
                        FlattenedGenreDTO.builder().id(4L).name(GenreType.ELECTRONIC).build()
                )));
            }
        }

        @Test
        void deleteAlbumById_ShouldThrowNotFoundException_WhenAlbumDoesNotExist() {
            when(albumRepository.findById(1L)).thenReturn(Optional.empty());

            NotFoundException thrown = assertThrows(NotFoundException.class, () -> albumService.deleteAlbumById(1L));

            assertEquals("NotFound: Album", thrown.getMessage());

            verify(albumRepository, times(1)).findById(1L);
            verify(albumRepository, never()).deleteById(1L);
        }

        @Test
        void deleteAlbumById_ShouldDeleteAlbum_WhenAlbumExists() {
            when(albumRepository.findById(1L)).thenReturn(Optional.of(tef.goodbyeYellowBrickRoad));

            albumService.deleteAlbumById(1L);

            verify(albumRepository, times(1)).findById(1L);
            verify(albumRepository, times(1)).deleteById(1L);
        }
    }
}
