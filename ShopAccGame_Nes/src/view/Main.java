package view;

import entity.User;
import entity.AccountGame;
import entity.Transaction;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import service.GeneralService;

import java.util.Scanner;
import sql.SQLConnection;
public class Main {
    public static void main(String[] args) throws SQLException {
        
        // Khởi tạo biến
        Scanner scanner = new Scanner(System.in);
        SQLConnection connection = new SQLConnection();
        boolean  checkConnection = connection.StartConnection(connection);
        List<AccountGame> listAccountGame = new ArrayList<>();
        List<User> listUser = new ArrayList<>();
        List<Transaction> listTransactions = new ArrayList<>();
        // Function
        if(checkConnection){
            int choice;
            do {
                try{
                    GeneralView.WelcomeView();
                    choice = GeneralService.WelcomeService();
                    GeneralService.SelectAndDoChoice(connection,choice);
                    
            }   catch (Exception ex) {
                    System.out.println("Lựa chọn không tồn tại");
            }
            }while (true);  
        }   
        connection.EndConnection(connection);
    }
}
