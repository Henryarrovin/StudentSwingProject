package containers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DetailsContainer extends JFrame implements ActionListener {
    JLabel name,userName,dept,section,dob,dor;
    JTextField tName,tUName,tDept,tSection,tDob,tDor;
    JButton logOut;
    Connection connection;
    Statement statement;
    String tNameFromLogin,tUNameFromLogin,tDeptFromLogin,tSectionFromLogin,tDobFromLogin,tDorFromLogin;
    public DetailsContainer(String tNameFromLogin,String tUNameFromLogin,String tDeptFromLogin,String tSectionFromLogin,String tDobFromLogin,String tDorFromLogin) {
        this.tNameFromLogin = tNameFromLogin;
        this.tUNameFromLogin = tUNameFromLogin;
        this.tDeptFromLogin = tDeptFromLogin;
        this.tSectionFromLogin = tSectionFromLogin;
        this.tDobFromLogin = tDobFromLogin;
        this.tDorFromLogin = tDorFromLogin;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/student","root","henryasarrovin");
            statement=connection.createStatement();
        }catch (Exception ex){
            ex.printStackTrace();
        }

        Container container=getContentPane();
        container.setLayout(null);

        name=new JLabel("Name");
        name.setBounds(40,50,100,30);
        container.add(name);

        userName=new JLabel("User Name");
        userName.setBounds(40,100,100,30);
        container.add(userName);

        dept=new JLabel("Department");
        dept.setBounds(40,150,100,30);
        container.add(dept);

        section=new JLabel("Section");
        section.setBounds(40,200,100,30);
        container.add(section);

        dob=new JLabel("Date Of Birth");
        dob.setBounds(40,250,100,30);
        container.add(dob);

        dor=new JLabel("Date Of Register");
        dor.setBounds(40,300,100,30);
        container.add(dor);

        tName=new JTextField();
        tName.setBounds(150,50,200,30);
        tName.setText(tNameFromLogin);
        tName.setEditable(false);
        container.add(tName);

        tUName=new JTextField();
        tUName.setBounds(150,100,200,30);
        tUName.setText(tUNameFromLogin);
        tUName.setEditable(false);
        container.add(tUName);

        tDept=new JTextField();
        tDept.setBounds(150,150,200,30);
        tDept.setText(tDeptFromLogin);
        tDept.setEditable(false);
        container.add(tDept);

        tSection=new JTextField();
        tSection.setBounds(150,200,200,30);
        tSection.setText(tSectionFromLogin);
        tSection.setEditable(false);
        container.add(tSection);

        tDob=new JTextField();
        tDob.setBounds(150,250,200,30);
        tDob.setText(tDobFromLogin);
        tDob.setEditable(false);
        container.add(tDob);

        tDor=new JTextField();
        tDor.setBounds(150,300,200,30);
        tDor.setText(tDorFromLogin);
        tDor.setEditable(false);
        container.add(tDor);

        logOut=new JButton("Log Out");
        logOut.setBounds(140,390,150,40);
        logOut.setBackground(Color.WHITE);
        container.add(logOut);
        logOut.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String s=e.getActionCommand();
        if (s.equals("Log Out")){
            LoginContainer loginContainer=new LoginContainer();
            loginContainer.setTitle("login page");
            loginContainer.setSize(430,300);
            loginContainer.setVisible(true);
            dispose();
        }
    }
}
