package models;

import java.sql.*;
import play.db.*;

public class JDBC {

	 public void createTable(){
		 
		     try {
		      Connection c = DB.getConnection();
		      Statement stmt = null;
		      stmt = c.createStatement();
		      String strCreateStudent =
		    		  "CREATE TABLE IF NOT EXISTS Student (" +
								"vorname  VARCHAR(50) NOT NULL," +
								"nachname VARCHAR(50) NOT NULL, " +
								"pass  VARCHAR(100) NOT NULL," +
								"email VARCHAR(150) PRIMARY KEY," +
								"studiengang  VARCHAR(100) NOT NULL," +
								"bday datetime," +
								"infos VARCHAR(4000) ," +
								"bild VARCHAR(255) NOT NULL," +
								
								")";
		      
		      stmt.executeUpdate(strCreateStudent);
		      
		      
		      stmt = c.createStatement();
		      String strCreateStelle =
		    		  "CREATE TABLE IF NOT EXISTS Stelle (" +
		    				  	"student  VARCHAR(150) NOT NULL,"	+
		    				  	"FOREIGN KEY (student) REFERENCES Student(email)"+
								"fach  VARCHAR(50) NOT NULL,"	+
								"tag VARCHAR(50) NOT NULL, " +
								"zeit  VARCHAR(100) NOT NULL," +
								"stundenlohn VARCHAR(50) NOT NULL," +
								"id INTEGER(10) PRIMARY KEY" +
								
								")";
		      
		      stmt.executeUpdate(strCreateStelle);
		   
		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		   
		     System.out.println("If did not exist: Table created successfully");
		  }
	  
	 public void insertInto(){
		 
		 try {
			  Connection c = DB.getConnection();
		      Statement stmt = null;
		      
		      //Insert Student
		      stmt = c.createStatement();
		      String strInsertIntoStudent = "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bday, infos, bild) " +
		                   "VALUES ('Metehan', 'Kilin', '123', 'metehan.kilin@htwg-konstanz.de', 'Wirschaftsinformatik', 28.01.1990, "
		                   + "Hallo ich heiße Methe, /play/public/images/login.jpg"
		                   +");"; 
		      stmt.executeUpdate(strInsertIntoStudent);
			 
		     
		      stmt = c.createStatement();
		      strInsertIntoStudent = "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bday, infos, bild) " +
	                   "VALUES ('Eugen', 'Gering', '123', 'eugen.gering@htwg-konstanz.de', 'Wirschaftsinformatik', 22.11.1992, "
	                   + "Hallo ich heiße Eugen, /play/public/images/login.jpg"
	                   +");"; 
		      stmt.executeUpdate(strInsertIntoStudent);
		      
		      
		      
		      
		      stmt = c.createStatement();
		      String strInsertIntoStelle = "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) " +
	                   "VALUES ('metehan.kilin@htwg-konstanz.de', 'Mathematik 1', 'Montag', '20.00 - 21.00', '15', 1);"; 
		      stmt.executeUpdate(strInsertIntoStelle);
		      
		      
		      stmt = c.createStatement();
		      strInsertIntoStelle = "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) " +
	                   "VALUES ('metehan.kilin@htwg-konstanz.de', 'Webtechnologien', 'Mittwoch', '21.00 - 22.00', '20', 2);"; 
		      stmt.executeUpdate(strInsertIntoStelle);
		      
		      
		      stmt = c.createStatement();
		      strInsertIntoStelle = "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) " +
	                   "VALUES ('eugen.gering@htwg-konstanz.de', 'Programmiertechnik', 'Freitag', '13.00 - 15.00', '11', 3);"; 
		      stmt.executeUpdate(strInsertIntoStelle);
		      

		      stmt.close();
		      c.close();
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Records created successfully");
	 }
	 
	 

	 
}
