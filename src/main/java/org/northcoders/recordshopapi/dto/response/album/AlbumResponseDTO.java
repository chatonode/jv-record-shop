package org.northcoders.recordshopapi.dto.response.album;


import java.util.Date;
import java.util.List;

import lombok.*;
import org.northcoders.recordshopapi.model.Currency;
import org.northcoders.recordshopapi.model.Format;

@Builder
public record AlbumResponseDTO(
        Long id,
        String title,
        List<FlattenedArtistDTO> artists,
        List<FlattenedGenreDTO> genres,
        Integer durationInSeconds, // 1:27 => 87
        String imageUrl,
        Integer releaseYear,
        String publisher,
        Integer priceInPences, // 2.5 => 250
        Currency currency,
        Integer quantityInStock,
        Format format,
        Date createdDate,
        Date updatedDate
) {
}
