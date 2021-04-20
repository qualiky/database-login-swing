import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseConsole {
    JFrame jFrame;
    JTable jTable;
    DefaultTableModel tableModel;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    
    public DatabaseConsole(){

        ArrayList<Student> arrStudents = new ArrayList<>();

        jFrame = new JFrame("Display Console");

        tableModel = new DefaultTableModel();

        tableModel.addColumn("Name");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Stream");
        tableModel.addColumn("Address");
        tableModel.addColumn("Java");
        tableModel.addColumn("Digital System");
        tableModel.addColumn("Macroeconomics");
        tableModel.addColumn("Mathematics");
        tableModel.addColumn("English");
        tableModel.addColumn("Total");

        jTable = new JTable(tableModel);

        connection = DBConnectUserData.connectDb();

        String sql = "SELECT * FROM StudentData";
        Student[] dataArr = new Student[100];
        int i=0;


        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            System.out.println(resultSet.getFetchSize());
            if(resultSet.getFetchSize() == 0){
                System.out.println("Big oof happened");
            }

            while (resultSet.next()){
                dataArr[i] = new Student();

                dataArr[i].setName(resultSet.getString(2));
                dataArr[i].setAddress(resultSet.getString(3));
                dataArr[i].setStream(resultSet.getString(4));
                dataArr[i].setGender(resultSet.getInt(5));
                dataArr[i].setSubJava(resultSet.getDouble(6));
                dataArr[i].setSubDS(resultSet.getDouble(7));
                dataArr[i].setSubEnglish(resultSet.getDouble(8));
                dataArr[i].setSubMaths(resultSet.getDouble(9));
                dataArr[i].setSubMacro(resultSet.getDouble(10));

                arrStudents.add(dataArr[i]);
                i++;
            }

        } catch (SQLException sqlException){
            System.out.println("Error!");
        }

        Student[] finalArr = new Student[arrStudents.size()];

        for(i=0; i<finalArr.length; ++i){
            finalArr[i] = new Student();
            finalArr[i] = arrStudents.get(i);

            tableModel.addRow(new Object[] {


                    finalArr[i].getName(),
                    finalArr[i].getGender(),
                    finalArr[i].getStream(),
                    finalArr[i].getAddress(),
                    finalArr[i].getSubJava(),
                    finalArr[i].getSubDS(),
                    finalArr[i].getSubMacro(),
                    finalArr[i].getSubMaths(),
                    finalArr[i].getSubEnglish(),
                    (finalArr[i].getSubJava() + finalArr[i].getSubDS() + finalArr[i].getSubMacro() + finalArr[i].getSubMaths() + finalArr[i].getSubEnglish())
            });
        }

        jTable.setPreferredScrollableViewportSize(new Dimension(500,10));
        jTable.setFillsViewportHeight(true);

        JScrollPane jsp = new JScrollPane(jTable);

        jFrame.add(jsp);
        jFrame.setSize(500,400);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
