package containers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class SignUpContainer extends JFrame implements ActionListener {
    JLabel name,userName,dept,section,dob,dor,pswd,lLoginPage;
    JPasswordField tPswd;
    JTextField tName,tUName,tDept,tSection,tDob,tDor;
    JButton submit,refresh,loginPage;
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String str = formatter.format(date);
    public SignUpContainer(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection=DriverManager.getConnection(
                    "jdbc:mysql://0.tcp.in.ngrok.io:19873/student","root","henryasarrovin");
            statement=connection.createStatement();
        }catch (Exception ex){
           ex.printStackTrace();
        }

        Container container=getContentPane();
        container.setLayout(null);

        name=new JLabel("Name");
        name.setBounds(40,40,100,30);
        container.add(name);

        userName=new JLabel("User Name");
        userName.setBounds(40,90,100,30);
        container.add(userName);

        dept=new JLabel("Department");
        dept.setBounds(40,140,100,30);
        container.add(dept);

        section=new JLabel("Section");
        section.setBounds(40,190,100,30);
        container.add(section);

        dob=new JLabel("Date Of Birth");
        dob.setBounds(40,240,100,30);
        container.add(dob);

        dor=new JLabel("Date Of Register");
        dor.setBounds(40,290,100,30);
        container.add(dor);

        pswd=new JLabel("Password");
        pswd.setBounds(40,340,100,30);
        container.add(pswd);

        tName=new JTextField();
        tName.setBounds(150,40,200,30);
        container.add(tName);

        tUName=new JTextField();
        tUName.setBounds(150,90,200,30);
        container.add(tUName);

        tDept=new JTextField();
        tDept.setBounds(150,140,200,30);
        container.add(tDept);

        tSection=new JTextField();
        tSection.setBounds(150,190,200,30);
        container.add(tSection);

        tDob=new JTextField();
        tDob.setBounds(150,240,200,30);
        container.add(tDob);

        tDor=new JTextField();
        tDor.setBounds(150,290,200,30);
        tDor.setText(String.valueOf(str));
        tDor.setEditable(false);
        container.add(tDor);

        tPswd=new JPasswordField();
        tPswd.setBounds(150,340,200,30);
        container.add(tPswd);

        submit=new JButton("submit");
        submit.setBounds(100,390,100,30);
        submit.setBackground(Color.WHITE);
        container.add(submit);
        submit.addActionListener(this);

        refresh=new JButton("refresh");
        refresh.setBounds(250,390,100,30);
        refresh.setBackground(Color.WHITE);
        container.add(refresh);
        refresh.addActionListener(this);

        lLoginPage=new JLabel("Already have an account?");
        lLoginPage.setBounds(100,420,150,30);
        lLoginPage.setOpaque(false);
        container.add(lLoginPage);

        loginPage=new JButton("login");
        loginPage.setBounds(220,420,100,30);
        loginPage.setOpaque(false);
        loginPage.setContentAreaFilled(false);
        loginPage.setBorderPainted(false);
        loginPage.setForeground(Color.BLUE);
        container.add(loginPage);
        loginPage.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String textUserName = null;
        String s=e.getActionCommand();
        if (s.equals("refresh")){
            tName.setText(null);
            tUName.setText(null);
            tDept.setText(null);
            tSection.setText(null);
            tDob.setText(null);
            tPswd.setText(null);
        } else if (s.equals("submit")) {
            try{
                resultSet=statement.executeQuery("select * from studentdb");
                while (resultSet.next()){
                    textUserName = resultSet.getString(2);
                }

                if (tName.getText().trim().isEmpty()|| tUName.getText().isEmpty() ||
                        tDept.getText().isEmpty() || tSection.getText().isEmpty() ||
                        tDob.getText().isEmpty()  || Arrays.toString(tPswd.getPassword()).isEmpty()){

                    SignUpContainer signUpContainer = new SignUpContainer();
                    JOptionPane.showMessageDialog(signUpContainer, "Enter all Credentials!");

                } else if (textUserName.equals(tUName.getText()) ) {
                    SignUpContainer signUpContainer = new SignUpContainer();
                    JOptionPane.showMessageDialog(signUpContainer, "User Name already exists!");
                } else {
                    String string = Arrays.toString(tPswd.getPassword());
                    string = string.replaceAll(",", "").replaceAll("\\[", "").replaceAll("]", "").replaceAll("\\s", "");

                    statement.execute(
                            "insert into studentdb values('"+tName.getText()+"','"+tUName.getText()+"','"+tDept.getText()+"','"+
                                    tSection.getText()+"','"+tDob.getText()+"','"+tDor.getText()+"','"+ string +"')");



                    DetailsContainer detailsContainer=new DetailsContainer(tName.getText(),tUName.getText(),
                            tDept.getText(),tSection.getText(),tDob.getText(),tDor.getText());
                    detailsContainer.setTitle("STUDENT");
                    detailsContainer.setSize(430,500);
                    detailsContainer.setVisible(true);
                    dispose();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        } else if (s.equals("login")) {
            LoginContainer loginContainer=new LoginContainer();
            loginContainer.setTitle("login page");
            loginContainer.setSize(430,300);
            loginContainer.setVisible(true);
            dispose();

        }
    }
}
