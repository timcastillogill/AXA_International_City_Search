package com.axa.citySearch.domain.services;


import com.axa.citySearch.domain.repos.ICityFinder;
import com.axa.citySearch.domain.repos.ICityRepository;
import com.axa.citySearch.domain.repos.ICityResult;

import java.util.Collection;
import java.util.HashSet;

public class CityFinder implements ICityFinder {

	private final ICityRepository cityRepository;

	public CityFinder(ICityRepository cityRepository) {
		this.cityRepository = cityRepository;
	}

	@Override
	public ICityResult search(String searchString) {
		ICityResult cityResult = new CityResult();

		Collection<String> nextLetters = new HashSet<>();
		Collection<String> nextCities = new HashSet<>();

		returnNextLettersAndNextCities(searchString, nextLetters, nextCities);
		cityResult.setNextLetters(nextLetters);
		cityResult.setNextCities(nextCities);
		return cityResult;
	}

	private void returnNextLettersAndNextCities(String searchString, Collection<String> nextLetters, Collection<String> nextCities) {
		for (String cityName : cityRepository.getAll()) {
			if (cityName.startsWith(searchString)) {
				if (cityName.length() > searchString.length()) {
					String nextChar = String.valueOf(cityName.charAt(searchString.length()));
					nextLetters.add(nextChar);
				}
				nextCities.add(cityName);
			}
		}
	}
}
