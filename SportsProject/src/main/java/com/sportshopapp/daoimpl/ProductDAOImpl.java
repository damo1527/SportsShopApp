package com.sportshopapp.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.sportshopapp.dao.ProductDAO;
import com.sportshopapp.model.ProductModel;
import com.sportshopapp.model.UserRegModel;
import com.sportshopapp.util.ConnectionUtil;

public class ProductDAOImpl implements ProductDAO {
	Scanner sc = new Scanner(System.in);

	public void insert() throws ClassNotFoundException, SQLException {
	char check;
	System.out.println("Do You Want Enter Product \n Y /N");
	check = sc.nextLine().charAt(0);
	if (check == 'y' || check == 'Y') {
	System.out.println("Enter the Product Name");
	String pName = sc.nextLine();
	System.out.println("Enter the Product ID");
	int prodId = Integer.parseInt(sc.nextLine());
	System.out.println("Enter the price");
	Double price = Double.parseDouble(sc.nextLine());
	System.out.println("Enter the product category");
	String prodCategory = sc.nextLine();
	System.out.println("enter the product quantity");
	int prodQuantity = sc.nextInt();
	
	
	ProductModel str = new ProductModel( pName, prodId, price,prodCategory,prodQuantity);
	Connection con= ConnectionUtil.getDbConnection();
	String query = "insert into product_items values(?,?,?,?,?)";
	PreparedStatement stmt = con.prepareStatement(query);
	
	stmt.setString(1, str.getProductName());
	stmt.setInt(2, str.getProductId());
	stmt.setDouble(3, str.getStrandardCost());
	stmt.setString(4, str.getCategory());
	stmt.setInt(5, str.getQuantity());
	stmt.executeUpdate();
	System.out.println("Product Added");
	}

	}

	public void delete() throws ClassNotFoundException, SQLException {
	char check;
	System.out.println("Do You Want delete Product \n Y /N");
	check = sc.nextLine().charAt(0);
	if (check == 'y' || check == 'Y') {
	System.out.println("Enter the Product id");
	int prodId = Integer.parseInt(sc.nextLine());

	Connection con= ConnectionUtil.getDbConnection();
	ProductModel str = new ProductModel();
	str.setProductId(prodId);
	String query = "delete from  product_items where products_id=?";
	PreparedStatement stmt = con.prepareStatement(query);

	stmt.setInt(1, str.getProductId());
	stmt.executeUpdate();
	System.out.println("Product deleted");
	}
     
	}
	
		public List<ProductModel> viewAllProducts() throws ClassNotFoundException, SQLException {
			
			Connection con = ConnectionUtil.getDbConnection();
			Statement stmt = con.createStatement();
			
			List<ProductModel> productList = new ArrayList<ProductModel>();
			String view = " SELECT * FROM product_items";
			ResultSet rs = stmt.executeQuery(view);
			while (rs.next()) {
				// System.out.println(rs.getInt(2) + " " + rs.getString(1)+" "+ rs.getInt(3));
				ProductModel product = new ProductModel(rs.getString(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
				productList.add(product);
			}
			return productList;
		}
		
	
		public void updateProducts(int updateProductId, String updateProductName, double updateStandardPrize, String updateProductCategory, int updateProductQuantity) throws SQLException, ClassNotFoundException {
			String updateQuery="update product_items set products_name=?,standard_cost=?, category=?, quantity=? where products_id=? " ;
			Connection con = ConnectionUtil.getDbConnection();
			PreparedStatement pstm=null;
			pstm = con.prepareStatement(updateQuery);
			pstm.setString(1, updateProductName);
			pstm.setInt(2, updateProductId);
			pstm.setDouble(3, updateStandardPrize);
			pstm.setString(4, updateProductCategory);
			pstm.setInt(5, updateProductQuantity);
			
			int result =  pstm.executeUpdate();
			if(result >0) {
				System.out.println(result + "product is updated");
			}
			else {
				System.out.println("Product not updated, Something went wrong");
			}
		}
		public ProductModel findProductById (int id) throws ClassNotFoundException, SQLException {
			int productId = 0;
			String query="select * from product_items where products_id= '" + id+ "'";
			Connection con = ConnectionUtil.getDbConnection();
			PreparedStatement pstm = con.prepareStatement(query);
			ProductModel product = null;
			ResultSet rs = pstm.executeQuery();
			if(rs.next()) {
				product = new ProductModel(rs.getString(1),rs.getInt(2),rs.getDouble(3),rs.getString(4),rs.getInt(5));
			}
			return product;
		}
		
//		public List<ProductModel> filterByPrice(int min,int max){   
//			ProductModel product = new ProductModel();
//		    List<ProductModel> listofproducts = product.getStrandardCost();
//  	    System.out.println(listofproducts +" size: " +listofproducts.size());
//
//		    for (ProductModel productsVar : listofproducts) {
//		        if(productsVar.getPrice()>= min && productsVar.getPrice()<= max){
//		            return  listofproducts; //here how do i print the reduced listOfProducts
//		        }
//		    }       
//		    return null;
//		}
}
	
	

