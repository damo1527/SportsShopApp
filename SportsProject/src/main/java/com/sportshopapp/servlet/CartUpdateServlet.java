package com.sportshopapp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sportshopapp.daoimpl.CartDAOImpl;
import com.sportshopapp.daoimpl.OderItemsDAOImpl;
import com.sportshopapp.daoimpl.OrderDetailDAOImpl;
import com.sportshopapp.daoimpl.ProductDAOImpl;
import com.sportshopapp.daoimpl.UserDAOImpl;
import com.sportshopapp.model.Cart;
import com.sportshopapp.model.OderDetails;
import com.sportshopapp.model.OrderItems;
import com.sportshopapp.model.Product;
import com.sportshopapp.model.UserReg;
@WebServlet("/cartorder")
public class CartUpdateServlet  extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		HttpSession session = req.getSession();
		System.out.println("cart servlet called");
		UserDAOImpl user = new UserDAOImpl();
		System.out.println("1");
		ProductDAOImpl productDao = new ProductDAOImpl();
		Product product = new Product();
		OrderItems orderItems = new OrderItems();
		Cart cart = new Cart();
		CartDAOImpl cartdao = new CartDAOImpl();
		System.out.println("2");
		OderDetails order = new OderDetails();
		OrderDetailDAOImpl orderDao = new OrderDetailDAOImpl();
		OderItemsDAOImpl orderItemsDaoImpl = new OderItemsDAOImpl();
		System.out.println("2");

		UserReg currentUser = (UserReg) session.getAttribute("logincustomer");
		int CartproductId = Integer.parseInt(req.getParameter("CartproductId"));
		int cartQuantity = Integer.parseInt(req.getParameter("cartQuantity"));
		double unitPrice = Double.parseDouble(req.getParameter("unitPrice"));
	//	double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
		double totalPrice = Double.parseDouble(req.getParameter("totalPrice"));
		
		int CartprodId = Integer.parseInt(req.getParameter("CartproductId"));
		int removeStatus;

		Product currentProduct = null;
		try {
			currentProduct = productDao.findProductById(CartprodId);
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		cart.setProduct(currentProduct);
		cart.setUser(currentUser);
		
		if (currentProduct.getQuantity() != 0 && (currentProduct.getQuantity() - cartQuantity) > 0) {

			if ((currentUser.getMyWallet() - totalPrice) >= 0) {
				order.setProducts(currentProduct);
				int updateQty = currentProduct.getQuantity() - cartQuantity;
				System.out.println(currentProduct.getQuantity());
				System.out.println(cartQuantity);

				try {
					productDao.updateProductQuantity(currentProduct, updateQty);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				order.setPrice(totalPrice);
				order.setUser(currentUser);
				order.getUser().setMyWallet(currentUser.getMyWallet() - totalPrice);
				System.out.println("Order called");
				try {
					orderDao.orders(order, currentUser);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					user.updateWalletMoney(order);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int orderId = 0;
				try {
					orderId = orderDao.getByOrderId();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("order id found"+orderId);
				orderItems.setProduct(currentProduct);
				orderItems.setUser(currentUser);
				orderItems.setOrderId(orderId);
				orderItems.setQuantity(cartQuantity);
				orderItems.setUnitPrice(unitPrice);
				orderItems.setTotalPrice(totalPrice);
				try {
					int result=	orderItemsDaoImpl.insertOrders(orderItems);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					removeStatus=cartdao.removecartItems(cart);
					res.sendRedirect("Cart.jsp");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
			else {
				System.out.println("Not Enough Money");
			}
		} else {
			res.sendRedirect("UserProfile.jsp");

		}

	}
	
}