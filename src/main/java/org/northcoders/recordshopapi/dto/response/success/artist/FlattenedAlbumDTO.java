package org.northcoders.recordshopapi.dto.response.success.artist;

import lombok.*;

@Builder
public record FlattenedAlbumDTO(
        Long id,
        String title
) {
}
