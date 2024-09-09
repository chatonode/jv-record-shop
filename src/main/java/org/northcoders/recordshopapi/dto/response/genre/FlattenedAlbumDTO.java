package org.northcoders.recordshopapi.dto.response.genre;

import lombok.Builder;

@Builder
public record FlattenedAlbumDTO(
        Long id,
        String title
) {
}
