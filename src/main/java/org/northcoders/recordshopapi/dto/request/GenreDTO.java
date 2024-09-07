package org.northcoders.recordshopapi.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.northcoders.recordshopapi.model.GenreType;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO {

    @NotNull(message = "Genre is required")
    private GenreType name;
}
