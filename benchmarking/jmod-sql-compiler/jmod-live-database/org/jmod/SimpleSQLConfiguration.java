package org.jmod;

import org.jmod.dsl.sql.SQLConfiguration;


public class SimpleSQLConfiguration extends SQLConfiguration {
    public boolean SQLMOD_LIVE_TEST = true;
    public String SQLMOD_DB_URL = "jdbc:mysql://localhost/test";
    public String SQLMOD_JDBC_DRIVER = "com.mysql.jdbc.Driver";
    public String SQLMOD_DB_LOGIN = "root";
    public String SQLMOD_DB_PASSWORD = "root";
}