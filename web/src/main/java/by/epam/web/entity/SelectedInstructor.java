package by.epam.web.entity;

public class SelectedInstructor extends Entity {
    private int userId;
    private int instructorId;

    public SelectedInstructor(){}

    public SelectedInstructor(int userId, int instructorId) {
        this.userId = userId;
        this.instructorId = instructorId;
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
