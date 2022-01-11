<%@page import="com.sportshopapp.model.UserReg"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User profile</title>
</head>
<style>
body {font-family: "Lato", sans-serif;}
.sidebar {
  height: 100%;
  width: 160px;
  position: fixed;
  z-index: 1;
  top: 0;
  left: 0;
  background-color: lightgreen;
  overflow-x: hidden;
  padding-top: 16px;
}
body {
	
	background-image: url(Assests/background.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
	/* background-static: */
}
.sidebar a {
  padding: 20px 2px 8px 16px;
  text-decoration: none;
  font-size: 15px;
  color: black;
  display: block;
  font-family:Arial Black;
}
.sidebar a:hover {
  color: #f1f1f1;
}
.main {
  margin-left: 160px; /* Same as the width of the sidenav */
  padding: 0px 10px;
}
 #allusers table,th,tr,td{
        border: 1px solid black;
        border-collapse: collapse;
        padding: 10px;
        }
             #allusers{
       position: absolute;
       top:100px;
       left:200px;
       right: 200px;
       bottom: 100px;
       }
        
@media screen and (max-height: 450px) {
  .sidebar {padding-top: 15px;}
  .sidebar a {font-size: 18px;}
}
</style>
<body>


<div class="sidebar">
<% UserReg customer = (UserReg) session.getAttribute("logincustomer");%>
	
 
  <a href="ChangePassword.jsp"><i class="fa fa-fw fa-user"></i> Change Password</a>
  <a href="addmoney.jsp"><i class="fa fa-fw fa-user"></i> Add money to wallet</a>
  <a href="Logout.jsp"><i class="fa fa-fw fa-envelope"></i> Logout</a>
</div>

<div class="main"></div> 
  

<div id="allusers">
<table>
<thead>
<tr>
<th>First Name</th>
<th>Last Name</th>
<th>Address</th>
<th>EmailId</th>
<th>User Name</th>
<th>Phone Number</th>
<th>Wallet</th>
</tr>
</thead>
<tbody>

<tr>
<td><%=customer.getFirstName() %></td>
<td><%=customer.getLastName() %></td>
<td><%=customer.getAddress() %></td>
<td><%=customer.getEmail() %></td>
<td><%=customer.getUserName() %></td>
<td><%=customer.getPhone() %></td>
<td><%=customer.getMyWallet() %></td>

</tr>

</tbody>
</table>
</div>
  
</div>
</body>
</html>