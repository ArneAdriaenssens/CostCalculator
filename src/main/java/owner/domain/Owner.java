package owner.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import cost.domain.Cost;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.eclipse.persistence.annotations.CascadeOnDelete;

/**
 * Created by Arne on 10/02/2016.
 */
@Entity
@CascadeOnDelete
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "email")
public class Owner implements Serializable {

    private String firstName;
    private String lastName;
    @Id
    private String email;

    @Enumerated(EnumType.STRING)
    private Role ownerRole;
    private String password;

    @OneToMany(mappedBy = "owner", orphanRemoval = true, cascade = {CascadeType.ALL})
    private List<Cost> costs;

    public Owner() {
        this.costs = new ArrayList<>();
        setRole(Role.USER);
    }

    public Owner(String firstName, String lastName, String email, String password) {
        this(firstName, lastName, email, password, Role.USER);
    }

    public Owner(String firstName, String lastName, String email, String password, Role role) {
        this(firstName, lastName, email, password, role, new ArrayList<>());
    }

    public Owner(String firstName, String lastName, String email, String password, Role role, List<Cost> costs) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setRole(role);
        setPassword(password);
        setCosts(costs);
    }

    public List<Cost> getCosts() {
        return costs;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return ownerRole;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setCosts(List<Cost> costs) {
        if (costs == null) {
            this.costs = new ArrayList<>();
        }
        this.costs = costs;
    }

    public void setRole(Role role) {
        if (role == null) {
            throw new OwnerException("Role can't be empty");
        }
        this.ownerRole = role;
    }

    @JsonProperty
    public void setPassword(String password) {
        if (password == null || password.equals("")) {
            throw new OwnerException("Password can't be empty");
        }
        this.password = password;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.equals("")) {
            throw new OwnerException("First name can't be empty");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.equals("")) {
            throw new OwnerException("Last name can't be empty");
        }
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if (email == null || email.equals("") || !isCorrectEmail(email)) {
            throw new OwnerException("Email can't be empty");
        }
        this.email = email;
    }

    private boolean isCorrectEmail(String userId) {
        String USERID_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(userId);
        return m.matches();
    }

    public boolean isSamePassword(String password) {
        return password.equals(getPassword());
    }

    public void addCosts(Cost cost) {
        if (cost == null) {
            throw new OwnerException("Cost can't be emtpy");
        }
        this.getCosts().add(cost);
    }

    public String toString() {
        return "user fName: " + getFirstName() + " lName: " + getLastName() + " email: " + getEmail();
    }

    public boolean equals(Object o) {
        if (o instanceof Owner) {
            return this.getEmail().equals(((Owner) o).getEmail());
        }
        return false;
    }
}
