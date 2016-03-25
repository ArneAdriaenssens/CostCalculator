package owner.repository;

import cost.domain.Cost;
import owner.domain.Role;
import owner.domain.Owner;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class OwnerRepositoryDb implements OwnerRepository{

    private PreparedStatement statement;
    private Connection connection;
    private Properties properties;

    public OwnerRepositoryDb()  {
        Properties properties = new Properties();
        try {
            properties.load(getClass().getClassLoader().getResourceAsStream("DBinfo.properties"));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        String url = "jdbc:postgresql://gegevensbanken.khleuven.be:51516/2TX33";
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");

        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, properties);
            System.out.println("message: connection to ProductDB made");
            //statement = connection.createStatement();
        }catch(SQLException e){
            throw new DbOwnerException(e.getMessage());
        }catch(ClassNotFoundException e){
            throw new DbOwnerException(e.getMessage());
        }
    }

    public Properties getProperties() {
        return properties;
    }

    private void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void closeConnecction()  {
        try{
            connection.close();
            statement.close();
        } catch(SQLException e){
            throw new DbOwnerException(e.getMessage());
        }
    }

    public List<Owner> getAllUsers()  {
        List<Owner> terug = new ArrayList<Owner>();
        String sql = "SELECT * "
                + "FROM r0576067.db_site_person";
        ResultSet rs;
        try {
            String url = getProperties().getProperty("url");
            connection = DriverManager.getConnection(url, getProperties());

            this.statement = connection.prepareStatement(sql);
            rs= statement.executeQuery();
        }
        catch (SQLException e) {
            throw new DbOwnerException(e.getMessage());
        }
        try {
            while(rs.next()){
                terug.add(new Owner(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), Role.valueOf(rs.getString(5))));
            }
        } catch (IllegalArgumentException e) {
            throw new DbOwnerException(e.getMessage());
        } catch (SQLException e) {
            throw new DbOwnerException(e.getMessage());
        } finally{
            this.closeConnecction();
        }
        return terug;
    }

    public Owner getUserByEmail(String email)  {
        if(email == null)throw new IllegalArgumentException("Nothing to find");
        if(email.equals("")) throw new DbOwnerException("Id is empty");
        String sql = "SELECT userid, password, firstname,  lastname, role, salt"
                + " FROM db_site_person"
                + " WHERE userid=?";
        ResultSet rs;
        try {
            String url = getProperties().getProperty("url");
            connection = DriverManager.getConnection(url, getProperties());

            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, email);
            rs = statement.executeQuery();
        }
        catch (SQLException e) {
            throw new DbOwnerException(e.getMessage());
        }
        Owner p=null;
        try {
            rs.next();
            p = new Owner(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), Role.valueOf(rs.getString(5)) );
        } catch (IllegalArgumentException e) {
            throw new DbOwnerException(e.getMessage());
        } catch (SQLException e) {
            throw new DbOwnerException(e.getMessage());
        } finally{
            this.closeConnecction();
        }
        return p;
    }

    public void addUser(Owner user){
        if(user == null) throw new IllegalArgumentException("No person given");
        if( this.getAllUsers().contains(user) ) throw new IllegalArgumentException("User already exists");
        String query = "insert into r0576067.db_site_person (userId, firstName, lastName, password, role, salt) values(?, ?, ?, ?, ?, ?)";
        try{
            String url = getProperties().getProperty("url");
            connection = DriverManager.getConnection(url, getProperties());
            statement = connection.prepareStatement(query);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole()+"");
            statement.execute();
        }
        catch(SQLException e){
            e.printStackTrace();
        } finally{
            this.closeConnecction();
        }
    }

    public void deleteUser(String email) {
        if(email == null) throw new IllegalArgumentException("No id given");
        String sql = "delete from r0576067.db_site_person where userid=?";
        try {
            String url = getProperties().getProperty("url");
            connection = DriverManager.getConnection(url, getProperties());

            this.statement = connection.prepareStatement(sql);
            this.statement.setString(1, email);
            statement.execute();
        } catch (SQLException e) {
            throw new DbOwnerException(e.getMessage());
        } finally{
            this.closeConnecction();
        }
    }

    @Override
    public void updateCost(Cost cost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
