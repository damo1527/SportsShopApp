package com.sportshopapp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sportshopapp.daoimpl.CartDAOImpl;
import com.sportshopapp.model.Cart;
import com.sportshopapp.model.Product;
import com.sportshopapp.model.UserReg;

@WebServlet("/cartserv")
public class CartServlet  extends HttpServlet {
	public void service(HttpServletRequest req,HttpServletResponse res) throws IOException {
		try {
		HttpSession session = req.getSession();
		System.out.println(req.getParameter("cartTotalPrice"));
		
		double totalprice=Double.parseDouble(req.getParameter("cartTotalPrice"));
		System.out.println(totalprice);
		
	//	double unitPrice=Double.parseDouble(req.getParameter("cartUnitPrice"));
		

		int quantity = Integer.parseInt(req.getParameter("cartQuanity"));
		System.out.println(quantity);
		
		UserReg currentUser = (UserReg) session.getAttribute("logincustomer");
		Product currentproduct = (Product) session.getAttribute("currentproduct");
		CartDAOImpl cartDao = new CartDAOImpl();
		Cart cart = new Cart();
		cart.setUser(currentUser);
		cart.setProduct(currentproduct);
		cart.setQuantity(quantity);
//		cart.setStandardCost(unitPrice);
		cart.setTotalPrice(totalprice);
		int prodquant = 0;
		
			prodquant = cartDao.productexist(cart);
		
		if(prodquant < 0) {
		
			
		
			cartDao.insertProduct(cart);
		
		}else {
		int oldprice = 0;
		
			oldprice = cartDao.productexist(cart);
		
		
		cart.setQuantity(quantity + prodquant);
		cart.setTotalPrice(oldprice + totalprice);
	
			cartDao.updatequantity(cart);
		
		
		}
		res.sendRedirect("Payment.jsp");
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
