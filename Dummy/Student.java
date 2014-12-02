package webTech;

public class Student {
	private String Vornname;
	private String Nachname;
	private String passwort;
	private String Studiengang;

	public Student(String vornname, String nachname, String passwort,
			String studiengang) {
		super();
		Vornname = vornname;
		Nachname = nachname;
		this.passwort = passwort;
		Studiengang = studiengang;
	}

	public String getVornname() {
		return Vornname;
	}

	public void setVornname(String vornname) {
		Vornname = vornname;
	}

	public String getNachname() {
		return Nachname;
	}

	public void setNachname(String nachname) {
		Nachname = nachname;
	}

	public String getPasswort() {
		return passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getStudiengang() {
		return Studiengang;
	}

	public void setStudiengang(String studiengang) {
		Studiengang = studiengang;
	}

}
