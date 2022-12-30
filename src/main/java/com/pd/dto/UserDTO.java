package com.pd.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {

	private Integer userId;
	private String lastName;
	private String email;
	private String phon;
	private String gender;
	private Integer cityId;
	private LocalDate dob;
	private Integer cId;
	private Integer stateId;
	char[] userPassword;
	private String userAccStatus;
}
