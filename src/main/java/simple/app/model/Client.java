package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //necessary, other generation types result in error
    @Column
    private long clientId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String clientLogin;

    @Column
    private String clientAccessCode;

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public String getClientAccessCode() {
        return clientAccessCode;
    }

    public void setClientAccessCode(String clientAccessCode) {
        this.clientAccessCode = clientAccessCode;
    }
}
