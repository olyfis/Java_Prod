package com.olympus.util;

import java.io.FileInputStream;
import java.util.Properties;

import org.w3c.dom.NodeList;

import java.sql.*;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	static Statement stmt = null;
	static Connection con = null;
	static ResultSet res  = null;
	static NodeList  node  = null;
	static 	String query = "SELECT DISTINCT  LS_MASTER.ID, UATB_SC_BRANCH, UATB_SC_CUST_NAME, INSURANCE_CODE, LS_MASTER.UATB_SC_ACTIV_DATE, EQUIPMENT_COST, INSUR_EFFECTIVE_DATE, INSUR_EXPIRE_DATE" +
     		  " FROM LS_MASTER_RPTS_NF LS_MASTER,  LS_NEW_FIELDS_NF LS_NEW_FIE,  LS_CADDR_NF LS_CADDR_N,  IT_POINTERS_NF IT_POINTER,  IT_INSURANCE_NF IT_INSURAN " 
     		  + " WHERE LS_MASTER.ID = LS_NEW_FIE.ID AND LS_MASTER.ID = LS_CADDR_N.ID AND LS_MASTER.CUST_CREDIT_ACCT = IT_POINTER.ITPTR_CUSTOMER_KEY "
     		  + "AND IT_POINTER.ID = IT_INSURAN.ID AND ( UATB_SC_NUM_OF_ASSETS > 0 AND CONTRACT_STATUS NOT IN( '03', '04' ) AND UATB_SC_ACTIV_DATE IS NOT NULL) and UATB_SC_CUST_NAME = 'UPMC' "; 
	
	
	public static void main(String[] args) throws Exception { // Auto-generated method stub
		
		String result = null;
		JUtils jutil = new JUtils();
		FileInputStream fis = new FileInputStream("C:\\Java_Dev\\InfoleaseRest\\infolease\\connection.prop");
		Properties connectionProps = new Properties();

		connectionProps.load(fis);
		System.out.println("Test");
		String id1 = jutil.view();
		System.out.println("returned ID: " + id1);

		try {
			con = jutil.getConnection(connectionProps);
			if(con != null) {
				 System.out.println("Connected to the database");
				 //res = jutil.getResultSet(con, stmt, query);
				// result = jutil.displayResults(res);	
			
				 node = JUtils.getXMLNodeList("D:\\pentaho\\Kettle\\Dashboard\\XML\\bobj_report3.xml", "Bobj_r");
				 JUtils.displayXMLNodeList(node);
			} else {
				System.out.println("**** NOT Connected to the database");
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		}

	}
}
