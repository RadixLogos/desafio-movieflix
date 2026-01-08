package com.devsuperior.movieflix.repositories;

import com.devsuperior.movieflix.entities.Movie;
import com.devsuperior.movieflix.projections.MovieGenreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {
    @Query(nativeQuery = true, value = """
            SELECT tb_movie.id, tb_movie.title, tb_movie.sub_title, tb_movie.movie_year, tb_movie.img_url FROM tb_movie
            INNER JOIN tb_genre ON tb_movie.genre_id = tb_genre.id
            WHERE tb_genre.name LIKE CONCAT('%',:genreName)
            ORDER BY tb_movie.title;
            """, countQuery = """
            SELECT COUNT(*) FROM (SELECT tb_movie.id, tb_movie.title, tb_movie.sub_title, tb_movie.movie_year, tb_movie.img_url FROM tb_movie
            INNER JOIN tb_genre ON tb_movie.genre_id = tb_genre.id
            WHERE tb_genre.name LIKE CONCAT('%',:genreName)
            ORDER BY tb_movie.title);
            """)
    List<MovieGenreProjection> getAllMovieCard(String genreName);

    @Query("SELECT obj FROM Movie obj JOIN FETCH obj.genre WHERE obj.id = :id ")
    Movie getMovieWithGenre(Long id);
}
