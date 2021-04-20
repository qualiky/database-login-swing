import javax.swing.*;
import java.sql.*;
import java.util.Base64;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;

public class SignIn {

    JPanel panelOne, panelTwo, panelThree;
    JFrame jFrame;
    JLabel tvName, tvPword;
    JButton btnRegister, btnLogin;
    String uName, pWord;
    HashMap<String,String> credentialData;
    JTextField textFieldUserName;
    JPasswordField passwordFieldLogin;
    Connection connection;
    Statement statement;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    SignIn(){

        //this creates the sqlite database table if it does not exist
        createNewTable();

        //this is the set of hashmap that will store the username-password pair retrieved from the database
        credentialData = new HashMap<>();


        jFrame = new JFrame("MIS");

        panelOne = new JPanel();

        tvName = new JLabel("Username: ");
        tvName.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        tvPword = new JLabel("Password: ");
        tvPword.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        btnRegister = new JButton("REGISTER");
        btnLogin = new JButton("LOGIN");

        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isInserted = false;
                uName = textFieldUserName.getText().trim();
                pWord = new String(passwordFieldLogin.getPassword()).trim();

                //checking if the username and password are null, if it is null then it is not possible to register
                if(uName.length()==0 || pWord.length()==0){
                    JOptionPane.showMessageDialog(null,"Username or Password cannot be blank!","REGISTRATION ERROR",
                            JOptionPane.ERROR_MESSAGE);
                } else {

                    //check if the user has admin level permission
                    String s = JOptionPane.showInputDialog(null,"Enter the admin password: (efika123)",
                            "CONFIRM PASSWORD",JOptionPane.QUESTION_MESSAGE);

                    if(s.equals("efika123")){
                        //add the username and password to sqlite database from the connection
                        isInserted = insertRecord(uName,pWord);
                    } else
                        JOptionPane.showMessageDialog(null,"Access Denied!","CREDENTIAL ERROR",
                                JOptionPane.ERROR_MESSAGE);
                }

                //if added, the dialog box appears
                if(isInserted){
                    JOptionPane.showMessageDialog(null,"Login Credentials added successfully!",
                            "Success",JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(null,"Login Credentials could not be added!",
                            "Error",JOptionPane.ERROR_MESSAGE);
                }

                //clearing the text fields once the username-password has been generated is a good practice, and this
                //also means that users will have to re-enter the registered username and password
                textFieldUserName.setText(null);
                passwordFieldLogin.setText(null);
            }
        });


        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String un = textFieldUserName.getText();
                String pw = new String(passwordFieldLogin.getPassword());

                //read the data from the sqlite database, add the username and password to the hashmap and
                //confirm the identity verification


                //this hashmap receives all the password-username pair from the getLoginCredentials function
                HashMap<String,String> resultLogin = getLoginCredentials();

                //error is shown if there are no login credentials saved yet
                if(resultLogin.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Access denied!","Login Error",JOptionPane.ERROR_MESSAGE);
                }

                //authentication process
                if(resultLogin.containsKey(un)){
                    if(resultLogin.get(un).equals(pw)){
                        new Dashboard();
                    }
                    else
                        JOptionPane.showMessageDialog(null,"Credential error, try again!","Error",
                                JOptionPane.ERROR_MESSAGE);
                }
                else
                    JOptionPane.showMessageDialog(null,"The entered account does not exist!","Error",
                            JOptionPane.ERROR_MESSAGE);
            }
        });

        textFieldUserName = new JTextField();
        textFieldUserName.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        passwordFieldLogin = new JPasswordField();
        passwordFieldLogin.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        panelOne.setLayout(new GridLayout(3,2,40,20));
        panelOne.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        panelOne.add(tvName);
        panelOne.add(textFieldUserName);

        panelOne.add(tvPword);
        panelOne.add(passwordFieldLogin);

        panelOne.add(btnRegister);
        panelOne.add(btnLogin);

        jFrame.setContentPane(panelOne);

        jFrame.setDefaultCloseOperation(3);
        jFrame.setSize((192*2),(108*2));
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


    }

    //creating the sqlite table if it does not exist yet
    public void createNewTable(){
        String sql = "CREATE TABLE IF NOT EXISTS LoginCreds (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "username TEXT NOT NULL, \n" +
                "password TEXT NOT NULL \n" +
                ");";

        connection = DBConnect.connectDB();
        try {
            statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }

    }

    //adding the username and password to the database
    public boolean insertRecord(String username, String password){
        boolean isInserted = false;

        connection = DBConnect.connectDB();
        String s = "INSERT INTO LoginCreds (username,password) VALUES(?,?)";

        try{

            preparedStatement = connection.prepareStatement(s);
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);
            preparedStatement.executeUpdate();
            isInserted = true;

        } catch (SQLException sqlException){
            JOptionPane.showMessageDialog(null, sqlException,"Error",JOptionPane.ERROR_MESSAGE);
        }

        return isInserted;
    }

    //getting all the username and password set from the database
    public HashMap<String,String> getLoginCredentials(){
        HashMap<String,String> resultData = new HashMap<>();

        String sql = "SELECT * FROM LoginCreds";

        try{
            connection = DBConnect.connectDB();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                resultData.put(resultSet.getString("username"),resultSet.getString("password"));
            }

        } catch (SQLException sqlEx){
            JOptionPane.showMessageDialog(null,sql,"Error",JOptionPane.ERROR_MESSAGE);
        }


        return resultData;
    }

    //main method, program starts here
    public static void main(String[] args) { new SignIn(); }
}
