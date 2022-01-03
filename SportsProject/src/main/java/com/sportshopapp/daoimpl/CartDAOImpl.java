package com.sportshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.sportshopapp.dao.CartDAO;
import com.sportshopapp.model.CartModel;
import com.sportshopapp.model.ProductModel;
import com.sportshopapp.model.UserRegModel;
import com.sportshopapp.util.ConnectionUtil;

public class CartDAOImpl implements CartDAO {
	public void insertProduct (CartModel cart) throws ClassNotFoundException, SQLException {
		Connection con = ConnectionUtil.getDbConnection();
		
		try {
			String insertProduct = " insert into cart (user_name, products_id, product_name, quantity, standard_cost, total_prize ) values (?,?,?,?,?,?)";
			PreparedStatement pstm;
			try {
				pstm = con.prepareStatement(insertProduct);
				pstm.setString(1, cart.getUser().getUserName());
				pstm.setInt(2, cart.getProduct().getProductId());
				pstm.setDouble(3, cart.getStandardCost());
				pstm.setDouble(4, cart.getTotalPrice());
				pstm.setInt(5, cart.getQuantity());
				int result = pstm.executeUpdate();
				System.out.println(result + "product added to cart");	
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		    }
             catch (Exception e)
		{
            	 e.getMessage();
            	 System.out.println("Something went wrong");
		}
	}
	public List<CartModel> viewCart(UserRegModel currentUser) throws ClassNotFoundException, SQLException {
		currentUser.getUserName();
		String query= "select * from cart where user_name='"+currentUser.getUserName()+"'";
		Connection con=ConnectionUtil.getDbConnection();
		List<CartModel> allCartItems=new ArrayList<CartModel>();
		Statement smts = null;
		ResultSet rs= null;
		try {
			smts = con.createStatement();
			rs=smts.executeQuery(query);
			ProductDAOImpl proDao=new ProductDAOImpl();
			while(rs.next()) {
				
				ProductModel product=proDao.findProductById(rs.getInt(2));
				CartModel cart=new CartModel(currentUser,product, rs.getDouble(4),rs.getDouble(5),rs.getInt(6));
				allCartItems.add(cart);				
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return allCartItems;
	}
}
