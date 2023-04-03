import containers.LoginContainer;


public class Main {
    public static void main(String[] args) {
        LoginContainer loginContainer=new LoginContainer();
        loginContainer.setTitle("login page");
        loginContainer.setSize(430,300);
        loginContainer.setVisible(true);
    }
}
