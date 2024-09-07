package org.northcoders.recordshopapi.util.mapper;

import org.northcoders.recordshopapi.dto.request.ArtistDTO;
import org.northcoders.recordshopapi.model.Artist;

import java.util.List;

public class ArtistMapper {
    public static Artist toEntity(ArtistDTO artistDTO) {
        return Artist.builder()
                .fullName(artistDTO.getFullName())
                .albums(List.of())
                .build();
    }

    public static ArtistDTO toDTO(Artist artist) {
        return ArtistDTO.builder()
                .fullName(artist.getFullName())
                .build();
    }
}
