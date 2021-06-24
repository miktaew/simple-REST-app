package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue
    @Column
    private long employeeId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String employeeLogin;

    @Column
    private String employeeAccessCode;

    @Column
    private Boolean isAdmin;

    public Employee() {
    }

    public Employee(String firstName, String lastName, String employeeLogin, String employeeAccessCode, Boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeLogin = employeeLogin;
        this.employeeAccessCode = employeeAccessCode;
        this.isAdmin = isAdmin;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
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

    public String getEmployeeLogin() {
        return employeeLogin;
    }

    public void setEmployeeLogin(String employeeLogin) {
        this.employeeLogin = employeeLogin;
    }

    public String getEmployeeAccessCode() {
        return employeeAccessCode;
    }

    public void setEmployeeAccessCode(String employeeAccessCode) {
        this.employeeAccessCode = employeeAccessCode;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
