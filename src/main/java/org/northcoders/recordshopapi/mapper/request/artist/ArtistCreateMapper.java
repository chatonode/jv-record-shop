package org.northcoders.recordshopapi.mapper.request.artist;

import org.northcoders.recordshopapi.dto.request.artist.ArtistCreateDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;

import java.util.List;

public class ArtistCreateMapper {
    public static Artist toEntity(ArtistCreateDTO albumCreateArtistDTO, List<Album> albums) {
        Artist artist = Artist.builder()
                .fullName(albumCreateArtistDTO.getFullName())
                .albums(albums)
                .build();

        return artist;
    }
}
