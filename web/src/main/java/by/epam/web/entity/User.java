package by.epam.web.entity;

import java.util.StringJoiner;

public class User extends Entity{
    private String email;
    private String pass;
    private String lastName;
    private String firstName;
    private UserRole role;

    public User(){}

    public User(String pass, String email,String lastName, String firstName, UserRole role) {
        this.pass = pass;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.role = role;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("email='" + email + "'")
                .add("pass='" + pass + "'")
                .add("lastName='" + lastName + "'")
                .add("firstName='" + firstName + "'")
                .add("role=" + role)
                .toString();
    }
}
