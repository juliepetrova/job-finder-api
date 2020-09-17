package Controller;

import com.mysql.jdbc.Connection;
import com.sun.org.apache.xerces.internal.impl.XMLEntityManager;
import com.sun.tools.javac.util.Convert;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserController extends Connect {

    public List<User> getAllUseres()
    {
        List<User> users = new ArrayList<>();

        try {
            Statement stmt = super.conn.createStatement();
            ResultSet rs  = stmt.executeQuery("SELECT * FROM users;");
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

    public User getUserById (int id) throws SQLException {
        User user = new User();
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM users WHERE user_id = 1;";
//            PreparedStatement prep = conn.prepareStatement(sql);
//            prep.setInt(1, id);
            ResultSet rs = stmt.executeQuery(sql);


            while ( rs.next() ) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String email = rs.getString("email");
                String username = rs.getString("username");
                String date_of_birth = rs.getString("date_of_birth");
                String city = rs.getString("city");
                String country = rs.getString("country");
                String phone = rs.getString("phone_number");


                user = new User(id, firstName, lastName, email, username, date_of_birth, city, country, phone);
            }

        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }finally {
            super.conn.close();
        }
        return user;
    }

    public int getUserIdByEmail(String email) throws SQLException {
        int user_id = -1;
        try {
            Statement stmt = conn.createStatement();
            String sql = "SELECT user_id FROM users WHERE email LIKE 'andrei.popov@gmail.com';";
//            PreparedStatement prep = conn.prepareStatement(sql);
//            prep.setString(1, email);
            ResultSet rs = stmt.executeQuery(sql);
            while ( rs.next() ) {
                user_id = rs.getInt("user_id");
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } finally {
            super.conn.close();
        }
        return user_id;
    }

}
