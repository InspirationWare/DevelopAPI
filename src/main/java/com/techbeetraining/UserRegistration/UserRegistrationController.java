package com.techbeetraining.UserRegistration;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Scanner;

@RestController
public class UserRegistrationController {
	
	private PropertyReader propertyReader = new PropertyReader();
	
	@GetMapping("/promptUser")
	public void promptUser() {
	
		propertyReader.loadFile();
		
		DataAccessObject database = new DataAccessObject(propertyReader.readValue("host") + propertyReader.readValue("port") + propertyReader.readValue("database"), propertyReader.readValue("user"), propertyReader.readValue("password"));
	
		Scanner scanner = new Scanner(System.in);
	
		String sql = "";

		String text = "";
		
		Validator userNameValidator = null;
		while (userNameValidator == null) {
			System.out.println(propertyReader.readValue("value_1") + propertyReader.readValue("column_1"));
			text = scanner.nextLine();
			userNameValidator = new Validator(text, 8, 24, false, false, false, true, true); 
			if (userNameValidator.getOverallResult() == true) {
				sql = sql + "\"" + text + "\"" + ", ";
				propertyReader.readValue("value_2");
			}
			else 
				userNameValidator = null;
		}
		
		text = "";
		Validator firstNameValidator = null;
		while (firstNameValidator == null) {
			System.out.println(propertyReader.readValue("value_1") + propertyReader.readValue("column_2"));
			text = scanner.nextLine();
			firstNameValidator = new Validator(text, 2, 24, false, false, false, true, false); 
			if (firstNameValidator.getOverallResult() == true) {
				sql = sql + "\"" + text + "\"" + ", ";
				propertyReader.readValue("value_2");
			}
			else 
				firstNameValidator = null;
		}
		
		text = "";
		Validator lastNameValidator = null;
		while (lastNameValidator == null) {
			System.out.println(propertyReader.readValue("value_1") + propertyReader.readValue("column_3"));
			text = scanner.nextLine();
			lastNameValidator = new Validator(text, 2, 24, false, false, false, true, false); 
			if (lastNameValidator.getOverallResult() == true) {
				sql = sql + "\"" + text + "\"" + ", ";
				propertyReader.readValue("value_2");
			}
			else 
				lastNameValidator = null;
		}
		
		text = "";
		Validator ageValidator = null; 
		while (ageValidator == null) {
			System.out.println(propertyReader.readValue("value_1") + propertyReader.readValue("column_4"));
			text = scanner.nextLine();
			try {
				ageValidator = new Validator(Integer.parseInt(text), 18, 110, false, false, false, false, true); 
				if (ageValidator.getOverallResult() == true) {
				sql = sql + "\"" + text + "\"" + ", ";
				propertyReader.readValue("value_2");
				}
				else 
					ageValidator = null;
			}
			catch (NumberFormatException | NullPointerException e) {	
			}
		}

		text = "";
		Validator phoneNumberValidator = null;
		while (phoneNumberValidator == null) {
			System.out.println(propertyReader.readValue("value_1") + propertyReader.readValue("column_5"));
			text = scanner.nextLine();
			phoneNumberValidator = new Validator(text, 10, 10, false, false, false, false, true); 
			if (phoneNumberValidator.getOverallResult() == true) {
				sql = sql + "\"" + text + "\"" + ", ";
				propertyReader.readValue("value_2");
			}
			else 
				phoneNumberValidator = null;
		}
		
		text = "";
		Validator emailAddressValidator = null;
		while (emailAddressValidator == null) {
			System.out.println(propertyReader.readValue("value_1") + propertyReader.readValue("column_6"));
			text = scanner.nextLine();
			emailAddressValidator = new Validator(text, false, true, false, true, true); 
			if (emailAddressValidator.getOverallResult() == true) {
				sql = sql + "\"" + text + "\"";
				propertyReader.readValue("value_2");
			}
			else
				emailAddressValidator = null;
		}
		
		//****CREATE****
		if (database.create() == true) {
			System.out.println(propertyReader.readValue("create_success"));	
		}
		else {
			System.out.println(propertyReader.readValue("create_failure"));	
		}
		//****CREATE****
		
		//****READ****
		/*
		ResultSet resultSet = null;
		ResultSetMetaData resultSetMetaData;
		try {
			resultSet = database.read();			
			resultSetMetaData = resultSet.getMetaData();
			while (resultSet.next()) {
				for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
				   System.out.println(resultSetMetaData.getColumnName(i) + ": " + resultSet.getString(i));
				}
			}
			System.out.println(propertyReader.readValue("read_success"));
		}
		catch (SQLException e) {
			System.out.println(propertyReader.readValue("read_failure"));
		}	
		*/
		//****READ****
		
		//****UPDATE****
		///*
		if (database.update(sql) == true) {
			System.out.println(propertyReader.readValue("update_success"));	
		}
		else {
			System.out.println(propertyReader.readValue("update_failure"));	
		}
		//*/
		//****UPDATE****
		
		//****DELETE****
		/*
		if (database.delete() == true) {
			System.out.println(propertyReader.readValue("delete_success"));	
		}
		else {
			System.out.println(propertyReader.readValue("delete_failure"));	
		}
		*/
		//****DELETE****
		
		System.exit(0);
	}
}