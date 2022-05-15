package com;
import java.sql.*;

	
	

	public class User {

		public Connection connect()
		{
		 Connection con = null;

		 try
		 {
			 Class.forName("com.mysql.jdbc.Driver");
			 con= DriverManager.getConnection("jdbc:mysql://localhost:3306/db01", 
					 "root", "199979");
			 
			 
		 //For testing
		 System.out.print("Successfully connected");
		 }
		 
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }

		 return con;
		}
		
		public String readcustomer()
		{ 
				String output = "";
				
				try
				{ 
						Connection con = connect();
						
						if (con == null) 
						{ 
							return "Error while connecting to the database for reading."; 
						} 
					 
			 // Prepare the html table to be displayed
					 output = "<table border='1'><tr><th>customerName</th>" 
								 + "<th>nic</th>"
								 + "<th>address</th>" 
								 + "<th>mobileNo</th>"
								 + "<th>email</th>"
								 + "<th>Update</th><th>Remove</th></tr>"; 
					 
					 String query = "select * from customer"; 
					 Statement stmt = con.createStatement(); 
					 ResultSet rs = stmt.executeQuery(query); 
					 
			 // iterate through the rows in the result set
					 while (rs.next()) 
					 { 
						 String customerID = Integer.toString(rs.getInt("customerID")); 
						 String customerName = rs.getString("customerName"); 
						 String nic = rs.getString("nic");
						 String address = rs.getString("address");
						 String mobileNo = rs.getString("mobileNo");
						 String email = rs.getString("email"); 
						 
			 // Add a row into the html table
						 output += "<tr><td><input id ='hidcustomerIDUpdate' name ='hidcustomerIDUpdate' type='hidden' value='" + customerID + " '>"	+ customerName + "</td>";
						
						 output += "<td>" + nic + "</td>"; 
						 output += "<td>" + address + "</td>";
						 output += "<td>" + mobileNo + "</td>";
						 output += "<td>" + email + "</td>";
			 // buttons
						 output += "<td><input name='btnUpdate' id ='" + customerID + " ' type='button' value='Update' class=' btnUpdate btn btn-secondary'></td><td>"
						 		+ "<input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-customerID='"+ customerID + " '>" + "</td></tr>";  
					 } 
					 con.close(); 
					 
			 // Complete the html table
					 output += "</table>"; 
					 
					 
					 
			 } 
				catch (Exception e) 
				{ 
					output = "Error while reading the items."; 
					System.err.println(e.getMessage()); 
				} 
				return output; 
		}

		public String insertcustomer(String customerName,String nic, String address, String mobileNo, String email)
	    {
				 String output = "";
				 
				 try
				 {
					 Connection con = connect();
					 
					 if (con == null)
					 {
						 return "Error while connecting to the database for inserting.";
					 }
					 
					 // create a prepared statement
					 String query = " insert into customer(`customerID`,`customerName`,`nic`,`address`,`mobileNo`,`email`)"+ " values (?, ?, ?, ?, ?, ?)";
				 
				 
					 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, customerName);
					 preparedStmt.setString(3, nic);
					 preparedStmt.setString(4, address);
					 preparedStmt.setString(5, mobileNo);
					 preparedStmt.setString(6, email);
				 
				 
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 
					 String newusers = readcustomer();
					 output = "{\"status\":\"success\", \"data\": \"" + newusers + "\"}";
				 }
				 catch (Exception e)
				 {
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
					 System.err.println(e.getMessage());
					 
				 }
				 return output;
				 
				 
	    
	     
			
	    }
		public String updatecustomer(int customerID,String customerName,String nic,String address,String mobileNo,String email)
		{ 
				String output = ""; 
				try
				 { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for updating."; 
					 } 
				 // create a prepared statement
					 String query = "update customer set  customerName = ?,  nic = ?, address = ?, mobileNo = ?, email = ? where customerID = ?"; 
					 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 
					 // binding values
					 preparedStmt.setString(1, customerName);
					 preparedStmt.setString(2, nic);
					 preparedStmt.setString(2, address);
					 preparedStmt.setString(4, mobileNo);
					 preparedStmt.setString(5, email);


					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					 
					 String newusers = readcustomer();
					 output = "{\"status\":\"success\", \"data\": \"" +newusers + "\"}";
					
				 } 
				catch (Exception e) 
				 { 
					output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
					 System.err.println(e.getMessage()); 
				 } 
				return output; 
		}

		public String deletecustomer(String customerID)
		{ 
				String output = ""; 
				try
			    { 
					 Connection con = connect(); 
					 if (con == null) 
					 { 
						 return "Error while connecting to the database for deleting."; 
					 } 
				 // create a prepared statement
					 String query = "delete from customer where customerID=?"; 
					 PreparedStatement preparedStmt = con.prepareStatement(query); 
					 // binding values
					 preparedStmt.setInt(1, Integer.parseInt(customerID)); 

					 // execute the statement
					 preparedStmt.execute(); 
					 con.close(); 
					
					 String newusers = readcustomer();
					 output = "{\"status\":\"success\", \"data\": \"" +
			 newusers + "\"}";
					 
				} 
				catch (Exception e) 
				{ 
					output = "{\"status\":\"error\", \"data\": \"Error while deleting the item.\"}"; 
					 System.err.println(e.getMessage()); 
			    } 
				return output; 
			}


		
	}
	


