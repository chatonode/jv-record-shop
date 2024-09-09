package org.northcoders.recordshopapi.dto.response.artist;

import lombok.*;

@Builder
public record FlattenedAlbumDTO(
        Long id,
        String title
) {
}
