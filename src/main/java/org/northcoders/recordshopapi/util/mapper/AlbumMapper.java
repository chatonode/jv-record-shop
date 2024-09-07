package org.northcoders.recordshopapi.util.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.northcoders.recordshopapi.dto.request.AlbumDTO;
import org.northcoders.recordshopapi.dto.request.ArtistDTO;
import org.northcoders.recordshopapi.dto.request.GenreDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;


public class AlbumMapper {
    public static Album toEntity(AlbumDTO albumDTO) {
        List<Artist> artists = albumDTO.getArtists().stream()
                .map(ArtistMapper::toEntity)
                .toList();

        List<Genre> genres = albumDTO.getGenres().stream()
                .map(GenreMapper::toEntity)
                .collect(Collectors.toList());

        return Album.builder()
                .title(albumDTO.getTitle())
                .artists(artists)
                .genres(genres)
                .durationInSeconds(albumDTO.getDurationInSeconds())
                .imageUrl(albumDTO.getImageUrl())
                .releaseYear(albumDTO.getReleaseYear())
                .publisher(albumDTO.getPublisher())
                .format(albumDTO.getFormat())
                .priceInPences(albumDTO.getPriceInPences())
                .currency(albumDTO.getCurrency())
                .build();
    }

    public static AlbumDTO toDTO(Album album) {
        List<ArtistDTO> artistDTOs = album.getArtistSet().stream()
                .map(ArtistMapper::toDTO)
                .collect(Collectors.toList());

        List<GenreDTO> genreDTOs = album.getGenreSet().stream()
                .map(GenreMapper::toDTO)
                .collect(Collectors.toList());

        return AlbumDTO.builder()
                .title(album.getTitle())
                .artists(artistDTOs)
                .genres(genreDTOs)
                .durationInSeconds(album.getDurationInSeconds())
                .imageUrl(album.getImageUrl())
                .releaseYear(album.getReleaseYear())
                .publisher(album.getPublisher())
                .priceInPences(album.getPriceInPences())
                .currency(album.getCurrency())
                .format(album.getFormat())
                .build();
    }
}
