package org.northcoders.recordshopapi.dto.response.success.album;

import lombok.Builder;

@Builder
public record FlattenedArtistDTO(
        Long id,
        String fullName
) {
}
