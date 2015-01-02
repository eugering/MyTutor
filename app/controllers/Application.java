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
	static String temp="";
	
	
	// Rendert die Registrierung Seite
		public static Result registrierung() {

			return ok(registrierung.render());
		}

		// Daten rauslesen
		public static Result addStudent() {
		
			Map<String, String[]> parameters = request().body().asFormUrlEncoded();
			// Parameteruebergaben werden ueberprueft
			if ((parameters != null && parameters.containsKey("vorname")
					&& parameters.containsKey("nachname")
					&& parameters.containsKey("email")
					&& parameters.containsKey("password")
					&& parameters.containsKey("passwordWiederholen")
					&& parameters.containsKey("Studiengang")
					)) {
				
				return redirect(routes.Application.registrierung());
			}else{
			// Parameterwerte werden ausgelesen
			String vorname = parameters.get("vorname")[0];
			String nachname = parameters.get("nachname")[0];
			String email = parameters.get("mail")[0];
			String passwort = parameters.get("passwort")[0];
			String passwortWiederholen = parameters.get("passwortWiederholen")[0];
			 student = new Student();
			 

			 //Email ueberpruefung
			 if (student.mailCheck(email)) {
				 if (passwort.equals(passwortWiederholen)) {
						// Daten befüllen
						student.setVorname(vorname);
						student.setNachname(nachname);
						student.setEmail(email);
						temp=email;
						student.setPass(passwort);
						studenten.add(student);
						
						
						return ok(login.render());
				}else {
					return redirect(routes.Application.registrierung());	
				}

			}else {
				return redirect(routes.Application.registrierung());	
			}			
		}
	}
			
			

		// Rendert die login Seite
		public static Result login() {
			return ok(login.render());
		}
	
	
	// Einloggen
	public static Result einloggen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
	
		String email = parameters.get("mail")[0];
		String passwort = parameters.get("passwort")[0];

		
		//Student wird aus der ArrayList rausgefunden
		for (Student student : studenten) {
			if (email.equals(student.getEmail())&&student.getPass().equals(passwort)) {
					 student=student;
					 
				return ok(profilAnzeigen.render(student));
			}else{
				return redirect(routes.Application.login());	
			}
		}
		return redirect(routes.Application.login());
	}

	
	// Rendert die ProfilAnzeigen Seite
	public static Result profilAnzeigen() {
		if (temp.equals(student.getEmail())) 
			 student=student;
		return ok(profilAnzeigen.render(student));
	}
	

	// Rendert die ProfilBearbeiten Seite
	public static Result profilBearbeiten() {
		
			return ok(profilBearbeiten.render());
		
}
		
	//ProfilBearbeiten
	public static Result bearbeiten(){

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
			for (Student student : studenten) {
				if (temp.equals(student.getEmail())) {
						 student=student;
		// Parameterwerte werden ausgelesen
		String bday = parameters.get("bday")[0];
		String studiengang = parameters.get("studiengang")[0];
		String infos = parameters.get("textField")[0];
		 student.setBday(bday);
		 student.setStudiengang(studiengang);
		 student.setInfos(infos);
		 return ok(profilAnzeigen.render(student));		
			}
		}
		return redirect(routes.Application.profilBearbeiten());
	}

	// POST empfangen :GET
	// gebotenen Stellen (Löschen, Aendern?)
	// aendern => POST an ProfilAnzeigen
	
	// Rendert die TutorWerden Seite
	public static Result tutorWerden() {
		return ok(tutorWerden.render());
	}
	
	//Student wird zum Tutor
	public static Result addTutor() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
			for (Student student : studenten) {
				if (temp.equals(student.getEmail())) {
						 student=student;
		// Parameterwerte werden ausgelesen
		String bday = parameters.get("bday")[0];
		String studiengang = parameters.get("studiengang")[0];
		String infos = parameters.get("textField")[0];
		
		 return ok(profilAnzeigen.render(student));		
			}
		}
		return redirect(routes.Application.tutorWerden());
	}	
			
	
	//TutorenStelle Löschen
	public static Result stelleLoeschen() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
			for (Student student : studenten) {
				if (temp.equals(student.getEmail())) {
						 student=student;
		// Parameterwerte werden ausgelesen
		String bday = parameters.get("bday")[0];

		
		 return ok(profilAnzeigen.render(student));		
			}
		}
		return redirect(routes.Application.tutorWerden());
	}	
	

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
