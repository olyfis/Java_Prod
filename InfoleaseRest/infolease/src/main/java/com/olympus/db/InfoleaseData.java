package com.olympus.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;
import org.json.simple.*;

public class InfoleaseData {

	static Statement stmt = null;

	// *************************************************************************************************************
	public static void viewTable(Connection conn, String query) throws SQLException {

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		int i = 1;
		while (rs.next()) {
			System.out.println("" + i + ":" + rs.getString("ID") + ":" + rs.getString("UATB_SC_BRANCH") + ":" + ":"
					+ rs.getString("UATB_SC_CUST_NAME") + ":" + rs.getString("INSURANCE_CODE")
					+ rs.getString("UATB_SC_ACTIV_DATE") + ":" + rs.getString("EQUIPMENT_COST") + ":"
					+ rs.getString("INSUR_EFFECTIVE_DATE") + ":" + rs.getString("INSUR_EXPIRE_DATE"));
		}
	}
//*************************************************************************************************************

	public static ResultSet getResultSet(Connection conn, String query) throws SQLException {

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return (rs);
	}

//*************************************************************************************************************
	public static String displayResults(ResultSet rs) throws SQLException {
		String result = null;
		JSONObject root = new JSONObject();

		int i = 1;
		System.out.println("Display results");
		root.put("recs", "recs");
		JSONArray jsonArr = new JSONArray();
		while (rs.next()) {
			JSONObject row = new JSONObject();
			row.put("ID", rs.getString("ID"));
			row.put("UATB_SC_BRANCH", rs.getString("UATB_SC_BRANCH"));
			row.put("UATB_SC_CUST_NAME", rs.getString("UATB_SC_CUST_NAME"));
			row.put("INSURANCE_CODE", rs.getString("INSURANCE_CODE"));
			row.put("UATB_SC_ACTIV_DATE", rs.getString("UATB_SC_ACTIV_DATE"));
			row.put("EQUIPMENT_COST", rs.getString("EQUIPMENT_COST"));
			row.put("INSUR_EFFECTIVE_DATE", rs.getString("INSUR_EFFECTIVE_DATE"));
			row.put("INSUR_EXPIRE_DATE", rs.getString("INSUR_EXPIRE_DATE"));
			jsonArr.add(row);

			System.out.println("" + i + ":" + rs.getString("ID") + ":" + rs.getString("UATB_SC_BRANCH") + ":" + ":"
					+ rs.getString("UATB_SC_CUST_NAME") + ":" + rs.getString("INSURANCE_CODE")
					+ rs.getString("UATB_SC_ACTIV_DATE") + ":" + rs.getString("EQUIPMENT_COST") + ":"
					+ rs.getString("INSUR_EFFECTIVE_DATE") + ":" + rs.getString("INSUR_EXPIRE_DATE"));

			// result += "" + rs.getString("ID") + ":" + rs.getString("UATB_SC_BRANCH");
		}

		root.put("jsonArr", jsonArr);
		// System.out.println(root.toJSONString());

		result = root.toJSONString();
		System.out.println(result);
		return result;
	}

//*************************************************************************************************************

	public static Connection getConnection() throws SQLException {
		Connection con = null;

		try {
			FileInputStream fis = new FileInputStream("C:\\Java_Dev\\InfoleaseRest\\infolease\\connection.prop");

			Properties connectionProps = new Properties();
			connectionProps.load(fis);

			String myHost = (String) connectionProps.get("myHost");
			String myAccount = (String) connectionProps.get("myAccount");
			String uid = (String) connectionProps.get("userID");
			String pw = (String) connectionProps.get("passWord");
			String dbClass = (String) connectionProps.get("dbClass");
			String dbmsType = (String) connectionProps.get("dbmsType");
			String passWord = pw.trim();
			String userID = uid.trim();

			System.out.println("dbmsType: " + dbmsType + "--");
			System.out.println("dbClass: " + dbClass + "--");
			System.out.println("myHost: " + myHost + "--");
			System.out.println("userID: " + userID + "--");
			System.out.println("passWord: " + passWord + "--");

			// Class.forName(dbClass);
			Class.forName("com.rs.u2.jdbc.UniJDBCDriver");

			String url = "jdbc:rs-u2://" + myHost + "/" + myAccount + ";" + "dbmstype=" + dbmsType; // generate URL
			con = DriverManager.getConnection(url, userID, passWord);

			System.out.println("URL: " + url);
			if (con == null) {
				System.out.println(" %%% Returned null connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
//*************************************************************************************************************
	/*
	 * public static void main(String[] argv) { Connection conn = null; ResultSet
	 * res = null;
	 * 
	 * String query =
	 * "SELECT DISTINCT  LS_MASTER.ID, UATB_SC_BRANCH, UATB_SC_CUST_NAME, INSURANCE_CODE, LS_MASTER.UATB_SC_ACTIV_DATE, EQUIPMENT_COST, INSUR_EFFECTIVE_DATE, INSUR_EXPIRE_DATE"
	 * +
	 * " FROM LS_MASTER_RPTS_NF LS_MASTER,  LS_NEW_FIELDS_NF LS_NEW_FIE,  LS_CADDR_NF LS_CADDR_N,  IT_POINTERS_NF IT_POINTER,  IT_INSURANCE_NF IT_INSURAN "
	 * +
	 * " WHERE LS_MASTER.ID = LS_NEW_FIE.ID AND LS_MASTER.ID = LS_CADDR_N.ID AND LS_MASTER.CUST_CREDIT_ACCT = IT_POINTER.ITPTR_CUSTOMER_KEY "
	 * +
	 * "AND IT_POINTER.ID = IT_INSURAN.ID AND ( UATB_SC_NUM_OF_ASSETS > 0 AND CONTRACT_STATUS NOT IN( '03', '04' ) AND UATB_SC_ACTIV_DATE IS NOT NULL) and UATB_SC_CUST_NAME = 'UPMC' "
	 * ; try { conn = getConnection(); if(conn != null) {
	 * System.out.println("Connected to the database"); //viewTable(conn, query);
	 * res = getResultSet(conn, query); displayResults(res); } else {
	 * System.out.println("**** NOT Connected to the database"); } }
	 * catch(SQLException se){ se.printStackTrace(); } finally { try { if (stmt !=
	 * null) { stmt.close(); } if (conn != null) { conn.close(); }
	 * 
	 * } catch(SQLException se){ se.printStackTrace(); } //end finally try } } //
	 * end main
	 * 
	 */
} // end class
