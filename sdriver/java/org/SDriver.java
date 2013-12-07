/*
 * Copyright (c) 2009, Dimitris Mitropoulos
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *
 *     * Redistributions in binary form must reproduce the above
 *       copyright notice, this list of conditions and the following
 *       disclaimer in the documentation and/or other materials provided
 *       with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */

package org;
import java.sql.*;
import java.util.*;

/*
 * Author : Dimitris Mitropoulos
 * Started on 16 September 2006
 * SDriver is a mechanism and a prototype application that prevents SQLIAs against
 * web applications. If an SQL injection happens, the structure of the query, and
 * therefore its blueprint will be altered, and SDriver will be able to detect it.
 */

/** SDriver stands between the application and the driver that the application uses to interact 
 * with the database. This is done by adding the prefix "jdbc:SDriver:" at the beginning of the
 * url the application uses to connect to the database. Then SDriver connects with that driver
 * using all it's methods hence gaining transparency.*/

public class SDriver implements java.sql.Driver {
	/**when the driver is called, the url must start with the following prefix. */
	private static final String URL_PREFIX = "jdbc:SDriver:";
    /**This is the driver that the application intends to use. */
    private Driver nextDriver;

	/*register SDriver */
	static {
        try {
        	SDriver SDriverInstance = new SDriver();
            java.sql.DriverManager.registerDriver(SDriverInstance);
        }
        catch (SQLException e) {
            System.err.println("Could not register SDriver");
            e.printStackTrace();
        }
        
    }

    /** Retrieves the other driver's major version number. */
	public int getMajorVersion() {
        return nextDriver.getMajorVersion();
	}
    
    /** Retrieves the other driver's minor version number. */
	public int getMinorVersion() {
		return nextDriver.getMinorVersion();
	}

    /** Reports whether the other driver is a genuine JDBC Compliant driver. */
	public boolean jdbcCompliant() {	
		return nextDriver.jdbcCompliant();
	}

    /** Retrieves whether the driver thinks that it can open a connection to the given URL. */
	public boolean acceptsURL(String url) throws SQLException {
		return url.startsWith(URL_PREFIX);
	}

    /** Attempts to make a connection to the driver that the application
     * uses to connect to the database. */
	public Connection connect(String url, Properties prop) throws SQLException {
		SConnection localConInstance = null;
		
		if(acceptsURL(url)) {
			try {
				localConInstance = new SConnection(url, prop);
				/* while creating the connection interface get the following driver
				 * in order to use his methods and provide transparency
				 */
				nextDriver = localConInstance.nextDriver;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return (Connection)localConInstance;
	}

    /**Gets information about the possible properties for the other driver driver. */
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties prop) throws SQLException {
		return nextDriver.getPropertyInfo(url, prop);
	}
	
}