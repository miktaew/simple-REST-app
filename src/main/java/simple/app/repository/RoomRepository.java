package simple.app.repository;

import org.springframework.data.repository.CrudRepository;
import simple.app.model.Room;

public interface RoomRepository extends CrudRepository<Room, String> {

}
