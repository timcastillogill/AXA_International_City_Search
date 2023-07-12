import com.axa.citySearch.domain.repos.ICityRepository;
import com.axa.citySearch.domain.repos.ICityResult;
import com.axa.citySearch.domain.services.CityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CityFinderTest {
	private CityFinder cityFinder;
	@Mock
	ICityRepository cityRepository;

	@BeforeEach
	void setup() {
		cityFinder = new CityFinder(cityRepository);
		List<String> cityNames = Arrays.asList("BANGUI", "BANGKOK", "BANGALORE", "BANDUNG", "LA PAZ", "LA PLATA", "LAGOS", "LEEDS");
		given(cityRepository.getAll()).willReturn(cityNames);
	}

	@Test
	public void returns_U_K_A_when_BANG_is_inputted() {
		ICityResult cityResult = cityFinder.search("BANG");
		Collection<String> expectedNextLetters = new HashSet<>(Arrays.asList("U", "K", "A"));
		assertThat(cityResult.getNextLetters()).isEqualTo(expectedNextLetters);
	}

	@Test
	public void returns_cities_with_bang_at_the_beginning_when_BANG_is_inputted() {
		ICityResult cityResult = cityFinder.search("BANG");

		Collection<String> expectedNextCities = new HashSet<>(Arrays.asList("BANGUI", "BANGKOK", "BANGALORE"));

		assertThat(cityResult.getNextCities()).isEqualTo(expectedNextCities);
		verify(cityRepository).getAll();
	}
	@Test
	public void returns_space_and_G_when_LA_is_inputted() {
		ICityResult cityResult = cityFinder.search("LA");
		Collection<String> expectedNextLetters = new HashSet<>(Arrays.asList(" ", "G"));
		assertThat(cityResult.getNextLetters()).isEqualTo(expectedNextLetters);
	}
	@Test
	public void returns_cities_with_la_at_the_beginning_when_la_is_inputted() {
		ICityResult cityResult = cityFinder.search("LA");

		Collection<String> expectedNextLetters = new HashSet<>(Arrays.asList(" ", "G"));
		Collection<String> expectedNextCities = new HashSet<>(Arrays.asList("LA PAZ", "LA PLATA", "LAGOS"));

		assertThat(cityResult.getNextLetters()).isEqualTo(expectedNextLetters);
		assertThat(cityResult.getNextCities()).isEqualTo(expectedNextCities);
		verify(cityRepository).getAll();
	}
	@Test
	public void returns_nothing_when_LA_is_inputted() {
		ICityResult cityResult = cityFinder.search("ZE");
		Collection<String> expectedNextLetters = new HashSet<>(List.of());
		assertThat(cityResult.getNextLetters()).isEqualTo(expectedNextLetters);
	}
	@Test
	public void returns_no_results_when_ze_is_inputted() {
		ICityResult cityResult = cityFinder.search("ZE");

		assertThat(cityResult.getNextLetters()).isEmpty();
		assertThat(cityResult.getNextCities()).isEmpty();
	}
}
