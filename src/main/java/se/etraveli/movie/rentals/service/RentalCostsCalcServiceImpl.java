package se.etraveli.movie.rentals.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.etraveli.movie.rentals.business.costs.MovieRent;
import se.etraveli.movie.rentals.model.MovieCode;
import se.etraveli.movie.rentals.model.MovieRental;
import se.etraveli.movie.rentals.model.MovieRentalCosts;
import se.etraveli.movie.rentals.business.bonus.FrequentEnterPoints;

@Service
@RequiredArgsConstructor
public class RentalCostsCalcServiceImpl implements RentalCostsCalcService {

    private final MovieDetailsService movieDetailsService;
    private final FrequentEnterPoints frequentEnterPointsService;

    @Override
    public MovieRentalCosts calcRentalAmountAndBonus(MovieRental movieRental) {

        // Movie rental costs object
        MovieRentalCosts movieRentalCost = new MovieRentalCosts(movieRental);

        // Fetch Movie Details
        MovieCode movieCode = movieDetailsService.getMovieCode(movieRental.getMovieId());
        int days = movieRental.getDays();

        // Calculate Movie Rent and update movies rental costs object
        movieRentalCost.setCost(MovieRent.movieRentAmount(movieCode, days));

        //Add frequent bonus points
        movieRentalCost.setBonus(frequentEnterPointsService.getFrequentEnterPoints(movieCode, days));

        return movieRentalCost;
    }
}
