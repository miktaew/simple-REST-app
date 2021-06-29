package simple.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import simple.app.misc.ClientNonConfidential;
import simple.app.misc.Misc;
import simple.app.misc.RoomNoClientId;
import simple.app.model.Room;
import simple.app.model.RoomStatus;
import simple.app.model.RoomType;
import simple.app.service.ClientService;
import simple.app.service.EmployeeService;
import simple.app.service.RoomService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    //todo: add room?

    @Autowired
    private RoomService roomService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ClientService clientService;

    //get rooms without ids of their clients
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

    //gets rooms with ids of their clients (employee access required)
    @GetMapping("/rooms/clients")
    @ResponseBody
    private List<HashMap<String,Object>> getRoomsWithClientsIds(@RequestParam Optional<String> roomStatus, @RequestParam Optional<String> roomType,
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

    //get client of specific room
    @GetMapping("/rooms/clients/{roomNumber}")
    @ResponseBody
    public ClientNonConfidential getRoomClient(@PathVariable String roomNumber, @RequestParam String userLogin,
                                               @RequestParam String accessCode) {
        if(employeeService.validateAccess(userLogin, accessCode)) {
            Optional<ClientNonConfidential> result = roomService.getClientOfRoom(roomNumber);
            if (result.isPresent()) {
                return result.get();
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    @PutMapping("/rooms/book/{roomNumber}")
    public String bookRoomByNumber(@PathVariable String roomNumber, @RequestParam String userLogin,
                             @RequestParam String accessCode) {
        if(clientService.validateAccess(userLogin, accessCode)) {
            Optional<Room> roomTemp = roomService.getRoomByRoomNumber(roomNumber);
            if (roomTemp.isPresent()) {
                Room room = roomTemp.get();
                if(room.getRoomStatus().toString().equals("FREE"))
                {
                    room.setRoomStatus(RoomStatus.TAKEN);
                    room.setClient(clientService.getClientByLogin(userLogin));
                    roomService.changeRoomStatusToTaken(room.getRoomNumber(), clientService.getClientByLogin(userLogin));
                }
                else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is already taken.");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return null;
    }

    @PutMapping("/rooms/free/{roomNumber}")
    public String freeRoomByNumber(@PathVariable String roomNumber, @RequestParam String userLogin,
                                   @RequestParam String accessCode) {
        if(employeeService.validateAccess(userLogin, accessCode)) {
            Optional<Room> roomTemp = roomService.getRoomByRoomNumber(roomNumber);
            if (roomTemp.isPresent()) {
                Room room = roomTemp.get();
                if(room.getRoomStatus().toString().equals("TAKEN"))
                {
                    room.setRoomStatus(RoomStatus.FREE);
                    room.setClient(null);
                    roomService.changeRoomStatusToFree(room.getRoomNumber());
                }
                else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Room is already free.");
                }
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

        return null;
    }
}
