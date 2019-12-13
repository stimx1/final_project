package by.epam.web.entity;

public class Diet extends Entity {
    private String name;
    private String description;

    public Diet(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Diet(){
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
}
