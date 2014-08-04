package info.bogossian.tablesync.model.dao;

import info.bogossian.tablesync.model.domain.Attribute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class ReaderDAO extends DAOResource{
	
	private static final String dburl = "jdbc:mysql://terra.dpi.inpe.br/terra";
	
	private static final String sql = "select * from focosAtributos";
	
	private static final String dbusername  = "proarco";

	private static final String dbpassword  = "at42vx12";
	
	private WriterDAO writer;
	
	public ReaderDAO(WriterDAO writer)
	{
		this.writer=writer;
	}
	
	public void copyTableData() throws ClassNotFoundException
	{
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection conn = getConnection(dburl, dbusername, dbpassword);
			
			PreparedStatement pstmt = conn.prepareStatement(sql,java.sql.ResultSet.TYPE_FORWARD_ONLY,
		              java.sql.ResultSet.CONCUR_READ_ONLY);
			pstmt.setFetchSize(Integer.MIN_VALUE);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			

			while(rs.next())
			{
				ArrayList<Attribute> attributes = new ArrayList<Attribute>();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					Attribute attribute = Attribute.getAttribute(rsmd.getColumnClassName(i));
					attribute.setName(rsmd.getColumnName(i));
					readAttributesValues(attribute, rs);
					attributes.add(attribute);
				}
				writer.writeData(attributes);
			}
			closeResources(conn, pstmt, rs);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}
