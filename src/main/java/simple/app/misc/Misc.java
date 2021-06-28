package simple.app.misc;

import simple.app.model.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Misc {

    // returns List of Maps with everything Rooms contain, except for roomIds and Clients (only clientId instead)
    public static List<HashMap<String, Object>> roomToMap(List<Room> rooms) {
        List<HashMap<String, Object>> result = new ArrayList<>();
        HashMap<String, Object> temp = new HashMap<>();
        Room room;

        for(int i = 0; i < rooms.size(); i++) {
            room = rooms.get(i);
            temp.put("roomNumber", room.getRoomNumber());
            temp.put("roomType", room.getRoomType().toString());
            temp.put("roomStatus", room.getRoomStatus().toString());
            if(room.getClient() != null) {
                temp.put("clientID", room.getClient().getClientId());
            } else {
                temp.put("clientID", null);
            }
            result.add((HashMap<String,Object>) temp.clone());
        }
        return result;
    }
}
