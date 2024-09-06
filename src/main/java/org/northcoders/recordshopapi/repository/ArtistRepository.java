package org.northcoders.recordshopapi.repository;

import org.northcoders.recordshopapi.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends CrudRepository<Artist, Long> {
    //    List<Album> findById(Long id);
    List<Artist> findAllByFullName(String fullName);

    //    Optional<Artist> findById(Long id);
    //    Artist save(Artist artist);
    //    void deleteById(Long id);
}
