package com.pd.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pd.binding.LoginForm;
import com.pd.binding.UnlockAccount;
import com.pd.binding.User;
import com.pd.binding.UserCountry;
import com.pd.binding.UserState;
import com.pd.repo.CityRepository;
import com.pd.repo.CountryRepository;
import com.pd.repo.StateRepository;
import com.pd.repo.UserRepository;
import com.pd.utils.GenerateRandomPassword;
import com.pd.utils.ObjectToMapConversion;

@Service
public class UserMgmtServiceImpl implements UserMgmtService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private CountryRepository countryRepo;

	@Autowired
	private StateRepository stateRepo;
	@Autowired
	private CityRepository cityRepo;

	@Autowired
	private ModelMapper modelmpMapper;

	@Override
	public String checkEmail(String email) {
		boolean existsByEmail = userRepo.existsByEmail(email);
		if (existsByEmail) {
			return "email is already existed";
		}
		return "email is not already existed";
	}

	@Override
	public Map<Integer, String> getCountryNames() {
		List<UserCountry> countryNames = countryRepo.getCountryNames();
		Map<Integer, String> map = new HashMap<>();
		for (UserCountry c : countryNames) {
			map.put(c.getCId(), c.getCName());
		}
		return map;
	}

	@Override
	public Map<Integer, String> getStateNames(Integer countryId) {
		Map<Integer, String> map = new HashMap<>();
		List<Object[]> stateNames = stateRepo.getStateNames(countryId);
		stateNames.forEach(row -> {
			Map<Integer, String> convertObjectToMap = ObjectToMapConversion.convertObjectToMap(row);

			List<Integer> ListofKeys = null;
			List<String> ListofValues = null;

			ListofKeys = convertObjectToMap.keySet().stream().collect(Collectors.toCollection(ArrayList::new));
			ListofValues = convertObjectToMap.values().stream().collect(Collectors.toCollection(ArrayList::new));
			map.put(ListofKeys.get(0), ListofValues.get(0));
			System.out.println(map);
		});

		return map;

	}

	@Override
	public List<String> getCityNames(Integer stateId) {
		List<String> cities = cityRepo.getCities(stateId);

		return cities;
	}

	@Override
	public String registerUser(User user) {
		char[] password = GenerateRandomPassword.generateRandomUserPassword();
		user.setUserPassword(password);
		user.setUserAccStatus("locked");
		User savedUser = userRepo.save(user);
		if (savedUser != null) {
			return "This is Email to unlock Your Account";
		}

		return "Registraion Failed";
	}

	@Override
	public String unlockaccount(UnlockAccount unlockaccount) {
		Integer id = userRepo.findUserIdByPassword(unlockaccount.getTempPassword());
		User user = userRepo.findUserById(id);
		if (user != null) {

			user.setUserAccStatus("Unlocked");
			user.setUserPassword(unlockaccount.getConfirmPassword());
			User savedUser = userRepo.save(user);
			if (savedUser != null) {
				return "Account Unlocked";
			}
		}
		return "Wrong Password";

	}

	@Override
	public String login(LoginForm loginForm) {

		boolean existsEmail = userRepo.existsByEmail(loginForm.getLoginEmail());
		if (existsEmail) {

			char[] existPassword = userRepo.findUserPasswordByEmail(loginForm.getLoginEmail());
			char[] pwd = loginForm.getLoginPwd().toCharArray();
			if (Arrays.equals(existPassword, pwd)) {

				return "Logined Successfully";
			}
		}
		return "Logined Failed";
	}

	@Override
	public String forgetPwd(String email) {

		boolean existsByEmail = userRepo.existsByEmail(email);
		String password = "";
		if (existsByEmail) {
			char[] existPassword = userRepo.findUserPasswordByEmail(email);
			for (int i = 0; i < existPassword.length; i++) {

				password = password + existPassword[i];
			}
			return password;
		}
		return "Incorrect Email";
	}

	@Override
	public Map<Integer, String> getStateName(Integer countryId) {
		List<UserState> stateName = stateRepo.getStateName(countryId);
		Map<Integer, String> map = new HashMap<>();
		stateName.forEach(state -> {
			map.put(state.getStateId(), state.getStateName());
		});
		return map;
	}

}
