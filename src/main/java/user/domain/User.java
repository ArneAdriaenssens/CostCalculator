package user.domain;


import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Arne on 10/02/2016.
 */
public class User {

    private String firstName;
    private String lastName;
    private String email;

    private Role role;

    private byte[] salt;
    private String password;

    public User(String firstName, String lastName, String email, String password){
        this(firstName, lastName, email, password, new SecureRandom().generateSeed(20));
    }

    public User(String firstName, String lastName, String email, String password, byte[] salt){
        this(firstName, lastName, email, password, salt, Role.USER);
    }

    public User(String firstName, String lastName, String email, String password, byte[] salt, Role role){
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPassword(password, salt);
        setRole(role);
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

    public byte[] getSalt() {
        return salt;
    }

    private void setSalt(byte[] salt) {
        this.salt = salt;
    }

    private void setRole(Role role) {
        if (role == null) throw new IllegalArgumentException("Role can't be empty");
        this.role = role;
    }

    private void setPassword(String password, byte[] salt) {
        if (password == null || password.equals("")) throw new IllegalArgumentException("Password can't be emtpy");
        try {
            this.password = this.hashPassword(password);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private String hashPassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest crypt = MessageDigest.getInstance("SHA-1");
        crypt.reset();

        if (this.getSalt() == null) {
            if (salt == null) this.setSalt(new SecureRandom().generateSeed(20));
            else setSalt(salt);
        }

        byte[] salt = this.getSalt();

        crypt.update(salt);
        crypt.update(password.getBytes("UTF-8"));
        byte[] digest = crypt.digest();
        return new BigInteger(1, digest).toString(16);
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

    public boolean isSamePassword(String password) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return this.hashPassword(password).equals(getPassword());
    }
}
