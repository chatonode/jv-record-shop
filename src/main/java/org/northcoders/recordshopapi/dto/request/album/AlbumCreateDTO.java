package org.northcoders.recordshopapi.dto.request.album;

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
public class AlbumCreateDTO {

    @NotBlank(message = "Album title is required")
    private String title;

    @NotEmpty(message = "At least one artist id is required")
    private List<Long> artistIds;

    @NotEmpty(message = "At least one genre id is required")
    private List<Long> genreIds;

    @NotNull(message = "Duration is required")
    @Min(value = 30, message = "Duration must be at least 30 seconds")
    private Integer durationInSeconds;

    @URL(message = "Invalid image URL")
    private String imageUrl;

    @Min(value = 1900, message = "Invalid release year")  // Can be used without @NotNull if release year is allowed to be null
    private Integer releaseYear;

    private String publisher;

    @NotNull(message = "Format is required")
    private Format format;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be at least 1 pence")
    private Integer priceInPences;

    @NotNull(message = "Currency is required")
    private Currency currency;

//    @NotNull(message = "Stock quantity is required")
//    @Min(value = 0, message = "Quantity in stock cannot be negative")
//    private Integer quantityInStock;
}
