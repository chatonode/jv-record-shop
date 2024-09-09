package org.northcoders.recordshopapi.mapper.response;

import org.northcoders.recordshopapi.dto.response.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.dto.response.artist.FlattenedAlbumDTO;
import org.northcoders.recordshopapi.model.Artist;

import java.util.stream.Collectors;

public class ArtistResponseMapper {

    public static ArtistResponseDTO toDTO(Artist artist) {
        return ArtistResponseDTO.builder()
                .id(artist.getId())
                .fullName(artist.getFullName())
                .albums(artist.getAlbumSet().stream()
                        .map(album -> FlattenedAlbumDTO.builder()
                                .id(album.getId())
                                .title(album.getTitle())
                                .build())
                        .collect(Collectors.toList()))
                .createdDate(artist.getCreatedDate())
                .updatedDate(artist.getUpdatedDate())
                .build();
    }
}