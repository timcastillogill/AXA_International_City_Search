import com.axa.citySearch.domain.repos.ICityFinder;
import com.axa.citySearch.domain.repos.ICityRepository;
import com.axa.citySearch.domain.repos.ICityResult;
import com.axa.citySearch.domain.services.CityFinder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTest {
	private ICityFinder cityFinder;

	@BeforeEach
	void setUp() {
		List<String> cityNames = Arrays.asList(
				"BANDUNG",
				"BANGUI",
				"BANGKOK",
				"BANGALORE",
				"LA PAZ",
				"LA PLATA",
				"LAGOS",
				"LEEDS",
				"ZARIA",
				"ZHUGAI",
				"ZIBO"
		);
		ICityRepository cityRepository = new InMemoryCityRepository(cityNames);
		cityFinder = new CityFinder(cityRepository);
	}

	@Test
	public void given_bang_returns_matching_cities_and_next_letters() {
		ICityResult cityResult = cityFinder.search("BANG");

		Collection<String> expectedNextLetters = new HashSet<>(Arrays.asList("A", "U", "K"));
		Collection<String> expectedNextCities = new HashSet<>(Arrays.asList("BANGUI", "BANGKOK", "BANGALORE"));

		assertThat(cityResult.getNextLetters()).isEqualTo(expectedNextLetters);
		assertThat(cityResult.getNextCities()).isEqualTo(expectedNextCities);
	}

	@Test
	public void given_ze_returns_nothing() {
		ICityResult cityResult = cityFinder.search("ZE");
		assertThat(cityResult.getNextLetters()).isEmpty();
		assertThat(cityResult.getNextCities()).isEmpty();
	}

	@Test
	public void given_LA_returns_matching_cities_and_next_letters() {
		ICityResult cityResult = cityFinder.search("LA");

		Collection<String> expectedNextLetters = new HashSet<>(Arrays.asList(" ", "G"));
		Collection<String> expectedNextCities = new HashSet<>(Arrays.asList("LA PAZ", "LA PLATA", "LAGOS"));

		assertThat(cityResult.getNextLetters()).isEqualTo(expectedNextLetters);
		assertThat(cityResult.getNextCities()).isEqualTo(expectedNextCities);
	}

	private static class InMemoryCityRepository implements ICityRepository {

		private final List<String> cityNames;

		public InMemoryCityRepository(List<String> cityNames) {
			this.cityNames = cityNames;
		}


		@Override
		public List<String> getAll() {
			return cityNames;
		}
	}
}
