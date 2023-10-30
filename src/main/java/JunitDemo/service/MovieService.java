package JunitDemo.service;

import JunitDemo.entity.Movie;
import JunitDemo.repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo movieRepo;

    public List<Movie> getallMovies() {

        return movieRepo.findAll();
    }


    public Movie newMovie(Movie movie) {
        return movieRepo.save(movie);
    }

    public Movie updateName(int id,String name) throws Exception {
        Optional<Movie> movie = movieRepo.findById(id);
        if (movie.isPresent()){
            Movie movie1 = movie.get();
            movie1.setName(name);
            return movieRepo.save(movie1);
        } else {
            throw new Exception("Invalid id");
        }
    }


    public void deleteMovie(int id) throws Exception {
        Optional<Movie> movie = movieRepo.findById(id);
        if (movie.isPresent()){
            movieRepo.deleteById(id);
        } else {
            throw new Exception("Invalid Id");
        }
    }


    public Movie getMovieById(int id) throws Exception {
        Optional<Movie> movie = movieRepo.findById(id);
        if (movie.isPresent()){
            return movie.get();
        } else {
            throw new Exception("Invalid Id");
        }
    }
}