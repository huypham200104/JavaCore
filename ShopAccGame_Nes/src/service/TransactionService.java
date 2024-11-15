package service;

import entity.AccountGame;
import entity.Transaction;
import entity.User;
import java.sql.Date;
import java.time.LocalDate;

public class TransactionService {
    public static Transaction AddATransaction(AccountGame accountGame, User user){
        LocalDate currentDate = LocalDate.now();
        Date date = new Date(currentDate.getYear(),currentDate.getMonthValue(), currentDate.getDayOfMonth());
        return new Transaction(accountGame.getGameId(), date, accountGame.getPrice(), user.getUserId());
    }
    
}
