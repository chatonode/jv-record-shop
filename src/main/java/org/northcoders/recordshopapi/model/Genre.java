package org.northcoders.recordshopapi.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;
import java.util.HashSet;
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

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdDate;

    @LastModifiedDate // Modifies after each .save()
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Builder
    public Genre(GenreType name) {
        this.name = name;
        this.albumSet = new HashSet<>();

        this.createdDate = new Date();
        this.updatedDate = this.createdDate;
    }
}
