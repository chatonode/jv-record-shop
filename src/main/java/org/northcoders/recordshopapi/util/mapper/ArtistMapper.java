//package org.northcoders.recordshopapi.util.mapper;
//
//import org.northcoders.recordshopapi.dto.request.AlbumCreateArtistDTO;
//import org.northcoders.recordshopapi.model.Artist;
//
//import java.util.List;
//
//public class ArtistMapper {
//    public static Artist toEntity(AlbumCreateArtistDTO albumCreateArtistDTO) {
//        return Artist.builder()
//                .fullName(albumCreateArtistDTO.getFullName())
//                .albums(List.of())
//                .build();
//    }
//
//    public static AlbumCreateArtistDTO toDTO(Artist artist) {
//        return AlbumCreateArtistDTO.builder()
//                .fullName(artist.getFullName())
//                .build();
//    }
//}
