package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.List;


public interface AlbumService {
    List<AlbumResponseDTO> getAllAlbums();

    List<AlbumResponseDTO> getAlbumsByReleaseYear(Integer releaseYear);

    List<AlbumResponseDTO> getAlbumsByTitle(String title);

    List<AlbumResponseDTO> getAlbumsByGenre(GenreType genre);

    List<AlbumResponseDTO> getAlbumsByFormat(Format format);

    AlbumResponseDTO getAlbumById(Long id);

    AlbumResponseDTO createAlbum(AlbumCreateDTO albumCreateDTO);

    AlbumResponseDTO updateAlbum(Long id, AlbumUpdateDTO album);

    void deleteAlbumById(Long id);

}
