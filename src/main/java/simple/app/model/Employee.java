package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue
    @Column
    private long employee_id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column
    private String employee_login;

    @Column
    private String employee_access_code;

    @Column
    private Boolean is_admin;

    public Employee() {
    }

    public Employee(String first_name, String last_name, String employee_login, String employee_access_code, Boolean is_admin) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.employee_login = employee_login;
        this.employee_access_code = employee_access_code;
        this.is_admin = is_admin;
    }

    public long getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(long employee_id) {
        this.employee_id = employee_id;
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

    public String getEmployee_login() {
        return employee_login;
    }

    public void setEmployee_login(String employee_login) {
        this.employee_login = employee_login;
    }

    public String getEmployee_access_code() {
        return employee_access_code;
    }

    public void setEmployee_access_code(String employee_access_code) {
        this.employee_access_code = employee_access_code;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
}
