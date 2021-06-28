package simple.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import simple.app.misc.ClientNonConfidential;
import simple.app.misc.RoomNoClientId;
import simple.app.model.Client;
import simple.app.model.Room;
import simple.app.model.RoomStatus;
import simple.app.model.RoomType;

import java.util.List;
import java.util.Optional;

public interface RoomRepository extends CrudRepository<Room, Long> {

    //without client:
    @Query("SELECT r.roomNumber as roomNumber, r.roomStatus as roomStatus, r.roomType as roomType FROM Room r")
    List<RoomNoClientId> getRoomsNoClientId();

    @Query("SELECT r.roomNumber as roomNumber, r.roomStatus as roomStatus, r.roomType as roomType FROM Room r WHERE r.roomType = ?1")
    List<RoomNoClientId> getRoomsNoClientIdByType(RoomType roomType);

    @Query("SELECT r.roomNumber as roomNumber, r.roomStatus as roomStatus, r.roomType as roomType FROM Room r WHERE r.roomStatus = ?1")
    List<RoomNoClientId> getRoomsNoClientIdByStatus(RoomStatus roomStatus);

    @Query("SELECT r.roomNumber as roomNumber, r.roomStatus as roomStatus, r.roomType as roomType FROM Room r WHERE r.roomStatus = ?1 AND r.roomType = ?2")
    List<RoomNoClientId> getRoomsNoClientIdByStatusByType(RoomStatus roomStatus, RoomType roomType);


    //with client:
    @Query("SELECT r FROM Room r")
    List<Room> getRooms();

    @Query("SELECT r FROM Room r WHERE r.roomType = ?1")
    List<Room> getRoomsByType(RoomType roomType);

    @Query("SELECT r FROM Room r WHERE r.roomStatus = ?1")
    List<Room> getRoomsByStatus(RoomStatus roomStatus);

    @Query("SELECT r FROM Room r WHERE r.roomStatus = ?1 AND r.roomType = ?2")
    List<Room> getRoomsByStatusByType(RoomStatus roomStatus, RoomType roomType);

    @Query("SELECT r.client.clientId as clientId, r.client.firstName as firstName, r.client.lastName as lastName FROM Room r WHERE r.roomNumber = ?1")
    Optional<ClientNonConfidential> getClientOfRoom(String roomNumber);
}
