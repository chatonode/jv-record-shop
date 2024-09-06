package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.model.*;

import java.util.List;


public interface AlbumService {
    List<Album> getAllAlbums();

    List<Album> getAlbumsByReleaseYear(Integer releaseYear);

    List<Album> getAlbumsByTitle(String title); // OR NotFoundException

    List<Album> getAlbumsByGenre(GenreType genre);

    List<Album> getAlbumsByFormat(Format format);

    Album getAlbumById(Long id); // OR NotFoundException

    Album createAlbum(Album album); // OR InvalidParameterException

    Album replaceAlbum(Long id, Album album); // OR NotFoundException

    void deleteAlbumById(Long id); // OR NotFoundException

}
