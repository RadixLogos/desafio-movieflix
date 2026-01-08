package com.devsuperior.movieflix.services;
import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieCardDTO;
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
    public Page<MovieCardDTO> getAllMoviesDetails(Pageable pageable, Long genreId){
        String genreName = "";
        if(genreId != 0) {
            Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("Genero não encontrado"));
            genreName = genre.getName();
        }
        var response = movieRepository.getAllMovieCard(genreName);
        List<MovieCardDTO> movieCardDTOList = new ArrayList<>();
        response.forEach(mgp->{
            var movieCardDTO = new MovieCardDTO();
            movieCardDTO.setId(mgp.getId());
            movieCardDTO.setTitle(mgp.getTitle());
            movieCardDTO.setSubTitle(mgp.getSubTitle());
            movieCardDTO.setYear(mgp.getMovieYear());
            movieCardDTO.setImgUrl(mgp.getImgUrl());
            movieCardDTOList.add(movieCardDTO);
        });
        return new PageImpl<>(movieCardDTOList, pageable, movieCardDTOList.size());
    }

}
