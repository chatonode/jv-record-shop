package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.List;


public interface AlbumService {
    List<AlbumResponseDTO> getAllAlbums();

    List<AlbumResponseDTO> getAlbumsByReleaseYear(Integer releaseYear);

    List<AlbumResponseDTO> getAlbumsByTitle(String title); // OR NotFoundException

    List<AlbumResponseDTO> getAlbumsByGenre(GenreType genre);

    List<AlbumResponseDTO> getAlbumsByFormat(Format format);

    AlbumResponseDTO getAlbumById(Long id); // OR NotFoundException

    AlbumResponseDTO createAlbum(AlbumCreateDTO albumCreateDTO); // OR InvalidParameterException

    AlbumResponseDTO replaceAlbum(Long id, Album album); // OR NotFoundException

    void deleteAlbumById(Long id); // OR NotFoundException

}
