import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectUserData {
    public static Connection connectDb(){
        Connection connection = null;

        String sql = "jdbc:sqlite:userdata.db";

        try {
            connection = DriverManager.getConnection(sql);
            if(connection!=null){
                System.out.println("Student Database formed successfully!");
            }
        } catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException);
        }

        return connection;
    }
}
