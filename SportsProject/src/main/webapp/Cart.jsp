<%@page import="com.sportshopapp.model.Product"%>
<%@page import="com.sportshopapp.daoimpl.ProductDAOImpl"%>
<%@page import="com.sportshopapp.model.UserReg"%>
<%@page import="com.sportshopapp.model.Cart"%>
<%@page import="java.util.List"%>
<%@page import="com.sportshopapp.daoimpl.CartDAOImpl"%>
<%@page import="com.sportshopapp.daoimpl.OderItemsDAOImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cart</title>
<link id ="tab" rel = "icon" type = "" href = "Assests/tab.png">
</head>
<style>
* {
margin: 0;
padding: 0;
box-sizing: border-box;
font-family: Arial, Helvetica, sans-serif;
}

body {
overflow-x: hidden;
}

.list ul li {
list-style: none;
display: inline-block;
}

.list li {
float: right;
padding: 20px;
}

.list ul {
/* background-color: #10847E;*/
height: 70px;
position: absolute;
margin-right: 0%;
position: absolute;
top: 0;
background-color: rgba(255, 255, 255, 0.603);
box-shadow: 0 5 black;
/* margin-top: 0%; */
right: 0px;
width: 1372px;
}

.list ul {
padding-right: 0px;
}

.list ul, .list li, .list a {
text-decoration: none;
color: black;
font-family: monospace;
font-size: 25px;
font-weight: 500;
/* margin-right: 20px; */
}

.list li:hover, .list a:hover {
color: white;
background-color: orange;
border-radius: 5px;
cursor: pointer;
}



.logo img {
height: 60px;
width: 60px;
margin-left: 20px;
}

/* banner */
.slide {
position: absolute;
top: 120px;
}
/* progress */
.products {
margin-top: 330px;
border-spacing: 0 200px;
}

.slide img {
box-shadow: 0 0 10px black;
}

.products tr td {
padding-left: 220px;
}




#allproducts a {
text-decoration: none;
color: black;
}

#allproducts a:hover {
text-decoration: underline;
color: white;
}

#allproducts {
text-align: center;
}

#product {
position: relative;
top: 30px;
left: 50px;
}
body {
	
	background-image: url(Assests/bcg.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	background-attachment: fixed;
	/* background-static: */
}

#product {
position: relative;
background-color: rgba(158, 202, 207, 0.5);
height: 190px;
border-radius: 5px;
width: 1200px;
top:150px;
}


#product img {
height: 100px;
width: 90px;
position: relative;
left: 40px;
top:20px;
}

#product h3 {
position: relative;
left: 10px;
}

#product #details {
position: relative;
left: 220px;
top: -90px;
}

#product #btn {
position: relative;
left: 900px;
top:-130px;
}
#product #btn {
position: relative;
left: 900px;
top:-130px;
}

#product #btn button {
height: 30px;
width: 90px;
background-color: orange;
border: none;
border-radius: 5px;
}
#product #btn button:hover {
background-color: white;
box-shadow: 0 0 5px black;
color: green;
color: orange;
}
#btn  {
position: relative;
top: 35px;
left: 1000px;
}

#product #btn button {
height: 50px;
width: 90px;
background-color: orange;
border: none;
border-radius: 5px;
}


#product #btn button:hover {
background-color: white;
box-shadow: 0 0 5px black;
color: green;
color: orange;
}

#product #img h3 {
position: relative;
left: 40px;
top:20px;
}



a {
text-decoration: none;
color: black;
}
#userName{
position: relative;
left: 1100px;
}
#name{
position: absolute;
left: -140px;
top: -160px;
width: 550px;
}
#pname{
position: absolute;
left: 300px;
}
#quan{
position: absolute;
left: 300px;
top: 240px;
}
#price{
position: absolute;
left: 300px;
top: 270px;
}
#prodname{
position: absolute;
top: 70px;
left: 50px;
}
/* #idButton{
position: relative;
top: 160px;
} */
</style>
<body>

<%UserReg currentUser = (UserReg)session.getAttribute("logincustomer");

%>
<div id="container">

<div class="nav">

<nav class="list">
<ul>

<li><a href="Login.jsp">SignOut</a></li>
<li><a href="UserProfile.jsp">MyProfile</a></li>
<li><a href= "MyOrder.jsp">MyOrders</a></li>
<li><a href="UserView.jsp">Home</a></li>

</ul>
<div class="logo">
<img
src=""
alt="logo">
</nav>
</div>
<!-- slideshow -->

<%-- <h2 id="userName">welcome <%=currentUser.getUserName()%></h2> --%>
 </div> 
<% OderItemsDAOImpl myOrder= new OderItemsDAOImpl();
CartDAOImpl cartDao = new CartDAOImpl();
/* Product cost = new Product(); */
/* orderDao.deleteProduct(myAllOrders.getOrderModel().getOrderId()); */
List<Cart> cartItems = cartDao.viewCart(currentUser);
%>
<% for(Cart cartList : cartItems)
{
%>

<div id="product">


<h3 id="prodname"><%=cartList.getProduct().getProductName() %></h3>
</div>
<div id="pname">
<h3  name="unitPrice">
price :<%=cartList.getStandardCost()+ "rs"%></h3>
</div>

<h3 id="quan" name="cartQuantity">Total Quantity:
<%=cartList.getQuantity() %></h3>
<h3 id="price" name="totalPrice">
Total Amount :
<%=cartList.getTotalPrice() %></h3>
</div>
<div id="btn">
<button id="idButton">
<a id="BuyNow" href="cartorder?CartproductId=<%=cartList.getProduct().getProductId()%>&unitPrice=<%=cartList.getStandardCost()%>&cartQuantity=<%=cartList.getQuantity()%>&totalPrice=<%=cartList.getTotalPrice() %>">Buy Now</a>

</button id="idButton">
<br>
<%-- <button>
<a id="Remove" href="removeCartItem?CartproductId=<%=cartList.getProduct().getProductId()%>">Remove</a>
					</button> --%>
<br>

</div>



<br>
<br>
<%}%>





<img id="name" alt="webName" src="Assests/name.png">
</body>
</html>