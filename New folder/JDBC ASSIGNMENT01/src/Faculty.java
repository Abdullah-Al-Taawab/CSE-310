import java.sql.*;
import java.util.Scanner;
public class Faculty {


    public void signup(String email, String password)
    {
        boolean emailExists = false;
        Scanner sc=new Scanner(System.in);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment", "root", "1234");

            boolean checkUser = false;

            PreparedStatement st = connection.prepareStatement("select * from faculty where Email = ? AND Password = ?");
            st.setString(1,email);
            st.setString(2,password);

            ResultSet r1=st.executeQuery();

            if(r1.next()) {
                emailExists = true;
                System.out.println("Login successfull");
                System.out.println("Enter the section number");
                int i=sc.nextInt();
                info(i);

            }
            else{
                System.out.println("No such account found");
            }
            connection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    void info(int i){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment", "root", "1234");

            Statement statement = connection.createStatement();
            if (i == 1) {
                ResultSet resultSet = statement.executeQuery("select * from students WHERE (`Section` = 'Section-01')");
                while (resultSet.next()) {

                    System.out.print(resultSet.getString("name") + " ");
                    System.out.print(resultSet.getString("ID") + " ");
                    System.out.println("");

                }
            } else {

                ResultSet resultSet = statement.executeQuery("select * from students WHERE (`Section` = 'Section-02')");
                while (resultSet.next()) {
                    System.out.print(resultSet.getString("name") + " ");
                    System.out.print(resultSet.getString("ID") + " ");
                    System.out.println("");
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }



    }

}
