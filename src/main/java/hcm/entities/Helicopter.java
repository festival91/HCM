package hcm.entities;

public class Helicopter {

    private int id;

    private String modelName;

    public Helicopter(int id, String modelName) {
        this.id = id;
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

}
