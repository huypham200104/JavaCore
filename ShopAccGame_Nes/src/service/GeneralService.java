package service;

import sql.User_SQL;
import java.util.Scanner;
import sql.SQLConnection;
import java.sql.SQLException;
import entity.AccountGame;
import entity.User;
import java.util.ArrayList;
import java.util.List;
import sql.AccountGame_SQL;
import view.AdminView;
import view.UserView;
import entity.Transaction;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import sql.Transaction_SQL;

public class GeneralService {

    public static int WelcomeService() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            choice = scanner.nextInt();
            if (choice <= 0 || choice > 3) {
                System.out.println("Chức năng không tồn tại");
                System.out.print("Mời bạn nhập lại: ");
            }
        } while (choice <= 0 || choice > 3);
        return choice;
    }

    public static User LogIn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập username: ");
        String userName = scanner.nextLine();
        System.out.print("Nhập password: ");
        String password = scanner.nextLine();
        return new User(userName, password);
    }

    public static void SelectAndDoChoice(SQLConnection connection, int choice) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        AccountGame accountGame = new AccountGame();
        int IngameId = 0, IdTransaction = 0;
        switch (choice) {
            case 1: {
                // Đăng nhập
                User user = LogIn();
                String username = user.getUserName();
                String password = user.getPassword();
                if (User_SQL.CheckUserExit(connection, username, password) == true) {
                    System.out.println("Welcome " + user.getUserName());
                    String role = User_SQL.CheckRoleUser(connection, user);
                    if (role.equals("admin")) {
                        int adminChoice = 0;
                        try {
                            do {
                                do {
                                    AdminView.MenuOptionsAdmin();
                                    adminChoice = scanner.nextInt();
                                    AdminService.GeneralAdminService(connection, adminChoice);
                                    if ((adminChoice > 11 || adminChoice < 1)) {
                                        System.out.println("Lựa chọn không tồn tại");
                                    }
                                } while (adminChoice > 11 || adminChoice < 1);
                            } while (true);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else if (role.equals("customer")) {
                        int customerChoice = 0;
                        try {
                            do {
                                do {
                                    UserView.MenuOptionsUser();
                                    customerChoice = scanner.nextInt();
                                    UserService.GeneralUserService(connection, user, customerChoice);
                                    if ((customerChoice > 4 || customerChoice < 1)) {
                                        System.out.println("Lựa chọn không tồn tại");
                                    }
                                } while (customerChoice > 4 || customerChoice < 1);
                            } while (true);
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println(role);
                    }
                } else {
                    System.out.println("Sai tài khoản hoặc mật khẩu");
                }
                break;
            }
            case 2: {
                // Đăng kí
                User newUser = new User();
                newUser = UserService.SignUpAUser();
                User_SQL.AddAUser(connection, newUser);
                System.out.println("Tạo tài khoản " + newUser.getUserName() + " thành công");
                break;
            }
            case 3: {
                EndProgramming();
            }
        }
    }

    public static void EndProgramming() {
        System.out.println("Kết thúc chương trình");
        System.exit(0);
    }

    public static void DataExport(SQLConnection connection, String table) {
        try {
            StringBuilder content = new StringBuilder();
            List<AccountGame> listAccountGames = new ArrayList<>();
            List<User> listUsers = new ArrayList<>();
            List<Transaction> listTransactions = new ArrayList<>();
            String fileName = table + ".txt";
            if (table.equals("accountgame")) {
                listAccountGames = AccountGame_SQL.AccountGameExport(connection);
                for (AccountGame accountGame : listAccountGames) {
                    content.append(accountGame.toString()).append(System.lineSeparator());
                }
            } else if (table.equals("users")) {
                listUsers = User_SQL.UserExport(connection);
                for (User user : listUsers) {
                    content.append(user.toString()).append(System.lineSeparator());
                }
            } else if (table.equals("transactions")) {
                listTransactions = Transaction_SQL.TransactionExport(connection);
                for (Transaction transaction : listTransactions) {
                    content.append(transaction.toString()).append(System.lineSeparator());
                }
            }
            try {
                // Lấy đường dẫn hiện tại của chương trình
                Path currentRelativePath = Paths.get("");
                String currentPath = currentRelativePath.toAbsolutePath().toString();

                // Tạo file tại đường dẫn hiện tại
                Path filePath = Paths.get(currentPath, fileName);
                Files.write(filePath, content.toString().getBytes());
                System.out.println("Ghi dữ liệu từ danh sách giao dịch vào file " + fileName + " thành công.");
            } catch (Exception e) {
                System.err.println("Lỗi khi ghi file: " + e.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Hàm CreateFile trong GeneralService có vấn đề");
            System.out.println(e.getMessage());
        }
    }
}
