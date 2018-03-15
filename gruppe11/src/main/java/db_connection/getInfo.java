package db_connection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class getInfo {
	
	private Statement stmt = null;
	
	//Keep the users login details
	private String userName = null;
	private Integer userID = null;
	
	//Set up new connection
	private ConnectService cs = new ConnectService(); 
	
	//Validate the User
	private Boolean getValidUser (String user) throws SQLException {
		
		//Bind the new connection to DB
		Connection c = cs.getConnection();
		
		// Start the statement for SQL query
		stmt = c.createStatement();
		
		//Prepare resultsSet
		ResultSet rs = null;
		
		//Ready the Query
		String query_String = "SELECT bruker.id, bruker.navn FROM bruker WHERE id = "+ user + ";";
		
		try {
			//Sends the query and saves the result
			rs = stmt.executeQuery(query_String);
			
			/// Saves the ID and Name of user
			while(rs.next()) {
				userID = rs.getInt("id");
				userName = rs.getString("navn");
			}
			
			//Exception shieet
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Check if user exists
		if(userID==Integer.parseInt(user)) {
			return true;
		}
		else {
			return false;
		}
		
	}
	 ///Returns the UserID if it is valid, if not returns Null
	public Integer checkUser(String user) throws SQLException {

		if(getValidUser(user)== true) {
			return userID;
		}
		else {
			return null;
		}
	}
		
	// Gets the current user
	public Integer getUserID() {
		if (userID != null) {
			return userID;
		}
		else {
			return null;
		}
	}
	
	
	// Gets the current name of User
	public String getUserName() {
		if (userName!= null) {
			return userName;
		}
		else {
			return null;
		}
	}
	
	
}
