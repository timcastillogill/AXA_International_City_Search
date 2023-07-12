package com.axa.citySearch.domain.services;

import com.axa.citySearch.domain.repos.ICityResult;

import java.util.Collection;

public class CityResult implements ICityResult {
	private Collection<String> nextLetters;
	private Collection<String> nextCities;

	@Override
	public Collection<String> getNextLetters() {
		return nextLetters;
	}

	@Override
	public void setNextLetters(Collection<String> nextLetters) {
		this.nextLetters = nextLetters;
	}

	@Override
	public Collection<String> getNextCities() {
		return nextCities;
	}

	@Override
	public void setNextCities(Collection<String> nextCities) {
		this.nextCities = nextCities;
	}
}
