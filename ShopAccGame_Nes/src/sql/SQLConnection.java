package sql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SQLConnection {

    private Connection connection;

    // Phương thức thiết lập kết nối
    public void connect(String url, String username, String password) throws SQLException {
        connection = DriverManager.getConnection(url, username, password);
    }

    // Phương thức thực thi câu lệnh SELECT
    public ResultSet executeQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }

    // Phương thức thực thi câu lệnh INSERT, UPDATE, DELETE
    public int executeUpdate(String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeUpdate(query);
    }

    // Phương thức đóng kết nối
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
    // Bắt đầu kết nối
    public static boolean StartConnection(SQLConnection connection){
        try {
            connection.connect("jdbc:mysql://localhost:3306/shopgame", "root", "");
            return true;
        } catch (Exception e) {
            System.out.println("Kết nối database thất bại");
        }
        return false;
    }
    // Kết thúc kết nối
    public static void EndConnection(SQLConnection connection) throws SQLException{
         connection.disconnect();
    }
}