package models;

import java.io.IOException;
import java.sql.*;
import java.util.Observable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.db.*;
import controllers.*;

public class JDBC extends Observable{
    
    public JDBC(){
    	dropTable();
        createTable();
        insertInto();
    }

	 public void createTable() {
	
	 try {
	 Connection c = DB.getConnection();
	 Statement stmt = null;
	 stmt = c.createStatement();
	 String strCreateStudent = "CREATE TABLE IF NOT EXISTS Student ("
	 + "vorname  VARCHAR(255) NOT NULL,"
	 + "nachname VARCHAR(255) NOT NULL, "
	 + "pass  VARCHAR(255) NOT NULL,"
	 + "email VARCHAR(255) PRIMARY KEY,"
	 + "studiengang  VARCHAR(255) NOT NULL,"
	 + "bday VARCHAR(255)," + "infos VARCHAR(255) "
	 + ");";
	
	 stmt.executeUpdate(strCreateStudent);
	
	 stmt = c.createStatement();
	 String strCreateStelle = "CREATE TABLE IF NOT EXISTS Stelle ("
	 + "email  VARCHAR(255) NOT NULL,"
	 + "fach  VARCHAR(255) NOT NULL,"
	 + "tag VARCHAR(255) NOT NULL, "
	 + "zeit  VARCHAR(255) NOT NULL,"
	 + "stundenlohn VARCHAR(255) NOT NULL,"
	 + "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
	 + "FOREIGN KEY (email) REFERENCES Student(email)"
	 + ");";
	
	 stmt.executeUpdate(strCreateStelle);
	
	 stmt.close();
	 c.close();
	 } catch (Exception e) {
	 System.err.println(e.getClass().getName() + ": " + e.getMessage());
	 System.exit(0);
	 }
	
	 System.out.println("If did not exist: Table created successfully");
	 }
	
	 public void insertInto() {
	
	 try {
	 Connection c = DB.getConnection();
	 Statement stmt = null;
	
	 // Insert Student
	 stmt = c.createStatement();
	 String strInsertIntoStudent =
	 "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bday, infos) "
	 +
	 "VALUES ('Metehan', 'Kilin', '123', 'metehan.kilin@htwg-konstanz.de', 'Wirschaftsinformatik', '28.01.1990', 'Hallo ich heiße Methe');";
	 stmt.executeUpdate(strInsertIntoStudent);
	
	 stmt = c.createStatement();
	 strInsertIntoStudent =
	 "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bday, infos) "
	 +
	 "VALUES ('Eugen', 'Gering', '123', 'eugen.gering@htwg-konstanz.de', 'Wirschaftsinformatik', '22.11.1992', 'Hallo ich heiße Eugen');";
	 stmt.executeUpdate(strInsertIntoStudent);
	
	 // Insert Stelle
	 stmt = c.createStatement();
	 String strInsertIntoStelle =
	 "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) "
	 +
	 "VALUES ('metehan.kilin@htwg-konstanz.de', 'Mathematik 1', 'Montag', '20.00 - 21.00', '15', 1);";
	 stmt.executeUpdate(strInsertIntoStelle);
	
	 stmt = c.createStatement();
	 strInsertIntoStelle =
	 "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) "
	 +
	 "VALUES ('metehan.kilin@htwg-konstanz.de', 'Webtechnologien', 'Mittwoch', '21.00 - 22.00', '20', 2);";
	 stmt.executeUpdate(strInsertIntoStelle);
	
	 stmt = c.createStatement();
	 strInsertIntoStelle =
	 "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) "
	 +
	 "VALUES ('eugen.gering@htwg-konstanz.de', 'Programmiertechnik', 'Freitag', '13.00 - 15.00', '11', 3);";
	 stmt.executeUpdate(strInsertIntoStelle);
	
	 stmt.close();
	 c.close();
	 } catch (Exception e) {
	 System.err.println(e.getClass().getName() + ": " + e.getMessage());
	 System.exit(0);
	 }
	 System.out.println("Records created successfully");
	 }

	public void dropTable() {
		Connection c = null;
		Statement stmt = null;

		try {
			c = DB.getConnection();

			// Insert Student
			stmt = c.createStatement();
			String strInsertIntoStudent = "DROP TABLE IF EXISTS Stelle;";
			stmt.executeUpdate(strInsertIntoStudent);
			strInsertIntoStudent = "DROP TABLE IF EXISTS Student;";
			stmt.executeUpdate(strInsertIntoStudent);
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Records created successfully");
	}

	public void insertIntoStudent(String vorname, String nachname, String pass,
			String email, String studiengang) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DB.getConnection();
			String strInsertIntoStudent = "INSERT INTO Student (vorname, nachname, pass, email, studiengang) VALUES (?,?,?,?,?);";
			ps = c.prepareStatement(strInsertIntoStudent);
			
			ps.setString(1, vorname);
			ps.setString(2, nachname);
			ps.setString(3, pass);
			ps.setString(4, email);
			ps.setString(5, studiengang);
			
			ps.executeUpdate();
			
			ps.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void insertIntoStudent(String email, String bday, String infos) {
		Connection c = null;
		PreparedStatement ps = null;
		try {
			c = DB.getConnection();
			String strInsertIntoStudent = "UPDATE Student SET bday ='?', infos ='?' WHERE email = '?';";
			ps = c.prepareStatement(strInsertIntoStudent);
			
			ps.setString(1, bday);
			ps.setString(2, infos);
			ps.setString(3, email);
			
			ps.executeUpdate();

			ps.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void insertIntoStelle(String email, String fach, String tag,
			String zeit, String stundenlohn) {
		Connection c = null;
		PreparedStatement ps = null;

		try {
			c = DB.getConnection();
			String strInsertIntoStelle = "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn) VALUES (?,?,?,?,?);";
			ps = c.prepareStatement(strInsertIntoStelle);
			
			ps.setString(1, email);
			ps.setString(2, fach);
			ps.setString(3, tag);
			ps.setString(4, zeit);
			ps.setString(5, stundenlohn);
			
			ps.executeUpdate();

			ps.close();
			c.close();
			setChanged();
			notifyObservers();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public String[] studentSuchen(String mail) {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strStudentSuchen = "SELECT * " + "FROM Student"
					+ " WHERE email = '" + mail + "';";
			ResultSet rs = stmt.executeQuery(strStudentSuchen);

			String[] student = new String[8];
			while (rs.next()) {
				student[0] = rs.getString("vorname");
				student[1] = rs.getString("nachname");
				student[2] = rs.getString("pass");
				student[3] = rs.getString("email");
				student[4] = rs.getString("studiengang");
				student[5] = rs.getString("bday");
				student[6] = rs.getString("infos");
			}
			rs.close();
			stmt.close();
			c.close();
			return student;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
		return null;
	}

	public void stellenSuchen(String mail) {
		Connection c = null;
		Statement stmt = null;
		Application.getStellen().clear();
		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strStelleSuchen = "SELECT stelle.*, student.vorname, student.nachname FROM Stelle stelle, Student student"
					+ " WHERE stelle.email = '" + mail + "'"
					+ " AND stelle.email = student.email;";
			ResultSet rs = stmt.executeQuery(strStelleSuchen);
			while (rs.next()) {
				String fach = rs.getString("fach");
				String tag = rs.getString("tag");
				String zeit = rs.getString("zeit");
				String stundenlohn = rs.getString("stundenlohn");
				String id = rs.getString("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				if (fach != null) {
					Stelle stelle = new Stelle((vorname + " " + nachname), fach, tag, zeit, stundenlohn, id);
					System.out.println(stelle.getTag());
					Application.getStellen().add(stelle);
				}
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
	}

	public void alleStellenSuchen(String fach2) {
		Connection c = null;
		PreparedStatement ps = null;
		Application.getStellen().clear();
		try {
			c = DB.getConnection();
			String strStelleSuchen = "SELECT stelle.* , student.vorname, student.nachname FROM Stelle stelle, Student student WHERE fach LIKE ? AND stelle.email = student.email;";
			ps = c.prepareStatement(strStelleSuchen);
			
			ps.setString(1, "%" + fach2 + "%");
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String fach = rs.getString("fach");
				String tag = rs.getString("tag");
				String zeit = rs.getString("zeit");
				String stundenlohn = rs.getString("stundenlohn");
				String id = rs.getString("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");

				System.out.println(fach);
				if (fach != null) {
					Stelle stelle = new Stelle((vorname + " " + nachname), fach, tag, zeit, stundenlohn, id);
					System.out.println(stelle.getTag());
					Application.getStellen().add(stelle);
				}
			}
			rs.close();
			ps.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
	}
	
	public void alleStellenSuchen(String studiengang, String fach2) {
		Connection c = null;
		PreparedStatement ps = null;
		Application.getStellen().clear();
		try {
			c = DB.getConnection();
			String strStelleSuchen = "SELECT stelle.*, student.vorname, student.nachname" + " FROM Stelle stelle, Student student"
					+ " WHERE student.email = stelle.email AND student.studiengang = ? AND stelle.fach LIKE ?;";
			
			ps = c.prepareStatement(strStelleSuchen);
			
			ps.setString(1, studiengang);
			ps.setString(2, "%" + fach2 + "%");

			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String fach = rs.getString("fach");
				String tag = rs.getString("tag");
				String zeit = rs.getString("zeit");
				String stundenlohn = rs.getString("stundenlohn");
				String id = rs.getString("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				System.out.println(fach);
				if (fach != null) {
					Stelle stelle = new Stelle((vorname + " " + nachname), fach, tag, zeit, stundenlohn, id);
					System.out.println(stelle.getTag());
					Application.getStellen().add(stelle);
				}
			}
			rs.close();
			ps.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
	}
	
	public void alleStellenSuchenOhneFach(String studiengang) {
		Connection c = null;
		PreparedStatement ps = null;
		Application.getStellen().clear();
		try {
			c = DB.getConnection();
			String strStelleSuchen = "SELECT stelle.*, student.vorname, student.nachname " + " FROM Stelle stelle, Student student"
					+ " WHERE student.email = stelle.email AND student.studiengang = ?;";
			
			ps = c.prepareStatement(strStelleSuchen);

			ps.setString(1, studiengang);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String fach = rs.getString("fach");
				String tag = rs.getString("tag");
				String zeit = rs.getString("zeit");
				String stundenlohn = rs.getString("stundenlohn");
				String id = rs.getString("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				System.out.println(fach);
				if (fach != null) {
					Stelle stelle = new Stelle((vorname + " " + nachname), fach, tag, zeit, stundenlohn, id);
					System.out.println(stelle.getTag());
					Application.getStellen().add(stelle);
				}
			}
			rs.close();
			ps.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
	}
	
	public void alleStellenSuchen() {
		Connection c = null;
		Statement stmt = null;
		Application.getStellen().clear();
		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strStelleSuchen = "SELECT stelle.*, student.vorname, student.nachname FROM Stelle stelle, Student student"
					+ " WHERE stelle.email = student.email;";
			ResultSet rs = stmt.executeQuery(strStelleSuchen);
			while (rs.next()) {
				String fach = rs.getString("fach");
				String tag = rs.getString("tag");
				String zeit = rs.getString("zeit");
				String stundenlohn = rs.getString("stundenlohn");
				String id = rs.getString("id");
				String vorname = rs.getString("vorname");
				String nachname = rs.getString("nachname");
				System.out.println(fach);
				if (fach != null) {
					Stelle stelle = new Stelle((vorname + " " + nachname), fach, tag, zeit, stundenlohn, id);
					System.out.println(stelle.getTag());
					Application.getStellen().add(stelle);
				}
			}
			rs.close();
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
	}
	
	public void studentÄndern(String email, String stelle, String wert) {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DB.getConnection();

			stmt = c.createStatement();
			String strUpdateStudent = "UPDATE Student" + " SET " + stelle
					+ "='" + wert + "'" + "WHERE email = '" + email + "';";

			stmt.executeUpdate(strUpdateStudent);
			stmt.close();
			c.close();

		} catch (Exception e) {
			System.out.println("Fehlgeschlagen");
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");

	}

	public void stelleLöschen(String email, int id) {
		Connection c = null;
		Statement stmt = null;

		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strStelleLöschen = "DELETE" + " FROM Stelle"
					+ " WHERE email = '" + email + "' AND id = " + id + ";";
			stmt.executeUpdate(strStelleLöschen);
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("If did not exist: Table created successfully");
	}

	public String[] tutorSuchen(String id) {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strTutorSuchen = "SELECT student.* FROM Student student, Stelle stelle"
					+ " WHERE stelle.id = '" + id + "'"
					+ " AND stelle.email = student.email"
					+ " ORDER BY student.email ASC LIMIT 1;";
			ResultSet rs = stmt.executeQuery(strTutorSuchen);

			String[] student = new String[8];
			while (rs.next()) {
				student[0] = rs.getString("vorname");
				student[1] = rs.getString("nachname");
				student[2] = rs.getString("pass");
				student[3] = rs.getString("email");
				student[4] = rs.getString("studiengang");
				student[5] = rs.getString("bday");
				student[6] = rs.getString("infos");
			}
			rs.close();
			stmt.close();
			c.close();
			return student;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
		return null;
	}


	public static String[] neustenTutorSuchen() {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strTutorSuchen = "SELECT student.vorname, student.nachname, stelle.fach FROM Student student, Stelle stelle"
					+ " WHERE stelle.email = student.email"
					+ " AND stelle.id = (SELECT MAX(stelle2.id) FROM Stelle stelle2);";
			System.out.println(strTutorSuchen);
			ResultSet rs = stmt.executeQuery(strTutorSuchen);

			String[] student = new String[3];
			while (rs.next()) {
				student[0] = rs.getString("vorname");
				student[1] = rs.getString("nachname");
				student[2] = rs.getString("fach");
				
			}
			System.out.println(student[2]);
			rs.close();
			stmt.close();
			c.close();
			return student;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
		return null;
	}


	public JsonNode erzeugeJSON(){
		System.out.println("json wird erzeugt");
		String jsonText = "";
			String[] neu = neustenTutorSuchen();
			jsonText += "{";
			jsonText += "\"vorname\": " + "\"" + neu[0]+ "\",";
			jsonText += "\"nachname\": " + "\"" + neu[1] + "\",";
			jsonText += "\"fach\": " + "\"" + neu[2] + "\"";
			jsonText += "}";
		
		
		System.out.println("JSON: " + jsonText);
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = null;
		try {
			node = mapper.readValue(jsonText, JsonNode.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("RETURN NODE");
		System.out.println(node);
		return node;
	}



}
