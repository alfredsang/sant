package cn.com.st.dss.jdbc;

import java.sql.*;
import java.util.*;
import net.sf.json.JSONObject;
 
public class DBConnection {
	public static ThreadLocal threadConnection = new ThreadLocal();
	public Connection conn = null;

	public DBConnection() {
		conn=getMySqlConnection();
	}

	public Connection getMySqlConnection() {
		try {
			Class.forName(Constants.driver);
			conn = DriverManager.getConnection(Constants.url, Constants.name,
					Constants.password);
			if (conn == null)
				throw new SQLException("Can't connect MsSql!");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Connection getCurrentConnection() throws SQLException {
		conn = (Connection) threadConnection.get();
		if (conn == null) {
			conn = getMySqlConnection();
			threadConnection.set(conn);
		}
		return conn;
	}

	public Connection getCurrentConnection(boolean isTransaction)
			throws SQLException {
		conn = (Connection) threadConnection.get();
		if (conn == null) {
			conn = getMySqlConnection();
			threadConnection.set(conn);
		}
		if (isTransaction)
			conn.setAutoCommit(false);
		return conn;
	}

	public void closeCurrentConnection() {
		try {
			Connection conn = (Connection) threadConnection.get();
			threadConnection.set(null);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection conn) throws SQLException {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void beginTransaction() {
		try {
			getCurrentConnection().setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void commitTransaction() {
		try {
			getCurrentConnection().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void rollbackTransaction() {
		try {
			getCurrentConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int executeUpdate(String sqlQuery, String sqlValue[]) {
		int count = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sqlQuery);
			if (sqlValue != null) {
				for (int i = 0; i < sqlValue.length; i++)
					ps.setString(i + 1, sqlValue[i]);
			}
			count = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int insert(String sql) {

		ResultSet rs = null;
		PreparedStatement ps;
		int i = 0;
		try {
			getCurrentConnection(true);
			ps = conn.prepareStatement(sql);
			i = ps.executeUpdate();
			commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			rollbackTransaction();
		}
		return i;
	}
 	public int executeQuery(String sql) {
		ResultSet rs = null;
		PreparedStatement ps;
		int count = 0;
		try {
			getCurrentConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public String getColumnValue(String sql, int l) {
		Statement st = null;
		// String colum[] = new Arrays();
		try {

			getCurrentConnection(true);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			int count = rsmd.getColumnCount();
			String colum[] = new String[count];
			for (int i = 0; i < colum.length; i++)
				if (rsmd.getColumnName(i + 1) != null)
					colum[i] = rsmd.getColumnName(i + 1);
				else
					colum[i] = rsmd.getColumnLabel(i + 1);

			HashMap row = null;
			String fieldValue = null;
			rs.next();

			row = new HashMap();
			for (int j = 0; j < colum.length; j++) {
				int iType = rsmd.getColumnType(j + 1);
				if (iType == 2 || iType == 3) {
					if (rsmd.getScale(j + 1) == 0)
						fieldValue = String.valueOf(rs.getLong(j + 1));
					else
						fieldValue = rs.getString(j + 1);
				} else if (iType == 8)
					fieldValue = String.valueOf(rs.getDouble(j + 1));
				else if (iType == 6 || iType == 7)
					fieldValue = String.valueOf(rs.getFloat(j + 1));
				else
					fieldValue = rs.getString(j + 1);
				if (fieldValue == null)
					fieldValue = "";
				else
					fieldValue = fieldValue.trim();
				row.put(colum[j], fieldValue);// .toLowerCase()
			}

			commitTransaction();
			return row.get(colum[l]).toString();
		} catch (Exception e) {
			rollbackTransaction();
		}
		return "";
	}

	public ArrayList executeQueryList(String sql) {
		Statement st = null;
		ArrayList alResult = new ArrayList();
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			int count = rsmd.getColumnCount();
			String colum[] = new String[count];
			for (int i = 0; i < colum.length; i++)
				if (rsmd.getColumnName(i + 1) != null)
					colum[i] = rsmd.getColumnName(i + 1);
				else
					colum[i] = rsmd.getColumnLabel(i + 1);

			HashMap row = null;
			String fieldValue = null;
			for (; rs.next(); alResult.add(row)) {
				row = new HashMap();
				for (int i = 0; i < colum.length; i++) {
					int iType = rsmd.getColumnType(i + 1);
					if (iType == 2 || iType == 3) {
						if (rsmd.getScale(i + 1) == 0)
							fieldValue = String.valueOf(rs.getLong(i + 1));
						else
							fieldValue = rs.getString(i + 1);
					} else if (iType == 8)
						fieldValue = String.valueOf(rs.getDouble(i + 1));
					else if (iType == 6 || iType == 7)
						fieldValue = String.valueOf(rs.getFloat(i + 1));
					else
						fieldValue = rs.getString(i + 1);
					if (fieldValue == null)
						fieldValue = "";
					else
						fieldValue = fieldValue.trim();
					row.put(colum[i], fieldValue);// .toLowerCase()
				}
			}
		} catch (Exception e) {
		}
		return alResult;
	}

	public String[] getArray(String sql) {
		Statement st = null;
		// String colum[] = new Arrays();
		try {

			getCurrentConnection(true);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			int count = rsmd.getColumnCount();
			String colum[] = new String[count];
			for (int i = 0; i < colum.length; i++)
				if (rsmd.getColumnName(i + 1) != null)
					colum[i] = rsmd.getColumnName(i + 1);
				else
					colum[i] = rsmd.getColumnLabel(i + 1);

			HashMap row = null;
			String fieldValue = null;
			rs.next();

			row = new HashMap();
			for (int j = 0; j < colum.length; j++) {
				int iType = rsmd.getColumnType(j + 1);
				if (iType == 2 || iType == 3) {
					if (rsmd.getScale(j + 1) == 0)
						fieldValue = String.valueOf(rs.getLong(j + 1));
					else
						fieldValue = rs.getString(j + 1);
				} else if (iType == 8)
					fieldValue = String.valueOf(rs.getDouble(j + 1));
				else if (iType == 6 || iType == 7)
					fieldValue = String.valueOf(rs.getFloat(j + 1));
				else
					fieldValue = rs.getString(j + 1);
				if (fieldValue == null)
					fieldValue = "";
				else
					fieldValue = fieldValue.trim();
				colum[j] = fieldValue;// .toLowerCase()
			}

			commitTransaction();
			return colum;
		} catch (Exception e) {
			rollbackTransaction();
		}
		return null;
	}



	public String getRecordJson(String sql) {
		Statement st = null;
		// String colum[] = new Arrays();
		try {

			getCurrentConnection(true);
			st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();

			int count = rsmd.getColumnCount();
			String colum[] = new String[count];
			for (int i = 0; i < colum.length; i++)
				if (rsmd.getColumnName(i + 1) != null)
					colum[i] = rsmd.getColumnName(i + 1);
				else
					colum[i] = rsmd.getColumnLabel(i + 1);

			HashMap row = null;
			String fieldValue = null;
			rs.next();

			row = new HashMap();
			for (int j = 0; j < colum.length; j++) {
				int iType = rsmd.getColumnType(j + 1);
				if (iType == 2 || iType == 3) {
					if (rsmd.getScale(j + 1) == 0)
						fieldValue = String.valueOf(rs.getLong(j + 1));
					else
						fieldValue = rs.getString(j + 1);
				} else if (iType == 8)
					fieldValue = String.valueOf(rs.getDouble(j + 1));
				else if (iType == 6 || iType == 7)
					fieldValue = String.valueOf(rs.getFloat(j + 1));
				else
					fieldValue = rs.getString(j + 1);
				if (fieldValue == null)
					fieldValue = "";
				else
					fieldValue = fieldValue.trim();
				//colum[j] = fieldValue;// .toLowerCase()
				row.put(colum[j], fieldValue);// .toLowerCase()
			}

			commitTransaction();
			return new JSONObject(row).toString();
		} catch (Exception e) {
			rollbackTransaction();
		}
		return null;
	}
	
	public static void main(String[] args) {

		DBConnection db = new DBConnection();
		// String a= db.getColumnValue("select * from si_dss_virtual_workspace",
		// 2);

		try {
			Connection c = db.getCurrentConnection();
			Statement st = c.createStatement();
			
			ResultSet rs = st.executeQuery("select count(*) from nmdss_dss_table");
			
			rs.next();
			System.out.println(rs.getString(1));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List l = db.executeQueryList("select * from nmdss_dss_table");
		
		int i = db.executeQuery("select count(*) from nmdss_dss_table");
		System.out.println(i);
 
	}
	
	
	
}
