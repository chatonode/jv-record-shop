package org.northcoders.recordshopapi.repository;

import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {

}
