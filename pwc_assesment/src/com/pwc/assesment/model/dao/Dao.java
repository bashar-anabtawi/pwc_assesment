package com.pwc.assesment.model.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hsqldb.jdbc.JDBCDataSource;

import com.pwc.assesment.utils.Log;
import com.pwc.assesment.utils.StringUtils;


public class Dao {

	public static Dao DAO = new Dao();
	// TODO: we can add code to handle deadlocks, by providing a thread safe getInstance() method.
	
	
	private Dao() {
	}

	public Connection getConnection() {
		Connection conn = null;

		JDBCDataSource ds = new JDBCDataSource();
		ds.setURL("jdbc:h2:˜/test");
		ds.setUser("sa");
		ds.setPassword("sa");
		
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public boolean isTableExists(String tableName) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rslt = null;
		
		try{
			// validateConnectionStatus();
			
			conn = getConnection();
			DatabaseMetaData metaData = conn.getMetaData();
			
			ResultSet res = metaData.getTables(null, null, null, new String[] {"TABLE"});
			List<String> tables = new ArrayList<String>();
			
			while (res.next()) {
				tables.add(res.getString("TABLE_NAME").toLowerCase());
			}
			
			return tables.contains(tableName.toLowerCase());
		} catch(SQLException e){
			throw new RuntimeException("DB error while getting tables names.");
		} finally {
			closeResources(rslt, stmt, conn);
		}
	}
	
	public void executeUpdate(String updateStatement) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rslt = null;
		
		try{
			if(StringUtils.isNullOrEmpty(updateStatement)) {
				return;
			}
			
			conn = getConnection();
			stmt = conn.createStatement();
			stmt.executeUpdate(updateStatement);

			Log.info(this, "The following update query is executed successfully [" + updateStatement + "]");
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeResources(rslt, stmt, conn);
		}
	}
	
	public int getNextPrimaryKeyId(String tableName){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rslt = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			if(!isTableExists(tableName)) {
				return 1;
			}
			
			rslt = stmt.executeQuery("SELECT MAX(id) AS maxId FROM " + tableName + ";");
			
			if(rslt.next()) {
				Integer maxId = rslt.getInt("maxId");

				return maxId + 1;				
			} else {
				return 1;
			}
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeResources(rslt, stmt, conn);
		}
	}
	
	public List<List<Object>> select(String tableName){

		Connection conn = null;
		Statement stmt = null;
		ResultSet rslt = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rslt = stmt.executeQuery("SELECT * FROM " + tableName + ";");

			List<List<Object>> table = populateTable(rslt);
			
			return table;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeResources(rslt, stmt, conn);
		}
	}
	
	public List<List<Object>> selectWhere(String tableName, String column, Object value){

		Connection conn = null;
		Statement stmt = null;
		ResultSet rslt = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			
			String formattedValue = "";
			
			if(value instanceof String){
				formattedValue = "'" + value + "'";
			} else if(value instanceof Integer){
				formattedValue = String.valueOf(value);
			}
			
			
			rslt = stmt.executeQuery("SELECT * FROM " + tableName + " where " + column + "=" + formattedValue + ";");

			List<List<Object>> table = populateTable(rslt);
			
			return table;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeResources(rslt, stmt, conn);
		}
	}
	
	private List<List<Object>> populateTable(ResultSet rslt) throws SQLException {
		List<List<Object>> table = new ArrayList<List<Object>>();
		
		ResultSetMetaData metaData = rslt.getMetaData();

		while (rslt.next()) {
			List<Object> record = new ArrayList<>();

			for (int i = 1; i <= metaData.getColumnCount(); i++) {
				record.add(rslt.getObject(i));
			}

			table.add(record);
		}
		
		return table;
	}
	
	public List<List<Object>> execute(String query){
		Connection conn = null;
		Statement stmt = null;
		ResultSet rslt = null;
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			rslt = stmt.executeQuery(query);

			Log.info(this, "The following query is executed successfully [" + query + "]");
			List<List<Object>> table = populateTable(rslt);
			return table;
		} catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			closeResources(rslt, stmt, conn);
		}
	}
	
	
	
	private void closeResources(ResultSet rslt, Statement stmt, Connection conn) {
		try { if (rslt != null) rslt.close(); } catch(Exception e) { e.printStackTrace();}
		try { if (stmt != null) stmt.close(); } catch(Exception e) { e.printStackTrace();}
		try { if (conn != null) conn.close(); } catch(Exception e) { e.printStackTrace();}
	}
	
}
