package org.jmod;

import java.sql.*;

import java.util.Properties;


public class SimpleSQLProgram {
	public static void main(String[] args) {
		final String jdbcUrl = "jdbc:mysql://127.0.0.1/test";
        final String jdbcDriver = "";

		try {
    		Properties connectionProps = new Properties();
    		connectionProps.put("user", "root");
    		connectionProps.put("password", "");
            Class.forName(jdbcDriver);
    		Connection conn = DriverManager.getConnection(jdbcUrl, connectionProps);
    		Statement stmnt = conn.createStatement();
        	ResultSet rs = stmnt.executeQuery("select * from customer");

        	while(rs.next()) {
        		System.out.println(rs.getString("customer_name"));
        	}
        	
        	rs.close();
        	stmnt.close();
        	conn.close();
		} catch (Exception e) {
			System.err.println("Ooops ... " + e.toString());
		}
	}
}