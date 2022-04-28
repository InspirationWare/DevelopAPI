package com.techbeetraining.UserRegistration;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
	
	private int minLength = 0;
	private int maxLength = 0;
	private int minAge = 0;
	private int maxAge = 0;
	private boolean allowedSpecialCharacters = false;
	private boolean allowedSpaces = false;
	private boolean allowedLetters = false;
	private boolean allowedDigits = false;
	private boolean allowedEmpty = false;
	private boolean hasSpecialCharacter = false;
	private boolean hasSpace = false;
	private boolean hasLetter = false;
	private boolean hasDigit = false;
	private boolean isEmpty = false;
	
	private boolean overallResult = false;
	
	private PropertyReader propertyReader = new PropertyReader();
	
	public Validator() {
	}
	
	public Validator(String value, int minLength, int maxLength, boolean allowedEmpty, boolean allowedSpecialCharacters, boolean allowedSpaces, boolean allowedLetters, boolean allowedDigits) {	

		propertyReader.loadFile();
	
		setMinLength(minLength);
		setMaxLength(maxLength);
		setAllowedEmpty(allowedEmpty);
		setAllowedSpecialCharacters(allowedSpecialCharacters);
		setAllowedSpaces(allowedSpaces);
		setAllowedLetters(allowedLetters);
		setAllowedDigits(allowedDigits);
		
		overallResult = validate(value);
	}
	
	public Validator(int age, int minAge, int maxAge, boolean allowedEmpty, boolean allowedSpecialCharacters, boolean allowedSpaces, boolean allowedLetters, boolean allowedDigits) {
		
		propertyReader.loadFile();
		
		setMinAge(minAge);
		setMaxAge(maxAge);
		setAllowedEmpty(allowedEmpty);
		setAllowedSpecialCharacters(allowedSpecialCharacters);
		setAllowedSpaces(allowedSpaces);
		setAllowedLetters(allowedLetters);
		setAllowedDigits(allowedDigits);
		
		overallResult = validate(age);
	}
	
	public Validator(String value, boolean allowedEmpty, boolean allowedSpecialCharacters, boolean allowedSpaces, boolean allowedLetters, boolean allowedDigits) {

		propertyReader.loadFile();

		setAllowedEmpty(allowedEmpty);
		setAllowedSpecialCharacters(allowedSpecialCharacters);
		setAllowedSpaces(allowedSpaces);
		setAllowedLetters(allowedLetters);
		setAllowedDigits(allowedDigits);
		
		Pattern pattern = Pattern.compile("^(.+)@(.+)$");
		Matcher matcher = pattern.matcher(value);
	
		overallResult = validate(value, matcher);
	}
	
	public boolean getOverallResult() {
		return overallResult;
	}
	
	public void setMinLength(int minLength) {
		this.minLength = minLength;
	}
	
	public int getMinLength() {
		return minLength;
	}
	
	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}
	
	public int getMaxLength() {
		return maxLength;
	}
	
	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}
	
	public int getMinAge() {
		return minAge;
	}
	
	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}
	
	public int getMaxAge() {
		return maxAge;
	}
		
	public void setAllowedEmpty(boolean allowedEmpty) {
		this.allowedEmpty = allowedEmpty;
	}
	
	public boolean getAllowedEmpty() {
		return allowedEmpty;
	}
	
	public void setAllowedSpecialCharacters(boolean allowedSpecialCharacters) {
		this.allowedSpecialCharacters = allowedSpecialCharacters;
	}
	
	public boolean getAllowedSpecialCharacters() {
		return allowedSpecialCharacters;
	}
	
	public void setAllowedSpaces(boolean allowedSpaces) {
		this.allowedSpaces = allowedSpaces;
	}
	
	public boolean getAllowedSpaces() {
		return allowedSpaces;
	}
	
	public void setAllowedLetters(boolean allowedLetters) {
		this.allowedLetters = allowedLetters;
	}
	
	public boolean getAllowedLetters() {
		return allowedLetters;
	}
	
	public void setAllowedDigits(boolean allowedDigits) {
		this.allowedDigits = allowedDigits;
	}
	
	public boolean getAllowedDigits() {
		return allowedDigits;
	}
	
	public boolean getIsEmpty(String value) {
		boolean result = false; 
		
		if (value.equals("") || value == null) {
			result = true;
		}
		
		return result;
	}
	
	public boolean getHasLetter(String value) {
		boolean result = false;
		loop:
		for (int i = 0; i < value.length(); i++) {
			if (Character.isLetter(value.charAt(i))) {
				result = true;
				break loop;
			}
		}
		return result;
	}
	
	public boolean getHasDigit(String value) {
		boolean result = false;
		loop:
		for (int i = 0; i < value.length(); i++) {
			if (Character.isDigit(value.charAt(i))) {
				result = true;
				break loop;
			}
		}
		return result;
	}
	
	public boolean getHasSpace(String value) {
		boolean result = false;
		loop:
		for (int i = 0; i < value.length(); i++) {
			if (Character.isWhitespace(value.charAt(i))) {
				result = true;
				break loop;
			}
		}
		return result;
	}
	
	public boolean getHasSpecialCharacter(String value) {
		boolean result = false;
		loop:
		for (int i = 0; i < value.length(); i++) {
			if (!Character.isWhitespace(value.charAt(i)) && !Character.isLetter(value.charAt(i)) && !Character.isDigit(value.charAt(i)) && !getIsEmpty(value)) {
				result = true;
				break loop;
			}
		}
		return result;
	}
	
	public boolean validate(String value) {
		boolean result = false;	
		
		if (getIsEmpty(value) == true && allowedEmpty == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_4"));
		}
		else if (getHasLetter(value) == true && allowedLetters == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_5"));
		}
		else if (getHasDigit(value) == true && allowedDigits == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_6"));
		}	
		else if (getHasSpace(value) == true && allowedSpaces == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_7"));
		}
		else if (getHasSpecialCharacter(value) == true && allowedSpecialCharacters == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_8"));
		}	
		else if (value.length() < minLength || value.length() > maxLength) {	
			result = false;
			System.out.println(propertyReader.readValue("error_1"));
		}	
		else {	
			result = true;
		}
		
		return result;
	}	
	
	public boolean validate(int age) {
		boolean result = false;	
		
		String value = String.valueOf(age);
		
		if (getIsEmpty(value) == true && allowedEmpty == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_4"));
		}
		else if (getHasLetter(value) == true && allowedLetters == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_5"));
		}
		else if (getHasDigit(value) == true && allowedDigits == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_6"));
		}	
		else if (getHasSpace(value) == true && allowedSpaces == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_7"));
		}
		else if (getHasSpecialCharacter(value) == true && allowedSpecialCharacters == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_8"));
		}	
		else if (Integer.parseInt(value) < minAge || Integer.parseInt(value) >= maxAge) {	
			result = false;
			System.out.println(propertyReader.readValue("error_2"));
		}
		else {	
			result = true;
		}
		
		return result;
	}

	public boolean validate(String value, Matcher matcher) {
		boolean result = false;	
		
		if (getIsEmpty(value) == true && allowedEmpty == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_4"));
		}
		else if (getHasLetter(value) == true && allowedLetters == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_5"));
		}
		else if (getHasDigit(value) == true && allowedDigits == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_6"));
		}	
		else if (getHasSpace(value) == true && allowedSpaces == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_7"));
		}
		else if (getHasSpecialCharacter(value) == true && allowedSpecialCharacters == false) {
			result = false;
			System.out.println(propertyReader.readValue("error_8"));
		}	
		else if (!matcher.matches()) {
			result = false;
			System.out.println(propertyReader.readValue("error_3"));
		}
		else {	
			result = true;
		}
		
		return result;
	}	
}