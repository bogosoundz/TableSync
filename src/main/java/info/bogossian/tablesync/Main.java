package info.bogossian.tablesync;

import info.bogossian.tablesync.model.dao.ReaderDAO;
import info.bogossian.tablesync.model.dao.WriterDAO;
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
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.execute();
	}
	
	public void execute() throws ClassNotFoundException
	{
		ReaderDAO r = new ReaderDAO(new WriterDAO());
		r.copyTableData();
		
	}

	
	

}
