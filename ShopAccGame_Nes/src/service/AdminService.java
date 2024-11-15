package service;

import entity.AccountGame;
import entity.User;
import java.sql.SQLException;
import java.util.Scanner;
import static service.GeneralService.EndProgramming;
import sql.AccountGame_SQL;
import sql.SQLConnection;
import sql.Transaction_SQL;
import sql.User_SQL;

public class AdminService {

    public static void GeneralAdminService(SQLConnection connection, int adminChoice) throws SQLException{
        switch (adminChoice) {
//        System.out.println("1. Xem danh sách acc");
//        System.out.println("2. Thêm acc");
//        System.out.println("3. Xóa acc");
//        System.out.println("4. Tìm kiếm acc");
//        System.out.println("5. Xem lịch sử giao dịch");
//        System.out.println("6. Xem account user");
//        System.out.println("7. Thêm user");
//        System.out.println("8. Xóa user");
//        System.out.println("9. Tìm kiếm user");
//        System.out.println("10. Xuất file user, account và transaction");
//        System.out.println("11. Thoát");
                                case 1:{
                                    AccountGame_SQL.ShowAllAccounts_Admin(connection);
                                    break;  
                                }
                                case 2: {
                                    AccountGame accountGame = AdminService.InputAnAccountGame();
                                    AccountGame_SQL.AddAnAccount(connection, accountGame);
                                    break;
                                }
                                case 3: {
                                    int IngameId = AdminService.InputIngameId();
                                    AccountGame_SQL.DeleteAnAccount(connection, IngameId);
                                    break;
                                }
                                case 4: {
                                    int IngameId = AdminService.InputIngameId();
                                    AccountGame_SQL.SearchAnAccount(connection, IngameId);
                                    break;
                                }
                                case 5: {
                                    Transaction_SQL.ShowAllTransaction(connection);
                                    break;
                                }
                                case 6: {
                                    User_SQL.ShowAllUser(connection);
                                    break;
                                }
                                case 7: {
                                    User newUser = UserService.InputAUser();
                                    User_SQL.AddAUser(connection, newUser);
                                    break;
                                }
                                case 8: {
                                    int UserID = UserService.InputAUserId();
                                    User_SQL.DeleteAUser(connection,UserID);
                                    break;
                                }
                                case 9: {
                                    int UserID = UserService.InputAUserId();
                                    User_SQL.SearchAnUser(connection, UserID);
                                    break;
                                }
                                case 10: {
                                    String table = InputATable();
                                    GeneralService.DataExport(connection, table);
                                    break;
                                }
                                case 11:{
                                    EndProgramming();
                                }
                            }
    }
        public static AccountGame InputAnAccountGame(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhâp id ingame: ");
            int IngameId = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Nhập username: ");
            String username = scanner.nextLine();
            System.out.print("Nhập password: ");
            String password = scanner.nextLine();
            System.out.print("Nhập giá: ");
            int price = scanner.nextInt();
            System.out.print("Nhập trạng thái: ");
            int status = scanner.nextInt();
            System.out.print("Nhập game id: ");
            int gameid = scanner.nextByte();
            AccountGame accountGame = new AccountGame(IngameId,username,password,price,status,gameid);
            return accountGame;
        }
        public static int InputIngameId(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập IngameId: ");
            int IngameId = scanner.nextInt();
            return IngameId;
        }
        public static int InputIdTransaction(){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nhập IdTransaction: ");
            int IdTransaction = scanner.nextInt();
            return IdTransaction;
        }
        public static String InputATable(){
            Scanner scanner = new Scanner(System.in);
            String table = "";
            do{
                System.out.println("accountgame - users - transactions");
                System.out.print("Nhập bảng bạn muốn xuất: ");
                table = scanner.nextLine();
                if(table.equals("accountgame")){
                    break;
                } else if(table.equals("users")){
                    break;
                } else if(table.equals("transactions")){
                    break;
                } else {
                    System.out.println("Bảng bạn chọn không tồn tại, vui lòng chọn lại");
                }
            } while(true);
            return table;
        }
}
