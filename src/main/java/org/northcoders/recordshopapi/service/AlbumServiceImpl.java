package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.AlbumDTO;
import org.northcoders.recordshopapi.exception.service.InvalidParameterException;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.model.*;
import org.northcoders.recordshopapi.repository.AlbumRepository;
import org.northcoders.recordshopapi.repository.ArtistRepository;
import org.northcoders.recordshopapi.repository.GenreRepository;
import org.northcoders.recordshopapi.util.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Album> getAllAlbums() {
        List<Album> albums = new ArrayList<>();

        albumRepository.findAll().forEach(albums::add);

        return albums;
    }

    @Override
    public List<Album> getAlbumsByReleaseYear(Integer releaseYear) {
        return albumRepository.findAllByReleaseYear(releaseYear);
    }

    @Override
    public List<Album> getAlbumsByTitle(String title) {
        return albumRepository.findAllByTitle(title);
    }

    @Override
    public List<Album> getAlbumsByGenre(GenreType genre) {
        return albumRepository.findAllByGenreSet(Set.of(new Genre(genre)));
    }

    @Override
    public List<Album> getAlbumsByFormat(Format format) {
        return albumRepository.findAllByFormat(format);
    }

    @Override
    public Album getAlbumById(Long id) {
        return albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class));
    }

    @Override
    public Album createAlbum(Album album) {
//        if (album.getId() != null) {
//            throw new InvalidParameterException(Album.class, "id");
//        }

        return albumRepository.save(album);
    }

    @Override
    public Album replaceAlbum(Long id, Album album) {
        Album foundAlbum = albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class));

        if (album.getId() != null) {
            throw new InvalidParameterException(Album.class, "id");
        }

        if (album.getTitle() != null) {
            foundAlbum.setTitle(album.getTitle());
        }

        if (album.getArtistSet() != null) {
            foundAlbum.setArtistSet(album.getArtistSet());
        }

        if (album.getGenreSet() != null) {
            foundAlbum.setGenreSet(album.getGenreSet());
        }

        if (album.getDurationInSeconds() != null) {
            foundAlbum.setDurationInSeconds(album.getDurationInSeconds());
        }

        if (album.getReleaseYear() != null) {
            foundAlbum.setReleaseYear(album.getReleaseYear());
        }

        if (album.getPublisher() != null) {
            foundAlbum.setPublisher(album.getPublisher());
        }

        if (album.getPriceInPences() != null) {
            foundAlbum.setPriceInPences(album.getPriceInPences());
        }


        if (album.getCurrency() != null) {
            foundAlbum.setCurrency(album.getCurrency());
        }


        if (album.getQuantityInStock() != null) {
            foundAlbum.setQuantityInStock(album.getQuantityInStock());
        }


        if (album.getFormat() != null) {
            foundAlbum.setFormat(album.getFormat());
        }

        return albumRepository.save(foundAlbum);
    }

    @Override
    public void deleteAlbumById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class));

        albumRepository.deleteById(id);
    }
}
