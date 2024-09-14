package org.northcoders.recordshopapi.dto.request.album;

import lombok.*;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.URL;
import org.northcoders.recordshopapi.model.*;
import org.northcoders.recordshopapi.validation.constraints.NullOrNotBlank;
import org.northcoders.recordshopapi.validation.constraints.NullOrNotEmpty;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumUpdateDTO {

    @NullOrNotBlank(message = "If provided, title is required")
    private String title;

    @NullOrNotEmpty(message = "If provided, at least one artist id is required")
    private List<Long> artistIds;

    @NullOrNotEmpty(message = "If provided, at least one artist id is required")
    private List<Long> genreIds;

    @Min(value = 30, message = "Duration must be at least 30 seconds")
    private Integer durationInSeconds;

    @URL(message = "Invalid image URL")
    private String imageUrl;

    @Min(value = 1900, message = "Invalid release year")
    private Integer releaseYear;

    @NullOrNotBlank(message = "Publisher can either be null or has non-blank value")
    private String publisher;

    private Format format;

    @Min(value = 1, message = "Price must be at least 1 pence")
    private Integer priceInPences;

    private Currency currency;

    @Min(value = 0, message = "Quantity must be at least 0")
    private Integer quantityInStock;
}