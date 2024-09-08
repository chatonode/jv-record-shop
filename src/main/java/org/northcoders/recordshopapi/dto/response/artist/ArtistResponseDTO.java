package org.northcoders.recordshopapi.dto.response.artist;

import lombok.Builder;

import java.util.Date;
import java.util.List;

@Builder
public record ArtistResponseDTO(
        Long id,
        String fullName,
        List<FlattenedAlbumDTO> albums,
        Date createdDate,
        Date updatedDate
) {
}
