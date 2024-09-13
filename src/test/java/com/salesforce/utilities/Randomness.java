package com.salesforce.utilities;


import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;

public class Randomness {

	private static final String DATE_TEMPLATE = "yyyy-MM-dd";
	private static final Random random = new Random();
	
	private Randomness() {
		
	}


    public static String randomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public static int randomNumberBetween(int min, int max) {
        return random.nextInt((max - min) + 1) + min;
    }

    public static String randomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String randomAlphaNumeric(int length) {
        return RandomStringUtils.randomAlphanumeric(length);
    }

    
}