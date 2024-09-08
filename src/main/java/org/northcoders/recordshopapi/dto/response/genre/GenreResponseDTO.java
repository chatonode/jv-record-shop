package org.northcoders.recordshopapi.dto.response.genre;

import lombok.Builder;
import org.northcoders.recordshopapi.model.GenreType;

import java.util.Date;
import java.util.List;

@Builder
public record GenreResponseDTO(
        Long id,
        GenreType name,
        List<FlattenedAlbumDTO> albums,
        Date createdDate,
        Date updatedDate
) {
}
