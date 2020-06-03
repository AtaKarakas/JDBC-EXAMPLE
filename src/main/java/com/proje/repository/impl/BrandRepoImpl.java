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
import com.proje.model.Brand;
import com.proje.model.queries.BrandQueries;
import com.proje.repository.BrandRepo;

public class BrandRepoImpl implements BrandRepo{
	
	private final Logger logger = LogManager.getLogger();
	
	private Connection connection = null ;
	
	private PreparedStatement preparedStatement = null;
	
	private ResultSet resultSet = null ;

	@Override
	public Brand saveBrand(Brand brand) {
		//INSERT INTO brand (brandId, brandName) VALUES (?, ?)"
		
		connection = DBConnection.getConnection();
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(BrandQueries.saveBrandQuery);
			
			preparedStatement.setInt(1, brand.getBrandId());
			preparedStatement.setString(2, brand.getBrand_name());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.warn(brand.getBrandId()+ "idli brand kaydolurken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return brand;
	}

	@Override
	public List<Brand> findBrands() {
		
		connection = DBConnection.getConnection();
		
		List<Brand> brands = new ArrayList<Brand>();
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(BrandQueries.findBrandsQuery);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				int brandId = resultSet.getInt("brandId");
				String brand_name = resultSet.getString("brand_name");
				
				Brand brand = new Brand(brandId, brand_name);
				brands.add(brand);
				
			}
			
		} catch (SQLException e) {
			logger.warn("Brandler listelenirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return brands;
	}

	@Override
	public Brand findBrandById(int id) {
		//SELECT * FROM brand WHERE brandId = ?"
		connection = DBConnection.getConnection();
		
		Brand brand = null ;
		
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement(BrandQueries.findBrandByIdQuery);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				int brandId = resultSet.getInt("brandId");
				String brand_name = resultSet.getString("brand_name");
				
				brand = new Brand(brandId, brand_name);
			}
			
		} catch (SQLException e) {
			logger.warn("Brand aranirken hata meydana geldi. Hata : " + e);
		}finally {
			DBConnection.closeConnection(connection, preparedStatement, resultSet);
		}
		
		return brand;
	}

}



