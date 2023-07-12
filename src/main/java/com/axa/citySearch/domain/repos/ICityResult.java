package com.axa.citySearch.domain.repos;


import java.util.Collection;

public interface ICityResult {

	Collection<String> getNextLetters();

	void setNextLetters(Collection<String> nextLetters);

	Collection<String> getNextCities();

	void setNextCities(Collection<String> nextCities);

}
