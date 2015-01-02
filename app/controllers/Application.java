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
				||parameters.get("nachname")[0].isEmpty()
				||parameters.get("mail")[0].isEmpty()
				||parameters.get("passwort")[0].isEmpty()
				||parameters.get("passwortWiederholen")[0].isEmpty()) {
			return redirect(routes.Application.registrierung());
		} else {
			// Parameterwerte werden ausgelesen
			String vorname = parameters.get("vorname")[0];
			String nachname = parameters.get("nachname")[0];
			String email = parameters.get("mail")[0];
			String passwort = parameters.get("passwort")[0];
			String passwortWiederholen = parameters.get("passwortWiederholen")[0];
			String studiengang = parameters.get("studiengang")[0];
			
			// Email ueberpruefung
			if (mailCheck(email)) {
				if (passwort.equals(passwortWiederholen)) {
					// Daten befüllen
					student = new Student(vorname, nachname, email, passwort, studiengang);
					temp = email;
					studenten.add(student);

					return ok(login.render());
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
				return ok(home.render());
			} else {
				return redirect(routes.Application.login());
			}
		}
		return redirect(routes.Application.login());
	}

	// Rendert die ProfilAnzeigen Seite
	public static Result profilAnzeigen() {
		for (Student student : studenten) {
			if (temp.equals(student.getEmail()))

				return ok(profilAnzeigen.render(student));
		}
		return redirect(routes.Application.registrierung());
	}

	// Rendert die ProfilBearbeiten Seite
	public static Result profilBearbeiten() {
		for (Student student : studenten) {
			if (temp.equals(student.getEmail()))

				return ok(profilBearbeiten.render(student));
		}
		return ok(profilBearbeiten.render(student));

	}

	// ProfilBearbeiten
	public static Result bearbeiten() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		for (Student student : studenten) {
			if (temp.equals(student.getEmail())) {
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

	// Student wird zum Tutor
	public static Result addTutor() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		for (Student student : studenten) {
			if (temp.equals(student.getEmail())) {
				// Parameterwerte werden ausgelesen
				String bday = parameters.get("bday")[0];
				String studiengang = parameters.get("studiengang")[0];
				String infos = parameters.get("textField")[0];

				return ok(profilAnzeigen.render(student));
			}
		}
		return redirect(routes.Application.tutorWerden());
	}

	// TutorenStelle Löschen
	public static Result stelleLoeschen() {
		for (Student student : studenten) {
			if (temp.equals(student.getEmail())) {
			
				
				return ok(profilAnzeigen.render(student));
			} 
		}
		return TODO;
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
	public static boolean mailCheck(String email) {
		return email.matches("\\w*\\-*\\w*\\.*\\w*@\\D+\\w*\\-?\\w*\\.*\\w*\\-*\\w*\\.(de|info|org|com|net)");
	}
	
	public static Result stelleAnbieten() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		// Parameteruebergaben werden ueberprueft
		if (parameters.get("fach")[0].isEmpty()
				||parameters.get("tag")[0].isEmpty()
				||parameters.get("zeit")[0].isEmpty()
				||parameters.get("geld")[0].isEmpty()) {
			return redirect(routes.Application.tutorWerden());
		} else {
			// Parameterwerte werden ausgelesen
			String fach = parameters.get("fach")[0];
			String tag = parameters.get("tag")[0];
			String zeit = parameters.get("zeit")[0];
			String geld = parameters.get("geld")[0];
		
			for (Student student : studenten) {
				if (temp.equals(student.getEmail())) {
					student.addStelle(new Stelle(fach, tag, zeit, geld));
					
					return ok(profilAnzeigen.render(student));
				} 
			}

				return redirect(routes.Application.tutorWerden());
		
			}
		}

}
