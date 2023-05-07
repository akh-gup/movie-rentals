package se.etraveli.movie.rentals.utils;

import org.springframework.util.StringUtils;
import se.etraveli.movie.rentals.constants.Constants;
import se.etraveli.movie.rentals.exception.InvalidCustomerException;
import se.etraveli.movie.rentals.exception.MovieRentalException;
import se.etraveli.movie.rentals.model.Customer;

public class CustomerValidation {

    public static void isValidCustomer(Customer customer){

        if(null == customer){

            throw new InvalidCustomerException(Constants.EMPTY_CUSTOMER);

        } else if (!StringUtils.hasLength(customer.getName())){

            throw new InvalidCustomerException(Constants.EMPTY_CUSTOMER_NAME);

        } else if (null == customer.getRentals() || customer.getRentals().isEmpty()){

            throw new MovieRentalException(Constants.EMPTY_MOVIE_RENTALS);

        } else if (!customer.getRentals().stream().allMatch(movieRental -> movieRental.getDays() != 0 && StringUtils.hasLength(movieRental.getMovieId()))){

            throw new MovieRentalException(Constants.INVALID_MOVIE_RENTAL);
        }

    }
}