package org.northcoders.recordshopapi.repository;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.util.repository.TestEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    private TestEntityFactory tef;

    @BeforeEach
    void setUp() {
        tef = new TestEntityFactory();
        tef.initialiseAllEntities();

        artistRepository.saveAll(List.of(tef.eltonJohn, tef.davidBowie, tef.madonna, tef.tarkan));
    }

    @Test
    void findAllByFullName_ShouldReturnArtist_WhenArtistExists() {
        List<Artist> actualArtists = artistRepository.findAllByFullName("Elton John");
        assertNotNull(actualArtists);
        assertEquals(1, actualArtists.size());
        assertEquals(tef.eltonJohn, actualArtists.get(0));
    }

    @Test
    void findAllByFullName_ShouldReturnMultipleArtists_WhenMultipleArtistsExist() {
        // Create a duplicate artist for testing multiple results
        Artist anotherMadonna = Artist.builder()
                .fullName("Madonna")
                .albums(List.of())
                .build();
        artistRepository.save(anotherMadonna);

        List<Artist> actualArtists = artistRepository.findAllByFullName("Madonna");
        assertNotNull(actualArtists);
        assertEquals(2, actualArtists.size());
        assertTrue(actualArtists.contains(tef.madonna));
        assertTrue(actualArtists.contains(anotherMadonna));
    }

    @Test
    void findAllByFullName_ShouldReturnEmptyList_WhenNoArtistExists() {
        List<Artist> actualArtists = artistRepository.findAllByFullName("Nonexistent Artist");
        assertNotNull(actualArtists);
        assertTrue(actualArtists.isEmpty());
    }
}
