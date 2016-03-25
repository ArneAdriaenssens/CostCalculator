package owner.domain;


import cost.domain.Cost;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arne on 10/02/2016.
 */
public class Owner implements Serializable{

    private String firstName;
    private String lastName;
    private String email;

    private Role role;
    private String password;
    private List<Cost> costs;

    public Owner(){
        this.costs = new ArrayList<>();
    }
    
    public Owner(String firstName, String lastName, String email, String password){
        this(firstName, lastName, email, password, Role.USER);
    }

    public Owner(String firstName, String lastName, String email, String password, Role role){
        this(firstName, lastName, email, password, role, new ArrayList<>());
    }

    public Owner(String firstName, String lastName, String email, String password, Role role, List<Cost> costs){
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

    public void setCosts(List<Cost> costs) {
        this.costs = costs;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password=password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        if(!isCorrectEmail(email)) return;
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
    
    public void addCosts(Cost cost){
        if(cost==null) throw new OwnerException("Cost can't be emtpy");
        this.getCosts().add(cost);
    }
    
    public String toString(){
        return "user fName: " + getFirstName() +" lName: " + getLastName() + " email: " + getEmail();
    }
}
