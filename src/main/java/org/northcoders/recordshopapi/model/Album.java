package org.northcoders.recordshopapi.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@RequiredArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artistSet;

    @Column(nullable = false)
    private Set<Genre> genreSet;

    @Column(nullable = false)
    private Integer durationInSeconds; // 1:27 => 87

    @Column
    private Integer releaseDate;

    @Column
    private String publisher;

    @Column(nullable = false)
    private Integer priceInPences; // 2.5 => 250

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Integer quantityInStock;

    @Builder
    public Album(String title, List<Artist> artists, List<Genre> genres, Integer durationInSeconds, Integer releaseDate, String publisher, Integer priceInPences, Currency currency) {
        this.title = title;
        this.artistSet = new HashSet<>(artists);
        this.genreSet = new HashSet<>(genres);
        this.durationInSeconds = durationInSeconds;
        this.releaseDate = releaseDate;
        this.publisher = publisher;

        this.quantityInStock = 0;
        this.priceInPences = priceInPences;
        this.currency = currency;
    }
}