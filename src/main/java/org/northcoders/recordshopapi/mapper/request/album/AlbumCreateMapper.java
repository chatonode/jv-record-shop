package org.northcoders.recordshopapi.mapper.request.album;

import org.northcoders.recordshopapi.dto.request.album.AlbumCreateDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
}
