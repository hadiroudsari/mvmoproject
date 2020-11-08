package biz;

import entity.UserDTO;
import pool.ConnectionGenerator;

import java.sql.*;

public class UserFacade {

    public UserFacade() {
    }

    public long insertHuman(UserDTO userDTO) {
        String SQL = "INSERT INTO userTable(name,family,email,sernumber) "
                + "VALUES(?,?,?,?)";

        long id = 0;
        long row = 10;

        try (Connection conn = ConnectionGenerator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL,
                     Statement.RETURN_GENERATED_KEYS)) {


            pstmt.setString(1, userDTO.getName());
            pstmt.setString(2, userDTO.getFamily());
            pstmt.setString(3, userDTO.getEmail());
            pstmt.setString(4, userDTO.getSerialnumber());

            int affectedRows = pstmt.executeUpdate();
            // check the affected rows
            if (affectedRows > 0) {
                // get the ID back
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        id = rs.getLong(1);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            System.out.println("problem here in creating new user");
            System.out.println(ex.getMessage());
        }
        return id;
    }


    public boolean checkUser(UserDTO userDTO) {
        System.out.println("in check user");
        boolean UserAvailable = false;
        String SQL = "SELECT * from userTable where serNumber =?;";
        try (Connection conn = ConnectionGenerator.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, userDTO.getSerialnumber());
            System.out.println(pstmt.toString());
            ResultSet rs = pstmt.executeQuery();
            UserAvailable = displayActor(rs, false);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return UserAvailable;
    }

    private boolean displayActor(ResultSet rs, boolean b) {
        try {
            System.out.println("in display:");
            if (!rs.next()) {
                b = false;
                System.out.println("it is a new user");

            } else {
                System.out.println("the user has  already exist");
                b = true;

                System.out.println(rs.getString("name") + "\t"
                        + rs.getString("family") + "\t"
                        + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.toString();
        }
        return b;
    }
}



