package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainDAO {
    public static Connection getConnection() {

        try {
            final String url = "jdbc:mariadb://localhost:3306/uber";
            return DriverManager.getConnection(url, "root", "8642");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println(MainDAO.getConnection());
    }

}