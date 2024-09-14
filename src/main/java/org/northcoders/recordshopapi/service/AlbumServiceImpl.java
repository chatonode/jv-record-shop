package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.exception.service.InvalidParameterException;
import org.northcoders.recordshopapi.exception.service.NotFoundException;
import org.northcoders.recordshopapi.mapper.request.album.AlbumUpdateMapper;
import org.northcoders.recordshopapi.mapper.response.AlbumResponseMapper;
import org.northcoders.recordshopapi.model.*;
import org.northcoders.recordshopapi.repository.AlbumRepository;
import org.northcoders.recordshopapi.repository.ArtistRepository;
import org.northcoders.recordshopapi.repository.GenreRepository;
import org.northcoders.recordshopapi.mapper.request.album.AlbumCreateMapper;
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
    public List<AlbumResponseDTO> getAllAlbums() {
        List<Album> albums = new ArrayList<>();

        albumRepository.findAll().forEach(albums::add);

        List<AlbumResponseDTO> albumResponseDTOs = albums.stream().map(AlbumResponseMapper::toDTO).toList();

        return albumResponseDTOs;
    }

    @Override
    public List<AlbumResponseDTO> getAlbumsByReleaseYear(Integer releaseYear) {
        List<Album> albums = albumRepository.findAllByReleaseYear(releaseYear);

        List<AlbumResponseDTO> albumResponseDTOs = albums.stream().map(AlbumResponseMapper::toDTO).toList();

        return albumResponseDTOs;
    }

    @Override
    public List<AlbumResponseDTO> getAlbumsByTitle(String title) {
        List<Album> albums = albumRepository.findAllByTitle(title);

        List<AlbumResponseDTO> albumResponseDTOs = albums.stream().map(AlbumResponseMapper::toDTO).toList();

        return albumResponseDTOs;
    }

    @Override
    public List<AlbumResponseDTO> getAlbumsByGenre(GenreType genre) {
        Genre foundGenre = genreRepository.findByName(genre).orElseThrow(() -> new NotFoundException(Genre.class));

        List<Album> albums = albumRepository.findAllByGenreSet(Set.of(foundGenre));


        List<AlbumResponseDTO> albumResponseDTOs = albums.stream().map(AlbumResponseMapper::toDTO).toList();

        return albumResponseDTOs;
    }

    @Override
    public List<AlbumResponseDTO> getAlbumsByFormat(Format format) {
        List<Album> albums = albumRepository.findAllByFormat(format);

        List<AlbumResponseDTO> albumResponseDTOs = albums.stream().map(AlbumResponseMapper::toDTO).toList();

        return albumResponseDTOs;
    }

    @Override
    public AlbumResponseDTO getAlbumById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class));

        AlbumResponseDTO albumResponseDTO = AlbumResponseMapper.toDTO(album);

        return albumResponseDTO;
    }


    public AlbumResponseDTO createAlbum(AlbumCreateDTO albumCreateDTO) {
        List<Artist> artists = albumCreateDTO.getArtistIds().stream()
                .map(artistId -> artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException(Artist.class)))
                .toList();

        List<Genre> genres = albumCreateDTO.getGenreIds().stream()
                .map(genreId -> genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException(Genre.class)))
                .toList();

        Album album = AlbumCreateMapper.toEntity(albumCreateDTO, artists, genres);

        Album createdAlbum = albumRepository.save(album);

        AlbumResponseDTO albumResponseDTO = AlbumResponseMapper.toDTO(createdAlbum);

        return albumResponseDTO;
    }

    @Override
    public AlbumResponseDTO updateAlbum(Long id, AlbumUpdateDTO albumUpdateDTO) {
        Album foundAlbum = albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class));

        List<Artist> artists = new ArrayList<>();
        List<Genre> genres = new ArrayList<>();

        if (albumUpdateDTO.getArtistIds() != null) {
            albumUpdateDTO.getArtistIds().stream()
                    .map(artistId -> artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException(Artist.class)))
                    .forEach(artists::add);
        }

        if (albumUpdateDTO.getGenreIds() != null) {
            albumUpdateDTO.getGenreIds().stream()
                    .map(genreId -> genreRepository.findById(genreId).orElseThrow(() -> new NotFoundException(Genre.class)))
                    .forEach(genres::add);
        }

        Album album = AlbumUpdateMapper.toEntity(foundAlbum, albumUpdateDTO, artists, genres);

        Album updatedAlbum = albumRepository.save(album);

        AlbumResponseDTO albumResponseDTO = AlbumResponseMapper.toDTO(updatedAlbum);

        return albumResponseDTO;
    }

    @Override
    public void deleteAlbumById(Long id) {
        Album album = albumRepository.findById(id).orElseThrow(() -> new NotFoundException(Album.class));

        albumRepository.deleteById(id);
    }

//    private Artist findOrCreateArtist(AlbumCreateArtistDTO albumCreateArtistDTO) {
//        return artistRepository.findByNameAndBirthdate(albumCreateArtistDTO.getName(), albumCreateArtistDTO.getBirthdate())
//                .orElseGet(() -> artistRepository.save(ArtistCreateMapper.toEntity(albumCreateArtistDTO)));
//    }
//
//    private Genre findOrCreateGenre(GenreDTO genreDTO) {
//        return genreRepository.findByNameAndDescription(genreDTO.getName(), genreDTO.getDescription())
//                .orElseGet(() -> genreRepository.save(GenreMapper.toEntity(genreDTO)));
//    }
}
