package org.northcoders.recordshopapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column
    private String title;

    @Column
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artistSet;

    @Column
    private Set<Genre> genreSet;

    @Builder
    public Album(String title, List<Artist> artists, List<Genre> genres) {
        this.title = title;
        this.artistSet = new HashSet<>(artists);
        this.genreSet = new HashSet<>(genres);
    }
}