import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.HashMap;

public class Dashboard extends JFrame {

    JFrame frameDashboard;
    JPanel panelDashboard, panelGender, panelButtons, panelSecondButtons;
    JLabel tvName, tvAddress, tvStream, tvGender, tvMath, tvEnglish, tvDS, tvJava, tvMacro;
    JTextField tbName, tbAddress, tbMath, tbEnglish, tbDS, tbJava, tbMacro;
    JRadioButton btnMale, btnFemale, btnOther;
    JComboBox<String> listStream;
    ButtonGroup gbGroup;
    String[] streamListData = {"BBA","BBA-BI","BBA-TT","BCIS","MBA","MBA-IT"};
    JButton btnAdd, btnDisplay, btnMarks, btnCancel;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    Student s;
    HashMap<String,Double> data;

    public Dashboard(){

        frameDashboard = new JFrame("Student Data Management System");

        panelDashboard = new JPanel(new GridLayout(10,2,30,30));
        panelDashboard.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        tvName = new JLabel("Name: ");
        tvName.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvAddress = new JLabel("Address: ");
        tvAddress.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvStream = new JLabel("Stream: ");
        tvStream.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvGender = new JLabel("Gender: ");
        tvGender.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbName = new JTextField();
        tbName.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbAddress = new JTextField();
        tbAddress.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        listStream = new JComboBox<>(streamListData);

        btnMale = new JRadioButton("Male");
        btnMale.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        btnFemale = new JRadioButton("Female");
        btnFemale.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        btnOther = new JRadioButton("Other");
        btnOther.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        gbGroup = new ButtonGroup();
        gbGroup.add(btnMale);
        gbGroup.add(btnFemale);
        gbGroup.add(btnOther);

        btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean yOn = studentDataWriter();
                if(yOn){
                    JOptionPane.showMessageDialog(null,
                            "User data added successfully!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "User data could not be added!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDisplay = new JButton("DISPLAY");
        btnDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DatabaseConsole();
            }
        });

        btnMarks = new JButton("MARKINGS");
        btnMarks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MarksConsole();
                data = MarksConsole.dataMap;

                if(data != null){
                    System.out.println("Data received!");
                }
            }
        });

        btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(11);
            }
        });

        tvJava = new JLabel("Java");
        tvJava.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvEnglish = new JLabel("English");
        tvEnglish.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvDS = new JLabel("Digital System");
        tvDS.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvMath = new JLabel("Mathematics");
        tvMath.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tvMacro = new JLabel("Macroeconomics");
        tvMacro.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));

        tbJava = new JTextField();
        tbJava.setEnabled(false);
        tbJava.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbEnglish = new JTextField();
        tbEnglish.setEnabled(false);
        tbEnglish.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbDS = new JTextField();
        tbDS.setEnabled(false);
        tbDS.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbMath = new JTextField();
        tbMath.setEnabled(false);
        tbMath.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));

        tbMacro = new JTextField();
        tbMacro.setEnabled(false);
        tbMacro.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));


        panelGender = new JPanel(new FlowLayout());
        panelGender.add(btnMale);
        panelGender.add(btnFemale);
        panelGender.add(btnOther);

        panelDashboard.add(tvName);
        panelDashboard.add(tbName);
        panelDashboard.add(tvStream);
        panelDashboard.add(listStream);
        panelDashboard.add(tvGender);
        panelDashboard.add(panelGender);
        panelDashboard.add(tvAddress);
        panelDashboard.add(tbAddress);

        panelButtons = new JPanel(new FlowLayout());
        panelSecondButtons = new JPanel(new FlowLayout());

        panelButtons.add(btnDisplay);
        panelButtons.add(btnAdd);

        panelSecondButtons.add(btnMarks);
        panelSecondButtons.add(btnCancel);

        panelDashboard.add(panelButtons);
        panelDashboard.add(panelSecondButtons);

        panelDashboard.add(tvJava);
        panelDashboard.add(tbJava);

        panelDashboard.add(tvEnglish);
        panelDashboard.add(tbEnglish);

        panelDashboard.add(tvDS);
        panelDashboard.add(tbDS);

        panelDashboard.add(tvMath);
        panelDashboard.add(tbMath);

        panelDashboard.add(tvMacro);
        panelDashboard.add(tbMacro);

        frameDashboard.add(panelDashboard);
        frameDashboard.setDefaultCloseOperation(3);
        frameDashboard.setSize((192*3),(108*6));
        frameDashboard.setLocationRelativeTo(null);
        frameDashboard.setVisible(true);

    }

    public boolean studentDataWriter(){

        Student s = new Student();

        //getting the required data
        String name = tbName.getText();
        String address = tbAddress.getText();
        String stream = (String) listStream.getSelectedItem();

        //getting the gender from radio buttons
        int gender = 0;
        if(btnMale.isSelected())
            gender = s.GENDER_MALE;
        else if(btnFemale.isSelected())
            gender = s.GENDER_FEMALE;
        else if(btnOther.isSelected())
            gender = s.GENDER_OTHER;

        s.setName(name);
        s.setAddress(address);
        s.setStream(stream);
        s.setGender(gender);

        //creating table to write the data if it does not exist
        createTable();

        //writing the data to database now
        boolean ifDone = writeData(name,address,stream,gender,data.get("Java"),data.get("English"),data.get("DS"),data.get("Maths"),data.get("Macro"));

        //setting the received data in the textboxes
        tbJava.setText(data.get("Java").toString());
        tbEnglish.setText(data.get("English").toString());
        tbDS.setText(data.get("DS").toString());
        tbMath.setText(data.get("Maths").toString());
        tbMacro.setText(data.get("Macro").toString());

        //returning if writing was successful or not
        return ifDone;
    }

    public void createTable(){
        connection = DBConnectUserData.connectDb();

        String sql = "CREATE TABLE IF NOT EXISTS StudentData (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "name TEXT NOT NULL,\n" +
                "address TEXT NOT NULL, \n" +
                "stream TEXT NOT NULL, \n" +
                "gender INTEGER NOT NULL, \n" +
                "java DOUBLE NOT NULL, \n" +
                "digital_system DOUBLE NOT NULL, \n" +
                "english DOUBLE NOT NULL, \n" +
                "math DOUBLE NOT NULL, \n" +
                "macroeconomics DOUBLE NOT NULL \n" +
                ");";

        try{
            statement = connection.createStatement();
            statement.execute(sql);

        } catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null,sqlException," Dashboard Error!",JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean writeData(String name, String address, String stream, int gender, double java, double english, double ds, double math, double macro){
        boolean isDone = false;

        connection = DBConnectUserData.connectDb();

        String ins = "INSERT INTO StudentData (name, address, stream, gender, java, digital_system, english, math, macroeconomics) VALUES(?,?,?,?,?,?,?,?,?)";

        try {

            preparedStatement = connection.prepareStatement(ins);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,address);
            preparedStatement.setString(3,stream);
            preparedStatement.setInt(4,gender);
            preparedStatement.setDouble(5, java);
            preparedStatement.setDouble(6,ds);
            preparedStatement.setDouble(7,english);
            preparedStatement.setDouble(8,math);
            preparedStatement.setDouble(9,macro);

            preparedStatement.executeUpdate();
            isDone = true;

        } catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null,sqlException,"Error!",JOptionPane.ERROR_MESSAGE);sqlException.printStackTrace();
        }

        return isDone;
    }

}
