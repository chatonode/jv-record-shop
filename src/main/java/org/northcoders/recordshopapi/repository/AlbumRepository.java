package org.northcoders.recordshopapi.repository;

import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Format;
import org.northcoders.recordshopapi.model.Genre;
import org.northcoders.recordshopapi.model.GenreType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface AlbumRepository extends CrudRepository<Album, Long> {
    //    List<Album> findAll();
    List<Album> findAllByReleaseYear(Integer releaseYear);

    List<Album> findAllByTitle(String title);

    List<Album> findAllByGenreSet(Set<Genre> genreSet);

    List<Album> findAllByFormat(Format format);

    //    Optional<Album> findById();
    //    Album save();
    //    void deleteById();
}
