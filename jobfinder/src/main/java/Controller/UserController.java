package Controller;

import com.mysql.jdbc.Connection;
import com.sun.tools.javac.util.Convert;
import model.User;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserController extends Connect {

    public List<User> getAllUseres()
    {
        List<User> users = new ArrayList<>();

        try {
            Statement stmt = super.conn.createStatement();
            ResultSet rs;

            rs = stmt.executeQuery("SELECT * FROM users;");
            while ( rs.next() ) {
                int id = rs.getInt("user_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String date_of_birth = rs.getString("date_of_birth");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String phone = rs.getString("phone_number");

                users.add(new User(id, firstName, lastName, email, username, date_of_birth, city, country, phone));
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        return users;
    }

}
