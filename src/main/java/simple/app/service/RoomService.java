package simple.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import simple.app.model.Room;
import simple.app.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms()
    {
        List<Room> RoomRecords = new ArrayList<>();
        roomRepository.findAll().forEach(RoomRecords::add);
        return RoomRecords;
    }

    public void AddRoom(Room room)
    {
        roomRepository.save(room);
    }
}
