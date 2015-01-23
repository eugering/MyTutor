package controllers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JsonNode;

import models.*;
import play.libs.F.Callback;
import play.libs.F.Callback0;
import play.mvc.*;
import views.html.*;


public class Application extends Controller {

	static JDBC db = new JDBC();
	public static List<Stelle> stellen = new LinkedList<Stelle>();
	static String [] neusterTutor = null;

	public static List<Stelle> getStellen() {
		return stellen;
	}

	// Rendert die Registrierung Seite
	public static Result registrierung(String meldung) {
		return ok(registrierung.render(meldung));
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
			return redirect(routes.Application
					.registrierung("Bitte alle Felder ausfüllen!"));
		} else {
			String[] student = db.studentSuchen(parameters.get("mail")[0]);
			String a = student[3];
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4),
			// bday(5),
			// infos(6), bild(7)
			if (!(parameters.get("mail")[0].equals(a))) {
				if (mailCheck(parameters.get("mail")[0])) {
					if (parameters.get("pass")[0].equals(parameters
							.get("passwortWiederholen")[0])) {
                        //passwort Hashen
                        String hashPass = getSecurePassword(parameters.get("pass")[0]);
                        String vorname = parameters.get("vorname")[0];
                        String nachname = parameters.get("nachname")[0];
                        String pass = hashPass;
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
						return redirect(routes.Application
								.registrierung("Passwörter stimmen nicht überein!"));
					}

				} else {
					return redirect(routes.Application
							.registrierung("Bitte gebe eine gültige Email-Adresse ein!"));
				}
			} else {
				return redirect(routes.Application
						.registrierung("Mit dieser E-Mail hat sich schon jemand registriert!"));
			}

		}
	}

	// Rendert die login Seite
	public static Result login() {
		// SessionDiscard
		stellen.clear();
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
		if ((getSecurePassword(parameters.get("pass")[0])).equals((student[2]))) {
			session("connected", parameters.get("mail")[0]);
			neusterTutor = db.neustenTutorSuchen();
			return ok(home.render(student[0], student[1], neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die ProfilAnzeigen Seite
	public static Result profilAnzeigen() {
		String user = session("connected");
		// SessionCheck
		if (sessionCheck(user)) {
			String[] student = db.studentSuchen(user);
			db.stellenSuchen(user);
			neusterTutor = db.neustenTutorSuchen();
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4), bday(5), infos(6), bild(7)
			return ok(profilAnzeigen.render(student[0], student[1], student[5],
					student[3], student[4], student[6], stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
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
			String[] student = db.studentSuchen(user);
			db.stellenSuchen(user);
			neusterTutor = db.neustenTutorSuchen();
			return ok(profilBearbeiten.render(student[0], student[1],
					student[5], student[3], student[4], student[6], stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));

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
				db.studentÄndern(user, "vorname", parameters.get("vorname")[0]);

			}
			if (parameters.get("nachname")[0] != "") {
				db.studentÄndern(user, "nachname",
						parameters.get("nachname")[0]);

			}
			if (parameters.get("bday")[0] != "") {
				db.studentÄndern(user, "bday", parameters.get("bday")[0]);

			}
			if (parameters.get("studiengang")[0] != "") {
				db.studentÄndern(user, "studiengang",
						parameters.get("studiengang")[0]);

			}
			if (parameters.get("infos")[0] != "") {
				db.studentÄndern(user, "infos", parameters.get("infos")[0]);

			}
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4),
			// bday(5), infos(6), bild(7)
			String[] student = db.studentSuchen(user);
			db.stellenSuchen(user);
			neusterTutor = db.neustenTutorSuchen();
			return ok(profilAnzeigen.render(student[0], student[1], student[5],
					student[3], student[4], student[6], stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		}
		return redirect(routes.Application.login());

	}

	// Upload
	public static Result upload() {
		String user = session("connected");
		// SessionCheck
		if (sessionCheck(user)) {
			String[] student = db.studentSuchen(user);
			db.stellenSuchen(user);
			neusterTutor = db.neustenTutorSuchen();
			return ok(profilAnzeigen.render(student[0], student[1], student[5],
					student[3], student[4], student[6], stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		} else {
			return redirect(routes.Application.login());
		}

	}

	// Rendert die Suchen Seite
	public static Result suchen() {
		String user = session("connected");
		if (sessionCheck(user)) {
			stellen.clear();
			neusterTutor = db.neustenTutorSuchen();
			return ok(suchen.render(stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die TutorWerden Seite
	public static Result tutorWerden() {
		// SessionCheck
		String user = session("connected");
		if (sessionCheck(user)) {
			neusterTutor = db.neustenTutorSuchen();
			return ok(tutorWerden.render(neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die Profilseite des Tutors
	public static Result tutorProfil() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		// Parameteruebergaben werden ueberprueft
		if (parameters.get("id")[0].isEmpty()) {
			return redirect(routes.Application.suchen());
		} else {
			String[] student = db.tutorSuchen(parameters.get("id")[0]);
			db.stellenSuchen(student[3]);
			// Schema vorname(0), nachname(1), pass(2), email(3), sg(4),
			// bday(5),
			// infos(6), bild(7)
			neusterTutor = db.neustenTutorSuchen();
			return ok(profil.render(student[0], student[1], student[5],
					student[3], student[4], student[6], stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		}
	}

	// Rendert die Suchen Seite
	public static Result tutorSuchen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		String user = session("connected");
		if (sessionCheck(user)) {
			// Beide leer
			if (parameters.get("studiengang")[0].equals("-Tutoren im Bereich-")
					&& parameters.get("fach")[0].equals("")) {
				db.alleStellenSuchen();
			} else {
				// sg leer fach voll
				if (parameters.get("studiengang")[0]
						.equals("-Tutoren im Bereich-")
						&& (!(parameters.get("fach")[0].equals("")))) {
					db.alleStellenSuchen(parameters.get("fach")[0]);
				} else {
					// sg voll fach leer
					if (!(parameters.get("studiengang")[0]
							.equals("-Tutoren im Bereich-"))
							&& parameters.get("fach")[0].equals("")) {
						db.alleStellenSuchenOhneFach(parameters
								.get("studiengang")[0]);
					} else {
						db.alleStellenSuchen(parameters.get("studiengang")[0],
								parameters.get("fach")[0]);
					}

				}

			}
			neusterTutor = db.neustenTutorSuchen();
			return ok(suchen.render(stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
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
			String[] student = new String[8];
			student = db.studentSuchen(user);	
			neusterTutor = db.neustenTutorSuchen();
			return ok(home.render(student[0], student[1], neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		} else {
			return redirect(routes.Application.login());
		}
	}

	// Rendert die UeberUns Seite
	public static Result ueberUns() {
		// SessionCheck
		String user = session("connected");
		if (sessionCheck(user)) {
			neusterTutor = db.neustenTutorSuchen();
			return ok(ueberUns.render(neusterTutor[0], neusterTutor[1], neusterTutor[2]));
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

	// mailCheck
	public static boolean mailCheck(String email) {
		return email
				.matches("\\w*\\-*\\w*\\.*\\w*@\\D+\\w*\\-?\\w*\\.*\\w*\\-*\\w*\\.(de|info|org|com|net)");
	}

	// stelleAnbieten
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
				db.stellenSuchen(user);

				return redirect(routes.Application.home());

			} else {
				return redirect(routes.Application.login());
			}
		}
	}

	// stelleLoeschen
	public static Result stelleLoeschen() {
		Map<String, String[]> parameters = request().body().asFormUrlEncoded();
		String user = session("connected");
		if (sessionCheck(user)) {
			db.stelleLöschen(user, Integer.parseInt(parameters.get("id")[0]));
			String[] student = db.studentSuchen(user);
			db.stellenSuchen(user);
			neusterTutor = db.neustenTutorSuchen();
			return ok(profilBearbeiten.render(student[0], student[1],
					student[5], student[3], student[4], student[6], stellen, neusterTutor[0], neusterTutor[1], neusterTutor[2]));
		} else {
			return redirect(routes.Application.login());
		}

	}

	public static WebSocket<JsonNode> neusterTutor() throws SQLException{
		WebSocket<JsonNode> ws = null;
			ws = new WebSocket<JsonNode>() {
				public void onReady(WebSocket.In<JsonNode> in, final WebSocket.Out<JsonNode> out) {
					final MyObserver obs = new MyObserver();
					System.out.println("++++++++++  angelegt " + obs);
					obs.stelle = out;
					
					
					in.onMessage(new Callback<JsonNode>() {
						@Override
						public void invoke(JsonNode event) throws Throwable {
							System.out.println("Versuch json auszugeben");
							db.erzeugeJSON();
						}
					});
					in.onClose(new Callback0() {
						public void invoke() {
							
							db.deleteObserver(obs);
							System.out.println("Disconnected!");
						}
					});
				}
			};
		
		return ws;
	}
    
    
<<<<<<< HEAD
	// Methode für das Passwort-Hashing
    public static String getSecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
       try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

=======
    // Methode für das Passwort-Hashing
public static String getSecurePassword(String passwordToHash){
                String generatedPassword = null;
               try {
                        // Create MessageDigest instance for MD5
                        MessageDigest md = MessageDigest.getInstance("MD5");
                        //Add password bytes to digest
                        md.update(passwordToHash.getBytes());
                        //Get the hash's bytes
                        byte[] bytes = md.digest();
                        //This bytes[] has bytes in decimal format
                        //Convert it to hexadecimal format
                        StringBuilder sb = new StringBuilder();
                        for(int i=0; i< bytes.length ;i++){
                                 sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                                }
                        //Get complete hashed password in hex format
                        generatedPassword = sb.toString();
                    }
                catch (NoSuchAlgorithmException e)
                {
                        e.printStackTrace();
                    }
                return generatedPassword;
            }
>>>>>>> 404950c82a8aa17a84f43c589349dfa1b30cf701
	

}
