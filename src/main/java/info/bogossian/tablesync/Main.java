package info.bogossian.tablesync;

import info.bogossian.tablesync.model.domain.Attribute;
import info.bogossian.tablesync.model.domain.DateAttribute;
import info.bogossian.tablesync.model.domain.DoubleAttribute;
import info.bogossian.tablesync.model.domain.IntegerAttribute;
import info.bogossian.tablesync.model.domain.StringAttribute;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.read();
	}
	
	public void read()
	{
		try {
		
			Connection conn =
		       DriverManager.getConnection("jdbc:mysql://terra.dpi.inpe.br/terra?" +
		                                   "user=proarco&password=at42vx12");

			System.out.println(conn.isValid(0));
			String sql="select * from focosAtributos limit 10";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			ArrayList<ArrayList<Attribute>> resultList = new ArrayList<ArrayList<Attribute>>();
			while(rs.next())
			{
				ArrayList<Attribute> attributes = new ArrayList<Attribute>();
				for (int i = 0; i < rsmd.getColumnCount(); i++) {
					Attribute attribute = Attribute.getAttribute(rsmd.getColumnTypeName(i));
					attribute.setName(rsmd.getColumnName(i));
					readAttributesValues(attribute, rs);
					attributes.add(attribute);
				}
				resultList.add(attributes);
			}
			
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	private void readAttributesValues(Attribute attribute, ResultSet rs) throws SQLException
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
	}
	
	

}
