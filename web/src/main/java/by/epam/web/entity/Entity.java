package by.epam.web.entity;

import java.io.Serializable;

public abstract class Entity implements Serializable, Cloneable {
    private int id;
    private State state = State.UNBLOCKED;

    public Entity() {
    }

    public Entity(int id) {
        this.id = id;
    }

    public Entity(int id, State state) {
        this.id = id;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
