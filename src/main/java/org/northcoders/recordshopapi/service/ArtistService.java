package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.artist.ArtistCreateDTO;
import org.northcoders.recordshopapi.dto.response.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArtistService {

    List<ArtistResponseDTO> getAllArtists();

    List<ArtistResponseDTO> getArtistsByName(String fullName);

    ArtistResponseDTO getArtistById(Long id);

    ArtistResponseDTO createArtist(ArtistCreateDTO artistCreateDTO);

    void deleteArtistById(Long id);
}
