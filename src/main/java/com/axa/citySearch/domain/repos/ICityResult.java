package com.axa.citySearch.domain.repos;


import com.axa.citySearch.Generic;

import java.util.Collection;
import java.util.List;

public interface ICityResult {
	List<String> cities = Generic.cityNames;

	Collection<String> getNextLetters();

	void setNextLetters(Collection<String> nextLetters);

	Collection<String> getNextCities();

	void setNextCities(Collection<String> nextCities);

}
