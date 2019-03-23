package springbootbackendchat.models.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import springbootbackendchat.models.documents.Mensaje;

public interface ChatRepository extends MongoRepository<Mensaje, String> {
	public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
