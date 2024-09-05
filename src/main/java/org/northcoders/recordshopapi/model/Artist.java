package org.northcoders.recordshopapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@RequiredArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String fullName;

    @Column
    @ManyToMany(mappedBy = "artistSet")
    private Set<Album> albumSet;

    @Column
    private Set<Genre> genreSet;

    @Builder
    public Artist(String fullName, List<Album> albums, List<Genre> genres) {
        this.fullName = fullName;
        this.albumSet = new HashSet<>(albums);
        this.genreSet = new HashSet<>(genres);
    }
}
