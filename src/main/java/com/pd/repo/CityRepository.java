package com.pd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.pd.binding.UserCity;

public interface CityRepository extends JpaRepository<UserCity, Integer> {
	
	@Query("select cityName from UserCity where stateId=:stateId")
	public List<String> getCities(Integer stateId);
}
