package models;

public class Stelle {
	private String fach;
	private String tag;
	private String zeiten;
	private String stundenLohn; 
	
	public Stelle(String fach, String tag, String zeiten, String stundenLohn) {
		this.fach = fach;
		this.tag = tag;
		this.zeiten = zeiten;
		this.stundenLohn = stundenLohn;
	}

	public String getFach() {
		return fach;
	}

	public void setFach(String fach) {
		this.fach = fach;
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

	public String getStundenLohn() {
		return stundenLohn;
	}

	public void setStundenLohn(String stundenLohn) {
		this.stundenLohn = stundenLohn;
	}
	
	
}
