package org.northcoders.recordshopapi.dto.request.genre;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.northcoders.recordshopapi.model.GenreType;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreCreateDTO {
    @NotNull(message = "Genre name is required")
    @JsonProperty(value = "name")
    private GenreType genreType;

//    private List<Long> albumIds;
}
