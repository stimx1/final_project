package by.epam.web.entity;

public class Diet extends Entity {
    private int id;
    private String name;
    private String description;

    public Diet(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Diet(){
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
