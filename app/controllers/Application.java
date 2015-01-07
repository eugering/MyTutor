package controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import ch.qos.logback.core.joran.conditional.ElseAction;
import models.*;
import play.*;
import play.api.data.Form;
import play.mvc.*;
import play.twirl.api.Html;
import views.html.*;
import models.Student;

public class Application extends Controller {

	private static ArrayList<Student> studenten = new ArrayList<Student>();
	static Student student;
	static String temp = "";

	// Rendert die Registrierung Seite
	public static Result registrierung() {
		return ok(registrierung.render());
	}

	// Daten rauslesen
	public static Result addStudent() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		// Parameteruebergaben werden ueberprueft
		if (parameters.get("vorname")[0].isEmpty()
				|| parameters.get("nachname")[0].isEmpty()
				|| parameters.get("mail")[0].isEmpty()
				|| parameters.get("passwort")[0].isEmpty()
				|| parameters.get("passwortWiederholen")[0].isEmpty()) {
			return redirect(routes.Application.registrierung());
		} else {
			// Parameterwerte werden ausgelesen
			// Email ueberpruefung
			if (mailCheck(parameters.get("mail")[0])) {
				if (parameters.get("passwort")[0].equals(parameters
						.get("passwortWiederholen")[0])) {
					// Daten befüllen
					student = new Student(parameters.get("vorname")[0],
							parameters.get("nachname")[0],
							parameters.get("mail")[0],
							parameters.get("passwortWiederholen")[0],
							parameters.get("studiengang")[0]);
					temp = parameters.get("mail")[0];
					studenten.add(student);
					
					//SessionCheck
					String user = session("connected"); 
					if (sessionCheck(user)) {
						return ok(login.render());
						}else{
						return redirect(routes.Application.login());
					}
					
				} else {
					return redirect(routes.Application.registrierung());
				}

			} else {
				return redirect(routes.Application.registrierung());
			}
		}
	}

	// Rendert die login Seite
	public static Result login() {
		//SessionDiscard
		session().clear();
		return ok(login.render());
	}

	// Einloggen
	public static Result einloggen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();

		String email = parameters.get("mail")[0];
		String passwort = parameters.get("passwort")[0];

		// Student wird in der ArrayList gesucht
		for (Student student : studenten) {
			if (email.equals(student.getEmail())
					&& student.getPass().equals(passwort)) {
				session("connected", email);
				return ok(home.render());
			} else {
				return redirect(routes.Application.login());
			}
		}
		return redirect(routes.Application.login());
	}

	// Rendert die ProfilAnzeigen Seite
	public static Result profilAnzeigen() {
		String user = session("connected"); 

		for (Student student : studenten) {
			if (temp.equals(student.getEmail()))
				//SessionCheck
				if (sessionCheck(user)) {
					return ok(profilAnzeigen.render(student));
					}else{
					return redirect(routes.Application.login());
				}	
		}
		return redirect(routes.Application.login());
	}

	// Rendert die ProfilBearbeiten Seite
	public static Result profilBearbeiten() {
		String user = session("connected"); 
		for (Student student : studenten) {
			if (temp.equals(student.getEmail()))
				//SessionCheck
				if (sessionCheck(user)) {
					return ok(profilBearbeiten.render(student));
					}else{
					return redirect(routes.Application.login());
				}
		}
		return redirect(routes.Application.login());

	}

	// ProfilBearbeiten
	public static Result bearbeiten() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		for (Student student : studenten) {
			if (temp.equals(student.getEmail())) {
				// Parameterwerte werden ausgelesen

				if (!(parameters.get("vorname")[0].isEmpty())) {
					student.setVorname(parameters.get("vorname")[0]);
				}
				if (!(parameters.get("nachname")[0].isEmpty())) {
					student.setNachname(parameters.get("nachname")[0]);
				}
				if (!(parameters.get("bday")[0].isEmpty())) {
					student.setBday(parameters.get("bday")[0]);
				}
				if (!(parameters.get("studiengang")[0].isEmpty())) {
					student.setStudiengang(parameters.get("studiengang")[0]);
				}
				if (!(parameters.get("infos")[0].isEmpty())) {
					student.setInfos(parameters.get("infos")[0]);
				}
				//SessionCheck
				String user = session("connected"); 
				if (sessionCheck(user)) {
					return ok(profilAnzeigen.render(student));
					}else{
					return redirect(routes.Application.login());
				}
			}
		}
		return redirect(routes.Application.login());
	}


	// Rendert die TutorWerden Seite
	public static Result tutorWerden() {
		//SessionCheck
		String user = session("connected"); 
		if (sessionCheck(user)) {
			return ok(tutorWerden.render());
			}else{
			return redirect(routes.Application.login());
		}
	}


	// Rendert die Suchen Seite
	public static Result suchen() {
		String user = session("connected"); 
		if (sessionCheck(user)) {
			return ok(suchen.render());
			}else{
			return redirect(routes.Application.login());
		}
		// POST kriterien =>per Ajax
		// SuchKriterien unten Anzeigen: verlinkung durch Button an die Profile

	}

	// Rendert die Home Seite
	public static Result home() {
		//SessionCheck
		String user = session("connected"); 
		if (sessionCheck(user)) {
			return ok(home.render());
		}else{
			return redirect(routes.Application.login());
		}
	}

	// Rendert die UeberUns Seite
		public static Result ueberUns() {
			//SessionCheck
			String user = session("connected"); 
			if (sessionCheck(user)) {
				return ok(ueberUns.render());
			}else{
				return redirect(routes.Application.login());
			}
		}

		//SessionCheck
		public static boolean sessionCheck(String s){
			if(s != null) {
				return true;
			     } else {
			    	 return false;
			     }
		}
	public static boolean mailCheck(String email) {
		return email
				.matches("\\w*\\-*\\w*\\.*\\w*@\\D+\\w*\\-?\\w*\\.*\\w*\\-*\\w*\\.(de|info|org|com|net)");
	}

	public static Result stelleAnbieten() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		// Parameteruebergaben werden ueberprueft
		if (parameters.get("fach")[0].isEmpty()
				|| parameters.get("tag")[0].isEmpty()
				|| parameters.get("zeit")[0].isEmpty()
				|| parameters.get("geld")[0].isEmpty()) {
			return redirect(routes.Application.tutorWerden());
		} else {
			// Parameterwerte werden ausgelesen

			for (Student student : studenten) {
				if (temp.equals(student.getEmail())) {
					student.getStellen().put(student.getCountStellen(), new Stelle(parameters.get("fach")[0],
							parameters.get("tag")[0],
							parameters.get("zeit")[0],
							parameters.get("geld")[0]));
					student.setCountStellen(student.getCountStellen()+1);
					
					//SessionCheck
					String user = session("connected"); 
					if (sessionCheck(user)) {
						return ok(profilAnzeigen.render(student));
					}else{
						return redirect(routes.Application.login());
					}
				}
			}

			return redirect(routes.Application.tutorWerden());

		}
	}
	
	public static Result stelleLoeschen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		for (Student student : studenten) {
			if (temp.equals(student.getEmail())){
				
				student.getStellen().remove(Integer.parseInt(parameters.get("stelle")[0]));
			}
				
				}
					//SessionCheck
					String user = session("connected"); 
					if (sessionCheck(user)) {
						return ok(profilAnzeigen.render(student));
						}else{
						return redirect(routes.Application.login());
					}
		}
		

}
