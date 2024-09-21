package org.northcoders.recordshopapi.mapper.response;

import org.northcoders.recordshopapi.dto.response.success.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.dto.response.success.album.FlattenedArtistDTO;
import org.northcoders.recordshopapi.dto.response.success.album.FlattenedGenreDTO;
import org.northcoders.recordshopapi.model.Album;

import java.util.stream.Collectors;

public class AlbumResponseMapper {
    public static AlbumResponseDTO toDTO(Album album) {
        return AlbumResponseDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artists(album.getArtistSet().stream()
                        .map(artist -> FlattenedArtistDTO.builder()
                                .id(artist.getId())
                                .fullName(artist.getFullName())
                                .build())
                        .collect(Collectors.toList()))
                .genres(album.getGenreSet().stream()
                        .map(genre -> FlattenedGenreDTO.builder()
                                .id(genre.getId())
                                .name(genre.getName())
                                .build())
                        .collect(Collectors.toList()))
                .durationInSeconds(album.getDurationInSeconds())
                .imageUrl(album.getImageUrl())
                .releaseYear(album.getReleaseYear())
                .publisher(album.getPublisher())
                .priceInPences(album.getPriceInPences())
                .currency(album.getCurrency())
                .quantityInStock(album.getQuantityInStock())
                .format(album.getFormat())
                .createdDate(album.getCreatedDate())
                .updatedDate(album.getUpdatedDate())
                .build();
    }

}
