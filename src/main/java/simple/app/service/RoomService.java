package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public List<Room> getAllRooms()
    {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }

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


    public void AddRoom(Room room)
    {
        roomRepository.save(room);
    }

}
