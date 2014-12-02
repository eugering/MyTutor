package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {


    public static Result home() {
        return ok(home.render());
    }
	
	 public static Result login() {
        return ok(login.render());
    }
    
     public static Result profilAnzeigen() {
        return ok(profilAnzeigen.render());
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
