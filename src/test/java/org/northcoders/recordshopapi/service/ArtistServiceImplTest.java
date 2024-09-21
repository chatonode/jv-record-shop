package org.northcoders.recordshopapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.northcoders.recordshopapi.dto.response.success.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.mapper.response.ArtistResponseMapper;
import org.northcoders.recordshopapi.repository.ArtistRepository;
import org.northcoders.recordshopapi.util.service.TestEntityFactory;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class ArtistServiceImplTest {

    @Mock
    ArtistRepository artistRepository;

    @InjectMocks
    ArtistServiceImpl artistService;

    TestEntityFactory tef;

    @BeforeEach
    void setUp() {
        tef = new TestEntityFactory();
        tef.initialiseAllEntities();
    }

    @Test
    void getAllArtists_ShouldReturnListOfArtists() {
        when(artistRepository.findAll()).thenReturn(List.of(tef.eltonJohn, tef.davidBowie));

        List<ArtistResponseDTO> artists = artistService.getAllArtists();

        assertNotNull(artists);
        assertEquals(2, artists.size());
        assertTrue(artists.contains(ArtistResponseMapper.toDTO(tef.eltonJohn)));
        assertTrue(artists.contains(ArtistResponseMapper.toDTO(tef.davidBowie)));
    }

    @Test
    void getArtistsByName_ShouldReturnListOfArtists() {
        when(artistRepository.findAllByFullName("Elton John")).thenReturn(List.of(tef.eltonJohn));

        List<ArtistResponseDTO> artists = artistService.getArtistsByName("Elton John");

        assertNotNull(artists);
        assertEquals(1, artists.size());
        assertTrue(artists.contains(ArtistResponseMapper.toDTO(tef.eltonJohn)));
    }

    @Test
    void getArtistById_ShouldReturnArtist_WhenArtistExists() {
        when(artistRepository.findById(1L)).thenReturn(Optional.of(tef.eltonJohn));

        ArtistResponseDTO foundArtist = artistService.getArtistById(1L);

        assertNotNull(foundArtist);
        assertEquals(ArtistResponseMapper.toDTO(tef.eltonJohn), foundArtist);
    }

    @Test
    void getArtistById_ShouldThrowNotFoundException_WhenArtistDoesNotExist() {
        when(artistRepository.findById(1L)).thenReturn(Optional.empty());

        NotFoundException thrown = assertThrows(NotFoundException.class, () -> artistService.getArtistById(1L));

        assertEquals("NotFound: Artist", thrown.getMessage());
    }


}