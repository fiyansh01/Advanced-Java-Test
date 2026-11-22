package Test1_CoreJava;
import java.lang.classfile.attribute.SourceDebugExtensionAttribute;
import java.sql.*;
import java.util.Locale;
import java.util.Scanner;

public class UserRegistrationApp {
    static final String URL = "jdbc:mysql://localhost:3306/app_db";
    static final String USER = "root";
    static final String Pass = "asdfghjkl098?";

    static void main() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name: ");
        String name = sc.nextLine().trim();
        System.out.println("Email: ");
        String email = sc.nextLine().trim().toLowerCase(Locale.ROOT);
        System.out.println("Password: ");
        String password = sc.nextLine().trim();

        // we have to handle the edge cases first by simple validation
        if(name.isEmpty() || email.isEmpty() || password.length() < 6){
            System.out.println("Invalid input! Name/ email required and password >= 6 chars.");
            sc.close();
            return;
        }
        try(Connection con = DriverManager.getConnection(URL, USER, Pass)){

            // Now we have to check if the email already exists or not
            String checkSql = "SELECT id FROM users WHERE email = ?";
            try(PreparedStatement checkPs = con.prepareStatement(checkSql)){
                checkPs.setString(1, email);
                ResultSet rs = checkPs.executeQuery();
                if(rs.next()){
                    System.out.println("Email already Registered!");
                    sc.close();
                    return;
                }
            }
            // insert new User
            String insertSql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";
            try(PreparedStatement insertPS = con.prepareStatement(insertSql)){
                insertPS.setString(1, name);
                insertPS.setString(2, email);
                insertPS.setString(3, password);
                insertPS.executeUpdate();
                System.out.println("User registered successfully!");
            }
// Also we have to tackle concurrent request race condition handeled at DB level
        }catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Email already registered (duplicate constraint)!");
        }catch (SQLException e){
            e.printStackTrace();
        }
        sc.close();


    }
}
