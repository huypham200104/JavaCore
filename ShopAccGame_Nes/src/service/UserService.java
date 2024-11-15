package service;
import entity.User;
import sql.AccountGame_SQL;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import sql.SQLConnection;
import static service.GeneralService.EndProgramming;
import sql.User_SQL;

public class UserService {
    public static void GeneralUserService(SQLConnection connection, User user, int choice) throws SQLException{
        Scanner sc = new Scanner(System.in);
        switch (choice) {
//        System.out.println("1. Xem danh sách acc");
//        System.out.println("2. Mua acc (nhâp id acc)");
//        System.out.println("3. Tìm kiếm acc");
//        System.out.println("4. Thoát");
                            case 1:{
                                AccountGame_SQL.ShowAllAccounts_User(connection);
                                break; 
                            }
                            case 2: {
                                int IngameId = AdminService.InputIngameId();
                                User_SQL.BuyAnAccount(connection,IngameId, user);
                                break;
                            }  
                            case 3: {
                                int IngameId = AdminService.InputIngameId();
                                AccountGame_SQL.SearchAnAccount(connection, IngameId);
                                break;
                            }
                            case 4:{
                                EndProgramming();
                                break;
                            }
                        }
    }
    public static int InputAnGameAcco(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập ingameID: ");
        int IngameId;
        IngameId = scanner.nextInt();
        return IngameId;
    }
    
     public static boolean isValidEmail(String email) {
        // Biểu thức chính quy để kiểm tra định dạng email
        String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        // Tạo đối tượng Pattern từ biểu thức chính quy
        Pattern pattern = Pattern.compile(emailRegex);
        // Tạo đối tượng Matcher để kiểm tra email
        Matcher matcher = pattern.matcher(email);
        // Trả về kết quả kiểm tra
        return matcher.matches();
    }
     
    public static User InputAUser(){
        Scanner scanner = new Scanner(System.in);
            //int UserID	
            //String UserName	
            //String Email	
            //String Password	
            //String Role
        System.out.print("Nhập UserName: ");
        String username = scanner.nextLine();
        String email;
        do{
            System.out.print("Nhập Email: ");
            email = scanner.nextLine();
            if(!isValidEmail(email))
                System.out.println("Email vừa nhập không hợp lệ");
        } while(!isValidEmail(email));
        System.out.print("Nhập Password: ");
        String password = scanner.nextLine();
        User user = new User(username,email,password,"customer");
        return user;
    }
    public static boolean CheckConditionPassword(String password){
        if(password == null){
            return false;
        } else if(password.length() < 8 || password.length() > 12){
            return false;
        } else if (!password.matches(".*[a-z].*")) {
            return false;
        } else if (!password.matches(".*[A-Z].*")) {
            return false;
        } if (!password.matches(".*\\d.*")) {
            return false;
        } else if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            return false;
        }
        return true;   
    }
    public static User SignUpAUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập UserName: ");
        String username = scanner.nextLine();
        System.out.print("Nhập Email: ");
        String email = scanner.nextLine();
        String password;
        do{
           System.out.println("Yêu cầu mật khẩu");
            System.out.println("1. 8-12 kí tự");
            System.out.println("2. 1 kí tự thường");
            System.out.println("3. 1 kí tự HOA");
            System.out.println("4. 1 kí tự đặc biệt");
            System.out.print("Nhập Password: ");
            password = scanner.nextLine(); 
        } while(!CheckConditionPassword(password) );
        User user = new User(username,email,password,"customer");
        return user;
    }
    
    public static int InputAUserId(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập user id: ");
        int UserID = sc.nextInt();
        return UserID;
    }

  
}
