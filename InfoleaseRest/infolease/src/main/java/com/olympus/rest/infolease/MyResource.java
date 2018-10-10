package com.olympus.rest.infolease;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.olympus.db.InfoleaseData;
import com.olympus.rest.*;
import java.util.*;
/**
 * Root resource (exposed at "myresource" path)
 */
@Path("myresource")
public class MyResource {
	//MessageService messageService = new MessageService();
	static Statement stmt = null;   
	static Connection conn  = null;
    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
	
	/*
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //public String getInfoleaseData() {
    public String getIt() {	
    	String result = null;
    	ResultSet res = null;
    	String query = "SELECT DISTINCT  LS_MASTER.ID, UATB_SC_BRANCH, UATB_SC_CUST_NAME, INSURANCE_CODE, LS_MASTER.UATB_SC_ACTIV_DATE, EQUIPMENT_COST, INSUR_EFFECTIVE_DATE, INSUR_EXPIRE_DATE" +
       		  " FROM LS_MASTER_RPTS_NF LS_MASTER,  LS_NEW_FIELDS_NF LS_NEW_FIE,  LS_CADDR_NF LS_CADDR_N,  IT_POINTERS_NF IT_POINTER,  IT_INSURANCE_NF IT_INSURAN " 
       		  + " WHERE LS_MASTER.ID = LS_NEW_FIE.ID AND LS_MASTER.ID = LS_CADDR_N.ID AND LS_MASTER.CUST_CREDIT_ACCT = IT_POINTER.ITPTR_CUSTOMER_KEY "
       		  + "AND IT_POINTER.ID = IT_INSURAN.ID AND ( UATB_SC_NUM_OF_ASSETS > 0 AND CONTRACT_STATUS NOT IN( '03', '04' ) AND UATB_SC_ACTIV_DATE IS NOT NULL) and UATB_SC_CUST_NAME = 'UPMC' "; 
	
    	try {
    		System.out.println("** Get Connection");
			conn = InfoleaseData.getConnection();
			if(conn != null) {
				 System.out.println("Connected to the database");
				//viewTable(conn, query);
				res = InfoleaseData.getResultSet(conn, query);
				result = InfoleaseData.displayResults(res);	 
			} else {
				System.out.println("**** NOT Connected to the database");
			}
	    } catch(SQLException se){
	         se.printStackTrace();
	    } finally {
	    	try {
	    		if (stmt != null) { stmt.close(); }
		        if (conn != null) { conn.close(); }
		         
		      } catch(SQLException se){
		         se.printStackTrace();
		      } //end finally try
	    }
    	
    	
    	
        return result;
    }
    
}
