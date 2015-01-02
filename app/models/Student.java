package models;

import java.util.*;
import java.util.LinkedList;
import java.util.List;

public class Student  {
	
	// Profilbild
	public String vorname;
	private String nachname;
	private String pass;
	private String email;
	
	//ProfilBearbeiten
	private String studiengang;
	private String bday="---";
	private String infos="---";
	
	//Tutor
	private List<Stelle> stellen = new LinkedList<Stelle>();
	
	

	public Student(String vorname, String nachname, String email, String pass, String studiengang) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = email;
		this.pass = pass;
		this.studiengang=studiengang;
		
	}


	
	public String getVorname() {
			return vorname;
			
			
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
			return nachname;
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPass() {
			return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
			return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBday() {
			return bday;
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getStudiengang() {
			return studiengang;
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	
	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}



	public List<Stelle> getStellen() {
		return stellen;
	}



	

}
