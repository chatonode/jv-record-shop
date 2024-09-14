package org.northcoders.recordshopapi.dto.request.album;

import lombok.*;
import jakarta.validation.constraints.Min;
import org.hibernate.validator.constraints.URL;
import org.northcoders.recordshopapi.model.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumUpdateDTO {

    // Optional, can be null if not updated
    private String title;

    // Optional, can be empty if no change to artists
    private List<Long> artistIds;

    // Optional, can be empty if no change to genres
    private List<Long> genreIds;

    // Optional, must be >= 30 if provided
    @Min(value = 30, message = "Duration must be at least 30 seconds")
    private Integer durationInSeconds;

    // Optional, must be a valid URL if provided
    @URL(message = "Invalid image URL")
    private String imageUrl;

    // Optional, must be >= 1900 if provided
    @Min(value = 1900, message = "Invalid release year")
    private Integer releaseYear;

    // Optional, no restrictions on publisher
    private String publisher;

    // Optional, must be a valid format if provided
    private Format format;

    // Optional, must be >= 1 pence if provided
    @Min(value = 1, message = "Price must be at least 1 pence")
    private Integer priceInPences;

    // Optional, must be valid currency if provided
    private Currency currency;

    // Optional, must be >= 0 if provided
    @Min(value = 0, message = "Quantity must be at least 0")
    private Integer quantityInStock;
}