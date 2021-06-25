package simple.app.model;
import javax.persistence.*;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //necessary, other generation types result in error
    @Column
    private Long employeeId;

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

    public Employee(Long employeeId, String firstName, String lastName, String employeeLogin, String employeeAccessCode, Boolean isAdmin) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeLogin = employeeLogin;
        this.employeeAccessCode = employeeAccessCode;
        this.isAdmin = isAdmin;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
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
