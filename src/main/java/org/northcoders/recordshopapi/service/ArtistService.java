package org.northcoders.recordshopapi.service;

import org.northcoders.recordshopapi.model.Artist;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ArtistService {

    List<Artist> getAllArtists();

    List<Artist> getArtistsByName(String fullName);

    Artist getArtistById(Long id); // OR NotFoundException
}
