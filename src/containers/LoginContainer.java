package containers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class LoginContainer extends JFrame implements ActionListener{
    JLabel name,userName,pswd,lSignPage;
    JPasswordField tPswd;
    JTextField tName,tUName;
    JButton login,signUp,refresh;

    Connection connection;
    Statement statement;
    ResultSet resultSet;
    java.util.Date date = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    String str = formatter.format(date);
    public LoginContainer(){
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
        name.setBounds(40,40,100,30);
        container.add(name);

        userName=new JLabel("User Name");
        userName.setBounds(40,90,100,30);
        container.add(userName);

        pswd=new JLabel("Password");
        pswd.setBounds(40,140,100,30);
        container.add(pswd);

        tName=new JTextField();
        tName.setBounds(150,40,200,30);
        container.add(tName);

        tUName=new JTextField();
        tUName.setBounds(150,90,200,30);
        container.add(tUName);

        tPswd=new JPasswordField();
        tPswd.setBounds(150,140,200,30);
        container.add(tPswd);

        login=new JButton("login");
        login.setBounds(150,180,100,30);
        login.setBackground(Color.WHITE);
        container.add(login);
        login.addActionListener(this);

        refresh=new JButton("refresh");
        refresh.setBounds(250,180,100,30);
        refresh.setBackground(Color.WHITE);
        container.add(refresh);
        refresh.addActionListener(this);

        lSignPage=new JLabel("Don't have an account?");
        lSignPage.setBounds(100,220,150,30);
        lSignPage.setOpaque(false);
        container.add(lSignPage);

        signUp=new JButton("signup");
        signUp.setBounds(220,220,100,30);
        signUp.setOpaque(false);
        signUp.setContentAreaFilled(false);
        signUp.setBorderPainted(false);
        signUp.setForeground(Color.BLUE);
        container.add(signUp);
        signUp.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String t1 = null;
        String t2 = null;
        String t3 = null;
        String t4 = null;
        String t5 = null;
        String t6 = null;
        String t7 = null;
        String s=e.getActionCommand();
        if (s.equals("signup")){
            SignUpContainer signUpContainer=new SignUpContainer();
            signUpContainer.setTitle("STUDENT");
            signUpContainer.setSize(430,500);
            signUpContainer.setVisible(true);
            dispose();
        } else if (s.equals("login")) {
            try{
                resultSet=statement.executeQuery("" +
                        "select * from studentdb where fName='"+tName.getText()+"' and lName='"+tUName.getText()+"' and pswd='"+tPswd.getText()+"'");
                while(resultSet.next()){
                    t1=resultSet.getString(1);
                    t2=resultSet.getString(2);
                    t3=resultSet.getString(3);
                    t4=resultSet.getString(4);
                    t5=resultSet.getString(5);
                    t6=resultSet.getString(6);
                    t7=resultSet.getString(7);
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
            if (Objects.equals(t1,tName.getText()) ||
                    Objects.equals(t2, tUName.getText()) ||
                    Objects.equals(t7, tPswd.getPassword())){

                DetailsContainer detailsContainer=new DetailsContainer(t1,t2,t3,t4,t5,t6);
                detailsContainer.setTitle("STUDENT");
                detailsContainer.setSize(430,510);
                detailsContainer.setVisible(true);
                dispose();
            }  else {
                tPswd.getPassword();
                LoginContainer loginContainer=new LoginContainer();
                JOptionPane.showMessageDialog(loginContainer, "please fill in the credentials properly!");
            }
        } else if (s.equals("refresh")) {
            tName.setText(null);
            tUName.setText(null);
            tPswd.setText(null);
        }
    }
}
