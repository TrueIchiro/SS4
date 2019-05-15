package Beispiel02.Functionalities.Dao.Implementation;

import Beispiel02.Functionalities.Dao.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements Dao<T> {

    //is the url for the database, is needed to connect it
    protected static final String DATABASE_URL = "";

    //for the connection to the database
    private Connection connection;

    static {
        try {
            //TODO: Change this Url
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
        } catch (InstantiationException
        | IllegalAccessException
        | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //creates the connection if there is none
    //uses the DATABASE_URL
    protected Connection createConnection() throws SQLException {
        //no connection exists yet
        if (connection == null) {
            //this is where the connection is first established
            connection = DriverManager.getConnection(DATABASE_URL);
        }

        return connection;
    }

    //close the connection, because it should not keep on existing
    protected void closeConnection() {
        try {
            //there's still a connection alive
            //which must be
            //DESTROYED
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
