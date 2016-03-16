package user.domain;


import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arne on 10/02/2016.
 */
public class User implements Serializable{

    private String firstName;
    private String lastName;
    private String email;

    private Role role;
    private String password;

    public User(){}
    
    public User(String firstName, String lastName, String email, String password){
        this(firstName, lastName, email, password, Role.USER);
    }

    public User(String firstName, String lastName, String email, String password, Role role){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setRole(role);
        setPassword(password);
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
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

    private void setRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Role can't be empty");
        this.role = role;
    }

    private void setPassword(String password) {
        if (password == null || password.equals("")) throw new IllegalArgumentException("Password can't be emtpy");
        this.password=password;
    }

    private void setFirstName(String firstName) throws IllegalArgumentException {
        if (firstName == null || firstName.equals("")) throw new IllegalArgumentException("First name can't be empty");
        this.firstName = firstName;
    }

    private void setLastName(String lastName) throws IllegalArgumentException {
        if (lastName == null || lastName.equals("")) throw new IllegalArgumentException("Last name can't be empty");
        this.lastName = lastName;
    }

    private void setEmail(String email) throws IllegalArgumentException {
        if (email == null || email.equals("")) throw new IllegalArgumentException("Last name can't be empty");
        if (!isCorrectEmail(email)) throw new IllegalArgumentException("Format for email is wrong!");
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
}
