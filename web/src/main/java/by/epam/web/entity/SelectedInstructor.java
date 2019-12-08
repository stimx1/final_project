package by.epam.web.entity;

public class SelectedInstructor extends Entity {
    private int id;
    private int userId;
    private int instructorId;

    public SelectedInstructor(){}

    public SelectedInstructor(int userId, int instructorId) {
        this.userId = userId;
        this.instructorId = instructorId;
    }

    public SelectedInstructor(int id, int userId, int instructorId) {
        this(userId,instructorId);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }
}
