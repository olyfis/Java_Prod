package com.olympus.fis;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class ReadXMLFile {
	
	public static ArrayList<String> getXMLdata(String appID) {
		
		String appNum = null;
		String oStat = null;
		String category = null;
		String matGrp = null;
		String matModel = null;
		String outData = null;
		String outDataLine = null;
		int count = 0;
		ArrayList<String> lines = new ArrayList<String>();
		ArrayList<String> xData = new ArrayList<String>();
		
		 try {
			    File fXmlFile = new File("d:\\Kettle\\XML\\bobj_report3.xml");
			    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			    Document doc = dBuilder.parse(fXmlFile);
			    doc.getDocumentElement().normalize();

			    //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			    NodeList nList = doc.getElementsByTagName("Bobj_r");
			    //System.out.println("----------------------------");

			    for (int temp = 0; temp < nList.getLength(); temp++) {
			        Node nNode = nList.item(temp);
			        //System.out.println("\nCurrent Element :" + nNode.getNodeName());
			        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			            Element eElement = (Element) nNode;
			        	appNum = eElement.getElementsByTagName("Customer_PO").item(0).getTextContent();
	    	            oStat = eElement.getElementsByTagName("Overall_Status").item(0).getTextContent();
	    	            if ('C' == oStat.charAt(0) ) {
	    	            	oStat = "Shipped";
	    	            } else if ('A' == oStat.charAt(0) ) {
	    	            	oStat = "On-Order";
	    	            }
	    	        	category = eElement.getElementsByTagName("Item_Category").item(0).getTextContent();
	    	        	matGrp = eElement.getElementsByTagName("Material_Group").item(0).getTextContent();
	    	        	matModel = eElement.getElementsByTagName("Material_Model_Number").item(0).getTextContent();
	    	            
	    	        	if (appNum.equals(appID)) {
	    	        		count++;
	    	        		/*
	    	        	   	System.out.println("ID:" + appID + "---");
		    	        	System.out.println("Customer_PO:" + appNum  + "---");
		    	        	System.out.println("Overall_Status:" + oStat  + "---");
		    	        	System.out.println("Item_Category:" + category  + "---");
		    	        	System.out.println("Material_Group:" + matGrp  + "---");
		    	        	System.out.println("Material_Model_Number:" + matModel  + "---");
		    	        	*/
	    	        		outDataLine =  appNum + ":" + oStat + ":" + category + ":" + matGrp + ":" + matModel;
	    	        		outData += appNum + ":" + oStat + ":" + category + ":" + matGrp + ":" + matModel + "<BR>";
	    	        		lines.add(outDataLine);
	    	           
	    	        	}
			        }
			    }
			    } catch (Exception e) {
			    e.printStackTrace();
			    }

		return lines;
	}
	
	//method to print array
	public static void printName(ArrayList<String> line_arr){

		//iterating ArrayList
	     for(String str:line_arr)  
	        System.out.println("DATA:" + str + "---");  
	       
	          //System.out.println(names[index]);
	}
	
	
	public static void main(String argv[]) {
		
		
		ArrayList<String> xData = getXMLdata("101-0017172-001");	
		printName(xData);
			//System.out.println("DATA: " + xData);
	}
	
	
}
