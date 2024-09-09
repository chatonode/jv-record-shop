package org.northcoders.recordshopapi.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.model.GenreType;
import org.northcoders.recordshopapi.util.repository.TestEntityFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class GenreRepositoryTest {
    @Autowired
    GenreRepository genreRepository;

    private TestEntityFactory tef;

    @BeforeEach
    void setUp() {
        tef = new TestEntityFactory();
        tef.initialiseAllEntities();

        genreRepository.saveAll(List.of(tef.rock, tef.pop, tef.country));
    }

    @Test
    void testFindByName() {
        // When
        Optional<Genre> rockGenre = genreRepository.findByName(GenreType.ROCK);
        Optional<Genre> popGenre = genreRepository.findByName(GenreType.POP);
        Optional<Genre> jazzGenre = genreRepository.findByName(GenreType.JAZZ);

        // Then
        assertTrue(rockGenre.isPresent());
        assertEquals(GenreType.ROCK, rockGenre.get().getName());

        assertTrue(popGenre.isPresent());
        assertEquals(GenreType.POP, popGenre.get().getName());

        assertTrue(jazzGenre.isEmpty()); // Jazz was not inserted, so it shouldn't be found
    }
}
