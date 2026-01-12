package com.devsuperior.movieflix.services;
import com.devsuperior.movieflix.dto.ReviewDTO;
import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.repositories.MovieRepository;
import com.devsuperior.movieflix.repositories.ReviewRepository;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthService authService;

    @Transactional
    public ReviewDTO insertReview(ReviewDTO reviewDTO){
        Review newReview = new Review();
        var user = authService.authenticated();
        var movie = movieRepository.findById(reviewDTO.getMovieId())
                .orElseThrow(()->new ResourceNotFoundException("Filme não encontrado"));

        newReview.setText(reviewDTO.getText());
        newReview.setUser(user);
        newReview.setMovie(movie);
        newReview = reviewRepository.save(newReview);
        return new ReviewDTO(newReview.getId(),newReview.getText(),newReview.getUser().getId(),newReview.getMovie().getId(), user.getName(), user.getEmail());
    }
}
