package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //necessary, other generation types result in error
    @Column
    private long roomId;

    @Column
    private String roomNumber;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomStatus roomStatus;

    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    public long getRoomId(){return roomId;};
    public void setRoomId(long roomId){this.roomId = roomId;};

    public String getRoomNumber(){return roomNumber;};
    public void setRoomNumber(String roomNumber){this.roomNumber = roomNumber;};

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }
}