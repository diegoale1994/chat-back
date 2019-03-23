package springbootbackendchat.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import springbootbackendchat.models.dao.ChatRepository;
import springbootbackendchat.models.documents.Mensaje;

@Service
public class ChatServiceImp implements ChatService {

	@Autowired
	private ChatRepository chatDao;
	
	@Override
	public List<Mensaje> obtenerUltimos10Mensajes() {
		return chatDao.findFirst10ByOrderByFechaDesc();
	}

	@Override
	public Mensaje guardar(Mensaje mensaje) {
		return chatDao.save(mensaje);
	}

}
