
package controllers;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import controllers.Application;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import play.mvc.WebSocket;

public class MyObserver implements Observer {
	Object varNull = null;
	public WebSocket.Out<JsonNode> stelle;
	
	public MyObserver(){
		Application.db.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object varNull) {
		System.out.println("Observer erneuert");
		stelle.write(Application.db.erzeugeJSON());
	}

}