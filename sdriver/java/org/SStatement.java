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
import java.security.*;
import java.util.HashMap;
import java.util.regex.*;

/*
 * Author : Dimitris Mitropoulos
 * Started on 16 September 2006
 * Every type 4 JDBC driver must implements a Statement interface
 */

/**This is the Statement interface of SDriver. It's functionality depends on the mode.
 * While in training mode, it saves signatures of queries to a database called ssql.
 * When in production mode it simply checks if the signature exists in ssql DB */ 

@SuppressWarnings("unchecked")
public class SStatement implements java.sql.Statement{
	
    private SConnection connection;
	/**this statement is the one we need to interact with ssql DB */
	private static Statement statement2;
	@SuppressWarnings("unused")
    private int resultSetType = java.sql.ResultSet.TYPE_FORWARD_ONLY;
	@SuppressWarnings("unused")
    private int resultSetConcurrency = java.sql.ResultSet.CONCUR_READ_ONLY;
	@SuppressWarnings("unused")
    private int resultSetHoldability = ResultSet.CLOSE_CURSORS_AT_COMMIT;
	/**this statement must pass to the constructor of the SResultSet */
	private Statement statement;
	/**so does this ResultSet */
	private SResultSet resultset;
	/**if this becomes true we are under attack */
	private boolean attack = false;
	//private String SQLoriginClass = null;
	final static HashMap mapID = new HashMap();
    final static Pattern escapeChar;
    final static Pattern numbers;
    final static Pattern comments;
    final static Pattern trivia;
    static {
        //Initializing patterns
    	trivia = Pattern.compile("\\+|-|\\.");
    	escapeChar = Pattern.compile("\\'(?:.|[\\n\\r])*?\\'");
        numbers = Pattern.compile("([<=>,\\(]+\\s*)(?:[0-9A-Fa-f])+");
        comments = Pattern.compile("/\\*(?:.|[\\n\\r])*?\\*/");
        //please make a connection with ssql DB
		try {
			try {
				Class.forName("org.gjt.mm.mysql.Driver").newInstance();
			} catch (InstantiationException e2) {
				e2.printStackTrace();
			} catch (IllegalAccessException e2) {
				e2.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		Connection pass2 = null;
		try {
			pass2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/ssql","root","");
		} catch (SQLException sqle) {
            System.err.println("Could not connect with ssql DB" 
                    + sqle.getMessage());
			sqle.printStackTrace();
		}

		try {
			statement2 = pass2.createStatement();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		ResultSet rs = null;
        try {
			rs = statement2.executeQuery("SELECT * FROM signatures");
			while (rs.next()) {
				mapID.put( rs.getString(1), null );
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}     
    }
 
	SStatement(SConnection conn, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
		this.resultSetType = resultSetType;
		this.resultSetConcurrency = resultSetConcurrency;
		this.resultSetHoldability  = resultSetHoldability;
		initialize(conn);
	}
	
	SStatement(SConnection conn) throws SQLException	{
		initialize(conn);
	}
	
	private void initialize(SConnection conn) throws SQLException {
		connection = conn;
        statement = connection.pass.createStatement();        
	}

	public int getFetchDirection() throws SQLException {
		if(connection != null){
			return statement.getFetchDirection();
		}
		return java.sql.ResultSet.FETCH_FORWARD;
	}

	public int getFetchSize() throws SQLException {
		if(connection != null){
			return statement.getFetchSize();
		}
		return 0;
	}

	public int getMaxFieldSize() throws SQLException {
		if(connection != null){
			return statement.getMaxFieldSize();
		}
		return 0;
	}

	public int getMaxRows() throws SQLException {
		if(connection != null){
			return statement.getMaxRows();
		}
		return 0;
	}

	public int getQueryTimeout() throws SQLException {
		if(connection != null){
			return statement.getQueryTimeout();
		}
		return 0;
	}

	public int getResultSetConcurrency() throws SQLException {
		if(connection != null){
			return statement.getResultSetConcurrency();
		}
		return java.sql.ResultSet.CONCUR_READ_ONLY;
	}
	
	public int getResultSetHoldability() throws SQLException {
		if(connection != null){
			return statement.getResultSetHoldability();
		}
		return java.sql.ResultSet.CLOSE_CURSORS_AT_COMMIT;
	}

	public int getResultSetType() throws SQLException {
		if(connection != null){
			return statement.getResultSetType();
		}
		return 0;
	}

	public int getUpdateCount() throws SQLException {
		if(connection != null){
			return statement.getUpdateCount();
		}
		return 0;
	}

	public void cancel() throws SQLException {
		if(connection != null){
			statement.cancel();
		}
	}

	public void clearBatch() throws SQLException {
		if(connection != null){
			statement.clearBatch();
		}
	}

	public void clearWarnings() throws SQLException {
		if(connection != null){
			statement.clearWarnings();
		}
	}

	public void close() throws SQLException {
		if(connection != null){
			statement.close();
		}
	}

	public boolean getMoreResults() throws SQLException {
		if(connection != null){
			return statement.getMoreResults();
		}
		return false;
	}

	public int[] executeBatch() throws SQLException {
		if(connection != null){
			return statement.executeBatch();
		}
		return null;
	}

	public void setFetchDirection(int arg0) throws SQLException {
		if(connection != null){
			statement.setFetchDirection(arg0);
		}
	}

	public void setFetchSize(int arg0) throws SQLException {
		if(connection != null){
			statement.setFetchSize(arg0);
		}
	}
	
	public void setMaxFieldSize(int arg0) throws SQLException {
		if(connection != null){
			statement.setMaxFieldSize(arg0);
		}
	}

	public void setMaxRows(int arg0) throws SQLException {
		if(connection != null){
			statement.setMaxRows(arg0);
		}
	}

	public void setQueryTimeout(int arg0) throws SQLException {
		if(connection != null){
			statement.setQueryTimeout(arg0);
		}
	}

	public boolean getMoreResults(int arg0) throws SQLException {
		if(connection != null){
			return statement.getMoreResults(arg0);
		}
		return false;
	}
	
	public void setEscapeProcessing(boolean arg0) throws SQLException {
		if(connection != null){
			statement.setEscapeProcessing(arg0);
		}
	}
	
    public boolean isClosed() throws SQLException {
        if(connection != null){
            statement.isClosed();
        }
        return false;
    }

    public boolean isPoolable() throws SQLException {
        if(connection != null){
            statement.isPoolable();
        }
        return false;
    }

    public void setPoolable(boolean arg0) throws SQLException {
    }

    public boolean isWrapperFor(Class arg0) throws SQLException {
        return false;
    }

    @SuppressWarnings("unchecked")
    public Object unwrap(Class arg0) throws SQLException {
        return null;
    }
    
	public int executeUpdate(String sql) throws SQLException {
		
		manageQuery(sql);
		
		if((connection != null) && (!this.attack))
		{
			int retval = 0;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.executeUpdate(sql); 
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
                }
            } else {
                retval = statement.executeUpdate(sql);
        } 
            retval = statement.executeUpdate(sql); 
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		}
		else if (this.attack)
		{
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return 0;
		}
		else
		{
			System.out.println("SDriver: NO CONNECTION!");
			return 0;
		}
	}

	public void addBatch(String arg0) throws SQLException {
		if(connection != null){
			statement.addBatch(arg0);
		}
	}

	public void setCursorName(String arg0) throws SQLException {
		if(connection != null){
			statement.setCursorName(arg0);
		}
	}

	public boolean execute(String sql) throws SQLException {
		manageQuery(sql);
		if((connection != null) && (!this.attack)) {
			boolean retval = false;
             if(connection.handleExceptions == true) {
                    try{
                        retval = statement.execute(sql); 
                    } catch (SQLException sqle) {
                        System.err.println("OLD: " 
                                + sqle.getMessage());
                        Throwable e = null;
                        throw new SQLException(e);
                    }
                } else {
                    retval = statement.execute(sql);
            } 
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return false;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return false;
		}
	}

	public int executeUpdate(String sql, int arg1) throws SQLException {
		manageQuery(sql);
		if((connection != null) && (!this.attack)) {
			int retval = 0;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.executeUpdate(sql, arg1); 
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
                }
            } else {
                retval = statement.executeUpdate(sql, arg1); 
            }
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return 0;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return 0;
		}
	}

	public boolean execute(String sql, int arg1) throws SQLException {
		manageQuery(sql);
		if((connection != null) && (!this.attack)) {
			boolean retval = false;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.execute(sql, arg1);
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
                }
            } else {
                retval = statement.execute(sql, arg1);
            }
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return false;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return false;
		}
	}

	public int executeUpdate(String sql, int[] arg1) throws SQLException {		
		manageQuery(sql);		
		if((connection != null) && (!this.attack))	{
			int retval = 0;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.executeUpdate(sql, arg1);
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
                }
            } else {
                retval = statement.executeUpdate(sql, arg1);
            }
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return 0;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return 0;
		}
	}

	public boolean execute(String sql, int[] arg1) throws SQLException {	
		manageQuery(sql);
		if((connection != null) && (!this.attack)) {
			boolean retval = false;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.execute(sql, arg1);
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
                }
            } else {
                retval = statement.execute(sql, arg1);
            }   
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return false;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return false;
		}
	}

	public Connection getConnection() throws SQLException {
		return connection;
	}

	public ResultSet getGeneratedKeys() throws SQLException {
		return null;
	}
	
	public ResultSet getResultSet() throws SQLException {
		if(connection != null) {
			statement.getResultSet();
		}
		return null;
	}

	public SQLWarning getWarnings() throws SQLException {
		if(connection != null){
			statement.getWarnings();
		}
		return null;
	}
	
	public int executeUpdate(String sql, String[] arg1) throws SQLException {
		manageQuery(sql);
		if((connection != null) && (!this.attack)) {
			int retval = 0;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.executeUpdate(sql, arg1);
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
        
                }
            } else {
                retval = statement.executeUpdate(sql, arg1);
            }   
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return 0;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return 0;
		}
	}

	public boolean execute(String sql, String[] arg1) throws SQLException {
		manageQuery(sql);
		if((connection != null) && (!this.attack)) {
			boolean retval = false;
            if(connection.handleExceptions == true) {
                try{
                    retval = statement.execute(sql, arg1);
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
        
                }
            } else {
                retval = statement.execute(sql, arg1);
            }	
			ResultSet rs = statement.getResultSet();
			resultset = new SResultSet(rs, this);
			return retval;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return false;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return false;
		}
	}

	public ResultSet executeQuery(String sql) throws SQLException {
		manageQuery(sql);	
		if((connection != null) && (!this.attack)) {
            ResultSet rs = null;
			if(connection.handleExceptions == true) {
                try{
                    rs = statement.executeQuery(sql);
                } catch (SQLException sqle) {
                    System.err.println("OLD: " 
                            + sqle.getMessage());
                    Throwable e = null;
                    throw new SQLException(e);
        
                }
            } else {
                rs = statement.executeQuery(sql);
            }
			resultset = new SResultSet(rs, this);
			return resultset;
		} else if (this.attack) {
			System.out.println("SDriver: NO RESULTS...YOU ARE ATTACKING!");
			return null;
		} else {
			System.out.println("SDriver: NO CONNECTION!");
			return null;
		}
	}
    
	/**before the excecution of any query manageQuery retrieves the query string */
	private void manageQuery(String sql) throws SQLException
	{
        System.out.println("SDriver: This is the query the application sent: " +sql);
		boolean mode = false;
        mode = connection.state;
		String strippedDown = null;
		String ID = null;
		strippedDown = strippedDownQuery(sql);
		ID = getStackID(strippedDown);
		queryFilter(mode, ID);
	}
	
	/**strippedDownQuery strips the query down by removing strings, comments and numbers using regex */
	@SuppressWarnings({ "static-access", "static-access" })
    public String strippedDownQuery(String sql)
	{
		Matcher tmpMatcher = this.trivia.matcher(sql);
		sql = tmpMatcher.replaceAll("");
		tmpMatcher = this.numbers.matcher(sql);
		sql = tmpMatcher.replaceAll("$1");
		tmpMatcher = this.comments.matcher(sql);
		sql = tmpMatcher.replaceAll("");
		tmpMatcher = this.escapeChar.matcher(sql);
		sql = tmpMatcher.replaceAll("");
        System.out.println("SDriver: The stripped query: " +sql);
		return sql;
	}
	
	/**
	 * getStackID goes down the stack. Then appends at the end of stack 
	 * trace the stripped down query. Then the hole string becomes the 
	 * input of an MD5 hash algorithm. The output is the signature
	 */
	public String getStackID(String strippedDown)
	{
		String ID = null;
		String complete = null;
		Throwable t = new Throwable();
		StackTraceElement stack[] = t.getStackTrace();
		if (stack == null || stack.length <= 1) return null;
		String frameToString = null;	
		String completeTrace = "";
		StringBuffer sb = new StringBuffer();
		int firstCounter = 0;
		while (firstCounter != stack.length) {
			frameToString = stack[firstCounter].toString();
			completeTrace = completeTrace.concat(frameToString);
			firstCounter++;
		}
		complete = completeTrace.concat(strippedDown);
		byte b[] = null;
		b = complete.getBytes();
		
		String s = null;
		byte d[] = null;
		int secondCounter = 0;
		try {
			MessageDigest algorithm = MessageDigest.getInstance("MD5");
			algorithm.reset();
		    algorithm.update(b);
		    d = algorithm.digest();
		    for(secondCounter = 0; secondCounter<d.length; secondCounter++) {
		       s = Integer.toHexString(d[secondCounter] & 0xff);
		       if(d.length == 1) sb.append('0');
			   sb.append(s);
		    }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		ID = sb.toString();
		return ID;
	}

	/**
	 * While in training mode queryFilter stores the ID to ssql DB
	 * During  production queryFilter checks if the ID exists in the database
	 */
	public void queryFilter(boolean mode, String ID)
	{
		try {
		    //training
			if(!mode) {
                ResultSet rs = statement2.executeQuery("SELECT ID FROM signatures WHERE ID='"+ID+"'");
				if(rs.next()) {	
					System.out.println("SDriver: I do nothing. Duplicate entry. This "+ ID + " exists");
				} else {
					System.out.println("SDriver: Updating "+ ID + "");
					statement2.executeUpdate("INSERT INTO signatures(ID)\n"+" VALUES('"+ ID +"');");
				}
			//production 
			} else if(mode) {
				if(mapID.containsKey(ID) && !this.attack) {
					System.out.println("SDriver: NO NEED TO WORRY");
				} else {
					System.out.println("SDriver: ATTACKING!!! This "+ ID + " does not exists!!!");
					this.attack = true;
				}			
			}		
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}


}
