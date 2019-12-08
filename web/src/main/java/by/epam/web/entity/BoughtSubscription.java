package by.epam.web.entity;

import java.time.LocalDate;

public class BoughtSubscription extends Entity{
    private int id;
    private int subscriptionId;
    private int userId;
    private int price;
    private LocalDate startDay;
    private LocalDate endDay;

    public BoughtSubscription(){
    }

    public BoughtSubscription(int subscriptionId, int userId, int price, LocalDate startDay, LocalDate endDay) {
        this.subscriptionId = subscriptionId;
        this.userId = userId;
        this.price = price;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }
}
