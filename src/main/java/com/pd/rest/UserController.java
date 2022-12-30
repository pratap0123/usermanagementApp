package com.pd.rest;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pd.binding.LoginForm;
import com.pd.binding.UnlockAccount;
import com.pd.binding.User;
import com.pd.dto.UserDTO;
import com.pd.service.UserMgmtServiceImpl;

@RestController
@RequestMapping("/user/api")
public class UserController {

	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private UserMgmtServiceImpl service;

	@GetMapping("/email/{email}")
	public ResponseEntity<String> userEmailExit(@PathVariable String email) {
		String checkEmail = service.checkEmail(email);
		String body = checkEmail + email;
		return new ResponseEntity<String>(body, HttpStatus.OK);

	}

	@GetMapping("/country")
	public Map<Integer, String> userCountry() {
		Map<Integer, String> countryNames = service.getCountryNames();
		return countryNames;

	}

	@GetMapping("/state/{cId}")
	public Map<Integer, String> userStates(@PathVariable Integer cId) {
		Map<Integer, String> stateNames = service.getStateNames(cId);
		return stateNames;

	}

	@GetMapping("/states/{cId}")
	public Map<Integer, String> userState(@PathVariable Integer cId) {
		Map<Integer, String> stateNames = service.getStateName(cId);
		return stateNames;

	}

	@GetMapping("/city/{sId}")
	public List<String> userCities(@PathVariable Integer sId) {
		List<String> cityNames = service.getCityNames(sId);
		return cityNames;

	}

	@PostMapping("/register")
	public String userResiter(@RequestBody User user) {

//		User map = modelMapper.map(userDTO, User.class);
		String email = service.registerUser(user);
		return email;

	}

	@PostMapping("/unlock")
	public String unlockAccount(@RequestBody UnlockAccount unlockAccount) {
		String accStatus = service.unlockaccount(unlockAccount);
		return accStatus;

	}

	@GetMapping("/forget/{email}")
	public String forgetPassword(@PathVariable String email) {
		String password = service.forgetPwd(email);
		return password;

	}

	@PostMapping("/login")
	public String loginUser(@RequestBody LoginForm loginForm) {
		String message = service.login(loginForm);
		return message;

	}
}
