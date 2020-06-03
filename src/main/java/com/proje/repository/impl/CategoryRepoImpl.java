package com.proje.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.proje.connection.DBConnection;
import com.proje.model.Category;
import com.proje.model.queries.CategoryQueries;
import com.proje.repository.CategoryRepo;


public class CategoryRepoImpl implements CategoryRepo{

	private final Logger logger = LogManager.getLogger();

	private Connection connection = null;
	
	private PreparedStatement preparedStatement = null;
	
	private ResultSet resultSet = null;
	
	@Override
	public Category findCategoryById(int id) {
		//"SELECT * FROM category WHERE categoryId = ?";
		
		connection = DBConnection.getConnection();
		
		Category category = null;
		
		try {
			
			preparedStatement = (PreparedStatement) connection.prepareStatement(CategoryQueries.findCategoryByIdQuery);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");
				
				category = new Category(categoryId, categoryName);
			}
			
		} catch (SQLException e) {
			logger.warn("Category bulunurken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return category;
	}

	@Override
	public List<Category> findCategories() {
		
		connection = DBConnection.getConnection();
		
		List<Category> categories = new ArrayList<Category>();
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(CategoryQueries.findCategoriesQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				int categoryId = resultSet.getInt("categoryId");
				String categoryName = resultSet.getString("categoryName");
				
				Category category = new Category(categoryId, categoryName);
				categories.add(category);
				
			}
			
		} catch (SQLException e) {
			logger.warn("Category'ler listelenirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return categories;
	}

}

