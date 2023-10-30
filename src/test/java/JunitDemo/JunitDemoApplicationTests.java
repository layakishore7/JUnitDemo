package JunitDemo;

import JunitDemo.entity.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class JunitDemoApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	@Autowired
	private TestH2Repository h2Repository;

	private static RestTemplate restTemplate;

	@BeforeAll
	public static void init() {
		restTemplate = new RestTemplate();
	}

	@BeforeEach
	public void setUp() {
		baseUrl = baseUrl.concat(":").concat(port+"").concat("/movie");
	}

	@Test
	public void testNewMovie() {
		Movie movie = new Movie(1,"Movie1","22-03-03");
		Movie response = restTemplate.postForObject(baseUrl+"/movies",movie,Movie.class);
		assertEquals("Movie1",response.getName());
		assertEquals(1,h2Repository.findAll().size());
	}

	@Test
	@Sql(statements = "INSERT INTO movie (name,date) VALUES ('Movie2','22-07-2023')",executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "DELETE FROM movie WHERE name='Movie2'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetMovies() {
		List<Movie> movies = restTemplate.getForObject(baseUrl+"/movies",List.class);
		assertEquals(1,movies.size());
		assertEquals(1,h2Repository.findAll().size());
	}

}
