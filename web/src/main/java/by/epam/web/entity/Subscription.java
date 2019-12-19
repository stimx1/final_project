package by.epam.web.entity;

import java.time.LocalDate;

public class Subscription extends Entity {
    private String name;
    private int price;
    private int duration;
    private LocalDate startDay;
    private LocalDate endDay;

    public Subscription() {
    }

    public Subscription(String name, int price, int duration) {
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public Subscription(int id, State state, String name, int price, int duration) {
        super(id, state);
        this.name = name;
        this.price = price;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (price != that.price) return false;
        if (duration != that.duration) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (startDay != null ? !startDay.equals(that.startDay) : that.startDay != null) return false;
        return endDay != null ? endDay.equals(that.endDay) : that.endDay == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + price;
        result = 31 * result + duration;
        result = 31 * result + (startDay != null ? startDay.hashCode() : 0);
        result = 31 * result + (endDay != null ? endDay.hashCode() : 0);
        return result;
    }
}
