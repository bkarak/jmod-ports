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

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/*
 * Author : Dimitris Mitropoulos
 * Started on 16 September 2006
 * Every type 4 JDBC driver must implements a Connection interface
 */

/**This is the Connection interface of SDriver. Apart from the standard operations this interface
 * sets up a connection with another database,
 * the one that stores the blueprints of every "legal" query. */

public class SConnection implements java.sql.Connection {
	/**The connection pass is the one that helps the SStatement interface to use all the methods of
     * the original driver's corresponding interface. */
	protected Connection pass;
	/**This is the connection with ssql DB - the database where the blueprints are stored. */
	protected Connection pass2;
	/**This Driver is used by the Driver interface to gain transparency. */
	protected Driver nextDriver;
    /**This variable declares if SDriver is in training or production mode (default). */
    protected boolean state = true;
    /**This variable declares if SDriver will handle exceptions during production mode or not. */
    protected boolean handleExceptions;
	@SuppressWarnings("deprecation")
    SConnection(String url, Properties prop) throws ClassNotFoundException, SQLException{
	    try {
            //to determine in which mode will work, and whether the ecxeption handling is
            // enabled, SDriver opens an external txt file
            File f = new File("mode.txt");
            String path2 = f.getAbsolutePath();
            System.out.println(path2);
            FileInputStream fis = new FileInputStream(f);
            BufferedInputStream bis = new BufferedInputStream(fis);
            DataInputStream data = new DataInputStream(bis);
            if (data.readLine().equals("training mode")) {
                state = false;
                System.out.println("SDriver: training mode");
            } else {
                System.out.println("SDriver: production mode");
                state = true;
            }
            if (data.readLine().equals("handle exceptions")) {
                handleExceptions = true;
                System.out.println("SDriver: exception handling");
            } else {
                handleExceptions = false;
                System.out.println("SDriver: exception handling disabled");
            }
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            } catch (IOException e2) {
                e2.printStackTrace();
        }

		/*
         *Suppose that the application's underlying database is MySQL.
         *The URL that the application will use to connect to the SDriver at first, will look
         *like this: "jdbc:SDriver:org.gjt.mm.mysql.Driver:mysql://localhost:3306/mymusicdb"
         *To connect to the next driver, SDriver has to remove the substring
		 *"SDriver:org.gjt.mm.mysql.Driver:" from this URL, keeping
		 *"org.gjt.mm.mysql.Driver" to a separate string.
		 */
		String tmpUrl = null;
		tmpUrl = url.replaceFirst("SDriver:","");
		//The URL now jdbc:org.gjt.mm.mysql.Driver:mysql://localhost:3306/mymusicdb
		String tmpUrlToSplit = null;
		tmpUrlToSplit = tmpUrl;
		String patternStr = ":";
		String[] fields = tmpUrlToSplit.split(patternStr);
		/*
		 *fields[1] is "org.gjt.mm.mysql.Driver"
		 *but I have to remove "org.gjt.mm.mysql.Driver:"
		 *so
		 */
		tmpUrl = tmpUrl.replaceFirst(fields[1]+":","");
		//URL is now ready for SDriver to use, and looks like this "jdbc:mysql://localhost:3306/mymusicdb"

		//Then the username and the password are needed.
		String username = null;
		String password = null;
		//user is the first property
		username = prop.get("user").toString();
		//password is the second
		password = prop.get("password").toString();
		//create the connection with the following driver
		Class.forName(fields[1]);
		try {
			pass=DriverManager.getConnection(tmpUrl,username,password);
			nextDriver = DriverManager.getDriver(tmpUrl);
		} catch(SQLException e) {
			throw e;
		}

	}


	public int getHoldability() throws SQLException {
		if(pass != null){
			return pass.getHoldability();
		}
		return 0;
	}

	public int getTransactionIsolation() throws SQLException {
		if(pass != null){
			return pass.getTransactionIsolation();
		}
		return 0;
	}

	public void clearWarnings() throws SQLException {
		if(pass != null){
			pass.clearWarnings();
		}
	}

	public void close() throws SQLException {
		if(pass != null){
			pass.close();
		}
	}

	public void commit() throws SQLException {
		if(pass != null){
			pass.commit();
		}
	}

	public void rollback() throws SQLException {
		if(pass != null){
			pass.rollback();
		}
	}

	public boolean getAutoCommit() throws SQLException {
		if(pass != null){
			return pass.getAutoCommit();
		}
		return false;
	}

	public boolean isClosed() throws SQLException {
		if(pass != null){
			return pass.isClosed();
		}
		return false;
	}

	public boolean isReadOnly() throws SQLException {
		if(pass != null){
			return pass.isReadOnly();
		}
		return false;
	}

	public void setHoldability(int arg0) throws SQLException {
		if(pass != null){
			pass.setHoldability(arg0);
		}
	}

	public void setTransactionIsolation(int arg0) throws SQLException {
		if(pass != null){
			pass.setTransactionIsolation(arg0);
		}
	}

	public void setAutoCommit(boolean arg0) throws SQLException {
		if(pass != null){
			pass.setAutoCommit(arg0);
		}
	}

	public void setReadOnly(boolean arg0) throws SQLException {
		if(pass != null){
			pass.setReadOnly(arg0);
		}
	}

	public String getCatalog() throws SQLException {
		if(pass != null){
			return pass.getCatalog();
		}
		return null;
	}

	public void setCatalog(String arg0) throws SQLException {
		if(pass != null){
			pass.setCatalog(arg0);
		}
	}

	public DatabaseMetaData getMetaData() throws SQLException {
		if(pass != null){
			return pass.getMetaData();
		}
		return null;
	}

	public SQLWarning getWarnings() throws SQLException {
		if(pass != null){
			return pass.getWarnings();
		}
		return null;
	}

	public Savepoint setSavepoint() throws SQLException {
		if(pass != null){
			return pass.setSavepoint();
		}
		return null;
	}

	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		if(pass != null){
			pass.releaseSavepoint(arg0);
		}
	}

	public void rollback(Savepoint arg0) throws SQLException {
		if(pass != null){
			pass.rollback(arg0);
		}
	}

	public Statement createStatement() throws SQLException {
		return new SStatement(this);
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
		return new SStatement(this, resultSetType, resultSetConcurrency, ResultSet.CLOSE_CURSORS_AT_COMMIT);
	}

	public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		return new SStatement(this, resultSetType, resultSetConcurrency, resultSetHoldability);
	}

	@SuppressWarnings("unchecked")
    public Map getTypeMap() throws SQLException {
		if(pass != null){
			return pass.getTypeMap();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
    public void setTypeMap(Map arg0) throws SQLException {
		if(pass != null){
			pass.setTypeMap(arg0);
		}
	}

	public String nativeSQL(String arg0) throws SQLException {
		if(pass != null){
			return pass.nativeSQL(arg0);
		}
		return null;
	}

	public CallableStatement prepareCall(String arg0) throws SQLException {
		if(pass != null){
			return pass.prepareCall(arg0);
		}
		return null;
	}

	public CallableStatement prepareCall(String arg0, int arg1, int arg2) throws SQLException {
		if(pass != null){
			return pass.prepareCall(arg0,arg1,arg2);
		}
		return null;
	}

	public CallableStatement prepareCall(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		if(pass != null){
			return pass.prepareCall(arg0,arg1,arg2,arg3);
		}
		return null;
	}

	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		if(pass != null){
			return pass.prepareStatement(arg0);
		}
		return null;
	}

	public PreparedStatement prepareStatement(String arg0, int arg1) throws SQLException {
		if(pass != null){
			return pass.prepareStatement(arg0,arg1);
		}
		return null;
	}

	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2) throws SQLException {
		if(pass != null){
			return pass.prepareStatement(arg0,arg1,arg2);
		}
		return null;
	}

	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2, int arg3) throws SQLException {
		if(pass != null){
			return pass.prepareStatement(arg0,arg1,arg2,arg3);
		}
		return null;
	}

	public PreparedStatement prepareStatement(String arg0, int[] arg1) throws SQLException {
		if(pass != null){
			return pass.prepareStatement(arg0,arg1);
		}
		return null;
	}

	public Savepoint setSavepoint(String arg0) throws SQLException {
		if(pass != null){
			return pass.setSavepoint(arg0);
		}
		return null;
	}

	public PreparedStatement prepareStatement(String arg0, String[] arg1) throws SQLException {
		if(pass != null){
			return pass.prepareStatement(arg0,arg1);
		}
		return null;
	}

	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
        if(pass != null){
            return pass.createArrayOf(arg0,arg1);
        }
		return null;
	}

	public Blob createBlob() throws SQLException {
        if(pass != null){
            return pass.createBlob();
        }
		return null;
	}

	public Clob createClob() throws SQLException {
        if(pass != null){
            return pass.createClob();
        }
        return null;
	}

	public NClob createNClob() throws SQLException {
        if(pass != null){
            return pass.createNClob();
        }
        return null;
	}

	public SQLXML createSQLXML() throws SQLException {
        if(pass != null){
            return pass.createSQLXML();
        }
        return null;
	}

	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
        if(pass != null){
            return pass.createStruct(arg0, arg1);
        }
        return null;
	}

	public Properties getClientInfo() throws SQLException {
        if(pass != null){
            return pass.getClientInfo();
        }
        return null;
	}

	public String getClientInfo(String arg0) throws SQLException {
        if(pass != null){
            return pass.getClientInfo(arg0);
        }
        return null;
	}

	public boolean isValid(int arg0) throws SQLException {
        if(pass != null){
            return pass.isValid(arg0);
        }
        return false;
	}

	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		pass.setClientInfo(arg0);
	}

	public void setClientInfo(String arg0, String arg1) throws SQLClientInfoException {
		pass.setClientInfo(arg0, arg1);
	}

	public boolean isWrapperFor(Class arg0) throws SQLException {
        if(pass != null){
            return pass.isWrapperFor(arg0);
        }
        return false;
	}

	@SuppressWarnings("unchecked")
    public Object unwrap(Class arg0) throws SQLException {
        if(pass != null){
            return pass.unwrap(arg0);
        }
		return null;
	}

}
