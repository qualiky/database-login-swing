import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class MarksConsole {

    public static boolean isReady = false;

    JFrame jFrame;
    JPanel marksPanel;
    JLabel tvJava, tvEnglish, tvDS, tvMaths, tvMacro;
    JTextField tbJava, tbEnglish, tbDS, tbMaths, tbMacro;
    JButton btnAdd, btnDone;

    public static HashMap<String, Double> dataMap = new HashMap<>();

    public MarksConsole(){

        jFrame = new JFrame("Marking Console");

        marksPanel = new JPanel(new GridLayout(6,2,30,30));
        marksPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        tvJava = new JLabel("Java");
        tvJava.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));
        marksPanel.add(tvJava);

        tbJava = new JTextField();
        tbJava.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        marksPanel.add(tbJava);

        tvEnglish = new JLabel("English");
        tvEnglish.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));
        marksPanel.add(tvEnglish);

        tbEnglish = new JTextField();
        tbEnglish.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        marksPanel.add(tbEnglish);

        tvDS = new JLabel("Digital System");
        tvDS.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));
        marksPanel.add(tvDS);

        tbDS = new JTextField();
        tbDS.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        marksPanel.add(tbDS);

        tvMaths = new JLabel("Mathematics");
        tvMaths.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));
        marksPanel.add(tvMaths);

        tbMaths = new JTextField();
        tbMaths.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        marksPanel.add(tbMaths);

        tvMacro = new JLabel("Macroeconomics");
        tvMacro.setBorder(BorderFactory.createEmptyBorder(20,20,20,5));
        marksPanel.add(tvMacro);

        tbMacro = new JTextField();
        tbMacro.setBorder(BorderFactory.createEmptyBorder(5,20,5,20));
        marksPanel.add(tbMacro);

        btnDone = new JButton("CANCEL");
        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.dispose();

            }
        });

        marksPanel.add(btnDone);

        btnAdd = new JButton("ADD");
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getSubjectMarks();
            }
        });

        marksPanel.add(btnAdd);

        jFrame.setSize((192*3),(int)(108*3.5));
        jFrame.setContentPane(marksPanel);
        jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        jFrame.setVisible(true);
    }

    public void getSubjectMarks(){

        try{
            Double java = Double.parseDouble(tbJava.getText());
            dataMap.put("Java",java);

            Double english = Double.parseDouble(tbEnglish.getText());
            dataMap.put("English",english);

            Double ds = Double.parseDouble(tbDS.getText());
            dataMap.put("DS",ds);

            Double math = Double.parseDouble(tbMaths.getText());
            dataMap.put("Maths",math);

            Double macro = Double.parseDouble(tbMacro.getText());
            dataMap.put("Macro",macro);

        } catch (NumberFormatException nmf){
            JOptionPane.showMessageDialog(null,"Input Error, please enter valid marks!","Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }
}
