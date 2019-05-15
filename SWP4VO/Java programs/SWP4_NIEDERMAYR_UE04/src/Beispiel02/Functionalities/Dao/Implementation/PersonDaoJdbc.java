package Beispiel02.Functionalities.Dao.Implementation;

import Beispiel02.Functionalities.Dao.PersonDao;
import Beispiel02.Functionalities.Domain.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDaoJdbc extends AbstractDao<Person> implements PersonDao {

    //returns one specific person
    @Override
    public Person readForIdentity(int id) {
        return null;
    }

    //returns the list of all persons in the database
    @Override
    public List<Person> readAll() {
        try {
            List<Person> personList = new ArrayList<Person>();

            //first establishe a new connection
            Connection connection = createConnection();
            //creates the SQL statement
            Statement statement = connection.createStatement();
            //actual statement
            String sql = "SELECT * FROM APP.PERSONS";
            PreparedStatement stmt= connection.prepareStatement(sql);

            int result = stmt.executeUpdate();
            stmt.close();
            closeConnection();

            return personList;
        } catch (SQLException e) {
            return null;
        }
    }

    //create a new person
    @Override
    public boolean create(Person entity) {
        try {
            //first establishe a new connection
            Connection connection = createConnection();
            //creates the SQL statement
            Statement statement = connection.createStatement();
            //actual statement
            String sql = "INSERT INTO APP.PERSONS (FIRSTNAME, LASTNAME, CITY, POSTALCODE, ADDRESS, PHONENUMBER) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt= connection.prepareStatement(sql);

            //now set the parameters
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setString(3, entity.getCity());
            stmt.setString(3, String.valueOf(entity.getPostalCode()));
            stmt.setString(3, entity.getAddress());
            stmt.setString(3, entity.getPhoneNumber());

            //statement gets executed
            int result = stmt.executeUpdate();
            stmt.close();
            closeConnection();

            return (result != 0);
        } catch (SQLException e) {
            //creation of person was not possible
            return false;
        }
    }

    //delete one person from the database via personId
    @Override
    public boolean delete(int id) {
        try {
            //first establishe a new connection
            Connection connection = createConnection();
            //creates the SQL statement
            Statement statement = connection.createStatement();
            //actual statement
            String sql = "DELETE FROM APP.PERSONS WHERE PERSONID = ?";
            PreparedStatement stmt= connection.prepareStatement(sql);

            //now set the parameters
            stmt.setString(1, String.valueOf(id));

            //statement gets executed
            int result = stmt.executeUpdate();
            stmt.close();
            closeConnection();

            return (result != 0);
        } catch (SQLException e) {
            //deletion of person was not possible
            return false;
        }
    }
}
