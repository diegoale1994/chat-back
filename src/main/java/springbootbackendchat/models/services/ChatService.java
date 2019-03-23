package springbootbackendchat.models.services;

import java.util.List;

import springbootbackendchat.models.documents.Mensaje;

public interface ChatService {
	public List<Mensaje> obtenerUltimos10Mensajes();
	public Mensaje guardar(Mensaje mensaje);
}
