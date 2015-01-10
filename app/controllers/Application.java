package controllers;


import java.lang.reflect.Array;
import java.util.Map;

import models.*;
import play.mvc.*;
import views.html.*;

public class Application extends Controller {

	static JDBC db = new JDBC();

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
				|| parameters.get("pass")[0].isEmpty()
				|| parameters.get("passwortWiederholen")[0].isEmpty()) {
			return redirect(routes.Application.registrierung());
		} else {
			String[] student = db.studentSuchen(parameters.get("mail")[0]);
			System.out.println(student [3]);
			String a = student[3];
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4), bday(5),
			// infos(6), bild(7)
			if (!(parameters.get("mail")[0].equals(a))) {
				if (mailCheck(parameters.get("mail")[0])) {
					if (parameters.get("pass")[0].equals(parameters
							.get("passwortWiederholen")[0])) {
						// Daten befüllen
						String vorname = parameters.get("vorname")[0];
						String nachname = parameters.get("nachname")[0];
						String pass = parameters.get("pass")[0];
						String email = parameters.get("mail")[0];
						String studiengang = parameters.get("studiengang")[0];

						db.insertIntoStudent(vorname, nachname, pass, email,
								studiengang);

						// SessionCheck
						String user = session("connected");
						if (sessionCheck(user)) {
							return ok(login.render());
						} else {
							return redirect(routes.Application.login());
						}

					} else {
						return redirect(routes.Application.registrierung());
					}

				} else {
					return redirect(routes.Application.registrierung());
				}
			}else {
				System.out.println("!!!!!!!!!!!!!!!!!!!!");
				return redirect(routes.Application.registrierung());
			}
			

			
			
		}
	}

	// Rendert die login Seite
	public static Result login() {
		// SessionDiscard
		session().clear();
		// db.dropTable();
		// db.createTable();
		// db.insertInto();

		return ok(login.render());
	}

	// Einloggen
	public static Result einloggen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();

		// Student wird in der Datenbank gesucht
		// Schema vorname(0), nachname(1), pass(2), email(3), sg(4), bday(5),
		// infos(6), bild(7)
		String[] student = db.studentSuchen(parameters.get("mail")[0]);
		if (parameters.get("pass")[0].equals(student[2])) {
			session("connected", parameters.get("mail")[0]);
			System.out.println("Eingeloggt!");
			return ok(home.render());
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die ProfilAnzeigen Seite
	public static Result profilAnzeigen() {
		String user = session("connected");
		// SessionCheck
		if (sessionCheck(user)) {
			String[] student= db.studentSuchen(user);
			
			return ok(profilAnzeigen.render(student[0], student[1], student[5],
					student[3], student[4], student[6]));
		} else {
			return redirect(routes.Application.login());
		}

	}

	// Rendert die ProfilBearbeiten Seite
	public static Result profilBearbeiten() {
		String user = session("connected");
		if (sessionCheck(user)) {
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4),
			// bday(5), infos(6), bild(7)
			String[] student = new String[8];
			student = db.studentSuchen(user);

			return ok(profilBearbeiten.render(student[0], student[1], student[5],
					student[3], student[4], student[6]));

		} else {
			return redirect(routes.Application.login());
		}
	}

	// ProfilBearbeiten
	public static Result bearbeiten() {

		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		String user = session("connected");

		if (sessionCheck(user)) {

			System.out.println("session checked");

			if (parameters.get("vorname")[0] != "") {
				System.out.println(" versuch vorname geändert");
				db.studentÄndern(user, "vorname", parameters.get("vorname")[0]);
				System.out.println("vorname geändert");

			}
			if (parameters.get("nachname")[0] != "") {
				db.studentÄndern(user, "nachname",
						parameters.get("nachname")[0]);
				System.out.println("nachname geändert");

			}
			if (parameters.get("bday")[0] != "") {
				db.studentÄndern(user, "bday", parameters.get("bday")[0]);
				System.out.println("bday geändert");

			}
			if (parameters.get("studiengang")[0] != "") {
				System.out.println("versuch sg zu ändern");
				db.studentÄndern(user, "studiengang",
						parameters.get("studiengang")[0]);
				System.out.println("sg geändert");

			}
			if (parameters.get("infos")[0] != "") {
				db.studentÄndern(user, "infos", parameters.get("infos")[0]);
				System.out.println("infos geändert");

			}
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4),
						// bday(5), infos(6), bild(7)
						String[] student = new String[8];
						student = db.studentSuchen(user);
			
				return ok(profilAnzeigen.render(student[0], student[1], student[5],
						student[3], student[4], student[6]));
		}
		return redirect(routes.Application.login());

	}

	// Rendert die TutorWerden Seite
	public static Result tutorWerden() {
		// SessionCheck
		String user = session("connected");
		if (sessionCheck(user)) {
			return ok(tutorWerden.render());
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die Suchen Seite
	public static Result suchen() {
		String user = session("connected");
		if (sessionCheck(user)) {
			return ok(suchen.render());
		} else {
			return redirect(routes.Application.login());
		}
		// POST kriterien =>per Ajax
		// SuchKriterien unten Anzeigen: verlinkung durch Button an die Profile

	}

	// Rendert die Home Seite
	public static Result home() {
		// SessionCheck
		String user = session("connected");
		if (sessionCheck(user)) {
			return ok(home.render());
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die UeberUns Seite
	public static Result ueberUns() {
		// SessionCheck
		String user = session("connected");
		if (sessionCheck(user)) {
			return ok(ueberUns.render());
		} else {
			return redirect(routes.Application.login());
		}
	}

	// SessionCheck
	public static boolean sessionCheck(String s) {
		if (s != null) {
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
				|| parameters.get("stundenlohn")[0].isEmpty()) {
			return redirect(routes.Application.tutorWerden());
		} else {
			// Parameterwerte werden ausgelesen
			String user = session("connected");
			if (sessionCheck(user)) {
				db.insertIntoStelle(user, parameters.get("fach")[0],
						parameters.get("tag")[0], parameters.get("zeit")[0],
						parameters.get("stundenlohn")[0]);
				// Schema vorname(0), nachname(1), pass(2), email(3), sg(4),
				// bday(5), infos(6), bild(7)
				String[] student = new String[8];
				student = db.studentSuchen(user);
				

				return ok(profilAnzeigen.render(student[0], student[1], student[5],
						student[3], student[4], student[6]));
				
			} else {
				return redirect(routes.Application.login());
			}
		}
	}

	public static Result stelleLoeschen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		String user = session("connected");
		if (sessionCheck(user)) {
			db.stelleLöschen(user,
					Integer.parseInt(parameters.get("stelle")[0]));
		} else {
			return redirect(routes.Application.login());
		}
		return redirect(routes.Application.login());

	}

}
