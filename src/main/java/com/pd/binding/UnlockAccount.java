package com.pd.binding;

import lombok.Data;

@Data
public class UnlockAccount {
	private char[] confirmPassword;
	private char[] tempPassword;
}
