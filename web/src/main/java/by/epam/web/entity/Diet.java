package by.epam.web.entity;

public class Diet extends Entity {
    private String name;
    private String description;

    public Diet(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Diet() {
    }

    public Diet(int id, State state, String name, String description) {
        super(id, state);
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diet diet = (Diet) o;

        if (name != null ? !name.equals(diet.name) : diet.name != null) return false;
        return description != null ? description.equals(diet.description) : diet.description == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
