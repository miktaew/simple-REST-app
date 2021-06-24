package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue
    @Column
    private long client_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String client_login;

    @Column
    private String client_access_code;

    public long getClient_id() {
        return client_id;
    }

    public void setClient_id(long client_id) {
        this.client_id = client_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getClient_login() {
        return client_login;
    }

    public void setClient_login(String client_login) {
        this.client_login = client_login;
    }

    public String getClient_access_code() {
        return client_access_code;
    }

    public void setClient_access_code(String client_access_code) {
        this.client_access_code = client_access_code;
    }
}
