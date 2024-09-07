package org.northcoders.recordshopapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.validation.constraints.NotBlank;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistDTO {

    @NotBlank(message = "Artist name is required")
    @JsonProperty(value = "name")
    private String fullName;
}
