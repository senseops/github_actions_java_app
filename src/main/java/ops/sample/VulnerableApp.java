package ops.sample;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.security.MessageDigest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VulnerableApp {

    // Hardcoded credentials (Security Issue)
    private static final String DB_URL = "jdbc:mysql://localhost:3306/testdb";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        // Simulate SQL injection vulnerability
        String userInput = "1 OR 1=1"; // Malicious input
        fetchUserData(userInput);
        
        // Simulate insecure hashing
        String password = "myPassword123";
        System.out.println("MD5 hash of password: " + insecureHash(password));
    }

    // Vulnerable method that leads to SQL injection
    public static void fetchUserData(String userId) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement statement = connection.createStatement()) {

            // SQL Injection vulnerability: Concatenating user input directly into the query
            String query = "SELECT * FROM users WHERE id = " + userId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("User ID: " + resultSet.getString("id"));
                System.out.println("User Name: " + resultSet.getString("name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // XSS vulnerability: Reflecting user input without sanitizing it
    public static void vulnerableXSS(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userInput = request.getParameter("username");

        // Directly reflecting user input in the response
        response.getWriter().write("<html><body>Welcome " + userInput + "</body></html>");
    }

    // Insecure hashing method using MD5
    public static String insecureHash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // Weak hash algorithm
            byte[] hashBytes = md.digest(input.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
