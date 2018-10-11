package com.olympus.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCtools {


	static Statement stmt = null;
	static Connection con = null;
	static ResultSet res  = null;
	
//***************************************************************************************************************************************************/
	public JDBCtools() {
		// TODO Auto-generated constructor stub
	}
//***************************************************************************************************************************************************/
	public static Connection getConnection(Properties connectionProps  ) throws SQLException {
		Connection conn = null;
		
		String url  = null;
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

		try {	
			if (dbmsType.equals("UNIDATA")) {
				Class.forName(dbClass);
				//Class.forName("com.rs.u2.jdbc.UniJDBCDriver");
				url = "jdbc:rs-u2://" + myHost + "/" + myAccount + ";" + "dbmstype=" + dbmsType; // generate URL
				conn = DriverManager.getConnection(url, userID, passWord);
				System.out.println("UNIDATA -> URL: " + url);
			} else if (dbmsType.equals("SalesForce")) {
				Class.forName("cdata.jdbc.salesforce.SalesforceDriver");
				url = "jdbc:salesforce://olympus--fis.cs60.my.salesforce.com";
				System.out.println("SalesForce -> URL: " + url);
				conn = DriverManager.getConnection(url, userID, passWord);
				
			}
			if (conn == null) {
				System.out.println(" %%% Returned null connection");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	
	//***************************************************************************************************************************************************/
	//***************************************************************************************************************************************************/

	//***************************************************************************************************************************************************/

	//***************************************************************************************************************************************************/

	//***************************************************************************************************************************************************/

	//***************************************************************************************************************************************************/

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		String result = null;
		JDBCtools jdbc1 = new JDBCtools();
		FileInputStream fis;
		try {
			fis = new FileInputStream("C:\\Java_Dev\\InfoleaseRest\\infolease\\connection_sf.prop");
			Properties connectionProps = new Properties();
			connectionProps.load(fis);
			con = jdbc1.getConnection(connectionProps);
			if(con != null) {
				 System.out.println("Connected to the database");
			} else {
				System.out.println("**** NOT Connected to the database");
			}
		 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Properties connectionProps = new Properties();

		
		 
	}

}
