//package org.northcoders.recordshopapi.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.northcoders.recordshopapi.exception.service.NotFoundException;
//import org.northcoders.recordshopapi.model.Artist;
//import org.northcoders.recordshopapi.repository.ArtistRepository;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@DataJpaTest
//class ArtistServiceImplTest {
//
//    @Mock
//    ArtistRepository artistRepository;
//
//    @InjectMocks
//    ArtistServiceImpl artistService;
//
//    private Artist eltonJohn, davidBowie, madonna, tarkan;
//
//    private void initialiseArtists() {
//        eltonJohn = Artist.builder()
//                .fullName("Elton John")
//                .albums(List.of())
//                .build();
//
//        davidBowie = Artist.builder()
//                .fullName("David Bowie")
//                .albums(List.of())
//                .build();
//
//        madonna = Artist.builder()
//                .fullName("Madonna")
//                .albums(List.of())
//                .build();
//
//        tarkan = Artist.builder()
//                .fullName("Tarkan")
//                .albums(List.of())
//                .build();
//    }
//
//    @BeforeEach
//    void setUp() {
//        this.initialiseArtists();
//    }
//
//    @Test
//    void getAllArtists_ShouldReturnListOfArtists() {
//        when(artistRepository.findAll()).thenReturn(List.of(eltonJohn, davidBowie));
//
//        List<Artist> artists = artistService.getAllArtists();
//
//        assertNotNull(artists);
//        assertEquals(2, artists.size());
//        assertTrue(artists.contains(eltonJohn));
//        assertTrue(artists.contains(davidBowie));
//    }
//
//    @Test
//    void getArtistsByName_ShouldReturnListOfArtists() {
//        when(artistRepository.findAllByFullName("Elton John")).thenReturn(List.of(eltonJohn));
//
//        List<Artist> artists = artistService.getArtistsByName("Elton John");
//
//        assertNotNull(artists);
//        assertEquals(1, artists.size());
//        assertTrue(artists.contains(eltonJohn));
//    }
//
//    @Test
//    void getArtistById_ShouldReturnArtist_WhenArtistExists() {
//        when(artistRepository.findById(1L)).thenReturn(Optional.of(eltonJohn));
//
//        Artist foundArtist = artistService.getArtistById(1L);
//
//        assertNotNull(foundArtist);
//        assertEquals(eltonJohn, foundArtist);
//    }
//
//    @Test
//    void getArtistById_ShouldThrowNotFoundException_WhenArtistDoesNotExist() {
//        when(artistRepository.findById(1L)).thenReturn(Optional.empty());
//
//        NotFoundException thrown = assertThrows(NotFoundException.class, () -> artistService.getArtistById(1L));
//
//        assertEquals("NotFound: Artist", thrown.getMessage());
//    }
//
//
//}