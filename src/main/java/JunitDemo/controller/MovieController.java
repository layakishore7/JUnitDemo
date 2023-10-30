package JunitDemo.controller;

import JunitDemo.entity.Movie;
import JunitDemo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @GetMapping("/movies")
    public List<Movie> getmovies(){
        return movieService.getallMovies();
    }
    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.newMovie(movie);
    }

    @PutMapping("/movies")
    public Movie updateName(@RequestParam int id,@RequestParam String name) throws Exception {
        return movieService.updateName(id,name);
    }

    @DeleteMapping("/movies")
    public void  deleteById(@RequestParam int id) throws Exception {
        movieService.deleteMovie(id);
    }
}
