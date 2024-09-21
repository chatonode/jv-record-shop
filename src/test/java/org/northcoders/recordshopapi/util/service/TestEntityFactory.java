package org.northcoders.recordshopapi.util.service;

import org.northcoders.recordshopapi.dto.response.album.AlbumResponseDTO;
import org.northcoders.recordshopapi.dto.response.album.FlattenedArtistDTO;
import org.northcoders.recordshopapi.dto.response.album.FlattenedGenreDTO;
import org.northcoders.recordshopapi.dto.response.artist.ArtistResponseDTO;
import org.northcoders.recordshopapi.dto.response.artist.FlattenedAlbumDTO;
import org.northcoders.recordshopapi.dto.response.genre.GenreResponseDTO;
import org.northcoders.recordshopapi.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


public class TestEntityFactory {
    public AtomicLong currentArtistId;
    public AtomicLong currentGenreId;
    public AtomicLong currentAlbumId;

    public Artist eltonJohn, davidBowie, michaelJackson, britneySpears, tarkan, madonna, billieEilish, duaLipa;
    public ArtistResponseDTO eltonJohnResponseDTO, davidBowieResponseDTO, michaelJacksonResponseDTO, britneySpearsResponseDTO,
            tarkanResponseDTO, madonnaResponseDTO, billieEilishResponseDTO, duaLipaResponseDTO;
    public Artist beyonce; // id: 9L

    public Genre rock, pop, dancePop, electronic, funk, world, jazz;
    public GenreResponseDTO rockResponseDTO, popResponseDTO, dancePopResponseDTO, electronicResponseDTO, funkResponseDTO, worldResponseDTO, jazzResponseDTO;

    public Album goodbyeYellowBrickRoad, heroes, bad, britney, karma, rayOfLight, whenWeAllFallAsleep, futureNostalgia;
    public AlbumResponseDTO goodbyeYellowBrickRoadResponseDTO, heroesResponseDTO, badResponseDTO, britneyResponseDTO, karmaResponseDTO, rayOfLightResponseDTO, whenWeAllFallAsleepResponseDTO, futureNostalgiaResponseDTO;

    // Entity creation & initialization
    private Artist createArtist(String fullName) {
        Artist artist = Artist.builder()
                .fullName(fullName)
                .albums(List.of())
                .build();
        artist.setId(currentArtistId.incrementAndGet());
        artist.setCreatedDate(new Date());
        artist.setUpdatedDate(new Date());

        return artist;
    }
    private Genre createGenre(GenreType genreType) {
        Genre genre = Genre.builder()
                .name(genreType)
                .build();
        genre.setId(currentGenreId.incrementAndGet());
        genre.setAlbumSet(new HashSet<>());
        genre.setCreatedDate(new Date());
        genre.setUpdatedDate(new Date());

        return genre;
    }

    private void initializeArtists() {
        eltonJohn = createArtist("Elton John");
        davidBowie = createArtist("David Bowie");
        michaelJackson = createArtist("Michael Jackson");
        britneySpears = createArtist("Britney Spears");
        tarkan = createArtist("Tarkan");
        madonna = createArtist("Madonna");
        billieEilish = createArtist("Billie Eilish");
        duaLipa = createArtist("Dua Lipa");
        beyonce = createArtist("Beyoncé"); // Album-less Artist
    }
    private void initializeGenres() {
        rock = createGenre(GenreType.ROCK);
        pop = createGenre(GenreType.POP);
        dancePop = createGenre(GenreType.DANCE_POP);
        electronic = createGenre(GenreType.ELECTRONIC);
        funk = createGenre(GenreType.FUNK);
        world = createGenre(GenreType.WORLD);
        jazz = createGenre(GenreType.JAZZ); // Album-less Genre
    }
    private void initializeAlbums() {

        goodbyeYellowBrickRoad = Album.builder()
                .title("Goodbye Yellow Brick Road")
                .artists(List.of(eltonJohn))
                .genres(List.of(rock, pop))
                .durationInSeconds(4000) // Approx. 66 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/8/86/Elton_John_-_Goodbye_Yellow_Brick_Road.jpg")
                .releaseYear(1973)
                .format(Format.CD)
                .publisher("DJM Records")
                .priceInPences(1999) // £19.99
                .currency(Currency.GBP)
                .build();
        goodbyeYellowBrickRoad.setId(currentAlbumId.incrementAndGet());
        goodbyeYellowBrickRoad.setQuantityInStock(0);
        goodbyeYellowBrickRoad.setCreatedDate(new Date());
        goodbyeYellowBrickRoad.setUpdatedDate(new Date());

        heroes = Album.builder()
                .title("Heroes")
                .artists(List.of(davidBowie))
                .genres(List.of(rock, electronic))
                .durationInSeconds(2600) // Approx. 43 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/7/7b/David_Bowie_-_Heroes.png")
                .releaseYear(1977)
                .format(Format.Vinyl)
                .publisher("RCA Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();
        heroes.setId(currentAlbumId.incrementAndGet());
        heroes.setQuantityInStock(0);
        heroes.setCreatedDate(new Date());
        heroes.setUpdatedDate(new Date());

        bad = Album.builder()
                .title("Bad")
                .artists(List.of(michaelJackson))
                .genres(List.of(pop, funk))
                .durationInSeconds(3200) // Approx. 53 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/5/51/Michael_Jackson_-_Bad.png")
                .releaseYear(1987)
                .format(Format.CD)
                .publisher("Epic Records")
                .priceInPences(1899) // £18.99
                .currency(Currency.GBP)
                .build();
        bad.setId(currentAlbumId.incrementAndGet());
        bad.setQuantityInStock(0);
        bad.setCreatedDate(new Date());
        bad.setUpdatedDate(new Date());

        britney = Album.builder()
                .title("Britney")
                .artists(List.of(britneySpears))
                .genres(List.of(pop))
                .durationInSeconds(2600) // Approx. 43 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/0/0c/Britney_Spears_-_Britney.png")
                .releaseYear(2001)
                .format(Format.Cassette)
                .publisher("Jive Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();
        britney.setId(currentAlbumId.incrementAndGet());
        britney.setQuantityInStock(0);
        britney.setCreatedDate(new Date());
        britney.setUpdatedDate(new Date());

        karma = Album.builder()
                .title("Karma")
                .artists(List.of(tarkan))
                .genres(List.of(pop, world))
                .durationInSeconds(3100) // Approx. 51 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/1/11/Tarkan_-_Karma_%28Tarkan_album%29.jpg")
                .releaseYear(2001)
                .format(Format.Vinyl)
                .publisher("Universal Music Turkey")
                .priceInPences(1499) // £14.99
                .currency(Currency.GBP)
                .build();
        karma.setId(currentAlbumId.incrementAndGet());
        karma.setQuantityInStock(0);
        karma.setCreatedDate(new Date());
        karma.setUpdatedDate(new Date());

        rayOfLight = Album.builder()
                .title("Ray of Light")
                .artists(List.of(madonna))
                .genres(List.of(pop, electronic))
                .durationInSeconds(3200) // Approx. 53 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/d/dd/Ray_of_Light_Madonna.png")
                .releaseYear(1998)
                .format(Format.CD)
                .publisher("Warner Bros. Records")
                .priceInPences(1799) // £17.99
                .currency(Currency.GBP)
                .build();
        rayOfLight.setId(currentAlbumId.incrementAndGet());
        rayOfLight.setQuantityInStock(0);
        rayOfLight.setCreatedDate(new Date());
        rayOfLight.setUpdatedDate(new Date());

        whenWeAllFallAsleep = Album.builder()
                .title("When We All Fall Asleep, Where Do We Go?")
                .artists(List.of(billieEilish))
                .genres(List.of(pop))
                .durationInSeconds(2600) // Approx. 43 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/3/38/When_We_All_Fall_Asleep%2C_Where_Do_We_Go%3F.png")
                .releaseYear(2019)
                .format(Format.Vinyl)
                .publisher("Interscope Records")
                .priceInPences(1499) // £14.99
                .currency(Currency.GBP)
                .build();
        whenWeAllFallAsleep.setId(currentAlbumId.incrementAndGet());
        whenWeAllFallAsleep.setQuantityInStock(0);
        whenWeAllFallAsleep.setCreatedDate(new Date());
        whenWeAllFallAsleep.setUpdatedDate(new Date());

        futureNostalgia = Album.builder()
                .title("Future Nostalgia")
                .artists(List.of(duaLipa))
                .genres(List.of(pop, dancePop))
                .durationInSeconds(2300) // Approx. 38 minutes
                .imageUrl("https://upload.wikimedia.org/wikipedia/en/f/f5/Dua_Lipa_-_Future_Nostalgia_%28Official_Album_Cover%29.png")
                .releaseYear(2020)
                .format(Format.CD)
                .publisher("Warner Records")
                .priceInPences(1599) // £15.99
                .currency(Currency.GBP)
                .build();
        futureNostalgia.setId(currentAlbumId.incrementAndGet());
        futureNostalgia.setQuantityInStock(2);
        futureNostalgia.setCreatedDate(new Date());
        futureNostalgia.setUpdatedDate(new Date());
    }
    public void initialiseAllEntities() {
        initializeArtists();
        initializeGenres();
        initializeAlbums();
    }

    // DTO creation & initialization
    public static AlbumResponseDTO createAlbumResponseDTO(Album album) {
        return AlbumResponseDTO.builder()
                .id(album.getId())
                .title(album.getTitle())
                .artists(album.getArtistSet().stream()
                        .map(artist -> FlattenedArtistDTO.builder()
                                .id(artist.getId())
                                .fullName(artist.getFullName())
                                .build())
                        .collect(Collectors.toList()))
                .genres(album.getGenreSet().stream()
                        .map(genre -> FlattenedGenreDTO.builder()
                                .id(genre.getId())
                                .name(genre.getName())
                                .build())
                        .collect(Collectors.toList()))
                .durationInSeconds(album.getDurationInSeconds())
                .imageUrl(album.getImageUrl())
                .releaseYear(album.getReleaseYear())
                .publisher(album.getPublisher())
                .priceInPences(album.getPriceInPences())
                .currency(album.getCurrency())
                .quantityInStock(album.getQuantityInStock())
                .format(album.getFormat())
                .createdDate(album.getCreatedDate())
                .updatedDate(album.getUpdatedDate())
                .build();
    }
    public static ArtistResponseDTO createArtistResponseDTO(Artist artist) {
        return ArtistResponseDTO.builder()
                .id(artist.getId())
                .fullName(artist.getFullName())
                .albums(artist.getAlbumSet().stream()
                        .map(album -> FlattenedAlbumDTO.builder()
                                .id(album.getId())
                                .title(album.getTitle())
                                .build())
                        .collect(Collectors.toList()))
                .createdDate(artist.getCreatedDate())
                .updatedDate(artist.getUpdatedDate())
                .build();
    }
    public static GenreResponseDTO createGenreResponseDTO(Genre genre) {
        return GenreResponseDTO.builder()
                .id(genre.getId())
                .name(genre.getName())
                .albums(genre.getAlbumSet().stream()
                        .map(album -> org.northcoders.recordshopapi.dto.response.genre.FlattenedAlbumDTO.builder()
                                .id(album.getId())
                                .title(album.getTitle())
                                .build())
                        .collect(Collectors.toList()))
                .createdDate(genre.getCreatedDate())
                .updatedDate(genre.getUpdatedDate())
                .build();
    }

    public void initializeArtistResponseDTOs() {
        eltonJohnResponseDTO = createArtistResponseDTO(eltonJohn);
        davidBowieResponseDTO = createArtistResponseDTO(davidBowie);
        michaelJacksonResponseDTO = createArtistResponseDTO(michaelJackson);
        britneySpearsResponseDTO = createArtistResponseDTO(britneySpears);
        tarkanResponseDTO = createArtistResponseDTO(tarkan);
        madonnaResponseDTO = createArtistResponseDTO(madonna);
        billieEilishResponseDTO = createArtistResponseDTO(billieEilish);
        duaLipaResponseDTO = createArtistResponseDTO(duaLipa);
    }
    public void initializeGenreResponseDTOs() {
        rockResponseDTO = createGenreResponseDTO(rock);
        popResponseDTO = createGenreResponseDTO(pop);
        dancePopResponseDTO = createGenreResponseDTO(dancePop);
        electronicResponseDTO = createGenreResponseDTO(electronic);
        funkResponseDTO = createGenreResponseDTO(funk);
        worldResponseDTO = createGenreResponseDTO(world);
        jazzResponseDTO = createGenreResponseDTO(jazz);
    }
    public void initializeAlbumResponseDTOs() {
        goodbyeYellowBrickRoadResponseDTO = createAlbumResponseDTO(goodbyeYellowBrickRoad);
        heroesResponseDTO = createAlbumResponseDTO(heroes);
        badResponseDTO = createAlbumResponseDTO(bad);
        britneyResponseDTO = createAlbumResponseDTO(britney);
        karmaResponseDTO = createAlbumResponseDTO(karma);
        rayOfLightResponseDTO = createAlbumResponseDTO(rayOfLight);
        whenWeAllFallAsleepResponseDTO = createAlbumResponseDTO(whenWeAllFallAsleep);
        futureNostalgiaResponseDTO = createAlbumResponseDTO(futureNostalgia);
    }

    public TestEntityFactory() {
        currentArtistId = new AtomicLong(0L);
        currentGenreId = new AtomicLong(0L);
        currentAlbumId = new AtomicLong(0L);
    }
}
