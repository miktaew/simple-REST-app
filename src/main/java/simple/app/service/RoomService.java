package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.app.misc.Misc;
import simple.app.misc.RoomNoClientId;
import simple.app.model.Room;
import simple.app.model.RoomStatus;
import simple.app.model.RoomType;
import simple.app.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    /*

     */
    @Autowired
    private RoomRepository roomRepository;


    //getting without id:
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

    //getting with id:
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


    public void AddRoom(Room room)
    {
        roomRepository.save(room);
    }

}
