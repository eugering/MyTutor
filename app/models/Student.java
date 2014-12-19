package models;

public class Student  {
	// Profilbild

	public String vorname;
	private String nachname;
	private String pass;
	private String passWiederholung;
	private String email;

	private String studiengang;
	private String fach;
	private String fakultaet;
	private String stundenLohn;
	private String bday;
	private String tag;
	private String zeiten;

	public Student() {
		super();
	}

	public boolean mailCheck(String email) {
		return email.matches("\\w*\\-*\\w*\\.*\\w*@\\D+\\w*\\-?\\w*\\.*\\w*\\-*\\w*\\.(de|info|org|com|net)");
	}
	
	public String getVorname() {
		if (!(vorname.equals(null))) {
			return vorname;
		} else {
			return "";
		}
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public String getNachname() {
		if (!(nachname.equals(null))) {
			return nachname;
		} else {
			return "";
		}
	}

	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	public String getPass() {
		if (!(pass.equals(null))) {
			return pass;
		}
		return "";
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getEmail() {
		if (!(email.equals(null))) {
			return email;
		} else {
			return "";
		}

	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBday() {
		if (!(bday.equals(null))) {
			return bday;
		} else {
			return "";
		}
	}

	public void setBday(String bday) {
		this.bday = bday;
	}

	public String getStudiengang() {
		if (!(studiengang.equals(null))) {
			return studiengang;
		} else {
			return "";
		}
	}

	public void setStudiengang(String studiengang) {
		this.studiengang = studiengang;
	}

	public String getFach() {
		if (!(fach.equals(null))) {
			return fach;
		} else {
			return "";
		}
	}

	public void setFach(String fach) {
		this.fach = fach;
	}

	public String getStundenLohn() {
		if (!(stundenLohn.equals(null))) {
			return stundenLohn;
		} else {
			return "";
		}

	}

	public void setStundenLohn(String stundenLohn) {
		this.stundenLohn = stundenLohn;
	}

	public String getTag() {
		if (!(tag.equals(null))) {
			return tag;
		} else {
			return "";
		}
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getZeiten() {
		if (!(zeiten.equals(null))) {
			return zeiten;
		} else {
			return "";
		}
	}

	public void setZeiten(String zeiten) {
		this.zeiten = zeiten;
	}

	public String getFakultaet() {
		return fakultaet;
	}

	public void setFakultaet(String fakultaet) {
		this.fakultaet = fakultaet;
	}
	

}
