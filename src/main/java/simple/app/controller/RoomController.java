package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import simple.app.misc.RoomNoClientId;
import simple.app.model.Room;
import simple.app.model.RoomStatus;
import simple.app.model.RoomType;
import simple.app.service.RoomService;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    //todo: get list of rooms with id of current user (only for employees); change room status (free -> booked; booked -> free)
    // so /rooms/clients, /rooms/book/{roomNumber} and /rooms/free/{roomNumber}
    // /rooms/clients can same as /rooms, except with access validation at beginning

    //todo: get client of specific room (only for employees)
    // so /rooms/clients/{roomNumber}

    @Autowired
    private RoomService roomService;

    @GetMapping("/rooms")
    @ResponseBody
    private List<RoomNoClientId> getRoomsNoClientId(@RequestParam Optional<RoomStatus> roomStatus, @RequestParam Optional<RoomType> roomType) {
        // todo: special exception message on wrong enum param instead of default?
        
        if (roomStatus.isPresent() && roomType.isPresent()) {
            return roomService.getAllRoomsNoClientIdByStatusByType(roomStatus.get(), roomType.get());

        } else if(roomStatus.isPresent() && roomType.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such room status or room type possible, " +
                    "status options are: FREE, TAKEN; type options are NORMAL, PREMIUM");

        } else if(roomStatus.isPresent()) {
            return roomService.getAllRoomsNoClientIdByStatus(roomStatus.get());

        } else if(roomStatus.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such room status possible, options are: FREE, TAKEN");

        } else if(roomType.isPresent()) {
            return roomService.getAllRoomsNoClientIdByType(roomType.get());

        } else if(roomType.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such room type possible, options are: NORMAL, PREMIUM");

        } else {
            return roomService.getAllRoomsNoClientId();
        }
    }

}
