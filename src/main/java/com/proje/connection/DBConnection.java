package com.proje.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class DBConnection {

	private static final Logger logger = LogManager.getLogger();
	
	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		
		Properties properties = new Properties();
		
		try {
			InputStream inputStream = new FileInputStream("src/main/resources/database.properties");
			properties.load(inputStream);
			
			driver = properties.getProperty("db_driver");
			url = properties.getProperty("db_url");
			username = properties.getProperty("db_username");
			password = properties.getProperty("db_password");
		} catch (IOException e) {
			logger.warn("database.properties dosyasından verileri çekerken hata meydana geldi. HATA : " + e);
		}
		
	}
	
	public static Connection getConnection() {
		
		Connection connection = null;
		
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			
			logger.warn("Database driver bulunamadı. Hata : " + e);
		}
				
		try {
			connection = (Connection) DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			
			logger.warn("Bağlantı oluşturulurken hata meydana geldi. Hata : " + e);
		}
	
		return null;
	}
	
	public static void closeConnection(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
		//ResultSet kapatma 
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {			
				logger.warn("ResultSet kapatılırken hata meydana geldi. Hata : " + e);
			}
		}
		//PreparedStatement kapatma
		if (preparedStatement != null ) {			
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				logger.warn("PreparedStatement kapatılırken hata meydana geldi. Hata : " + e);
			}		
		}
		//Connection kapatma
		if (connection != null) {			
			try {
				connection.close();
			} catch (SQLException e) {
				logger.warn("Connection kapatılırken hata meydana geldi. Hata : " + e);
			}
		}
	}

}
