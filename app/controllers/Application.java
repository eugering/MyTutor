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
	private static ArrayList<Student> tutoren = new ArrayList<Student>();
	static Student student;
	
	
	// Rendert die Registrierung Seite
		public static Result registrierung() {

			return ok(registrierung.render());
		}

		// Daten rauslesen
		public static Result addStudent() {

			Map<String, String[]> parameters = request().body().asFormUrlEncoded();
			// Parameteruebergaben werden ueberprueft
//			if (!(parameters != null && parameters.containsKey("vorname")
//					&& parameters.containsKey("nachname")
//					&& parameters.containsKey("email")
//					&& parameters.containsKey("password")
//					&& parameters.containsKey("passwordWiederholen")
//					&& parameters.containsKey("Studiengang")
//					)) {
//				Logger.warn("bad login request");
//				return redirect(routes.Application.addStudent());
//			}else{
			// Parameterwerte werden ausgelesen
			String vorname = parameters.get("vorname")[0];
			String nachname = parameters.get("nachname")[0];
			String email = parameters.get("mail")[0];
			String passwort = parameters.get("passwort")[0];
			String passwortWiederholen = parameters.get("passwortWiederholen")[0];
			String studiengang = parameters.get("form")[0];
			 student = new Student();
			 student.setVorname(vorname);
			 student.setNachname(nachname);
			 student.setPass(passwort);
			 student.setStudiengang(studiengang);
			 studenten.add(student);

			// passwort == passwortWiederhlen & regex fuer die mail
			if (passwort.equals(passwortWiederholen)) {
					// Daten befüllen
					Student student = new Student();
					student.setVorname(vorname);
					student.setNachname(nachname);
					student.setEmail(email);
					student.setPass(passwort);
					studenten.add(student);
					return ok(login.render());
			}else {
				return redirect(routes.Application.registrierung());	
			}
	}

		// Rendert die login Seite
		public static Result login() {
			return ok(login.render());
		}
	
	
	// Einloggen
	public static Result einloggen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
	
		String mail = parameters.get("mail")[0];
		
		//Student wird aus der ArrayList rausgefunden
		for (Student student : studenten) {
			if (mail.equals(student.getEmail())) {
				
				String vorname = null;
				String nachname = null;	
				String fakultaet = null;
				String studiengang =null;
				
					 vorname = student.getVorname();
					 nachname = student.getNachname();
					 mail = student.getEmail();
					 studiengang = student.getStudiengang();

				return ok(profilAnzeigen.render(vorname,nachname,mail,studiengang));
				
			}else{
				return redirect(routes.Application.login());	
			}
		}
		return redirect(routes.Application.login());
	}
	

	// POST empfangen :GET
	// mail ueberprufung ob es den Studenten schon git
//	public static Result checkStudent() {
//
//		// ueberprufung durch mail =>Daten werden geladen
//		String mail = null;
//		for (Student student : studenten) {
//			if (mail.equals(student.getEmail())) {
//				return ok(profilAnzeigen.render());
//			}else {
//				return redirect(@routes.Application.login());
//			}
//		}
//		return redirect(@routes.Application.login());
//	}



	
	// Rendert die ProfilAnzeigen Seite
	public static Result profilAnzeigen() {
		String vorname = null;
		String nachname = null;
		String mail = null;
		String studiengang = null;
		String fach = null;
		String stundenLohn = null;
		String bday = null;
		String tag = null;
		String zeiten = null;
		String fakultaet = null;
		
		for (Student student : studenten) {
		
			 vorname = student.getVorname();
			 nachname = student.getNachname();
			 mail = student.getEmail();
			 fach = student.getFach();
			 stundenLohn = student.getStundenLohn();
			 bday = student.getBday();
			 tag = student.getTag();
			 zeiten = student.getZeiten();
			 fakultaet=student.getFakultaet();
			 studiengang = student.getStudiengang();

			 
	}
		return ok(profilAnzeigen.render(vorname,nachname,mail,studiengang));
	}
	

	// Rendert die ProfilBearbeiten Seite
	public static Result profilBearbeiten() {
//		String mail = null;
//		String studiengang = null;
//		String fach = null;
//		String stundenLohn = null;
//		String bday = null;
//		String tag = null;
//		String zeiten = null;
//	
//		//Student wird identifiziert
//		for (Student student : studenten) {
//			if (mail.equals(student.getEmail())) {
//				return ok(profilBearbeiten.render());
//			}else{
//				return redirect("views.profilBearbeiten");
//			}
//				
//				
//				
//		}
		return ok(profilBearbeiten.render());
	}
		


	// Student attribute hinzugfügen
	// POST an ProfilAnzeigen
	// neue Kriterien werden unten angezeit
	

	// POST empfangen :GET
	// gebotenen Stellen (Löschen, Aendern?)
	// aendern => POST an ProfilAnzeigen

	// Rendert die TutorWerden Seite
	public static Result tutorWerden() {
		return ok(tutorWerden.render());
	}
	
//	public static Result addTutor() {
//		
//		String mail = null;
//		String studiengang = null;
//		String fach = null;
//		String stundenLohn = null;
//		String bday = null;
//		String tag = null;
//		String zeiten = null;
//		
//		//Student wird aus der ArrayList rausgefunden
//		for (Student student : studenten) {
//			if (mail.equals(student.getEmail())) {
//				
//				//Tutor Array hinzufuegen
//				tutoren.add(student);
//		}else {
//			return redirect("views.registrierung");
//		}
//		}
//		for (Student tutor : tutoren) {
//			if (mail.equals(tutor.getEmail())) {
//				
//				tutor = studenten.get(0);
//				tutor.setStudiengang(studiengang);
//				tutor.setFach(fach);
//				tutor.setStundenLohn(stundenLohn);
//				tutor.setBday(bday);
//				tutor.setTag(tag);
//				tutor.setZeiten(zeiten);
//
//				
//				return ok(profilAnzeigen.render());
//			}else {
//				return redirect("views.registrierung");
//			}
//		}
//	}	
			
	

	// Rendert die Suchen Seite
	public static Result suchen() {
		return ok(suchen.render());
		// POST kriterien =>per Ajax
		// SuchKriterien unten Anzeigen: verlinkung durch Button an die Profile
		
	}


	
	// Rendert die Home Seite
		public static Result home() {
			return ok(home.render());
		}

	// Rendert die UeberUns Seite
	public static Result ueberUns() {
		return ok(ueberUns.render());
	}

}
