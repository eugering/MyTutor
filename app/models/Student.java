package models;

public class Student  {
	// Profilbild

	public String vorname;
	private String nachname;
	private String pass;
	private String passWiederholung;
	private String email;
	
	//ProfilBearbeiten
	private String studiengang = "";
	private String bday="";
	private String infos="";
	
	//Tutor
	private String fach="";
	private String tag="";
	private String zeiten="";
	private String stundenLohn="";

	public Student() {
		super();
	}

	public boolean mailCheck(String email) {
		return email.matches("\\w*\\-*\\w*\\.*\\w*@\\D+\\w*\\-?\\w*\\.*\\w*\\-*\\w*\\.(de|info|org|com|net)");
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

	public String getFach() {
			return fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

	public String getStundenLohn() {
			return stundenLohn;
	}

	public void setStundenLohn(String stundenLohn) {
		this.stundenLohn = stundenLohn;
	}

	public String getTag() {
			return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getZeiten() {
			return zeiten;
	}

	public void setZeiten(String zeiten) {
		this.zeiten = zeiten;
	}

	public String getInfos() {
		return infos;
	}

	public void setInfos(String infos) {
		this.infos = infos;
	}
	

}
