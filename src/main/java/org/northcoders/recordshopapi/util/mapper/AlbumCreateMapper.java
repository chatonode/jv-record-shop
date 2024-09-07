package org.northcoders.recordshopapi.util.mapper;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;

import java.util.List;


public class AlbumCreateMapper {
    public static Album toEntity(AlbumCreateDTO albumCreateDTO, List<Artist> artists, List<Genre> genres) {
        return Album.builder()
                .title(albumCreateDTO.getTitle())
                .artists(artists)
                .genres(genres)
                .durationInSeconds(albumCreateDTO.getDurationInSeconds())
                .imageUrl(albumCreateDTO.getImageUrl())
                .releaseYear(albumCreateDTO.getReleaseYear())
                .publisher(albumCreateDTO.getPublisher())
                .format(albumCreateDTO.getFormat())
                .priceInPences(albumCreateDTO.getPriceInPences())
                .currency(albumCreateDTO.getCurrency())
                .build();
    }

    public static AlbumCreateDTO toDTO(Album album, List<Long> artistIds, List<Long> genreIds) {
        return AlbumCreateDTO.builder()
                .title(album.getTitle())
                .artistIds(artistIds)
                .genreIds(genreIds)
                .durationInSeconds(album.getDurationInSeconds())
                .imageUrl(album.getImageUrl())
                .releaseYear(album.getReleaseYear())
                .publisher(album.getPublisher())
                .priceInPences(album.getPriceInPences())
                .currency(album.getCurrency())
                .format(album.getFormat())
                .build();
    }
}
