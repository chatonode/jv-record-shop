package org.northcoders.recordshopapi.dto.response.success.album;

import lombok.Builder;
import org.northcoders.recordshopapi.model.GenreType;

@Builder
public record FlattenedGenreDTO(
        Long id,
        GenreType name
) {
}
