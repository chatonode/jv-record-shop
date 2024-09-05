package org.northcoders.recordshopapi.repository;

import org.northcoders.recordshopapi.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    //    - list all albums by a given artist
    //    List<Album> findById(Long id);
}
