package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.projections.MovieGenreProjection;
import com.devsuperior.movieflix.entities.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre,Long> {


}
