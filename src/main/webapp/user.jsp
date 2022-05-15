<%@ page import="com.User"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/user.js"></script>
</head>
<body>
	<div class = "container">
		<div class = "row">
				<div class = "col-6">
					<h1>User Management</h1>
	
	<form id="formItem" name="formItem" method="post" action="user.jsp">
		 customer Name:
		 <input id="customerName" name="customerName" type="text"
 						class="form-control form-control-sm">
 						
		<br> nic:
		<input id="nic" name="nic" type="text"
 						class="form-control form-control-sm">
 						
		<br> address:
		<input id="address" name="address" type="text"
 						class="form-control form-control-sm">
 						
 						
		<br> mobile number:
		<input id="mobileNo" name="mobileNo" type="text"
						 class="form-control form-control-sm">
						 
		<br> Email:
		<input id="email" name="email" type="text"
 						class="form-control form-control-sm">
						 
		<br>
		<input id="btnSave" name="btnSave" type="button" value="Save"
						 class="btn btn-primary">
						 
		<input type="hidden" id="hidcustomerIDSave" name="hidcustomerIDSave" value="">
	</form>
	
	<br/>
	<!-- Show output -->

	<div id= "alertSuccess" class="alert alert-success"></div>
 	<div id = "alertError" class="alert alert-danger"></div>
	
	<br>
	<div id ="divItemsGrid">
		<%
		User userobj = new User(); 
			 		 out.print(userobj.readcustomer());
		%>
    </div>

   </div> 
  </div>
  </div>
  


</body>
</html> 