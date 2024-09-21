package org.northcoders.recordshopapi.mapper.request.album;

import org.northcoders.recordshopapi.dto.request.album.AlbumUpdateDTO;
import org.northcoders.recordshopapi.model.Album;
import org.northcoders.recordshopapi.model.Artist;
import org.northcoders.recordshopapi.model.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlbumUpdateMapper {

    public static Album toEntity(Album existingAlbum, AlbumUpdateDTO albumUpdateDTO, List<Artist> artists, List<Genre> genres) {
        if (albumUpdateDTO.getTitle() != null) {
            existingAlbum.setTitle(albumUpdateDTO.getTitle());
        }

        Set<Artist> updatedArtists = new HashSet<>(artists);
        existingAlbum.setArtistSet(updatedArtists);

        Set<Genre> updatedGenres = new HashSet<>(genres);
        existingAlbum.setGenreSet(updatedGenres);

        if (albumUpdateDTO.getDurationInSeconds() != null) {
            existingAlbum.setDurationInSeconds(albumUpdateDTO.getDurationInSeconds());
        }

        if (albumUpdateDTO.getImageUrl() != null) {
            existingAlbum.setImageUrl(albumUpdateDTO.getImageUrl());
        }

        if (albumUpdateDTO.getReleaseYear() != null) {
            existingAlbum.setReleaseYear(albumUpdateDTO.getReleaseYear());
        }

        if (albumUpdateDTO.getPublisher() != null) {
            existingAlbum.setPublisher(albumUpdateDTO.getPublisher());
        }

        if (albumUpdateDTO.getFormat() != null) {
            existingAlbum.setFormat(albumUpdateDTO.getFormat());
        }

        if (albumUpdateDTO.getPriceInPences() != null) {
            existingAlbum.setPriceInPences(albumUpdateDTO.getPriceInPences());
        }

        if (albumUpdateDTO.getCurrency() != null) {
            existingAlbum.setCurrency(albumUpdateDTO.getCurrency());
        }

        if (albumUpdateDTO.getQuantityInStock() != null) {
            existingAlbum.setQuantityInStock(albumUpdateDTO.getQuantityInStock());
        }

        return existingAlbum;
    }
}
