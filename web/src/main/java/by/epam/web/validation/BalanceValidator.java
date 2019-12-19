package by.epam.web.validation;

import by.epam.web.entity.UserBalance;

public class BalanceValidator {
    public static boolean validateTransaction(UserBalance userBalance, int amount) {
        return userBalance.getAmount() >= amount;
    }

    public static boolean validateDeposit(UserBalance userBalance, String amount) {
        return amount.matches("^\\d+$") && userBalance.getAmount() + Integer.parseInt(amount) <= 1000;
    }

}

