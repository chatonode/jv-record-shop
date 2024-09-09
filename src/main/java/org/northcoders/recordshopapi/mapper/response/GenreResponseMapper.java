package org.northcoders.recordshopapi.mapper.response;

import org.northcoders.recordshopapi.dto.response.genre.FlattenedAlbumDTO;
import org.northcoders.recordshopapi.dto.response.genre.GenreResponseDTO;
import org.northcoders.recordshopapi.model.Genre;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

public class GenreResponseMapper {
    public static GenreResponseDTO toDTO(Genre genre) {
        return GenreResponseDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .albums(genre.getAlbumSet().stream()
                        .map(album -> FlattenedAlbumDTO.builder()
                                .id(album.getId())
                                .title(album.getTitle())
                                .build())
                        .collect(Collectors.toList()))
                .createdDate(genre.getCreatedDate())
                .updatedDate(genre.getUpdatedDate())
                .build();
    }
}
