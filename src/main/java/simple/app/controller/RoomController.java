package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import simple.app.misc.Misc;
import simple.app.misc.RoomNoClientId;
import simple.app.model.Room;
import simple.app.model.RoomStatus;
import simple.app.model.RoomType;
import simple.app.service.EmployeeService;
import simple.app.service.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    //todo: change room status (free -> booked; booked -> free)
    // /rooms/book/{roomNumber} and /rooms/free/{roomNumber}
    //todo: get client of specific room (only for employees)
    // so /rooms/clients/{roomNumber}

    @Autowired
    private RoomService roomService;
    @Autowired
    private EmployeeService employeeService;

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

    @GetMapping("/rooms/clients")
    @ResponseBody
    private List<HashMap<String,Object>> getRoomsWithClients(@RequestParam Optional<String> roomStatus, @RequestParam Optional<String> roomType,
                                                             @RequestParam String userLogin, @RequestParam String accessCode) {

        if(!employeeService.validateAccess(userLogin, accessCode)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        if(roomStatus.isPresent() && roomType.isPresent()) {
            String status = roomStatus.get();
            String type = roomType.get();
            if((status.equals("FREE") || status.equals("TAKEN")) && (type.equals("NORMAL") || type.equals("PREMIUM"))) {
                return Misc.roomToMap(roomService.getAllRoomsByStatusByType(RoomStatus.valueOf(status), RoomType.valueOf(type)));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong status or type. Status has to be FREE or TAKEN, type has to be NORMAL or PREMIUM");
            }

        } else if(roomStatus.isPresent()) {
            String status = roomStatus.get();
            if(status.equals("FREE") || status.equals("TAKEN")) {
                return Misc.roomToMap(roomService.getAllRoomsByStatus(RoomStatus.valueOf(status)));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong status. Status has to be FREE or TAKEN");
            }

        } else if(roomType.isPresent()) {
            String type = roomType.get();
            if(type.equals("NORMAL") || type.equals("PREMIUM")) {
                return Misc.roomToMap(roomService.getAllRoomsByType(RoomType.valueOf(type)));
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong type. Type has to be NORMAL or PREMIUM");
            }

        } else {
            return Misc.roomToMap(roomService.getAllRooms());
        }
    }

}
