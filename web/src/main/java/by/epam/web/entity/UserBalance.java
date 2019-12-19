package by.epam.web.entity;

public class UserBalance extends Entity {
    private int userId;
    private int amount;

    public UserBalance() {
    }

    public UserBalance(int userId, int amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
