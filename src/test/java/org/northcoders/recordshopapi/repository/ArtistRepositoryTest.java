package org.northcoders.recordshopapi.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    private Artist eltonJohn, davidBowie, madonna, tarkan;

    private void initialiseArtists() {
        eltonJohn = Artist.builder()
                .fullName("Elton John")
                .albums(List.of())
                .build();

        davidBowie = Artist.builder()
                .fullName("David Bowie")
                .albums(List.of())
                .build();

        madonna = Artist.builder()
                .fullName("Madonna")
                .albums(List.of())
                .build();

        tarkan = Artist.builder()
                .fullName("Tarkan")
                .albums(List.of())
                .build();
    }

    @BeforeEach
    void setUp() {
        this.initialiseArtists();

        artistRepository.saveAll(List.of(eltonJohn, davidBowie, madonna, tarkan));
    }

    @Test
    void findAllByFullName_ReturnsArtist() {
        List<Artist> actualArtists = artistRepository.findAllByFullName("Elton John");
        assertNotNull(actualArtists);
        assertEquals(1, actualArtists.size());
        assertEquals(eltonJohn, actualArtists.get(0));
    }

    @Test
    void findAllByFullName_ReturnsMultipleArtists() {
        // Create a duplicate artist for testing multiple results
        Artist anotherMadonna = Artist.builder()
                .fullName("Madonna")
                .albums(List.of())
                .build();
        artistRepository.save(anotherMadonna);

        List<Artist> actualArtists = artistRepository.findAllByFullName("Madonna");
        assertNotNull(actualArtists);
        assertEquals(2, actualArtists.size());
        assertTrue(actualArtists.contains(madonna));
        assertTrue(actualArtists.contains(anotherMadonna));
    }

    @Test
    void findAllByFullName_ReturnsEmptyList() {
        List<Artist> actualArtists = artistRepository.findAllByFullName("Nonexistent Artist");
        assertNotNull(actualArtists);
        assertTrue(actualArtists.isEmpty());
    }
}
