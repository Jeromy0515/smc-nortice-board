package com.norticeboard.smc.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionManager {
	
	private Connection connection = null;
	
	public void connect(String url, String user, String password) throws SQLException {
		connection = DriverManager.getConnection(url,user,password);
	}
	
	public void execute(String sql) {
		try(PreparedStatement pstmt = connection.prepareStatement(sql)){
			
		} catch(SQLException e) {
			
		}
	}
	
	private void setObject(String...args) {
		
	}
	
}
