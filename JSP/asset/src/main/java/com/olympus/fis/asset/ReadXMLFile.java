package com.olympus.fis.asset;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

public class ReadXMLFile {
	

  public static void main(String argv[]) {
	  
	String appNum = null;
	String oStat = null;
	String category = null;
	String matGrp = null;
	String matModel = null;
	String outData = null;
		
    try {
    File fXmlFile = new File("d:\\Kettle\\XML\\bobj_report2.xml");
    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    Document doc = dBuilder.parse(fXmlFile);
    doc.getDocumentElement().normalize();

    System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
    NodeList nList = doc.getElementsByTagName("Bobj_r");
    System.out.println("----------------------------");

    for (int temp = 0; temp < nList.getLength(); temp++) {
        Node nNode = nList.item(temp);
        System.out.println("\nCurrent Element :" + nNode.getNodeName());
        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element eElement = (Element) nNode;
           
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
            
            appNum = eElement.getElementsByTagName("Customer_PO").item(0).getTextContent();
            oStat = eElement.getElementsByTagName("Overall_Status").item(0).getTextContent();
        	category = eElement.getElementsByTagName("Item_Category").item(0).getTextContent();
        	matGrp = eElement.getElementsByTagName("Material_Group").item(0).getTextContent();
        	matModel = eElement.getElementsByTagName("Material_Model_Number").item(0).getTextContent();
            
        	System.out.println("Customer_PO : " + appNum);
        	System.out.println("Overall_Status : " + oStat);
        	System.out.println("Item_Category : " + category);
        	System.out.println("Material_Group : " + matGrp);
        	System.out.println("Material_Model_Number : " + matModel);
        	
    
        }
    }
    } catch (Exception e) {
    e.printStackTrace();
    }
  }
}