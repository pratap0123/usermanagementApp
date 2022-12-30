package com.pd.utils;

import java.util.HashMap;
import java.util.Map;

public class ObjectToMapConversion {

	public static Map<Integer, String> convertObjectToMap(Object[] row) {

		Map<Integer, String> map = new HashMap<>();
		map.put((Integer)row[0],(String) row[1]);
		System.out.println();
		return map;

	}


}
