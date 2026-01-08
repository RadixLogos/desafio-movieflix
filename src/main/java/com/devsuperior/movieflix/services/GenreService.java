package com.devsuperior.movieflix.services;
import com.devsuperior.movieflix.dto.GenreDTO;
import com.devsuperior.movieflix.dto.MovieDetailsDTO;
import com.devsuperior.movieflix.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Transactional(readOnly = true)
    public List<GenreDTO> findAllGenres(){
        return genreRepository.findAll().stream().map(GenreDTO::genreToDTO).toList();
    }


}
