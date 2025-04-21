package com.verizon.crm.api.generic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

/*
 * @author Saif
 *  
 */

public class DataBaseUtility {
	// database connection
	Connection conn;
	public void getConnection() {
		try {
			Driver driver= new Driver();
			DriverManager.registerDriver(driver);
			conn = DriverManager.getConnection("jdbc:mysql:/localhost:3306/projects", "root", "root");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	// database close connection
	public void closeConnection() throws SQLException {
		conn.close();
	}
	// database execute select query
	public ResultSet executeSelectQuery(String query, int i, String projName) throws SQLException {
		ResultSet result= null;
		try{
			Statement stat = conn.createStatement();
			 result = stat.executeQuery(query);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	// database execute non select query
	public int executeNonSelectQuery(String query) {
		int result=0;
		try {
			Statement stat = conn.createStatement();
			result=stat.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
		
	}

}
