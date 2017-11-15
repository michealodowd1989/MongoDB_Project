package com.mongo.micheal.mongo_app;


import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import org.bson.Document;

public class App extends JFrame
	{
		final String COLLECTION_NAME = "properties", DB_NAME = "rentAccom";
		JComboBox houseTypesList, matchingHouseList;
		JButton viewMapPropertyBtn,AddPropertyBtn,viewPropertyBtn,updatePropertyBtn,deletePropertyBtn;
		JTextField propertyFounWithMapdIdTextField, propertyFoundIdTextField, addressTownTextField,addressCountyTextField,propertyRevField,propertyIdTextField, addressPostCodeTextField, noRoomTextField, weeklyRentTextField, addressLine1TextField,addressLine2TextField,addressCountryTextField;
		JLabel searchWithMapRentAccomlabel,propertyFoundIdLabel, WelcomeToRentAccomlabel,houseTypeLabel,noRoomsLabel,weeklyRent,addressLabel,addressLine1Label, addressLine2Label;
		JLabel propertyFoundWithMapIdLabel,searchRentAccomlabel,addressCountyLabel,addressCountryLabel, addressTownLabel,addressPostCodeLabel,propertyIdLabel,displayLabel;
		Container cpane;
		String[] listOfHouseType = {"Bungalow", "Cottage", "Two Story", "Farmhouse", "Aparment","Detached","semi- Detached"};
		int houseIdCount;
		Property propertyFound = null;
		MongoClient mongo;
		MongoDatabase database;	
		MongoCollection collection;
		DB db = mongo.getDB(DB_NAME);

	public App(){
		
		
		setTitle("Air bnb");
		setSize(800,800);
		setLocation(0,0);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		myMenu();  	
		
		cpane = getContentPane();
		cpane.setLayout(null);
		WelcomeToRentAccomlabel = new JLabel("Welcome To Rent Accom ");
		WelcomeToRentAccomlabel.setBounds(150,80,300,50);
		cpane.add(WelcomeToRentAccomlabel);
		
		///------CREATE----///
		
		houseTypeLabel = new JLabel("House Type:");
		houseTypeLabel.setBounds(100,160,150,20);
		cpane.add(houseTypeLabel);	
		houseTypeLabel.setVisible(false);
		
		
	    houseTypesList = new JComboBox<String>(listOfHouseType);
		houseTypesList.setSelectedIndex(0);
		houseTypesList.setBounds(200,162,100,20);
		cpane.add(houseTypesList);
		houseTypesList.setVisible(false);
		
		noRoomsLabel = new JLabel("No. Rooms:");
		noRoomsLabel.setBounds(100,200,150,20);
		cpane.add(noRoomsLabel);
		noRoomsLabel.setVisible(false);
		
		noRoomTextField = new JTextField(10);
		noRoomTextField.setBounds(200,200,100,20);
		cpane.add(noRoomTextField);
		noRoomTextField.setVisible(false);
			
		weeklyRent = new JLabel("Weekly Rent:");
		weeklyRent.setBounds(100,230,150,20);
		cpane.add(weeklyRent);
		weeklyRent.setVisible(false);
		
		weeklyRentTextField = new JTextField(10);
		weeklyRentTextField.setBounds(200,230,100,20);
		cpane.add(weeklyRentTextField);
		weeklyRentTextField.setVisible(false);
		
		addressLabel = new JLabel("Address");
		addressLabel.setBounds(180,280,150,20);
		cpane.add(addressLabel);
		addressLabel.setVisible(false);
		
		addressLine1Label = new JLabel("Address Line 1:");
		addressLine1Label.setBounds(100,310,150,20);
		cpane.add(addressLine1Label);
		addressLine1Label.setVisible(false);
		
		addressLine1TextField = new JTextField(10);
		addressLine1TextField.setBounds(200,310,100,20);
		cpane.add(addressLine1TextField);
		addressLine1TextField.setVisible(false);
		
		addressLine2Label = new JLabel("Address Line 2:");
		addressLine2Label.setBounds(100,340,150,20);
		cpane.add(addressLine2Label);
		addressLine2Label.setVisible(false);
		
		addressLine2TextField = new JTextField(10);
		addressLine2TextField.setBounds(200,340,100,20);
		cpane.add(addressLine2TextField);
		addressLine2TextField.setVisible(false);
		
		addressTownLabel = new JLabel("Town:");
		addressTownLabel.setBounds(100,370,150,20);
		cpane.add(addressTownLabel);
		addressTownLabel.setVisible(false);
		
		addressTownTextField = new JTextField(10);
		addressTownTextField.setBounds(200,370,100,20);
		cpane.add(addressTownTextField);
		addressTownTextField.setVisible(false);
		
		addressCountyLabel = new JLabel("County:");
		addressCountyLabel.setBounds(100,400,150,20);
		cpane.add(addressCountyLabel);
		addressCountyLabel.setVisible(false);
		
		addressCountyTextField = new JTextField(10);
		addressCountyTextField.setBounds(200,400,100,20);
		cpane.add(addressCountyTextField);
		addressCountyTextField.setVisible(false);
		
		addressCountryLabel = new JLabel("Country:");
		addressCountryLabel.setBounds(100,430,150,20);
		cpane.add(addressCountryLabel);
		addressCountryLabel.setVisible(false);
		
		addressCountryTextField = new JTextField(10);
		addressCountryTextField.setBounds(200,430,100,20);
		cpane.add(addressCountryTextField);
		addressCountryTextField.setVisible(false);
		
		addressPostCodeLabel = new JLabel("Post Code:");
		addressPostCodeLabel.setBounds(100,460,150,20);
		cpane.add(addressPostCodeLabel);
		addressPostCodeLabel.setVisible(false);
		
		addressPostCodeTextField = new JTextField(10);
		addressPostCodeTextField.setBounds(200,460,100,20);
		cpane.add(addressPostCodeTextField);
		addressPostCodeTextField.setVisible(false);
		
		AddPropertyBtn = new JButton("Submit");
		AddPropertyBtn.setBounds(150,500,100,20);
		AddPropertyBtn.addActionListener(new SubmitBtnListener());
		cpane.add(AddPropertyBtn);
		AddPropertyBtn.setVisible(false);
		
		
		///------View----///
		
		searchRentAccomlabel = new JLabel("Search For A Property: ");
		searchRentAccomlabel.setBounds(450,80,300,50);
		cpane.add(searchRentAccomlabel);
		searchRentAccomlabel.setVisible(false);
		
		propertyFoundIdLabel = new JLabel("Property Id:");
		propertyFoundIdLabel.setBounds(400,160,150,20);
		cpane.add(propertyFoundIdLabel);	
		propertyFoundIdLabel.setVisible(false);
		
		propertyFoundIdTextField = new JTextField(10);
		propertyFoundIdTextField.setBounds(500,162,100,20);
		cpane.add(propertyFoundIdTextField);
		propertyFoundIdTextField.setVisible(false);
		
		
		propertyIdLabel = new JLabel("Property Id:");
		propertyIdLabel.setBounds(100,160,150,20);
		cpane.add(propertyIdLabel);	
		propertyIdLabel.setVisible(false);
		
		propertyIdTextField = new JTextField(10);
		propertyIdTextField.setBounds(100,162,100,20);
		cpane.add(propertyIdTextField);
		propertyIdTextField.setVisible(false);
	
		
		viewPropertyBtn = new JButton("Search");
		viewPropertyBtn.setBounds(450,220,100,20);
		viewPropertyBtn.addActionListener(new SearchBtnListener());
		cpane.add(viewPropertyBtn);
		viewPropertyBtn.setVisible(false);
		
		
		
		///-------update-----///
			
		updatePropertyBtn = new JButton("Update");
		updatePropertyBtn.setBounds(150,500,100,20);
		updatePropertyBtn.addActionListener(new UpdateBtnListener() );
		cpane.add(updatePropertyBtn);
		updatePropertyBtn.setVisible(false);
		
		
		///-------delete-----///
			
		deletePropertyBtn = new JButton("Delete");
		deletePropertyBtn.setBounds(150,240,100,20);
		deletePropertyBtn.addActionListener(new DeleteBtnListener());
		cpane.add(deletePropertyBtn);
		deletePropertyBtn.setVisible(false);
	
		///--Database---///
		
		mongoOperation(DB_NAME, COLLECTION_NAME);
		
		//counts how many properties in the collection and add 1 on to it for the next properies ID
		houseIdCount = (int) collection.count() + 1;
  	  	
		
		
		////------DISPLAY-------////
  	  	  	
  	  	  	
  	    displayLabel = new JLabel("Display");
  	    displayLabel.setBounds(100,500,200,200);
		
		cpane.add(displayLabel);
		displayLabel.setVisible(false);
		
		
	////------MAP-REDUCE-------////
		
		
		searchWithMapRentAccomlabel = new JLabel("Search Property Using Map-Reduce: ");
		searchWithMapRentAccomlabel.setBounds(400,80,300,50);
		cpane.add(searchWithMapRentAccomlabel);
		searchWithMapRentAccomlabel.setVisible(false);
		
		propertyFoundWithMapIdLabel = new JLabel("Property Location:");
		propertyFoundWithMapIdLabel.setBounds(400,160,150,20);
		cpane.add(propertyFoundWithMapIdLabel);	
		propertyFoundWithMapIdLabel.setVisible(false);
		
		propertyFounWithMapdIdTextField = new JTextField(10);
		propertyFounWithMapdIdTextField.setBounds(520,162,100,20);
		cpane.add(propertyFounWithMapdIdTextField);
		propertyFounWithMapdIdTextField.setVisible(false);
		
		viewMapPropertyBtn = new JButton("Search");
		viewMapPropertyBtn.setBounds(450,220,100,20);
		viewMapPropertyBtn.addActionListener(new SearchMapBtnListener());
		cpane.add(viewMapPropertyBtn);
		viewMapPropertyBtn.setVisible(false);
		 	
  	  	
	}
	
	public void mongoOperation(String dbName, String collectionName){
		
		mongo = new MongoClient("localhost", 27017);
		database = mongo.getDatabase(dbName);
		collection = database.getCollection(collectionName);
	
	}
		
	
	public void myMenu()
	{	
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("Options");
		menuBar.add(menu);
		
		JMenuItem add = new JMenuItem("Add new Accomadation");	
		add.addActionListener(new addAccomListener());
		menu.add(add);
		
		JMenuItem viewAccom = new JMenuItem("View / Update Accomadation");	
		viewAccom.addActionListener(new viewAccomListener());
		menu.add(viewAccom);

		JMenuItem delete = new JMenuItem("Delete Accomadation");
		delete.addActionListener(new deleteAccomListener());
		menu.add(delete);
		

		JMenuItem mapReduce = new JMenuItem("Map Reduce");
		mapReduce.addActionListener(new mapReduceListener());
		menu.add(mapReduce);

		
		JMenuItem logout = new JMenuItem("Logout");	
		logout.addActionListener(new logoutListener());
		menu.add(logout);
	}
	
	public void isVisable(boolean boo){
		JTextField[] textfields = {addressCountyTextField,addressTownTextField,addressPostCodeTextField, noRoomTextField, weeklyRentTextField, addressLine1TextField,addressLine2TextField,addressTownTextField,addressCountryTextField};
		JLabel[] lables = {houseTypeLabel,noRoomsLabel,weeklyRent,addressLabel,addressLine1Label, addressLine2Label , addressCountyLabel,addressCountryLabel, addressTownLabel,addressPostCodeLabel};
		
		for(JLabel lable: lables){
			lable.setVisible(boo);
		}
		for(JTextField textfield: textfields){
			textfield.setVisible(boo);
		}
	}
	
	public void clearTextBoxs(){
		JTextField[] textfields = {propertyFoundIdTextField, addressCountyTextField,addressTownTextField,addressPostCodeTextField, noRoomTextField, weeklyRentTextField, addressLine1TextField,addressLine2TextField,addressTownTextField,addressCountryTextField};
	
		for(JTextField textfield: textfields){
			textfield.setText("");;
		}
	}
	
	private class SearchMapBtnListener implements ActionListener	{
			 public void actionPerformed(ActionEvent e){ 
					 isVisable(false);    
					 DBCollection properties = db.getCollection(COLLECTION_NAME);		 
					 String userInput = propertyFounWithMapdIdTextField.getText();		 
					 String userInputWrapped = "'" + userInput + "'";		 
					 String map = "function() { "+ 
				             "var houseCategory; " +  
				             "if ( this.county == "+userInputWrapped +  ") "+  
				             "emit(houseCategory, {houseId: this.houseId});}";
				   
				   String reduce = "function( key, values) { " +
				                            "var num = 0; " +
				                            "values.forEach(function(doc) { " +
				                            "num += 1; " + " }); " +
				                            "return {properties: num};}";
				   
				   MapReduceCommand cmd = new MapReduceCommand(properties, map, reduce, null, MapReduceCommand.OutputType.INLINE, null);
				   MapReduceOutput out = properties.mapReduce(cmd);	   
				   String houses = "";
				   for (DBObject o : out.results()) {	   
					   houses += o.get("value");
				   }
				   
				   WelcomeToRentAccomlabel.setText("Result: " + houses);;
				   WelcomeToRentAccomlabel.setBounds(400,240,300,50);
					 		 
			 }
	}

			
	private class SubmitBtnListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){ 
			 	  	 
			 	 //creating my objects and assigning values from user input
			 	 
				 Address address = new Address(addressLine1TextField.getText(),addressLine2TextField.getText(),addressTownTextField.getText(),addressCountyTextField.getText(),addressCountryTextField.getText(),addressPostCodeTextField.getText());
				 int noRooms = Integer.parseInt(noRoomTextField.getText());
				 double rent = Double.parseDouble(weeklyRentTextField.getText());
				 Property property = new Property(houseIdCount,houseTypesList.getSelectedItem().toString(),noRooms,rent,address);
			 	 
				 //creating document to be put into the collection
				 
				 Document doc = new Document();	
				 doc.append("houseId", property.getId());
				 doc.append("houseType", property.getHouseType());
				 doc.append("noRooms", property.getNumRoom());
				 doc.append("weeklyRent", property.getWeeklyRent());
				 doc.append("addressLine1", property.getAddress().getLine1());
				 doc.append("addressLine2", property.getAddress().getLine2());
				 doc.append("town", property.getAddress().getTown());
				 doc.append("county", property.getAddress().getCounty());
				 doc.append("country", property.getAddress().getCountry());
				 doc.append("postcode", property.getAddress().getPostcode());	
				 
				 //inserting document into collection
				 collection.insertOne(doc);
				 displayLabel.setText("Property Added Successfully");
				 displayLabel.setVisible(true);
				 
				 houseIdCount++;
				 clearTextBoxs();
		 }
	}
	
	public Property convertJsonToProperty(BasicDBObject object){
		
			int houseId = Integer.parseInt(object.get("houseId").toString());
			String houseType = object.get("houseType").toString();
			int noRooms = Integer.parseInt(object.get("noRooms").toString());
			double weeklyRent = Double.parseDouble(object.get("weeklyRent").toString());
			String addressLine1 = object.get("addressLine1").toString();
			String addressLine2 = object.get("addressLine2").toString();
			String town = object.get("town").toString();
			String county = object.get("county").toString();
			String country = object.get("country").toString();
			String postcode = object.get("postcode").toString();	
			Address address = new Address(addressLine1, addressLine2, town, county, country, postcode);
			Property property = new Property(houseId, houseType, noRooms, weeklyRent, address);
		
			return property;
	}

	private class SearchBtnListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){		 			 
		
			 DBCollection collection = db.getCollection(COLLECTION_NAME);
			 int searchedPropertyId = Integer.parseInt( propertyFoundIdTextField.getText());
			 boolean found = false; 
			 BasicDBObject query = new BasicDBObject();
			 query.put("houseId", searchedPropertyId);
			 
			 // FindIterable cursorDoc = collection.find(query);
			 DBCursor cursorDoc = collection.find(query);
			 
			 while(cursorDoc.hasNext() && !found) {
			     BasicDBObject property = (BasicDBObject) cursorDoc.next(); 
			     int houseId = Integer.parseInt(property.get("houseId").toString()); 
				     if(houseId == searchedPropertyId){
				    	 found = true;
				    	 propertyFound = convertJsonToProperty(property);
				    	 propertyFound.getId();
				     }
			   }

			 if(found){
				 
				 searchRentAccomlabel.setVisible(true);
				 WelcomeToRentAccomlabel.setText("Property Found");
				 WelcomeToRentAccomlabel.setBounds(100,40,150,20);		
				 propertyIdLabel.setBounds(100,100,100,20);
				 propertyIdTextField.setBounds(200,100,100,20);				 
				 propertyIdLabel.setVisible(true);	      
			     propertyIdTextField.setText(propertyFound.getId()+"");
			     propertyIdTextField.setVisible(true);			     
			     houseTypesList.setSelectedItem(propertyFound.getHouseType());								 
				 addressCountyTextField.setText(propertyFound.getAddress().getCounty());
				 addressTownTextField.setText(propertyFound.getAddress().getTown());
				 addressPostCodeTextField.setText(propertyFound.getAddress().getPostcode());
				 noRoomTextField.setText(propertyFound.getNumRoom()+"");
				 weeklyRentTextField.setText(propertyFound.getWeeklyRent()+"");
				 addressLine1TextField.setText(propertyFound.getAddress().getLine1());
				 addressLine2TextField.setText(propertyFound.getAddress().getLine2());
				 addressTownTextField.setText(propertyFound.getAddress().getTown());
				 addressCountryTextField.setText(propertyFound.getAddress().getCountry());
				 updatePropertyBtn.setVisible(true);
			 
			 }
			 else{
				 WelcomeToRentAccomlabel.setText("property NOT Found");
				 clearTextBoxs();
			 }			 
		 }
	}
	private class UpdateBtnListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){
	
			 DBCollection collection = db.getCollection(COLLECTION_NAME);	 
			 int searchedPropertyId = Integer.parseInt( propertyFoundIdTextField.getText());
			 int noRooms = Integer.parseInt(noRoomTextField.getText());
			 double rent = Double.parseDouble(weeklyRentTextField.getText());
			 int id = Integer.parseInt( propertyIdTextField.getText());	
			 
			 BasicDBObject doc = new BasicDBObject();	
			 doc.append("houseId", id);
			 doc.append("houseType", houseTypesList.getSelectedItem().toString());
			 doc.append("noRooms", noRooms);
			 doc.append("weeklyRent", rent);
			 doc.append("addressLine1", addressLine1TextField.getText());
			 doc.append("addressLine2", addressLine2TextField.getText());
			 doc.append("town", addressTownTextField.getText());
			 doc.append("county",addressCountyTextField.getText());
			 doc.append("country",addressCountryTextField.getText());
			 doc.append("postcode", addressPostCodeTextField.getText());			  
			
			 BasicDBObject query = new BasicDBObject();		 	 
			 query.put("houseId", searchedPropertyId);			  
			
			 collection.update(query, doc);	 
			 clearTextBoxs();
			 displayLabel.setVisible(true);
			 displayLabel.setText("Property Updated Successfully");
	
		 }
	}
	
	private class DeleteBtnListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){	
			 
			 DBCollection collection = db.getCollection(COLLECTION_NAME);	 
			 int enteredHouseId = Integer.parseInt(propertyIdTextField.getText());    
			 BasicDBObject document = new BasicDBObject();
			 document.put("houseId", enteredHouseId);
			 
			 //removes document with matching houseId
			 collection.remove(document);
			 displayLabel.setVisible(true);
			 displayLabel.setBounds(110,290,200,20);
			 displayLabel.setText("Property Deleted Successfully");
			
		 }
	}
	
	private class mapReduceListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){
			 WelcomeToRentAccomlabel.setText("");
			 searchWithMapRentAccomlabel.setVisible(true);
				propertyFoundWithMapIdLabel.setVisible(true);
				propertyFounWithMapdIdTextField.setVisible(true);
				viewMapPropertyBtn.setVisible(true);
					 
		 		}
		 }

	
	
	private class addAccomListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){
			    clearTextBoxs();
				WelcomeToRentAccomlabel.setText("Add New Accomadation");
			    isVisable(true);
				houseTypesList.setVisible(true);
				propertyIdLabel.setVisible(false);	
				propertyIdTextField.setVisible(false);	
				searchRentAccomlabel.setVisible(false);
				AddPropertyBtn.setVisible(true);
				viewPropertyBtn.setVisible(false);
				updatePropertyBtn.setVisible(false);
				WelcomeToRentAccomlabel.setBounds(150,80,300,50);
				deletePropertyBtn.setVisible(false);	
				displayLabel.setVisible(false);		
				propertyFoundIdLabel.setVisible(false);
				propertyFoundIdTextField.setVisible(false);
				searchWithMapRentAccomlabel.setVisible(false);
				propertyFoundWithMapIdLabel.setVisible(false);
				propertyFounWithMapdIdTextField.setVisible(false);
				viewMapPropertyBtn.setVisible(false);
		 }
	}

	private class viewAccomListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){
			    clearTextBoxs();
			    propertyIdTextField.disable();
			 	isVisable(true);
			 	houseTypesList.setVisible(true);
			 	propertyFoundIdLabel.setVisible(true);
			 	searchRentAccomlabel.setVisible(true);
			    propertyFoundIdTextField.setVisible(true);	 	
				AddPropertyBtn.setVisible(false);
				WelcomeToRentAccomlabel.setText("View Property");		
				propertyIdLabel.setVisible(false);	
				propertyIdTextField.setVisible(false);	
				viewPropertyBtn.setVisible(true);
				updatePropertyBtn.setVisible(false);
				displayLabel.setVisible(false);
				//moving back into position of update was selected before view
				propertyIdLabel.setBounds(100,160,150,20);	
				propertyIdTextField.setBounds(200,162,100,20);		
				WelcomeToRentAccomlabel.setBounds(150,80,300,50);
				deletePropertyBtn.setVisible(false);	
				
				searchWithMapRentAccomlabel.setVisible(false);
				propertyFoundWithMapIdLabel.setVisible(false);
				propertyFounWithMapdIdTextField.setVisible(false);
				viewMapPropertyBtn.setVisible(false);
		 }
	}

	private class deleteAccomListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e){
			    clearTextBoxs();
			 	propertyIdTextField.enable();
			 	isVisable(false);	
				WelcomeToRentAccomlabel.setText("Delete Property");
				AddPropertyBtn.setVisible(false);
				houseTypesList.setVisible(false);
				WelcomeToRentAccomlabel.setText("Enter Property's Id");		
				propertyIdLabel.setVisible(true);	
				propertyIdTextField.setVisible(true);	
				viewPropertyBtn.setVisible(false);
				updatePropertyBtn.setVisible(false);
				//moving back into position of update was selected before view
				propertyIdLabel.setBounds(100,160,150,20);	
				propertyIdTextField.setBounds(200,162,100,20);	
				WelcomeToRentAccomlabel.setBounds(150,80,300,50);
				deletePropertyBtn.setVisible(true);
				displayLabel.setVisible(false);
				searchRentAccomlabel.setVisible(false);
				propertyFoundIdTextField.setVisible(false);
				propertyFoundIdLabel.setVisible(false);
				propertyFoundIdTextField.setVisible(false);
				searchWithMapRentAccomlabel.setVisible(false);
				propertyFoundWithMapIdLabel.setVisible(false);
				propertyFounWithMapdIdTextField.setVisible(false);
				viewMapPropertyBtn.setVisible(false);
			 
		 }
	}
	
	private class logoutListener implements ActionListener	{
		 public void actionPerformed(ActionEvent e)
	{
		JOptionPane.showMessageDialog(null,"Thank you for using airbnb " +  "\nLogging out...");
		System.exit(0);
		
		 }
	}
	
    public static void main(String args[])
	{
    	App myGui = new App();
    	myGui.setVisible(true);		
	}
}
