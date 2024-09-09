package org.northcoders.recordshopapi.dto.response.album;

import lombok.Builder;

@Builder
public record FlattenedArtistDTO(
        Long id,
        String fullName
) {
}
