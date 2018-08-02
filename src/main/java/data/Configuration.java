package data;

import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Configuration{
	
	public String user = "";
	public String password = "";
	public String apiPoint = "";
	public String apiVersion = "";
	public boolean authenticate;
	
	private Collection collection;
	public ArrayList<Collection> collections = new ArrayList<Collection>(); 
		
	public void loadSettings(String source){
		try{
			ClassLoader classLoader = getClass().getClassLoader();
			File config = new File(classLoader.getResource(source).getFile());
			
			DocumentBuilderFactory docBuildFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuildFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(config);

			doc.getDocumentElement().normalize();

			//load discovery settings
			NodeList elementList = doc.getElementsByTagName("discovery");
			for (int temp = 0; temp < elementList.getLength(); temp++) {

				Node nNode = elementList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;					
					user = eElement.getElementsByTagName("user").item(0).getTextContent();
					password = eElement.getElementsByTagName("password").item(0).getTextContent();
					apiPoint = eElement.getElementsByTagName("apiPoint").item(0).getTextContent();
					apiVersion = eElement.getElementsByTagName("apiVersion").item(0).getTextContent();	
					authenticate = Boolean.parseBoolean(eElement.getElementsByTagName("authenticate").item(0).getTextContent());
				}
			}
			
			//load collection settings
			collections.clear();
			elementList = doc.getElementsByTagName("collections");
			
			for (int temp = 0; temp < elementList.getLength(); temp++) {
			
				NodeList childNodes=elementList.item(temp).getChildNodes();

				for (int i = 0; i < childNodes.getLength(); i++){
					
					Node nNode = childNodes.item(i);
					
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	
						Element eElement = (Element) nNode;		
						
						collection=new Collection();
						collection.id = eElement.getElementsByTagName("collectionId").item(0).getTextContent();
						collection.name = eElement.getElementsByTagName("name").item(0).getTextContent();
						collection.environmentId = eElement.getElementsByTagName("environmentId").item(0).getTextContent();
						collection.configurationId =  eElement.getElementsByTagName("configurationId").item(0).getTextContent();
						
						collections.add(collection);
					}
				}
				
			}		
			
		}catch(Exception ex){
			ex.printStackTrace();
		}	
	}
	
	public Configuration(){
		loadSettings("configuration.xml");
	}
	
}