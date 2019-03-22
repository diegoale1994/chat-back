package springbootbackendchat.controllers;

import java.util.Date;
import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import springbootbackendchat.models.documents.Mensaje;

@Controller
public class ChatController {

	private String[] colores = {"red", "green", "black", "purple", "orange", "grey"};
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setTexto("Nuevo usuario");
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
		}
		//mensaje.setTexto("Recibido por el broker: "+ mensaje.getTexto());
		
		return mensaje;
	}
}
