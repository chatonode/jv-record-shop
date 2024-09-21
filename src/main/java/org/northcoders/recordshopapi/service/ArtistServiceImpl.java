package org.northcoders.recordshopapi.service;


import org.northcoders.recordshopapi.dto.request.artist.ArtistCreateDTO;
import org.northcoders.recordshopapi.dto.response.success.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.mapper.request.artist.ArtistCreateMapper;
import org.northcoders.recordshopapi.mapper.response.ArtistResponseMapper;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.repository.AlbumRepository;
import org.northcoders.recordshopapi.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Override
    public List<ArtistResponseDTO> getAllArtists() {
        List<Artist> artists = new ArrayList<>();

        artistRepository.findAll().forEach(artists::add);

        List<ArtistResponseDTO> artistResponseDTOs = artists.stream().map(ArtistResponseMapper::toDTO).toList();

        return artistResponseDTOs;
    }

    @Override
    public List<ArtistResponseDTO> getArtistsByName(String fullName) {
        List<Artist> artists = artistRepository.findAllByFullName(fullName);

        List<ArtistResponseDTO> artistResponseDTOs = artists.stream().map(ArtistResponseMapper::toDTO).toList();

        return artistResponseDTOs;
    }

    @Override
    public ArtistResponseDTO getArtistById(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NotFoundException(Artist.class));

        ArtistResponseDTO artistResponseDTO = ArtistResponseMapper.toDTO(artist);

        return artistResponseDTO;
    }

    @Override
    public ArtistResponseDTO createArtist(ArtistCreateDTO artistCreateDTO) {
        List<Album> albums = new ArrayList<>();
        if (artistCreateDTO.getAlbumIds() != null && !artistCreateDTO.getAlbumIds().isEmpty()) {
            artistCreateDTO.getAlbumIds().stream()
                    .map(albumId -> albumRepository.findById(albumId).orElseThrow(() -> new NotFoundException(Album.class)))
                    .toList();
        }

        Artist artist = ArtistCreateMapper.toEntity(artistCreateDTO, albums);

        Artist createdArtist = artistRepository.save(artist);

        ArtistResponseDTO artistResponseDTO = ArtistResponseMapper.toDTO(createdArtist);

        return artistResponseDTO;
    }

    @Override
    public void deleteArtistById(Long id) {
        Artist artist = artistRepository.findById(id).orElseThrow(() -> new NotFoundException(Artist.class));

        artistRepository.deleteById(id);
    }

}
