package org.northcoders.recordshopapi.dto.request.artist;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArtistCreateDTO {
    @NotBlank(message = "Artist full name is required")
    private String fullName;

    private List<Long> albumIds;
}
