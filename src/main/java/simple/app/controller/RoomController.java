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
    private List<RoomNoClientId> getRoomsNoClientId(@RequestParam Optional<String> roomStatus, @RequestParam Optional<String> roomType) {
        if(roomStatus.isPresent() && roomType.isPresent()) {
            String status = roomStatus.get();
            String type = roomType.get();
            if((status.equals("FREE") || status.equals("TAKEN")) && (type.equals("NORMAL") || type.equals("PREMIUM"))) {
                return roomService.getAllRoomsNoClientIdByStatusByType(RoomStatus.valueOf(status), RoomType.valueOf(type));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong status or type. Status has to be FREE or TAKEN, type has to be NORMAL or PREMIUM");
            }

        } else if(roomStatus.isPresent()) {
            String status = roomStatus.get();
            if(status.equals("FREE") || status.equals("TAKEN")) {
                return roomService.getAllRoomsNoClientIdByStatus(RoomStatus.valueOf(status));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong status. Status has to be FREE or TAKEN");
            }

        } else if(roomType.isPresent()) {
            String type = roomType.get();
                if(type.equals("NORMAL") || type.equals("PREMIUM")) {
                    return roomService.getAllRoomsNoClientIdByType(RoomType.valueOf(type));
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong type. Type has to be NORMAL or PREMIUM");
                }

        } else {
            return roomService.getAllRoomsNoClientId();
        }
    }

}
