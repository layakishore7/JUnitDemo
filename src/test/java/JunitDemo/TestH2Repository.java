package JunitDemo;

import JunitDemo.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestH2Repository extends JpaRepository<Movie,Integer> {
}
