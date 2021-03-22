  package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class MainProgram {

	public static void main(String[] args) {
		
		
		//Initiating null variables with the JDBC Class-Types needed for selection
		Connection con = null;
		
		/*Using Method PreparedStatement to set query commands with parameters
		that will be fulfilled later*/	
		PreparedStatement pst = null;
		
		
		//Using try catch body to treat SQL exceptions
		try {
			
		//Opening db connection	
		con = DB.getConnection();
		/*Setting PreparedStatement Object pst with DELETE command 
		  with ? parameters passed as values to be replaced right after*/
		
		/*OBS1: It is needed to always pay attention to the existence of restriction
		  "where", since a deletion process without restriction will be spread to 
		  all the elements at the table. Which makes this process more dangerous than
		  the others*/
		
		//OBS2: No case sensitivity at the command string
		
		pst = con.prepareStatement("DELETE FROM department WHERE id = ?");
		//Replacing respective ? parameters with the desired data for new seller insertion
		
		pst.setInt(1, 5);
		
		/*Executing command with all updates with an integer of net number of 
          lines as return, and saving this information in an int variable 
          rowsChanged
        */
		int rowsChanged = pst.executeUpdate();
		
		System.out.println("Done! Number of Rows Changed: " + rowsChanged);
		
		}//Handling specific exceptions
		catch (SQLException e) {
			/*Throwing custom exception returning system's message
			  in case of DB Integrity problems*/
			throw new DbIntegrityException(e.getMessage());
		}
		
		
		//Using finally block to ensure all external resources to JVM will be closed
		finally {
			
			DB.closeConnection();
			
			//Upcast in closeStatement as PreparedStatement pst is passed as a Statement parameter
			
			DB.closeStatement(pst);
			
			
		}


}}