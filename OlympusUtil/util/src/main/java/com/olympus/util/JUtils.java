package com.olympus.util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.json.simple.*;


public class JUtils {

/*********************************************************************************************************************************/	

	public JUtils() { } // TODO Auto-generated constructor stub

/*********************************************************************************************************************************/
	public static Connection getConnection(Properties connectionProps ) throws SQLException {
		Connection conn = null;
		
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
				String url = "jdbc:rs-u2://" + myHost + "/" + myAccount + ";" + "dbmstype=" + dbmsType; // generate URL
				conn = DriverManager.getConnection(url, userID, passWord);
				System.out.println("URL: " + url);
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
/*********************************************************************************************************************************/	

	
	public static ResultSet getResultSet(Connection conn, Statement stmt, String query) throws SQLException {

		stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		return (rs);
	}
/*********************************************************************************************************************************/	
	public static String view() {
		String id = "109";
		System.out.println("*** In method: view -- ID: " + id );
		return(id);
	}
/*********************************************************************************************************************************/	
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

	
/*********************************************************************************************************************************/	
	//method to print array
	public static void printStrArray(ArrayList<String> strArr) {

		
		for (String str : strArr) { // iterating ArrayList
			System.out.println("DATA:" + str + "---");
		}
		// System.out.println(names[index]);
	}
/*********************************************************************************************************************************/	
	public static NodeList getXMLNodeList(String fileName, String elemName) {
		ArrayList<String>  strData = new ArrayList<String>();
		NodeList nList = null;
		try {
		    File fXmlFile = new File(fileName);
		    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		    Document doc = dBuilder.parse(fXmlFile);
		    doc.getDocumentElement().normalize();
		    
		  //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		   nList = doc.getElementsByTagName(elemName);
		    //System.out.println("----------------------------");
		    
		} catch (Exception e) {
		    e.printStackTrace();
		    }
		
		return nList;
	}
/*********************************************************************************************************************************/	
	public static void displayXMLNodeList(NodeList nList) {
		String tagName = null;
		String appID = null;
		String eTag = null;
		String eTagValue = null;
		
		for (int temp = 0; temp < nList.getLength(); temp++) {
	        Node nNode = nList.item(temp);
	       System.out.println("\nCurrent Element : " + nNode.getNodeName());
	        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	        	Element eElement = (Element) nNode;
	        	NodeList nodes = eElement.getChildNodes();
	        	for (int j = 0; j < nodes.getLength(); j++) {
	        		Node n = nodes.item(j);
	        		if (n.getNodeType() == Node.ELEMENT_NODE) {
	        			Element eName = (Element) n;
	        			eTag = eName.getTagName();
	        			eTagValue = eName.getTextContent();
	        			//System.out.println("\nCurrent Element TagName : " + tagName + ":" + eName.getTagName() + ":" + eName.getTextContent());
	        			//System.out.println(eName.geName.getTagName()etTagName() + ":" + eName.getTextContent());
	        			System.out.println(eTag + ":" + eTagValue);
	        		}        		
	        	}	        	
	        	//appID = eElement.getElementsByTagName(tagName).item(0).getTextContent();
	        	//System.out.println("ID:" + appID + "---");
	        }
		}	
	}
/*********************************************************************************************************************************/	
	/*********************************************************************************************************************************/	
	/*********************************************************************************************************************************/	
		
	
	
}
