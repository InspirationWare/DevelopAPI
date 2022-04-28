package com.techbeetraining.UserRegistration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import com.techbeetraining.UserRegistration.Validator;

@SpringBootTest
class UserRegistrationApplicationTests {

	Validator testValidator = new Validator();
	
	int maxLengthReq = 24;
	int minLengthReq = 8;
	int maxAgeReq = 110;
	int minAgeReq = 18;

	//****
	@Test
	public void testMaxLength_Higher() {
		testValidator.setMaxLength(maxLengthReq + 1);
		Assertions.assertFalse(testValidator.getMaxLength() <= maxLengthReq);	
	}
	@Test
	public void testMaxLength_Equal() {
		testValidator.setMaxLength(maxLengthReq);
		Assertions.assertTrue(testValidator.getMaxLength() <= maxLengthReq);	
	}
	@Test
	public void testMaxLength_Lower() {
		testValidator.setMaxLength(maxLengthReq - 1);
		Assertions.assertTrue(testValidator.getMaxLength() <= maxLengthReq);	
	}
	@Test
	public void testMaxLength_Zero() {
		testValidator.setMaxLength(0);
		Assertions.assertFalse(testValidator.getMaxLength() != 0);	
	}
	//****
	@Test
	public void testMinLength_Higher() {
		testValidator.setMinLength(minLengthReq + 1);
		Assertions.assertTrue(testValidator.getMinLength() >= minLengthReq);	
	}
	@Test
	public void testMinLength_Equal() {
		testValidator.setMinLength(minLengthReq);
		Assertions.assertTrue(testValidator.getMinLength() >= minLengthReq);	
	}
	@Test
	public void testMinLength_Lower() {
		testValidator.setMinLength(minLengthReq - 1);
		Assertions.assertFalse(testValidator.getMinLength() >= minLengthReq);	
	}		
	@Test
	public void testMinLength_Zero() {
		testValidator.setMinLength(0);
		Assertions.assertFalse(testValidator.getMinLength() != 0);	
	}
	//****
	@Test
	public void testMaxAge_Higher() {
		testValidator.setMaxAge(maxAgeReq + 1);
		Assertions.assertFalse(testValidator.getMaxAge() <= maxAgeReq);	
	}
	@Test
	public void testMaxAge_Equal() {
		testValidator.setMaxAge(maxAgeReq);
		Assertions.assertTrue(testValidator.getMaxAge() <= maxAgeReq);	
	}
	@Test
	public void testMaxAge_Lower() {
		testValidator.setMaxAge(maxAgeReq - 1);
		Assertions.assertTrue(testValidator.getMaxAge() <= maxAgeReq);	
	}
	@Test
	public void testMaxAge_Zero() {
		testValidator.setMaxAge(0);
		Assertions.assertFalse(testValidator.getMaxAge() != 0);	
	}
	@Test
	public void testMaxAge_Negative() {
		testValidator.setMaxAge(maxAgeReq * -1);
		Assertions.assertFalse(testValidator.getMaxAge() > 0);	
	}
	//****
	@Test
	public void testMinAge_Higher() {
		testValidator.setMinAge(minAgeReq + 1);
		Assertions.assertTrue(testValidator.getMinAge() >= minAgeReq);	
	}
	@Test
	public void testMinAge_Equal() {
		testValidator.setMinAge(minAgeReq);
		Assertions.assertTrue(testValidator.getMinAge() >= minAgeReq);	
	}
	@Test
	public void testMinAge_Lower() {
		testValidator.setMinAge(minAgeReq - 1);
		Assertions.assertFalse(testValidator.getMinAge() >= minAgeReq);	
	}
	@Test
	public void testMinAge_Zero() {
		testValidator.setMinAge(0);
		Assertions.assertFalse(testValidator.getMinAge() != 0);	
	}
	@Test
	public void testMinAge_Negative() {
		testValidator.setMinAge(minAgeReq * -1);
		Assertions.assertFalse(testValidator.getMinAge() > 0);	
	}
}
