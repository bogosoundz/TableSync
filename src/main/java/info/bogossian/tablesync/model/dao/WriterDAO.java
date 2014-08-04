package info.bogossian.tablesync.model.dao;

import info.bogossian.tablesync.model.domain.Attribute;
import info.bogossian.tablesync.model.domain.DoubleAttribute;
import info.bogossian.tablesync.model.domain.StringAttribute;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WriterDAO extends DAOResource{
	
	private static final String dburl = "jdbc:postgresql://chronos.dpi.inpe.br:5433/FocosQueimadas?allowEncodingChanges";
	
	private static final String dbusername  = "postgres";

	private static final String dbpassword  = "postgres";
	private static final String sql  = "set client_encoding='UTF-8'; INSERT INTO \"focosAtributos\" (ID, \"LAT\", \"LON\", \"Data\", \"Satelite\", \"Municipio\", \"Estado\"," +
			" \"Regiao\", \"Pais\", \"Vegetacao\", \"Suscetibilidade\", \"Precipitacao\"," +
			" \"NumDiasSemPrecip\", \"Risco\", \"BiomaBrasileiro\", \"SateliteRef\", \"spatial_data\") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,$)";
	
	private static final int latIdxColumn = 1;
	private static final int lonIdxColumn = 2;
	private static final int idIdxColumn = 0;

	public void writeData(ArrayList<Attribute> data) throws ClassNotFoundException
	{
		try {
			Class.forName("org.postgresql.Driver");
		
			Connection conn = getConnection(dburl, dbusername, dbpassword);
			
			
			String id= ((StringAttribute)data.get(idIdxColumn)).getValue();	
			if(!verifyIfExists(conn, id))
			{
				Double lon = ((DoubleAttribute)data.get(lonIdxColumn)).getValue();				
				Double lat = ((DoubleAttribute)data.get(latIdxColumn)).getValue();
				
				String sql_geom = sql.replace("$", "ST_GeomFromText('POINT(" + lon +" "+ lat + ")', 4326)");
//				System.out.println(sql_geom);
				
				
				PreparedStatement pstmt = conn.prepareStatement(sql_geom);
				fillStatement(pstmt, data);
				
				pstmt.execute();
				
				closeResources(null, pstmt, null);
				System.out.println("Gravado registro " + id + ".");
			}
			else
			{
				System.out.println("Pulando registro " + id + " já existe.");
			}
			

			
		} catch (SQLException ex) {
			System.err.println("Erro ao escrever objeto " + data);
			ex.printStackTrace();
		}
	}
/*	public ArrayList<ArrayList<Attribute>> writeTableData(ArrayList<ArrayList<Attribute>> resultList) throws ClassNotFoundException
	{
		try {
			Class.forName("org.postgresql.Driver");
		
			Connection conn = getConnection(dburl, dbusername, dbpassword);
			
			for (ArrayList<Attribute> arrayList : resultList) {
				
				String id= ((StringAttribute)arrayList.get(idIdxColumn)).getValue();	
				if(!verifyIfExists(conn, id))
				{
					Double lon = ((DoubleAttribute)arrayList.get(lonIdxColumn)).getValue();				
					Double lat = ((DoubleAttribute)arrayList.get(latIdxColumn)).getValue();
					
					String sql_geom = sql.replace("$", "ST_GeomFromText('POINT(" + lon +" "+ lat + ")', 4326)");
					System.out.println(sql_geom);
					
					
					PreparedStatement pstmt = conn.prepareStatement(sql_geom);
					
					fillStatement(pstmt, arrayList);
					
					pstmt.execute();
					
					pstmt.close();
				}
				

			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return resultList;
	}*/
	private void fillStatement(PreparedStatement pstmt, ArrayList<Attribute> attributes) throws SQLException
	{
		for (int i = 0; i < attributes.size(); i++) {
			
			writeStatementAttributeValue(i+1, attributes.get(i), pstmt);			
		}

	}
	private boolean verifyIfExists(Connection conn, String id) throws SQLException
	{
		String sql = "SELECT * FROM \"focosAtributos\" where id = ?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, id);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
		{
			closeResources(null, pstmt, rs);
			return true;
		}
		else
		{
			closeResources(null, pstmt, rs);
			return false;
		}
			
	}

}
