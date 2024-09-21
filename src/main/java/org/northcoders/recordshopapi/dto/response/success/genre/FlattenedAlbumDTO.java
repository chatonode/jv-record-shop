package org.northcoders.recordshopapi.dto.response.success.genre;

import lombok.Builder;

@Builder
public record FlattenedAlbumDTO(
        Long id,
        String title
) {
}
