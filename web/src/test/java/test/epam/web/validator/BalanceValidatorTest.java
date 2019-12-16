package test.epam.web.validator;

import by.epam.web.entity.UserBalance;
import by.epam.web.validation.BalanceValidator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BalanceValidatorTest {

    UserBalance userBalance = new UserBalance(0,100);



    @Test
    void testValidateTransaction(){
        int amount = 10;
        boolean actual = BalanceValidator.validateTransaction(userBalance, amount);
        Assert.assertTrue(actual);
    }

    @Test
    void testValidateDeposit(){
        String amount = "1";
        boolean actual = BalanceValidator.validateDeposit(userBalance,amount);
        Assert.assertTrue(actual);
    }
}
