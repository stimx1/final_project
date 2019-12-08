package by.epam.web.validation;

import by.epam.web.entity.UserBalance;

public class BalanceValidator {
    public static boolean validateTransaction(UserBalance userBalance, int amount){
        return userBalance.getAmount() >= amount;
    }
    public static boolean validateDeposit(UserBalance userBalance, int amount){
        return userBalance.getAmount()+amount <= 1000;
    }
}
