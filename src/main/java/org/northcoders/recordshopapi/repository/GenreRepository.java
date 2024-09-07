package org.northcoders.recordshopapi.repository;

import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.model.GenreType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    Optional<Genre> findByName(GenreType name);
}
