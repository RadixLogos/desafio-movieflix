package com.devsuperior.movieflix.projections;

public interface MovieGenreProjection {
    Long getId();
    String getTitle();
    String getSubTitle();
    String getSynopsis();
    Integer getMovieYear();
    String getImgUrl();
    String getGenreName();
    Long getGenreId();
}
