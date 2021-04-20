import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class SwingTeach3  implements ActionListener{
    JFrame jframe;
    JRadioButton btnMale;
    JRadioButton btnFemale;
    //JRadioButton btnOther=null;
    JComboBox comboProgram;

    ButtonGroup btnGroup;


    JTextField textUserName=null;
    JPasswordField textPassword=null;

    String Uname="bisheshwor";  //SET THE USERNAME AND PASSWORD
    String Upassword="bibash";



    public void someMethod(){

        jframe=new JFrame("Alci System");


        //object of layout manager
        FlowLayout fl=new FlowLayout();


        GridLayout gl=new GridLayout(4,3,10,10);//row,colum,size/hight


        JPanel panel_one,panel_two,parent_panel;

        panel_one=new JPanel(gl);  //the constructor of jpanel takes an object of gridlayout
        panel_two=new JPanel(fl);
        parent_panel=new JPanel(); //it takes object of flowlayout because it is default layout


        textUserName = new JTextField();
        textPassword = new JPasswordField();

        JLabel userName=new JLabel("User Name: ");
        JLabel password=new JLabel("Password: ");

        JTextField textUserName=new JTextField(10);
        JPasswordField textPassword=new JPasswordField(10);


        JButton btnSubmit=new JButton();
        btnSubmit.setText("SUBMIT");

        btnMale=new JRadioButton("Male");
        btnFemale=new JRadioButton("Female");
        //  btnOther=new JRadioButton("Others");

        String[] programList={"BCIS", "BBA-BI", "BBA-TT","BBA"};
        comboProgram =new JComboBox(programList);



        btnGroup=new ButtonGroup();

        btnGroup.add(btnMale);
        btnGroup.add(btnFemale);
        // btnGroup.add(btnOther);





        panel_one.add(userName);
        panel_one.add(textUserName);
        panel_one.add(password);
        panel_one.add(textPassword);

        panel_one.add(btnMale);
        panel_one.add(btnFemale);
        // panel_one.add(btnOther);

        panel_two.add(btnSubmit);
        panel_two.add(comboProgram);


        parent_panel.add(panel_one);
        parent_panel.add(panel_two);

        jframe.add(parent_panel);

        //jframe.setSize(400,400);
        jframe.pack();
        jframe.setVisible(true);

        btnSubmit.addActionListener(this); //regestering the action Listner

    }


    public void actionPerformed(ActionEvent ae){
        // System.out.println("bunnon clicked! by bisheshwor");
        //JOptionPane.showMessageDialog(null,"button is clecked","Event performed",JOptionPane.WARNING_MESSAGE);
        String tempUsername= textUserName.getText();
        String tempPassword= new String(textPassword.getPassword());


        if(tempUsername.equals(Uname) && tempPassword.equals(Upassword)){

            JOptionPane.showMessageDialog(null,"Username and Password is correcr","Succesfull to Log in:",JOptionPane.INFORMATION_MESSAGE);


        }else{


            JOptionPane.showMessageDialog(null,"Username and Password is not correcr","Unsuccesfull to Log in:",JOptionPane.WARNING_MESSAGE);

        }




    }

    public static void main(String[] args){

        SwingTeach3 s3 = new SwingTeach3();

        s3.someMethod();
    }
}