package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {
    
    
    private static String vorname = "Melissa";
    private static String nachname = "Ries";
    private static String bday = "20.08.1992";
    private static String passwort = "12345";
    private static String email = "melisa@ries.com";

    public static Result home() {
        return ok(home.render());
    }
	
	 public static Result login() {
        return ok(login.render());
    }
    
     public static Result profilAnzeigen() {
        return ok(profilAnzeigen.render(vorname, nachname, bday, email));
    }
    
     public static Result profilBearbeiten() {
        return ok(profilBearbeiten.render());
    }
    
     public static Result registrierung() {
        return ok(registrierung.render());
    }
    
     public static Result tutorWerden() {
        return ok(tutorWerden.render());
    }
    
     public static Result suchen() {
        return ok(suchen.render());
    }
    
     public static Result ueberUns() {
        return ok(ueberUns.render());
    }
    
        


    
}
