package org.northcoders.recordshopapi.dto.request;

import java.util.List;

import lombok.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

import org.northcoders.recordshopapi.model.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlbumDTO {

    @NotBlank(message = "Album title is required")
    private String title;

    @NotEmpty(message = "At least one artist is required")
    private List<ArtistDTO> artists;

    @NotNull(message = "At least one genre is required")
    private List<GenreDTO> genres;

    @NotNull(message = "Duration is required")
    @Min(value = 30, message = "Duration must be at least 30 seconds")
    private Integer durationInSeconds;

    @URL(message = "Invalid image URL")
    private String imageUrl;

    @Min(value = 1900, message = "Invalid release year")
    private Integer releaseYear;

    private String publisher;

    @NotNull(message = "Format is required")
    private Format format;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1 pence")
    private Integer priceInPences;

    @NotNull(message = "Currency is required")
    private Currency currency;

    @NotNull(message = "Stock quantity is required")
    @Min(value = 0, message = "Quantity in stock cannot be negative")
    private Integer quantityInStock;
}
