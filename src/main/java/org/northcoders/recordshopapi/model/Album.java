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
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "albums_artists",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private Set<Artist> artistSet;

    @Column(nullable = false)
    @ManyToMany(cascade = CascadeType.PERSIST) // During creations?
    @JoinTable(
            name = "albums_genres",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genreSet;

    @Column(nullable = false)
    private Integer durationInSeconds; // 1:27 => 87

    @Column
    private String imageUrl;

    @Column
    private Integer releaseYear;

    @Column
    private String publisher;

    @Column(nullable = false)
    private Integer priceInPences; // 2.5 => 250

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private Integer quantityInStock;

    @Column(nullable = false)
    private Format format;

    @Builder
    public Album(String title, List<Artist> artists, List<Genre> genres, Integer durationInSeconds, String imageUrl, Integer releaseYear, String publisher, Format format, Integer priceInPences, Currency currency) {
        this.title = title;
        this.artistSet = new HashSet<>(artists);
        this.genreSet = new HashSet<>(genres);
        this.durationInSeconds = durationInSeconds;
        this.imageUrl = imageUrl;
        this.releaseYear = releaseYear;
        this.publisher = publisher;

        this.format = format;
        this.quantityInStock = 0;
        this.priceInPences = priceInPences;
        this.currency = currency;
    }
}