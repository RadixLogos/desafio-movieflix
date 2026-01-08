package com.devsuperior.movieflix.services;
import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.entities.Genre;
import com.devsuperior.movieflix.repositories.GenreRepository;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Transactional(readOnly = true)
    public Page<MovieDetailsDTO> getAllMoviesDetails(Pageable pageable, Long genreId){
        String genreName = "";
        if(genreId != 0) {
            Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genero não encontrado"));
            genreName = genre.getName();
        }
        var response = movieRepository.getMovieDetails(genreName);
        List<MovieDetailsDTO> movieDetailsDTOList = new ArrayList<>();
        response.forEach(mgp->{
            var movieDetailsDTO = new MovieDetailsDTO();
            movieDetailsDTO.setId(mgp.getId());
            movieDetailsDTO.setTitle(mgp.getTitle());
            movieDetailsDTO.setSubTitle(mgp.getSubTitle());
            movieDetailsDTO.setSynopsis(mgp.getSynopsis());
            movieDetailsDTO.setYear(mgp.getMovieYear());
            movieDetailsDTO.setImgUrl(mgp.getImgUrl());
            movieDetailsDTO.setGenre(new GenreDTO(mgp.getGenreId(),mgp.getGenreName()));
            movieDetailsDTOList.add(movieDetailsDTO);
        });
        return new PageImpl<>(movieDetailsDTOList, pageable,movieDetailsDTOList.size());
    }

}
