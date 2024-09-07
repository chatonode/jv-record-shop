package org.northcoders.recordshopapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreType name;

    @ManyToMany(mappedBy = "genreSet")
    private Set<Album> albumSet;

    @Builder
    public Genre(GenreType name) {
        this.name = name;
    }
}
