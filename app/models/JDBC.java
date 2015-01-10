package models;

import java.sql.*;

import play.db.*;

public class JDBC {

	// public void createTable() {
	//
	// try {
	// Connection c = DB.getConnection();
	// Statement stmt = null;
	// stmt = c.createStatement();
	// String strCreateStudent = "CREATE TABLE IF NOT EXISTS Student ("
	// + "vorname  VARCHAR(255) NOT NULL,"
	// + "nachname VARCHAR(255) NOT NULL, "
	// + "pass  VARCHAR(255) NOT NULL,"
	// + "email VARCHAR(255) PRIMARY KEY,"
	// + "studiengang  VARCHAR(255) NOT NULL,"
	// + "bday VARCHAR(255)," + "infos VARCHAR(255) ,"
	// + "bild VARCHAR(255) NOT NULL"
	// + ");";
	//
	// stmt.executeUpdate(strCreateStudent);
	//
	// stmt = c.createStatement();
	// String strCreateStelle = "CREATE TABLE IF NOT EXISTS Stelle ("
	// + "email  VARCHAR(255) NOT NULL,"
	// + "fach  VARCHAR(255) NOT NULL,"
	// + "tag VARCHAR(255) NOT NULL, "
	// + "zeit  VARCHAR(255) NOT NULL,"
	// + "stundenlohn VARCHAR(255) NOT NULL,"
	// + "id INTEGER PRIMARY KEY AUTOINCREMENT UNIQUE,"
	// + "FOREIGN KEY (email) REFERENCES Student(email)"
	// + ");";
	//
	// stmt.executeUpdate(strCreateStelle);
	//
	// stmt.close();
	// c.close();
	// } catch (Exception e) {
	// System.err.println(e.getClass().getName() + ": " + e.getMessage());
	// System.exit(0);
	// }
	//
	// System.out.println("If did not exist: Table created successfully");
	// }
	//
	// public void insertInto() {
	//
	// try {
	// Connection c = DB.getConnection();
	// Statement stmt = null;
	//
	// // Insert Student
	// stmt = c.createStatement();
	// String strInsertIntoStudent =
	// "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bday, infos, bild) "
	// +
	// "VALUES ('Metehan', 'Kilin', '123', 'metehan.kilin@htwg-konstanz.de', 'Wirschaftsinformatik', '28.01.1990', "
	// + "'Hallo ich heiße Methe', '/play/public/images/login.jpg'"
	// + ");";
	// stmt.executeUpdate(strInsertIntoStudent);
	//
	// stmt = c.createStatement();
	// strInsertIntoStudent =
	// "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bday, infos, bild) "
	// +
	// "VALUES ('Eugen', 'Gering', '123', 'eugen.gering@htwg-konstanz.de', 'Wirschaftsinformatik', '22.11.1992', "
	// + "'Hallo ich heiße Eugen', '/play/public/images/login.jpg'"
	// + ");";
	// stmt.executeUpdate(strInsertIntoStudent);
	//
	// // Insert Stelle
	// stmt = c.createStatement();
	// String strInsertIntoStelle =
	// "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) "
	// +
	// "VALUES ('metehan.kilin@htwg-konstanz.de', 'Mathematik 1', 'Montag', '20.00 - 21.00', '15', 1);";
	// stmt.executeUpdate(strInsertIntoStelle);
	//
	// stmt = c.createStatement();
	// strInsertIntoStelle =
	// "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) "
	// +
	// "VALUES ('metehan.kilin@htwg-konstanz.de', 'Webtechnologien', 'Mittwoch', '21.00 - 22.00', '20', 2);";
	// stmt.executeUpdate(strInsertIntoStelle);
	//
	// stmt = c.createStatement();
	// strInsertIntoStelle =
	// "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn, id) "
	// +
	// "VALUES ('eugen.gering@htwg-konstanz.de', 'Programmiertechnik', 'Freitag', '13.00 - 15.00', '11', 3);";
	// stmt.executeUpdate(strInsertIntoStelle);
	//
	// stmt.close();
	// c.close();
	// } catch (Exception e) {
	// System.err.println(e.getClass().getName() + ": " + e.getMessage());
	// System.exit(0);
	// }
	// System.out.println("Records created successfully");
	// }

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
		Statement stmt = null;

		try {
			c = DB.getConnection();

			// Insert Student
			stmt = c.createStatement();
			String strInsertIntoStudent = "INSERT INTO Student (vorname, nachname, pass, email, studiengang, bild) VALUES ('"
					+ vorname
					+ "','"
					+ nachname
					+ "','"
					+ pass
					+ "','"
					+ email
					+ "','" + studiengang + "','" + "images/login.jpg');";
			System.out.println(strInsertIntoStudent);
			stmt.executeUpdate(strInsertIntoStudent);

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Records created successfully");
	}

	public void insertIntoStudent(String email, String bday, String infos) {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DB.getConnection();

			// Insert Student
			stmt = c.createStatement();
			String strInsertIntoStudent = "UPDATE Student" + " SET bday ='"
					+ bday + "', infos ='" + infos + "'" + "WHERE email = '"
					+ email + "';";
			stmt.executeUpdate(strInsertIntoStudent);

			stmt.close();
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
		Statement stmt = null;
		try {
			c = DB.getConnection();

			stmt = c.createStatement();
			String strInsertIntoStelle = "INSERT INTO Stelle (email, fach, tag, zeit, stundenlohn) "
					+ "VALUES ('"
					+ email
					+ "','"
					+ fach
					+ "','"
					+ tag
					+ "','"
					+ zeit + "','" + stundenlohn + "');";
			stmt.executeUpdate(strInsertIntoStelle);

			stmt.close();
			c.close();
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
			System.out.println(strStudentSuchen);
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
				student[7] = rs.getString("bild");
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
//TODO
	public String[] stellenSuchen(String mail) {
		Connection c = null;
		Statement stmt = null;
		try {
			c = DB.getConnection();
			stmt = c.createStatement();
			String strStelleSuchen = "SELECT * " + "FROM Stelle"
					+ " WHERE student = '" + mail + "';";
			System.out.println(strStelleSuchen);
			ResultSet rs = stmt.executeQuery(strStelleSuchen);

			String[] stellen = new String[8];
			while (rs.next()) {
				stellen[0] = rs.getString("student");
				stellen[1] = rs.getString("fach");
				stellen[2] = rs.getString("tag");
				stellen[3] = rs.getString("zeit");
				stellen[4] = rs.getString("stundenlohn");
				stellen[5] = rs.getString("id");
			
			}
			rs.close();
			stmt.close();
			c.close();
			return stellen;

		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);

		}
		System.out.println("Records created successfully");
		return null;
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
			String strStelleLöschen = "DELETE" + "FROM Stelle"
					+ " WHERE student = " + email + " AND id = " + id + ";";
			stmt.executeUpdate(strStelleLöschen);

			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("If did not exist: Table created successfully");
	}
}