import java.sql.*;
import java.util.Scanner;

public class MyJDBC {
    public static void main(String[] args) {

            Scanner sc=new Scanner(System.in);



            System.out.println("if you are a student press 1 or if you are a faculty member then press 0");
            int n=sc.nextInt();
            if(n==1){
                System.out.println("If you want to register then press 1 or if you weant to sign up then press 0");
                int p=sc.nextInt();
                if(p==1){
                    System.out.println("Enter your name");
                    String name=sc.next();
                    System.out.println("Enter your email");
                    String email=sc.next();
                    System.out.println("Enter your ID");
                    String id=sc.next();
                    System.out.println("Enter your password");
                    String pass=sc.next();
                    Student s=new Student(name,email,id,pass);
                    s.register();
                    System.out.println("Enter your email");
                    String n1=sc.next();
                    System.out.println("Enter your password");
                    String n2=sc.next();
                    s.signup(n1,n2);

                }
                else{
                    System.out.println("Enter your name");
                    String np= sc.next();
                    Student l=new Student(np);
                    System.out.println("Enter your email");
                    String n1=sc.next();
                    System.out.println("Enter your password");
                    String n2=sc.next();
                    l.signup(n1,n2);

                }

            }
            else{
                System.out.println("Enter your email");
                String n1=sc.next();
                System.out.println("Enter your password");
                String n2=sc.next();
                Faculty f=new Faculty();
                f.signup(n1,n2);






            }






        }



    }

