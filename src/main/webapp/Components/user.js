$(document).ready(function()
{

	$("#alertSuccess").hide();
	$("#alertError").hide();
});


$(document).on("click", "#btnSave", function(event)
{
	// Clear alerts---------------------

	
	 $("#alertSuccess").text("");
 	 $("#alertSuccess").hide();
 	 $("#alertError").text("");
 	 $("#alertError").hide();
 	 
 	 
   	// Form validation-------------------
  	
	var status = validateItemForm();
	if (status != true)
	{
		 $("#alertError").text(status);
 		 $("#alertError").show();
 		 
         return;
    }
 
	// If valid------------------------
	
	
	var type = ($("#hidcustomerIDSave").val() == "") ? "POST" : "PUT";

	 $.ajax(
 	 {
 		url : "UserAPI",
 		type : type,
 		data : $("#formItem").serialize(),
 		dataType : "text",
	    complete : function(response, status)
        {
   
      			onItemSaveComplete(response.responseText, status);
	    }
	    
     });
     
});
function onItemSaveComplete(response, status)
{
	if (status == "success")
	{
		 var resultSet = JSON.parse(response);
		 
	 	 if (resultSet.status.trim() == "success")
		 {
 				$("#alertSuccess").text("Successfully saved.");
		    	$("#alertSuccess").show();
 				$("#divItemsGrid").html(resultSet.data);
 			
 	 	  } else if (resultSet.status.trim() == "error")
 	 	  {
 	 
 				$("#alertError").text(resultSet.data);
 				$("#alertError").show();
		  }
		  
    } else if (status == "error")
    {
 			$("#alertError").text("Error while saving.");
 			$("#alertError").show();
 			
 	} else
 	{
 			$("#alertError").text("Unknown error while saving..");
 			$("#alertError").show();
    } 

 	$("#hidcustomerIDSave").val("");
	 $("#formItem")[0].reset();
}
$(document).on("click", ".btnUpdate", function(event)
{
		var id = event.target.id;
		$("#hidcustomerIDSave").val(id.substring(0, id.length-1));
 		$("#customerName").val($(this).closest("tr").find('td:eq(0)').text());
 		$("#nic").val($(this).closest("tr").find('td:eq(1)').text());
 		$("#address").val($(this).closest("tr").find('td:eq(2)').text());
 		$("#mobileNo").val($(this).closest("tr").find('td:eq(3)').text());
 		$("#email").val($(this).closest("tr").find('td:eq(4)').text());
});
$(document).on("click", ".btnRemove", function(event)
{
	 $.ajax(
 	{
 		url : "UserAPI",
 		type : "DELETE",
	    data : "customerID=" + $(this).data("customerID"),
 		dataType : "text",
 		complete : function(response, status)
		{
			 onItemDeleteComplete(response.responseText, status);
 		}
	 });
});



function onItemDeleteComplete(response, status)
{
	if (status == "success")
    {
 			var resultSet = JSON.parse(response);
 			
		   if (resultSet.status.trim() == "success")
 		   {
 		   
 				$("#alertSuccess").text("Successfully deleted.");
 				$("#alertSuccess").show();
 				
			    $("#divItemsGrid").html(resultSet.data);
			    
			    setTimeout( (function(){alert(43)}, 1000));
 			} else if (resultSet.status.trim() == "error")
 			{
				 $("#alertError").text(resultSet.data);
 				 $("#alertError").show();
		    }
 	} else if (status == "error")
    {
		 $("#alertError").text("Error while deleting.");
 		 $("#alertError").show();
    } else
    {
 		$("#alertError").text("Unknown error while deleting..");
 		$("#alertError").show();
 	}
}
function validateItemForm()
{
	// CODE
	if ($("#customerName").val().trim() == "")
 	{
		 return "Insert customer name.";
    }
    
    
	// NAME
	if ($("#nic").val().trim() == "")
    {
		 return "Insert nic.";
 	} 
 	

	// PRICE-------------------------------
	if ($("#address").val().trim() == "")
    {
 		return "Insert address.";
 	}
 	
 	
	// NAME
	if ($("#mobileNo").val().trim() == "")
    {
		 return "Insert mobile number.";
 	} 
 	  
	// NAME
	if ($("#email").val().trim() == "")
    {
		 return "Insert email.";
 	} 
 	
 	 	
 	
	
   return true;
}
