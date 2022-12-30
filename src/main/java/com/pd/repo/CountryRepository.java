package com.pd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pd.binding.UserCountry;

public interface CountryRepository extends JpaRepository<UserCountry, Integer> {

	@Query("select c from UserCountry c ")
	public List<UserCountry> getCountryNames();

}
