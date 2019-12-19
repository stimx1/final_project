package by.epam.web.entity;

public class AssignedDiet extends Entity {
    private int userId;
    private int dietId;

    public AssignedDiet() {
    }

    public AssignedDiet(int userId, int dietId) {
        this.userId = userId;
        this.dietId = dietId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDietId() {
        return dietId;
    }

    public void setDietId(int dietId) {
        this.dietId = dietId;
    }
}
