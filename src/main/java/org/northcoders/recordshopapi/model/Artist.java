package org.northcoders.recordshopapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    @ManyToMany(mappedBy = "artistSet")
    private Set<Album> albumSet;

    @Builder
    public Artist(String fullName, List<Album> albums) {
        this.fullName = fullName;
        this.albumSet = new HashSet<>(albums);
    }
}
