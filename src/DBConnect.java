import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection connectDB() {
        Connection connection = null;

        try{
            connection = DriverManager.getConnection("jdbc:sqlite:login.db");

            if(connection != null){
                DatabaseMetaData metaData = connection.getMetaData();
                System.out.println("Connection successful!" + metaData);
            }

        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

        return connection;
    }
}
