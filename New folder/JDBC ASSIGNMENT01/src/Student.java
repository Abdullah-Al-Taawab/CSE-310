import java.sql.*;
import java.util.Scanner;

public class Student {
    String name, ID, email, password;

    Student(String n) {
        this.name=n;



    }
    Student(String n, String e, String i, String p) {
        this.name = n;
        this.email = e;
        this.ID = i;
        this.password = p;


    }
    Student() {



    }


    void register() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment", "root", "1234");
            String sql = "INSERT INTO students (name,Email,ID,password) VALUES(?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, ID);
            statement.setString(4, password);
            int rows = statement.executeUpdate();

                System.out.println("Registration Completed ");

            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void signup(String email,String pass)
    {
        boolean emailExists = false;

        try {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment", "root", "1234");

        boolean checkUser = false;

        PreparedStatement st = connection.prepareStatement("select * from students where Email = ? AND password=?");
        st.setString(1,email);
        st.setString(2,pass);
        ResultSet r1=st.executeQuery();

            if(r1.next()) {
                emailExists = true;
                System.out.println("Login successfull");
                timeslot();
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
    void timeslot(){
        Scanner sc=new Scanner(System.in);
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment","root","1234");

            Statement statement = connection.createStatement();
            ResultSet resultSet= statement.executeQuery("select * from sections");
            System.out.println("Class timing");
            while (resultSet.next()) {
                System.out.print(resultSet.getString("Section") + " ");
                System.out.print(resultSet.getString("Day")+ " ");
                System.out.print(resultSet.getString("Time")+ " ");
                System.out.print(resultSet.getString("RemainingSeats") + "  Seats Remaining");
                System.out.println();
            }
            System.out.println("Press 1 or 2 according to your section");
            int a=sc.nextInt();
            update_number_of_seat(a);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    void update_number_of_seat(int a){
     try {
         Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment", "root", "1234");

         Statement st = connection.createStatement();
         if (a == 1) {
             String query = "SELECT RemainingSeats from sections WHERE (`Section` = 'Section-01')";
             ResultSet rs = st.executeQuery(query);
             String v = "";
             while (rs.next()) {
                 v = rs.getString("RemainingSeats");
             }

             int i = Integer.parseInt(v);
             if(i==0){
                 System.out.println("There is no remaining seat for this section , kindly try another section");
                 return;
             }
             i = i - 1;
             String sql = "UPDATE `jdbc_assignment`.`sections` SET RemainingSeats = ? WHERE Section = ?";
             PreparedStatement pstmt = connection.prepareStatement(sql);
             pstmt.setInt(1, i);
             pstmt.setString(2, "Section-01");
             pstmt.executeUpdate();
             assignsection(a);

             //int rowsAffected =st.executeUpdate(sql);

         }
         else{
             String q = "SELECT RemainingSeats from sections WHERE (`Section` = 'Section-02')";
             ResultSet rs = st.executeQuery(q);
             String v = "";
             while (rs.next()) {
                 v = rs.getString("RemainingSeats");
             }

             int i = Integer.parseInt(v);
             if(i==0){
                 System.out.println("There is no remaining seat for this section , kindly try another section");
                 return;
             }
             i = i - 1;
             String sql = "UPDATE `jdbc_assignment`.`sections` SET RemainingSeats = ? WHERE Section = ?";
             PreparedStatement pstmt = connection.prepareStatement(sql);
             pstmt.setInt(1, i);
             pstmt.setString(2, "Section-02");
             pstmt.executeUpdate();
             assignsection(a);


         }
     }
     catch(Exception e){
         e.printStackTrace();
     }


    }
    void assignsection(int a){
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_assignment", "root", "1234");

            Statement statement = connection.createStatement();

            if (a == 1) {
                String sql = "UPDATE `jdbc_assignment`.`students` SET Section = ? WHERE name = ?";
                PreparedStatement pt = connection.prepareStatement(sql);
                pt.setString(1, "Section-01");
                pt.setString(2, name);
                pt.executeUpdate();
                System.out.println("Successful");
            } else {
                String sql = "UPDATE `jdbc_assignment`.`students` SET Section = ? WHERE name = ?";
                PreparedStatement pt = connection.prepareStatement(sql);
                pt.setString(1, "Section-02");
                pt.setString(2, name);
                pt.executeUpdate();
                System.out.println("Successful");

            }
        }
        catch(Exception e){
            e.printStackTrace();
        }



    }

}
