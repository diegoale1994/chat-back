package springbootbackendchat.controllers;

import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import springbootbackendchat.models.documents.Mensaje;
import springbootbackendchat.models.services.ChatService;

@Controller
public class ChatController {

	private String[] colores = {"red", "green", "black", "purple", "orange", "grey"};

	@Autowired
	private ChatService chatService;
	
	@Autowired
	private SimpMessagingTemplate webSocket;
	
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibeMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")) {
			mensaje.setTexto("Nuevo usuario");
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
		}else {
			chatService.guardar(mensaje);
		}
		//mensaje.setTexto("Recibido por el broker: "+ mensaje.getTexto());
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String estaEscribiendo(String username) {
		return username.concat(" Esta escribiendo");
	}
	
	@MessageMapping("/historial")
	public void historial(String clienteId) {
		webSocket.convertAndSend("/chat/historial/"+clienteId, chatService.obtenerUltimos10Mensajes());
	}
}
