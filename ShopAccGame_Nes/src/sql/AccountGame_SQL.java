package sql;

import entity.AccountGame;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AccountGame_SQL {
    public static void ShowAllAccounts_Admin(SQLConnection connection) throws SQLException {
        try {
            String query = "select * from accountgame";
            ResultSet resultSet = connection.executeQuery(query);
            System.out.println("IngameId - UserNameGame - PassWordGame - Price - Status - GameId");
        while(resultSet.next()){
            System.out.print(resultSet.getInt("IngameId") + " - ");
            System.out.print(resultSet.getString("UserNameGame") + " - ");
            System.out.print(resultSet.getString("PassWordGame") + " - ");
            System.out.print(resultSet.getInt("Price") + " - ");
            System.out.print(resultSet.getInt("Status") + " - ");
            System.out.print(resultSet.getInt("GameId"));
            System.out.println("");
        }
        } catch (Exception e) {
            System.out.println("Select All Accounts thất bại");
        }
    }
    public static List<AccountGame> AccountGameExport(SQLConnection connection) throws SQLException{
        try {
            String query = "select * from accountgame";
            ResultSet resultSet = connection.executeQuery(query);
            List<AccountGame> listAccountGames = new ArrayList<>();
            while(resultSet.next()){
                AccountGame newAccountGame = new AccountGame(resultSet.getInt("IngameId"),resultSet.getString("UserNameGame"),resultSet.getString("PassWordGame"),resultSet.getInt("Price"),resultSet.getInt("Status"),resultSet.getInt("GameId"));
                listAccountGames.add(newAccountGame);
            }
            return listAccountGames;
        } catch (Exception e) {
            System.out.println("Ghi file accountgame có vấn đề");
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void ShowAllAccounts_User(SQLConnection connection) throws SQLException {
        try {
            String query = "select * from accountgame, kindofgame where accountgame.GameId = kindofgame.GameId";
            ResultSet resultSet = connection.executeQuery(query);
        while(resultSet.next()){
            if(resultSet.getInt("Status") == 0)
                continue;
            else {
                System.out.print(resultSet.getString("UserNameGame") + " - ");
                System.out.print(resultSet.getInt("Price") + " - ");
                System.out.print(resultSet.getString("NameGame") + " - ");
                System.out.print(resultSet.getString("Platform") + " - ");
                System.out.print(resultSet.getString("Genre"));
                System.out.println();
            }
            
        }
        } catch (Exception e) {
            System.out.println("Select All Accounts User thất bại");
            System.out.println(e.getMessage());
        }
    }
    public static void AddAnAccount(SQLConnection connection ,AccountGame accountGame) throws SQLException{
        try {
            String query = "insert into accountgame (IngameId,UsernameGame, PasswordGame, Price,Status, GameId) values ('";
            query += accountGame.getIngameId() + "', '" + accountGame.getUserNameGame()+ "', '" + accountGame.getPasswordGame() + "', '" + accountGame.getPrice() + "', '" + accountGame.getStatus() + "', " + accountGame.getGameId() + " )";
            connection.executeUpdate(query);
            System.out.println("Thêm account thành công");
        } catch (Exception e) {
            System.out.println("Thêm account không thành công");
            System.out.println(e.getMessage());
        }
    }
    public static void DeleteAnAccount(SQLConnection connection, int IngameId) throws SQLException{
        
        try {
            String query = "delete from accountgame where IngameId = " + IngameId;
            connection.executeUpdate(query);
            System.out.println("Xóa account game với ingameId = " + IngameId + " thành công");
        } catch (Exception e) {
            System.out.println("Delete an account with id " + IngameId + " fail");
            System.out.println(e.getMessage());
        }
        
    }
    public static void SearchAnAccount(SQLConnection connection, int IngameId) throws SQLException{
        try {
            String query = "select * from accountgame where IngameId =  " + IngameId;
            ResultSet rs = connection.executeQuery(query);
             while (rs.next()) {
            System.out.print(rs.getInt("IngameId") + " - ");
            System.out.print(rs.getString("UserNameGame") + " - ");
//          System.out.print(rs.getString("PasswordGame") + " - "); 
            System.out.print(rs.getInt("Price") + " - ");
            if(rs.getInt("Status") == 1)
                System.out.print("Còn hàng" + " - "); 
            else 
                System.out.println("Hết hàng" + " - ");
            System.out.print(rs.getInt("GameId"));
            System.out.println("");
             }
        } catch (Exception e) {
            System.out.println("Tìm kiếm account xuất hiện lỗi");
            System.out.println(e.getMessage());
        }
    }
    public static void UpdateStatusAccount(SQLConnection connection, int IngameId) throws SQLException{
        try {
            String query = "update accountgame set Status = 0 where IngameId = " + IngameId;
            connection.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Update status account failed");
            System.out.println(e.getMessage());
        }
    }
}
