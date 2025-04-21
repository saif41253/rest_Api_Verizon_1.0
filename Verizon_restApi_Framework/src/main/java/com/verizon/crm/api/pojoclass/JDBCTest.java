package com.verizon.crm.api.pojoclass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;
import java.util.Properties;
import java.util.logging.Logger;

import com.mysql.cj.jdbc.Driver;

public class JDBCTest {
	public static void main(String[] args) throws SQLException {
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ninza_hrm","root", "root");
		Statement state = conn.createStatement();
		ResultSet result = state.executeQuery("select * from emp");
		while(result.next()) {
			System.out.println(result.getString(1));
		}
		conn.close();
	}
}
