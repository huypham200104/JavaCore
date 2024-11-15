
package sql;

import entity.Transaction;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Transaction_SQL {
    public static void ShowAllTransaction(SQLConnection connection){
         try {
            String query = "select * from transactions";
            ResultSet rs = connection.executeQuery(query);
        while(rs.next()){
//              private int transactionId;
//        private int ingameId;
//        private Date transactionDate;
//        private int total;
//        private int userId;
               System.out.print(rs.getInt("TransactionID") + "/t");
                 System.out.print(rs.getInt("IngameID") + "/t") ;
                 System.out.print(rs.getDate("TransactionDate") + "/t");
                 System.out.print(rs.getInt("Total") + "/t");
                 System.out.print(rs.getInt("UserId")  );
                 System.out.println("");
        }
        } catch (Exception e) {
            System.out.println("Select All Transactions thất bại");
             System.out.println(e.getMessage());
        }
    }
    public static List<Transaction> TransactionExport(SQLConnection connection) throws SQLException{
        try {
            String query = "select * from transactions";
            ResultSet rs = connection.executeQuery(query);
            List<Transaction> listTransactions = new ArrayList<>();
            while(rs.next()){
                Transaction newTransaction = new Transaction(rs.getInt("TransactionID"),rs.getInt("IngameID"),rs.getDate("TransactionDate"),rs.getInt("Total"),rs.getInt("UserId"));
                listTransactions.add(newTransaction);
            }
            return listTransactions;
        } catch (Exception e) {
            System.out.println("Ghi file transaction có vấn đề");
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void AddATransaction(SQLConnection connection, Transaction transaction, int UserID,int price) throws SQLException {
    try {
        // Kiểm tra xem UserID có tồn tại trong bảng users hay không
        String checkUserQuery = "SELECT * FROM users WHERE UserID = " + UserID;
        ResultSet checkUserRs = connection.executeQuery(checkUserQuery);

        if (checkUserRs.next()) { // Người dùng tồn tại
            String query = "insert into transactions(IngameId, TransactionDate, Total, UserID) values ('";
            query += transaction.getIngameId() + "', '" + transaction.getTransactionDate() + "', '" + price + "', " + UserID + " )";
            connection.executeUpdate(query);
        } else {
            System.out.println("Lỗi: Không tìm thấy người dùng với UserID " + UserID);
        }
        
    } catch (Exception e) {
        System.out.println("Tạo giao dịch không thành công");
        System.out.println(e.getMessage());
    } 
}

    public static void SearchAnAccount(SQLConnection connection, int IdTransaction) throws SQLException{
        try {
            String query = "select * from transactions where TransactionID =  " + IdTransaction;
            ResultSet rs = connection.executeQuery(query);
             while (rs.next()) {
                 System.out.print(rs.getInt("TransactionID") + "/t");
                 System.out.print(rs.getInt("IngameID") + "/t") ;
                 System.out.print(rs.getDate("TransactionDate") + "/t");
                 System.out.print(rs.getInt("Total") + "/t");
                 System.out.print(rs.getInt("UserId")  );
                 System.out.println("");
             }
        } catch (Exception e) {
            System.out.println("Tìm kiếm transaction xuất hiện lỗi");
        }
    }
}
