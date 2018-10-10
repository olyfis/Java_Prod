package com.olympus.fis.asset;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;


/**
 * Root resource (exposed at "myresource" path)
 */

@Path("assetdata")
public class AssetDataShow {
	String appNum = null;
	String oStat = null;
	String category = null;
	String matGrp = null;
	String matModel = null;
	String outData = null;
	int count = 0;
	
	    /**
	     * Method handling HTTP GET requests. The returned object will be sent
	     * to the client as "text/plain" media type.
	     *
	     * @return String that will be returned as a text/plain response.
	     */
	
	    @GET
	    @Path("{id}")
	    @Produces(MediaType.TEXT_HTML)
	    public String getAssetData(@PathParam("id") String id) {
	        
	    	//String outData = "<h1>*** Asset data for App ID: " + id  +  " TBD</h1>";
	    	try {
	    	    File fXmlFile = new File("d:\\Kettle\\XML\\bobj_report3.xml");
	    	    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	    	    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	    	    Document doc = dBuilder.parse(fXmlFile);
	    	    doc.getDocumentElement().normalize();
	    	    //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
	    	    NodeList nList = doc.getElementsByTagName("Bobj_r");
	    	    //System.out.println("----------------------------");
	    	    outData = "<h3> ";
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
	    	            
	    	            /*
	    	            System.out.println("Customer_PO : " + eElement.getElementsByTagName("Customer_PO")
	    	                                 .item(0).getTextContent());
	    	            System.out.println("Overall_Status : " + eElement.getElementsByTagName("Overall_Status")
	    	            .item(0).getTextContent());
	    	            System.out.println("Item_Category : " + eElement.getElementsByTagName("Item_Category")
	    	            .item(0).getTextContent());
	    	            System.out.println("Material_Group : " + eElement.getElementsByTagName("Material_Group")
	    	            .item(0).getTextContent());
	    	            System.out.println("Material_Model_Number : " + eElement.getElementsByTagName("Material_Model_Number")
	    	            .item(0).getTextContent());
	    	            
	    	            */ 	
	    	        	if (appNum.equals(id)) {
	    	        		count++;
	    	        	   	System.out.println("ID:" + id + "---");
		    	        	System.out.println("Customer_PO:" + appNum  + "---");
		    	        	System.out.println("Overall_Status:" + oStat  + "---");
		    	        	System.out.println("Item_Category:" + category  + "---");
		    	        	System.out.println("Material_Group:" + matGrp  + "---");
		    	        	System.out.println("Material_Model_Number:" + matModel  + "---");
	    	        		outData += appNum + ":" + oStat + ":" + category + ":" + matGrp + ":" + matModel + "<BR>";
	    	           
	    	        	}
	    	        }
	    	    }
	    	    } catch (Exception e) {
	    	    e.printStackTrace();
	    	    }
	    	if (count == 0) {
	    		outData += "No Asset data found in BOBJ report.";
	    	}
	    	outData += "</h3> ";
	    	return outData;
	    }     
}
