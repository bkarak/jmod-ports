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
import java.io.*;
import java.sql.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Calendar;
import java.util.Map;

/*
 * Author : Dimitris Mitropoulos
 * Started on 16 September 2006
 * Every type 4 JDBC driver must implements a ResultSet interface
 */

/** SDriver adds no functionality here. It simply calls the corresponding methods of 
 * the underlying driver. */

public class SResultSet implements ResultSet{
	
	protected ResultSet resultset;
	protected Statement statement;

	public SResultSet(ResultSet sourceResultSet, Statement sourceStatement) {
		resultset = sourceResultSet;
		statement = sourceStatement;
	}

	public int getConcurrency() throws SQLException {
		return resultset.getConcurrency();
	}

	public int getFetchDirection() throws SQLException {
		return resultset.getFetchDirection();
	}

	public int getFetchSize() throws SQLException {
		return resultset.getFetchSize();
	}

	public int getRow() throws SQLException {
		return resultset.getRow();
	}

	public int getType() throws SQLException {
		return resultset.getType();
	}

	public void afterLast() throws SQLException {
		resultset.afterLast();
	}

	public void beforeFirst() throws SQLException {	
		resultset.beforeFirst();
	}

	public void cancelRowUpdates() throws SQLException {	
		resultset.cancelRowUpdates();
	}

	public void clearWarnings() throws SQLException {
		resultset.clearWarnings();
	}

	public void close() throws SQLException {	
		resultset.close();
	}

	public void deleteRow() throws SQLException {
		resultset.deleteRow();
	}

	public void insertRow() throws SQLException {
		resultset.insertRow();
	}

	public void moveToCurrentRow() throws SQLException {	
		resultset.moveToCurrentRow();
	}

	public void moveToInsertRow() throws SQLException {
		resultset.moveToInsertRow();
	}

	public void refreshRow() throws SQLException {
		resultset.refreshRow();
	}

	public void updateRow() throws SQLException {
		resultset.updateRow();
	}

	public boolean first() throws SQLException {
		return resultset.first();
	}

	public boolean isAfterLast() throws SQLException {
		return resultset.isAfterLast();
	}

	public boolean isBeforeFirst() throws SQLException {
		return resultset.isBeforeFirst();
	}

	public boolean isFirst() throws SQLException {
		return resultset.isFirst();
	}

	public boolean isLast() throws SQLException {
		return resultset.isLast();
	}

	public boolean last() throws SQLException {
		return resultset.last();
	}

	public boolean next() throws SQLException {
		return resultset.next();
	}

	public boolean previous() throws SQLException {
		return resultset.previous();
	}

	public boolean rowDeleted() throws SQLException {
		return resultset.rowDeleted();
	}

	public boolean rowInserted() throws SQLException {
		return resultset.rowInserted();
	}

	public boolean rowUpdated() throws SQLException {
		return resultset.rowUpdated();
	}

	public boolean wasNull() throws SQLException {
		return resultset.wasNull();
	}

	public byte getByte(int arg0) throws SQLException {
		return resultset.getByte(arg0);
	}

	public double getDouble(int arg0) throws SQLException {
		return resultset.getDouble(arg0);
	}

	public float getFloat(int arg0) throws SQLException {
		return resultset.getFloat(arg0);
	}

	public int getInt(int arg0) throws SQLException {
		return resultset.getInt(arg0);
	}

	public long getLong(int arg0) throws SQLException {
		return resultset.getLong(arg0);
	}

	public short getShort(int arg0) throws SQLException {
		return resultset.getShort(arg0);
	}

	public void setFetchDirection(int arg0) throws SQLException {		
		resultset.setFetchDirection(arg0);
	}

	public void setFetchSize(int arg0) throws SQLException {
		resultset.setFetchSize(arg0);
	}

	public void updateNull(int arg0) throws SQLException {
		resultset.updateNull(arg0);
	}

	public boolean absolute(int arg0) throws SQLException {
		return resultset.absolute(arg0);
	}

	public boolean getBoolean(int arg0) throws SQLException {
		return resultset.getBoolean(arg0);
	}

	public boolean relative(int arg0) throws SQLException {
		return resultset.relative(arg0);
	}

	public byte[] getBytes(int arg0) throws SQLException {
		return resultset.getBytes(arg0);
	}

	public void updateByte(int arg0, byte arg1) throws SQLException {
		resultset.updateByte(arg0,arg1);
	}

	public void updateDouble(int arg0, double arg1) throws SQLException {
		resultset.updateDouble(arg0,arg1);
	}

	public void updateFloat(int arg0, float arg1) throws SQLException {
		resultset.updateFloat(arg0,arg1);
	}

	public void updateInt(int arg0, int arg1) throws SQLException {
		resultset.updateInt(arg0,arg1);
	}

	public void updateLong(int arg0, long arg1) throws SQLException {
		resultset.updateLong(arg0,arg1);
	}

	public void updateShort(int arg0, short arg1) throws SQLException {
		resultset.updateShort(arg0,arg1);
	}

	public void updateBoolean(int arg0, boolean arg1) throws SQLException {
		resultset.updateBoolean(arg0,arg1);
	}

	public void updateBytes(int arg0, byte[] arg1) throws SQLException {
		resultset.updateBytes(arg0,arg1);
	}

	public InputStream getAsciiStream(int arg0) throws SQLException {
		return resultset.getAsciiStream(arg0);
	}

	public InputStream getBinaryStream(int arg0) throws SQLException {		
		return resultset.getBinaryStream(arg0);
	}

	@SuppressWarnings("deprecation")
    public InputStream getUnicodeStream(int arg0) throws SQLException {		
		return resultset.getUnicodeStream(arg0);
	}

	public void updateAsciiStream(int arg0, InputStream arg1, int arg2) throws SQLException {		
		resultset.updateAsciiStream(arg0,arg1,arg2);
	}

	public void updateBinaryStream(int arg0, InputStream arg1, int arg2) throws SQLException {
		resultset.updateBinaryStream(arg0,arg1, arg2);
	}

	public Reader getCharacterStream(int arg0) throws SQLException {	
		return resultset.getCharacterStream(arg0);
	}

	public void updateCharacterStream(int arg0, Reader arg1, int arg2) throws SQLException {	
		resultset.updateCharacterStream(arg0,arg1,arg2);
	}

	public Object getObject(int arg0) throws SQLException {		
		return resultset.getObject(arg0);
	}

	public void updateObject(int arg0, Object arg1) throws SQLException {		
		resultset.updateObject(arg0,arg1);
	}

	public void updateObject(int arg0, Object arg1, int arg2) throws SQLException {
		resultset.updateObject(arg0,arg1,arg2);
	}

	public String getCursorName() throws SQLException {		
		return resultset.getCursorName();
	}

	public String getString(int arg0) throws SQLException {		
		return resultset.getString(arg0);
	}

	public void updateString(int arg0, String arg1) throws SQLException {
		resultset.updateString(arg0,arg1);
	}

	public byte getByte(String arg0) throws SQLException {		
		return resultset.getByte(arg0);
	}

	public double getDouble(String arg0) throws SQLException {		
		return resultset.getDouble(arg0);
	}

	public float getFloat(String arg0) throws SQLException {		
		return resultset.getFloat(arg0);
	}

	public int findColumn(String arg0) throws SQLException {		
		return resultset.findColumn(arg0);
	}

	public int getInt(String arg0) throws SQLException {		
		return resultset.getInt(arg0);
	}

	public long getLong(String arg0) throws SQLException {		
		return resultset.getLong(arg0);
	}

	public short getShort(String arg0) throws SQLException {		
		return resultset.getShort(arg0);
	}

	public void updateNull(String arg0) throws SQLException {
		resultset.updateNull(arg0);
	}

	public boolean getBoolean(String arg0) throws SQLException {		
		return resultset.getBoolean(arg0);
	}

	public byte[] getBytes(String arg0) throws SQLException {		
		return resultset.getBytes(arg0);
	}

	public void updateByte(String arg0, byte arg1) throws SQLException {
		resultset.updateByte(arg0,arg1);
	}

	public void updateDouble(String arg0, double arg1) throws SQLException {
		resultset.updateDouble(arg0,arg1);
	}

	public void updateFloat(String arg0, float arg1) throws SQLException {
		resultset.updateFloat(arg0,arg1);
	}

	public void updateInt(String arg0, int arg1) throws SQLException {
		resultset.updateInt(arg0,arg1);
	}

	public void updateLong(String arg0, long arg1) throws SQLException {
		resultset.updateLong(arg0,arg1);
	}

	public void updateShort(String arg0, short arg1) throws SQLException {
		resultset.updateShort(arg0,arg1);	
	}

	public void updateBoolean(String arg0, boolean arg1) throws SQLException {
		resultset.updateBoolean(arg0,arg1);
	}

	public void updateBytes(String arg0, byte[] arg1) throws SQLException {
		resultset.updateBytes(arg0,arg1);
	}

	public BigDecimal getBigDecimal(int arg0) throws SQLException {		
		return resultset.getBigDecimal(arg0);
	}

	@SuppressWarnings("deprecation")
    public BigDecimal getBigDecimal(int arg0, int arg1) throws SQLException {		
		return resultset.getBigDecimal(arg0,arg1);
	}

	public void updateBigDecimal(int arg0, BigDecimal arg1) throws SQLException {
		resultset.updateBigDecimal(arg0,arg1);
	}
	 
	public URL getURL(int arg0) throws SQLException {		
		return resultset.getURL(arg0);
	}

	public Array getArray(int arg0) throws SQLException {		
		return resultset.getArray(arg0);
	}

	public void updateArray(int arg0, Array arg1) throws SQLException {		
		resultset.updateArray(arg0,arg1);
	}

	public Blob getBlob(int arg0) throws SQLException {		
		return resultset.getBlob(arg0);
	}

	public void updateBlob(int arg0, Blob arg1) throws SQLException {		
		resultset.updateBlob(arg0,arg1);
	}

	public Clob getClob(int arg0) throws SQLException {		
		return resultset.getClob(arg0);
	}

	public void updateClob(int arg0, Clob arg1) throws SQLException {		
		resultset.updateClob(arg0,arg1);
	}

	public Date getDate(int arg0) throws SQLException {		
		return resultset.getDate(arg0);
	}

	public void updateDate(int arg0, Date arg1) throws SQLException {
		resultset.updateDate(arg0,arg1);
	}

	public Ref getRef(int arg0) throws SQLException {		
		return resultset.getRef(arg0);
	}

	public void updateRef(int arg0, Ref arg1) throws SQLException {
		resultset.updateRef(arg0,arg1);
	}

	public ResultSetMetaData getMetaData() throws SQLException {		
		return resultset.getMetaData();
	}

	public SQLWarning getWarnings() throws SQLException {		
		return resultset.getWarnings();
	}

	public Statement getStatement() throws SQLException {		
		return statement;
	}

	public Time getTime(int arg0) throws SQLException {		
		return resultset.getTime(arg0);
	}

	public void updateTime(int arg0, Time arg1) throws SQLException {		
		resultset.updateTime(arg0,arg1);
	}

	public Timestamp getTimestamp(int arg0) throws SQLException {		
		return resultset.getTimestamp(arg0);
	}

	public void updateTimestamp(int arg0, Timestamp arg1) throws SQLException {		
		resultset.updateTimestamp(arg0,arg1);
	}

	public InputStream getAsciiStream(String arg0) throws SQLException {		
		return resultset.getAsciiStream(arg0);
	}

	public InputStream getBinaryStream(String arg0) throws SQLException {		
		return resultset.getBinaryStream(arg0);
	}

	@SuppressWarnings("deprecation")
    public InputStream getUnicodeStream(String arg0) throws SQLException {		
		return resultset.getUnicodeStream(arg0);
	}

	public void updateAsciiStream(String arg0, InputStream arg1, int arg2) throws SQLException {
		resultset.updateAsciiStream(arg0,arg1,arg2);
	}

	public void updateBinaryStream(String arg0, InputStream arg1, int arg2) throws SQLException {		
		resultset.updateBinaryStream(arg0,arg1,arg2);
	}

	public Reader getCharacterStream(String arg0) throws SQLException {		
		return resultset.getCharacterStream(arg0);
	}

	public void updateCharacterStream(String arg0, Reader arg1, int arg2) throws SQLException {
		resultset.updateCharacterStream(arg0,arg1,arg2);
	}

	public Object getObject(String arg0) throws SQLException {		
		return resultset.getObject(arg0);
	}

	public void updateObject(String arg0, Object arg1) throws SQLException {	
		resultset.updateObject(arg0,arg1);
	}

	public void updateObject(String arg0, Object arg1, int arg2) throws SQLException {
		resultset.updateObject(arg0,arg1,arg2);
	}

	@SuppressWarnings("unchecked")
    public Object getObject(int arg0, Map arg1) throws SQLException {		
		return resultset.getObject(arg0,arg1);
	}

	public String getString(String arg0) throws SQLException {		
		return resultset.getString(arg0);
	}

	public void updateString(String arg0, String arg1) throws SQLException {
		resultset.updateString(arg0,arg1);
	}

	public BigDecimal getBigDecimal(String arg0) throws SQLException {	
		return resultset.getBigDecimal(arg0);
	}

	@SuppressWarnings("deprecation")
    public BigDecimal getBigDecimal(String arg0, int arg1) throws SQLException {		
		return resultset.getBigDecimal(arg0,arg1);
	}

	public void updateBigDecimal(String arg0, BigDecimal arg1) throws SQLException {
		resultset.updateBigDecimal(arg0,arg1);
	}

	public URL getURL(String arg0) throws SQLException {	
		return resultset.getURL(arg0);
	}

	public Array getArray(String arg0) throws SQLException {	
		return resultset.getArray(arg0);
	}

	public void updateArray(String arg0, Array arg1) throws SQLException {
		resultset.updateArray(arg0,arg1);
	}

	public Blob getBlob(String arg0) throws SQLException {		
		return resultset.getBlob(arg0);
	}

	public void updateBlob(String arg0, Blob arg1) throws SQLException {
		resultset.updateBlob(arg0,arg1);
	}

	public Clob getClob(String arg0) throws SQLException {
		return resultset.getClob(arg0);
	}

	public void updateClob(String arg0, Clob arg1) throws SQLException {
		resultset.updateClob(arg0,arg1);
	}

	public Date getDate(String arg0) throws SQLException {
		return resultset.getDate(arg0);
	}

	public void updateDate(String arg0, Date arg1) throws SQLException {
		resultset.updateDate(arg0,arg1);
	}

	public Date getDate(int arg0, Calendar arg1) throws SQLException {
		return resultset.getDate(arg0,arg1);
	}

	public Ref getRef(String arg0) throws SQLException {	
		return resultset.getRef(arg0);
	}

	public void updateRef(String arg0, Ref arg1) throws SQLException {
		resultset.updateRef(arg0,arg1);
	}

	public Time getTime(String arg0) throws SQLException {		
		return resultset.getTime(arg0);
	}

	public void updateTime(String arg0, Time arg1) throws SQLException {	
		resultset.updateTime(arg0,arg1);
	}

	public Time getTime(int arg0, Calendar arg1) throws SQLException {		
		return resultset.getTime(arg0,arg1);
	}
	 
	public Timestamp getTimestamp(String arg0) throws SQLException {		
		return resultset.getTimestamp(arg0);
	}

	public void updateTimestamp(String arg0, Timestamp arg1) throws SQLException {
		resultset.updateTimestamp(arg0,arg1);
	}

	public Timestamp getTimestamp(int arg0, Calendar arg1) throws SQLException {		
		return resultset.getTimestamp(arg0,arg1);
	}



	public Date getDate(String arg0, Calendar arg1) throws SQLException {	
		return resultset.getDate(arg0,arg1);
	}

	public Time getTime(String arg0, Calendar arg1) throws SQLException {	
		return resultset.getTime(arg0,arg1);
	}

	public Timestamp getTimestamp(String arg0, Calendar arg1) throws SQLException {
		return resultset.getTimestamp(arg0,arg1);
	}

	public int getHoldability() throws SQLException {
        return resultset.getHoldability();
	}

	public Reader getNCharacterStream(int arg0) throws SQLException {
		return resultset.getNCharacterStream(arg0);
	}

	public Reader getNCharacterStream(String arg0) throws SQLException {
        return resultset.getNCharacterStream(arg0);
	}

	public NClob getNClob(int arg0) throws SQLException {
        return resultset.getNClob(arg0);
	}

	public NClob getNClob(String arg0) throws SQLException {
        return resultset.getNClob(arg0);
	}

	public String getNString(int arg0) throws SQLException {
		return resultset.getNString(arg0);
	}

	public String getNString(String arg0) throws SQLException {
        return resultset.getNString(arg0);
	}

	public RowId getRowId(int arg0) throws SQLException {
		return resultset.getRowId(arg0);
	}

	public RowId getRowId(String arg0) throws SQLException {
        return resultset.getRowId(arg0);
	}

	public SQLXML getSQLXML(int arg0) throws SQLException {
		return resultset.getSQLXML(arg0);
	}

	public SQLXML getSQLXML(String arg0) throws SQLException {
        return resultset.getSQLXML(arg0);
	}

	public boolean isClosed() throws SQLException {
		return resultset.isClosed();
	}

	public void updateAsciiStream(int arg0, InputStream arg1) throws SQLException {	
	}

	public void updateAsciiStream(String arg0, InputStream arg1) throws SQLException {	
	}

	public void updateAsciiStream(int arg0, InputStream arg1, long arg2) throws SQLException {	
	}

	public void updateAsciiStream(String arg0, InputStream arg1, long arg2) throws SQLException {	
	}

	public void updateBinaryStream(int arg0, InputStream arg1) throws SQLException {		
	}

	public void updateBinaryStream(String arg0, InputStream arg1) throws SQLException {		
	}

	public void updateBinaryStream(int arg0, InputStream arg1, long arg2) throws SQLException {		
	}

	public void updateBinaryStream(String arg0, InputStream arg1, long arg2) throws SQLException {		
	}

	public void updateBlob(int arg0, InputStream arg1) throws SQLException {		
	}

	public void updateBlob(String arg0, InputStream arg1) throws SQLException {		
	}

	public void updateBlob(int arg0, InputStream arg1, long arg2) throws SQLException {		
	}

	public void updateBlob(String arg0, InputStream arg1, long arg2) throws SQLException {		
	}

	public void updateCharacterStream(int arg0, Reader arg1) throws SQLException {		
	}

	public void updateCharacterStream(String arg0, Reader arg1) throws SQLException {		
	}

	public void updateCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {		
	}

	public void updateCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {
	}

	public void updateClob(int arg0, Reader arg1) throws SQLException {		
	}

	public void updateClob(String arg0, Reader arg1) throws SQLException {		
	}

	public void updateClob(int arg0, Reader arg1, long arg2) throws SQLException {		
	}

	public void updateClob(String arg0, Reader arg1, long arg2) throws SQLException {		
	}

	public void updateNCharacterStream(int arg0, Reader arg1) throws SQLException {		
	}

	public void updateNCharacterStream(String arg0, Reader arg1) throws SQLException {		
	}

	public void updateNCharacterStream(int arg0, Reader arg1, long arg2) throws SQLException {
	}

	public void updateNCharacterStream(String arg0, Reader arg1, long arg2) throws SQLException {	
	}

	public void updateNClob(int arg0, NClob arg1) throws SQLException {			
	}

	public void updateNClob(String arg0, NClob arg1) throws SQLException {	
	}

	public void updateNClob(int arg0, Reader arg1) throws SQLException {
	}

	public void updateNClob(String arg0, Reader arg1) throws SQLException {		
	}

	public void updateNClob(int arg0, Reader arg1, long arg2) throws SQLException {	
	}

	public void updateNClob(String arg0, Reader arg1, long arg2) throws SQLException {	
	}

	public void updateNString(int arg0, String arg1) throws SQLException {	
	}

	public void updateNString(String arg0, String arg1) throws SQLException {	
	}

	public void updateRowId(int arg0, RowId arg1) throws SQLException {	
	}

	public void updateRowId(String arg0, RowId arg1) throws SQLException {	
	}

	public void updateSQLXML(int arg0, SQLXML arg1) throws SQLException {	
	}

	public void updateSQLXML(String arg0, SQLXML arg1) throws SQLException {	
	}

	@SuppressWarnings("unchecked")
    public Object getObject(String arg0, Map arg1) throws SQLException {
		return resultset.getObject(arg0, arg1);
	}

	public boolean isWrapperFor(Class arg0) throws SQLException {
		return resultset.isWrapperFor(arg0);
	}

	@SuppressWarnings("unchecked")
    public Object unwrap(Class arg0) throws SQLException {
		return resultset.unwrap(arg0);
	}

}
