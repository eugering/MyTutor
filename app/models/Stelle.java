package models;

public class Stelle {
	private String name;
	private String fach;
	private String tag;
	private String zeit;
	private String stundenlohn; 
	private String id;
	
	
	public Stelle(String name, String fach, String tag, String zeit, String stundenlohn, String id) {
		this.name = name;
		this.fach = fach;
		this.tag = tag;
		this.zeit = zeit;
		this.stundenlohn = stundenlohn;
		this.id = id;
		
	}

	public String getFach() {
		return fach;
	}

	public String getTag() {
		return tag;
	}


	public String getZeit() {
		return zeit;
	}

	public String getStundenlohn() {
		return stundenlohn;
	}	
	
	public String getID() {
		return id;
	}

	public String getName() {
		return name;
	}
	
}
