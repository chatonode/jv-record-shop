package org.northcoders.recordshopapi.util.service;

import org.northcoders.recordshopapi.model.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


public class TestEntityFactory {
    public AtomicLong currentArtistId;
    public AtomicLong currentGenreId;
    public AtomicLong currentAlbumId;

    public Artist eltonJohn, davidBowie, michaelJackson, britneySpears, tarkan, madonna, billieEilish, duaLipa;
    public Artist elvisPresley;

    public Genre rock, pop, dancePop, electronic, funk, world, jazz;
    public Genre rockRoll, country;

    public Album goodbyeYellowBrickRoad, heroes, bad, britney, karma, rayOfLight, whenWeAllFallAsleep, futureNostalgia;

    private void createArtists() {
        eltonJohn = Artist.builder()
                .fullName("Elton John")
                .albums(List.of())
                .build();
        eltonJohn.setId(currentArtistId.incrementAndGet());
        eltonJohn.setCreatedDate(new Date());
        eltonJohn.setUpdatedDate(new Date());

        davidBowie = Artist.builder()
                .fullName("David Bowie")
                .albums(List.of())
                .build();
        davidBowie.setId(currentArtistId.incrementAndGet());
        davidBowie.setCreatedDate(new Date());
        davidBowie.setUpdatedDate(new Date());

        michaelJackson = Artist.builder()
                .fullName("Michael Jackson")
                .albums(List.of())
                .build();
        michaelJackson.setId(currentArtistId.incrementAndGet());
        michaelJackson.setCreatedDate(new Date());
        michaelJackson.setUpdatedDate(new Date());

        britneySpears = Artist.builder()
                .fullName("Britney Spears")
                .albums(List.of())
                .build();
        britneySpears.setId(currentArtistId.incrementAndGet());
        britneySpears.setCreatedDate(new Date());
        britneySpears.setUpdatedDate(new Date());

        tarkan = Artist.builder()
                .fullName("Tarkan")
                .albums(List.of())
                .build();
        tarkan.setId(currentArtistId.incrementAndGet());
        tarkan.setCreatedDate(new Date());
        tarkan.setUpdatedDate(new Date());

        madonna = Artist.builder()
                .fullName("Madonna")
                .albums(List.of())
                .build();
        madonna.setId(currentArtistId.incrementAndGet());
        madonna.setCreatedDate(new Date());
        madonna.setUpdatedDate(new Date());

        billieEilish = Artist.builder()
                .fullName("Billie Eilish")
                .albums(List.of())
                .build();
        billieEilish.setId(currentArtistId.incrementAndGet());
        billieEilish.setCreatedDate(new Date());
        billieEilish.setUpdatedDate(new Date());

        duaLipa = Artist.builder()
                .fullName("Dua Lipa")
                .albums(List.of())
                .build();
        duaLipa.setId(currentArtistId.incrementAndGet());
        duaLipa.setCreatedDate(new Date());
        duaLipa.setUpdatedDate(new Date());

        elvisPresley = Artist.builder()
                .fullName("Elvis Presley")
                .albums(List.of())
                .build();
        // Leave it for creation test
//        elvisPresley.setId(currentArtistId.incrementAndGet());
//        elvisPresley.setCreatedDate(new Date());
//        elvisPresley.setUpdatedDate(new Date());
    }

    private void createGenres() {

        rock = Genre.builder()
                .name(GenreType.ROCK)
                .build();
        rock.setId(currentGenreId.incrementAndGet());
        rock.setAlbumSet(new HashSet<>());
        rock.setCreatedDate(new Date());
        rock.setUpdatedDate(new Date());

        pop = Genre.builder()
                .name(GenreType.POP)
                .build();
        pop.setId(currentGenreId.incrementAndGet());
        pop.setAlbumSet(new HashSet<>());
        pop.setCreatedDate(new Date());
        pop.setUpdatedDate(new Date());

        dancePop = Genre.builder()
                .name(GenreType.DANCE_POP)
                .build();
        dancePop.setId(currentGenreId.incrementAndGet());
        dancePop.setAlbumSet(new HashSet<>());
        dancePop.setCreatedDate(new Date());
        dancePop.setUpdatedDate(new Date());

        electronic = Genre.builder()
                .name(GenreType.ELECTRONIC)
                .build();
        electronic.setId(currentGenreId.incrementAndGet());
        electronic.setAlbumSet(new HashSet<>());
        electronic.setCreatedDate(new Date());
        electronic.setUpdatedDate(new Date());

        funk = Genre.builder()
                .name(GenreType.FUNK)
                .build();
        funk.setId(currentGenreId.incrementAndGet());
        funk.setAlbumSet(new HashSet<>());
        funk.setCreatedDate(new Date());
        funk.setUpdatedDate(new Date());

        world = Genre.builder()
                .name(GenreType.WORLD)
                .build();
        world.setId(currentGenreId.incrementAndGet());
        world.setAlbumSet(new HashSet<>());
        world.setCreatedDate(new Date());
        world.setUpdatedDate(new Date());

        jazz = Genre.builder()
                .name(GenreType.JAZZ)
                .build(); // No Albums own this genre
        jazz.setId(currentGenreId.incrementAndGet());
        jazz.setAlbumSet(new HashSet<>());
        jazz.setCreatedDate(new Date());
        jazz.setUpdatedDate(new Date());

        rockRoll = Genre.builder()
                .name(GenreType.ROCK_ROLL)
                .build();
        // Leave it for creation test
//        rockRoll.setId(currentGenreId.incrementAndGet());
//        rockRoll.setAlbumSet(new HashSet<>());

        country = Genre.builder()
                .name(GenreType.COUNTRY)
                .build();
        // Leave it for creation test
//        country.setId(currentGenreId.incrementAndGet());
//        country.setAlbumSet(new HashSet<>());
    }

    private void createAlbums() {

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
        createArtists();
        createGenres();
        createAlbums();
    }

    public TestEntityFactory() {
        currentArtistId = new AtomicLong(0L);
        currentGenreId = new AtomicLong(0L);
        currentAlbumId = new AtomicLong(0L);
    }
}
