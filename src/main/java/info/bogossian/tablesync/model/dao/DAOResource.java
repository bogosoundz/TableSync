package info.bogossian.tablesync.model.dao;

import info.bogossian.tablesync.model.domain.Attribute;
import info.bogossian.tablesync.model.domain.DateAttribute;
import info.bogossian.tablesync.model.domain.DoubleAttribute;
import info.bogossian.tablesync.model.domain.IntegerAttribute;
import info.bogossian.tablesync.model.domain.StringAttribute;
import info.bogossian.tablesync.model.domain.TimestampAttribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DAOResource {

	private Connection conn;
	
	
	protected void readAttributesValues(Attribute attribute, ResultSet rs) throws SQLException
	{
		
		if (attribute instanceof StringAttribute) {
			((StringAttribute) attribute).setValue(rs.getString(attribute.getName()));
		}
		if (attribute instanceof DoubleAttribute) {
			((DoubleAttribute) attribute).setValue(rs.getDouble(attribute.getName()));
		}
		if (attribute instanceof IntegerAttribute) {
			((IntegerAttribute) attribute).setValue(rs.getInt(attribute.getName()));
		}
		if (attribute instanceof DateAttribute) {
			((DateAttribute) attribute).setValue(rs.getDate(attribute.getName()));
		}
		if (attribute instanceof TimestampAttribute) {
			((TimestampAttribute) attribute).setValue(rs.getTimestamp(attribute.getName()));
		}
	}
	public Connection getConnection(String connStr, String user, String password)
			throws SQLException {
		if(conn==null||
				!conn.isValid(0))
		{
			Properties props = new Properties();
			props.setProperty("user",user);
			props.setProperty("password",password);
			props.setProperty("allowEncodingChanges", "true");
			conn = DriverManager.getConnection(connStr, props);
		}
		return conn;
	}
	protected void writeStatementAttributeValue(int idx, Attribute attribute, PreparedStatement pstmt) throws SQLException
	{
		
		if (attribute instanceof StringAttribute) {
			pstmt.setString(idx, ((StringAttribute) attribute).getValue());
		}
		if (attribute instanceof DoubleAttribute) {
			pstmt.setDouble(idx, ((DoubleAttribute) attribute).getValue());
		}
		if (attribute instanceof IntegerAttribute) {
			pstmt.setInt(idx, ((IntegerAttribute) attribute).getValue());
		}
		if (attribute instanceof DateAttribute) {
			java.sql.Date sqlDate = new java.sql.Date(((DateAttribute) attribute).getValue().getTime());
			pstmt.setDate(idx, sqlDate);
		}
		if (attribute instanceof TimestampAttribute) {
			pstmt.setTimestamp(idx, ((TimestampAttribute) attribute).getValue());
		}
	}
	protected void closeResources(Connection conn, PreparedStatement pstmt, ResultSet rs) throws SQLException
	{
		if (conn!=null
				&&!conn.isClosed()) {
			conn.close();
			conn=null;
		}
		if (pstmt!=null
				&&!pstmt.isClosed()) {
			pstmt.close();
			pstmt=null;
		}
		if (rs!=null
				&&!rs.isClosed()) {
			rs.close();
			rs=null;
		}
	}
}
