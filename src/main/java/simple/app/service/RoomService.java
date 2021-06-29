package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.app.misc.ClientNonConfidential;
import simple.app.misc.RoomNoClientId;
import simple.app.model.Client;
import simple.app.model.Room;
import simple.app.model.RoomStatus;
import simple.app.model.RoomType;
import simple.app.repository.RoomRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    /*

     */
    @Autowired
    private RoomRepository roomRepository;


    //getting without client id:
    public List<RoomNoClientId> getAllRoomsNoClientId()
    {
        return roomRepository.getRoomsNoClientId();
    }

    public List<RoomNoClientId> getAllRoomsNoClientIdByStatus(RoomStatus roomStatus)
    {
        return roomRepository.getRoomsNoClientIdByStatus(roomStatus);
    }

    public List<RoomNoClientId> getAllRoomsNoClientIdByType(RoomType roomType)
    {
        return roomRepository.getRoomsNoClientIdByType(roomType);
    }

    public List<RoomNoClientId> getAllRoomsNoClientIdByStatusByType(RoomStatus roomStatus, RoomType roomType)
    {
        return roomRepository.getRoomsNoClientIdByStatusByType(roomStatus, roomType);
    }

    //getting with client id:
    public List<Room> getAllRooms()
    {
        return roomRepository.getRooms();
    }
    public List<Room> getAllRoomsByStatus(RoomStatus roomStatus)
    {
        return roomRepository.getRoomsByStatus(roomStatus);
    }

    public List<Room> getAllRoomsByType(RoomType roomType)
    {
        return roomRepository.getRoomsByType(roomType);
    }

    public List<Room> getAllRoomsByStatusByType(RoomStatus roomStatus, RoomType roomType)
    {
        return roomRepository.getRoomsByStatusByType(roomStatus, roomType);
    }

    public Optional<ClientNonConfidential> getClientOfRoom(String roomNumber) {
        return roomRepository.getClientOfRoom(roomNumber);
    }

    //

    public Optional<Room> getRoomByRoomNumber(String roomNumber) {
        return roomRepository.getRoomByRoomNumber(roomNumber);
    }

    public void AddRoom(Room room)
    {
        roomRepository.save(room);
    }

    public void changeRoomStatusToTaken(String roomNumber, Client client) {
        roomRepository.changeRoomStatusToTaken(client, roomNumber);
    }
    public void changeRoomStatusToFree(String roomNumber) {
        roomRepository.changeRoomStatusToFree(roomNumber);
    }

}
