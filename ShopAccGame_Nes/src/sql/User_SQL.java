package sql;

import entity.User;
import entity.AccountGame;
import entity.Transaction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.TransactionService;


public class User_SQL {
       public static void ShowAllUser(SQLConnection connection) throws SQLException {
        try {
            String query = "select * from users";
            ResultSet rs= connection.executeQuery(query);
        while(rs.next()){
            System.out.print(rs.getInt("UserID") + " - ");
            System.out.print(rs.getString("UserName") + " - ");
            System.out.print(rs.getString("Email") + " - "); 
            System.out.print(rs.getString("Password") + " - ");
            System.out.print(rs.getString("Role"));
            System.out.println("");
        }
        } catch (Exception e) {
            System.out.println("Select All User thất bại");
        }
    }
       public static List<User> UserExport(SQLConnection connection) throws SQLException{
           try {
               List<User> listUsers = new ArrayList<>();
               String query = "select * from users";
                ResultSet rs= connection.executeQuery(query);
                while(rs.next()) {
                    User newUser = new User(rs.getInt("UserID"),rs.getString("UserName"),rs.getString("Email"),rs.getString("Password"),rs.getString("Role"));
                    listUsers.add(newUser);
                }
                return listUsers;
           } catch (Exception e) {
               System.out.println("Xuất file user có vấn đề");
               System.out.println(e.getMessage());
           }
           return null;
       }
    public static void AddAUser(SQLConnection connection ,User user) throws SQLException{
        try {
            String query = "insert into users (UserName, Email, Password, Role) values ('";
            query += user.getUserName()+ "', '" + user.getEmail() + "', '" + user.getPassword() + "', \"" + user.getRole() + "\" )";
            connection.executeUpdate(query);
            System.out.println("Thêm user thành công");
        } catch (Exception e) {
            System.out.println("Thêm user không thành công");
            System.out.println(e.getMessage());
        }
    }
    public static void DeleteAUser(SQLConnection connection, int UserID) throws SQLException{
        try {
            String query = "delete from users where UserID = " + UserID;
            connection.executeUpdate(query);
            System.out.println("Xóa user với UserID = " + UserID + " thành công");
        } catch (Exception e) {
            System.out.println("Delete a user with id " + UserID + " fail");
            System.out.println(e.getMessage());
        }
    }
    public static void SearchAnUser(SQLConnection connection, int UserID) throws SQLException{
        try {
            String query = "select * from users where UserID =  " + UserID;
            ResultSet rs = connection.executeQuery(query);
             while (rs.next()) {
            System.out.print(rs.getInt("UserID") + " - ");
            System.out.print(rs.getString("UserName") + " - ");
            System.out.print(rs.getString("Email") + " - "); 
            System.out.print(rs.getString("Password") + " - ");
            System.out.print(rs.getString("Role"));
            System.out.println("");
             }
        } catch (Exception e) {
            System.out.println("Tìm kiếm xuất user hiện lỗi");
            System.out.println(e.getMessage());
        }
    }
    public static int SearchIDAUser(SQLConnection connection, User user)  throws SQLException{
        try {
            String query = "select * from users where UserName =  \"" + user.getUserName() +"\"";
            ResultSet rs = connection.executeQuery(query);
            int id = 0;
            while (rs.next()) {
            id = rs.getInt("UserID") ;
            }
            return id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
           return 0;
    }
    public static void BuyAnAccount(SQLConnection connection, int IngameId, User user ) throws SQLException{
        try {
            String query = "select * from accountgame, kindofgame where accountgame.GameId = kindofgame.GameId and IngameId = " + IngameId;
            ResultSet resultSet = connection.executeQuery(query);
             AccountGame accountGame = new AccountGame();
             int price = -1;
            while(resultSet.next()){
             System.out.print(resultSet.getInt("IngameId") + " - ");
             System.out.print(resultSet.getString("UserNameGame") + " - ");
             System.out.print(resultSet.getString("PasswordGame") + " - ");
             System.out.print(resultSet.getInt("Price") + " - ");
             System.out.print(resultSet.getInt("GameId") + " - ");
             System.out.print(resultSet.getString("NameGame") + " - ");
             System.out.print(resultSet.getString("Platform") + " - ");
             System.out.print(resultSet.getString("Genre"));
             System.out.println();
             // Select a account
            accountGame = new AccountGame(resultSet.getInt("IngameId"),resultSet.getString("UserNameGame"), resultSet.getString("PasswordGame"),resultSet.getInt("Price"), resultSet.getInt("Status"), resultSet.getInt("GameId") );
            price = resultSet.getInt("GameId");
            }
        AccountGame_SQL.UpdateStatusAccount(connection, IngameId);
        Transaction transaction = TransactionService.AddATransaction(accountGame, user);
        int id = User_SQL.SearchIDAUser(connection, user);
        Transaction_SQL.AddATransaction(connection,transaction, id,price);
        } catch (Exception e) {
            System.out.println("Mua acc thất bại");
            System.out.println(e.getMessage());
        }
    }
    public static boolean  CheckUserExit(SQLConnection connection, String username, String password){
         try {
            String query = "select * from users where UserName = \"" + username + "\"" + " and Password = \"" + password +"\"";
            ResultSet rs = connection.executeQuery(query);
            if(rs.next())
                return true;
            else 
                return false;
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        return false;
    } 
    public static User LoginUser(SQLConnection connection, String username){
         try {
            String query = "select * from users where   UserName = \"" + username + "\"";
            ResultSet rs = connection.executeQuery(query);
            return new User(rs.getInt("UserID"), rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("Role"));
        } catch (Exception e) {
             System.out.println("Log in user failed");
             return new User();
        }
    }
    public static String CheckRoleUser(SQLConnection connection, User user){
        try {
            String query = "select * from users where UserName like  '" + user.getUserName() + "'";
            ResultSet rs = connection.executeQuery(query);
            String role = "Check role fail";
            if( rs.next()){
                role = rs.getString("Role");
            }
            return role;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    
}
