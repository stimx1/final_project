package by.epam.web.entity;

public class Instructor extends Entity {
    private String firstName;
    private String lastName;
    private String info;

    public Instructor(String firstName, String lastName, String info) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.info = info;
    }

    public Instructor(int id, State state, String firstName, String lastName, String info) {
        super(id, state);
        this.firstName = firstName;
        this.lastName = lastName;
        this.info = info;
    }

    public Instructor(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
