package com.proje.repository.impl;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.proje.connection.DBConnection;
import com.proje.model.Brand;
import com.proje.model.Category;
import com.proje.model.Product;
import com.proje.model.User;
import com.proje.model.queries.UserQueries;
import com.proje.repository.UserRepo;

public class UsersRepoImpl implements UserRepo{
	
	private final Logger logger = LogManager.getLogger();
	
	private Connection connection;
	private PreparedStatement preparedStatement;
	private ResultSet resultSet;
	
	@Override
	public User saveUser(User user) {
		
		connection = DBConnection.getConnection();
		//"INSERT INTO user (userId, firstName, lastName, birthDate, userName) VALUES (?, ?, ?, ?, ?)";
		try {
		preparedStatement =	(PreparedStatement) connection.prepareStatement(UserQueries.saveUserQuery);
		
		preparedStatement.setInt(1, user.getUserId());
		preparedStatement.setString(2, user.getFirstName());
		preparedStatement.setString(3, user.getLastName());
		preparedStatement.setDate(4, user.getBirthDate());
		preparedStatement.setString(5, user.getUserName());
		
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
		
			logger.warn(user.getUserId() + " id'li user kaydedilirken hata meydana geldi. HATA :" + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return user;
	}

	@Override
	public boolean saveUserProduct(int userId, int productId) {
		
		connection = DBConnection.getConnection();
		//"INSERT INTO user_product (userId, productId) VALUES (?, ?)";
		try {
		preparedStatement =	(PreparedStatement) connection.prepareStatement(UserQueries.saveUserProductQuery);
		
		preparedStatement.setInt(1, userId);
		preparedStatement.setInt(1, productId);
		
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
		
			logger.warn("USER_PRODUCT kaydedilirken hata meydana geldi. HATA :" + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return true;
	}

	@Override
	public User updateUser(User user) {
		connection = DBConnection.getConnection();
		//"UPDATE user SET firstName = ?, lastName = ?, birthDate = ?, userName = ?";
		try {
		preparedStatement =	(PreparedStatement) connection.prepareStatement(UserQueries.updateUserQuery);
		
		
		preparedStatement.setString(1, user.getFirstName());
		preparedStatement.setString(2, user.getLastName());
		preparedStatement.setDate(3, user.getBirthDate());
		preparedStatement.setString(4, user.getUserName());
		preparedStatement.setInt(5, user.getUserId());
		
		preparedStatement.executeUpdate();
		
		} catch (SQLException e) {
		
			logger.warn(user.getUserId() + " id'li user güncellenirken hata meydana geldi. HATA :" + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return user;
	}
	
	//DELETE FROM user_product WHERE userId = ?";
	//"DELETE FROM user WHERE userId = ?";
	@Override
	public boolean removeUser(int id) {
	
		connection = DBConnection.getConnection();
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQueries.deleteUserProductQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQueries.deleteUserQuery);
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.warn("User silinirken hata meydana geldi. Hata : "+ e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return false;
	}

	@Override
	public User findUserById(int id) {

		connection = DBConnection.getConnection();
		
		User user = null;
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQueries.findUserByIdQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				int userId = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Date birthDate = resultSet.getDate("birthDate");
				String userName = resultSet.getString("userName");
				
				user = new User(userId, firstName, lastName, birthDate, userName);
			}
			
 		} catch (SQLException e) {
 			logger.warn("User bulurken hata meydana geldi. Hata : " + e);
 		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return user;
	}

	@Override
	public User findUserByProductId(int id) {

		connection = DBConnection.getConnection();
		
		User user = null ;
		
		//"SELECT u.firstName, u.lastName, u.userName, p.productName, p.unitPrice, p.avaible, c.categoryName, b.brand_name "
		//+ " FROM user u LEFT OUTER JOIN user_product up ON (u.userId = up.userId) "
		//+ " LEFT JOIN product p ON (up.productId = p.productId) "
		//+ " LEFT JOIN category c ON (p.categoryId = c.categoryId) "
		//+ " LEFT JOIN brand b ON (p.brandId = b.brandId) "
		//+ " WHERE u.userId = ?";
		try {
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQueries.findUserProductQuery);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();			
			boolean durum =true ;
			
			List<Product> products = new ArrayList<Product>();
			
			while (resultSet.next()) {
				
				if (durum) {
					

					int userId = resultSet.getInt("userId");
					String firstName = resultSet.getString("firstName");
					String lastName = resultSet.getString("lastName");
					Date birthDate = resultSet.getDate("birthDate");
					String userName = resultSet.getString("userName");
					
					user = new User(userId, firstName, lastName, birthDate, userName);
					durum = false;
				}
					int productId = resultSet.getInt("productId");
					String productName = resultSet.getString("productName");
					Double unitPrice = resultSet.getDouble("unitPrice");
					int avaible = resultSet.getInt("avaible");
					Date addTime = resultSet.getDate("addTime");
					Date updateTime = resultSet.getDate("updateTime");
					
					int categoryId = resultSet.getInt("category");
					String categoryName = resultSet.getString("categoryName");
					
					int brandId = resultSet.getInt("brand");
					String brand_name = resultSet.getString("brand_name");
					
					Category category = new Category(categoryId, categoryName);
					Brand brand = new Brand(brandId, brand_name);
					Product product = new Product(productId, productName, unitPrice, avaible, addTime, updateTime, category, brand);
					
					products.add(product);
 			}
			user.setProducts(products);
			
		} catch (SQLException e) {
			logger.warn("User ve ürünleri bulunurken hata meydana geldi. Hata : "+ e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return user;
	}

	@Override
	public List<User> findUsers() {
		
		connection = DBConnection.getConnection();
		
		List<User> users = new ArrayList<User>();
		try {
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(UserQueries.findUsersQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				int userId = resultSet.getInt("userId");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				Date birthDate = resultSet.getDate("birthDate");
				String userName = resultSet.getString("userName");
				
				User user = new User(userId, firstName, lastName, birthDate, userName);
				
				users.add(user);
			}
			
		} catch (SQLException e) {
			logger.warn("User listesi oluşturulurken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return users;
	}

}
