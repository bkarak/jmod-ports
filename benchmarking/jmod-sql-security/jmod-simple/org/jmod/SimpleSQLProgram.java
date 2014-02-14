package org.jmod;

import java.sql.*;

import java.util.Properties;


public class SimpleSQLProgram {
    public static void main(String[] args) {
        final String jdbcUrl = "SDriver:jdbc:mysql://localhost/test";
        final String jdbcDriver = "org.SDriver";

        try {
            Properties connectionProps = new Properties();
            connectionProps.put("user", "root");
            connectionProps.put("password", "root");
            Class.forName(jdbcDriver);
            Connection conn = DriverManager.getConnection(jdbcUrl, connectionProps);
            PreparedStatement ps = (new CustomerSelect()).getStatement(conn);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                System.out.println(rs.getString("customer_name"));
            }
            
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e) {
            System.err.println("Ooops ... " + e.toString());
        }
    }
}