package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="rooms")
public class Room {

    @Id
    @GeneratedValue
    @Column
    private long room_id;

    @Column
    private String room_number;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomType room_type;

    @Column
    @Enumerated(EnumType.STRING)
    private RoomStatus room_status;

    @OneToOne
    @JoinColumn(name="client_id")
    private Client client;

    public long getRoom_id(){return room_id;};
    public void setRoom_id(long room_id){this.room_id = room_id;};

    public String getRoom_number(){return room_number;};
    public void setRoom_number(String room_number){this.room_number=room_number;};

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public RoomType getRoom_type() {
        return room_type;
    }

    public void setRoom_type(RoomType room_type) {
        this.room_type = room_type;
    }

    public RoomStatus getRoom_status() {
        return room_status;
    }

    public void setRoom_status(RoomStatus room_status) {
        this.room_status = room_status;
    }
}