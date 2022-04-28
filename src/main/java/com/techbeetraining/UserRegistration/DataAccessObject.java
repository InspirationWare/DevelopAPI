package com.techbeetraining.UserRegistration;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

//import java.util.Properties;

public class DataAccessObject {
	
	private Connection connection;
	private Statement statement;
	
	private PropertyReader propertyReader = new PropertyReader();
	
	public DataAccessObject(String host, String username, String password) {
		
		propertyReader.loadFile();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			connection = DriverManager.getConnection(host, username, password);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			statement = connection.createStatement();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean create() {
		boolean result = false;
		
		String sql = "CREATE TABLE IF NOT EXISTS " + propertyReader.readValue("table") + " (" +
			propertyReader.readValue("column_1") + " VARCHAR(255) PRIMARY KEY, " + 
			propertyReader.readValue("column_2") + " VARCHAR(255) NOT NULL, " + 
			propertyReader.readValue("column_3") + " VARCHAR(255) NOT NULL, " +
			propertyReader.readValue("column_4") + " VARCHAR(255) NOT NULL, " +
			propertyReader.readValue("column_5") + " VARCHAR(255) NOT NULL, " + 
			propertyReader.readValue("column_6") + " VARCHAR(255) NOT NULL " +
			");";		
		
		if (connection != null) {
			try {
				statement = connection.createStatement();
				statement.executeUpdate(sql);
				result = true;
			}
			catch (SQLException e) {
				//e.printStackTrace();
				result = false;
			}
		}	
		
		return result;
	}
	
	public ResultSet read() {
		
		ResultSet resultSet = null;
		if (connection != null) {
			try {
				statement = connection.createStatement();
				resultSet = statement.executeQuery("SELECT * FROM " + propertyReader.readValue("table"));
			}
			catch (SQLException e) {
				//e.printStackTrace();
			}
		}
		
		return resultSet;
	}
	
	public boolean update(String data) {
		boolean result = false;
		
		if (connection != null) {
			try {
				statement = connection.createStatement();
				statement.executeUpdate("INSERT INTO " + propertyReader.readValue("table") + " VALUES (" + data + ");");
				result = true;
			}
			catch (SQLException e) {		
				//e.printStackTrace();
				result = false;
			}
		}
		
		return result;
	}
	
	public boolean delete() {
		boolean result = false;
		
		if (connection != null) {	
			try {
				statement = connection.createStatement();
				statement.executeUpdate("DROP TABLE IF EXISTS " + propertyReader.readValue("table"));
				result = true;
			}
			catch (SQLException e) {
				e.printStackTrace();	
				result = false;
			}
		}	

		return result;		
	}
}
