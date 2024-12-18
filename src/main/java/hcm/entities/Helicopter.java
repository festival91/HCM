package hcm.entities;

import java.time.LocalDate;

public class Helicopter {

    private int id;

    private String modelName;

    private LocalDate createdOn;

    public Helicopter(String modelName) {
        this.modelName = modelName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    @Override
    public String toString() {
        return "Helicopter ID: " + id +
                "\nModel Name: " + modelName +
                "\nCreated On: " + createdOn +
                "\n";
    }

}
