package by.epam.web.entity;

public class AssignedExercise extends Entity {
    private int userId;
    private int exerciseId;

    public AssignedExercise(){}

    public AssignedExercise(int userId, int exerciseId) {
        this.userId = userId;
        this.exerciseId = exerciseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(int exerciseId) {
        this.exerciseId = exerciseId;
    }
}
