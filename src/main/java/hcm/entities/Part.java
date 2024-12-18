package hcm.entities;

public class Part {

    private int id;
    private String partName;
    private String partType;
    private int helicopterId;

    public Part(String partName, String partType, int helicopterId) {
        this.partName = partName;
        this.partType = partType;
        this.helicopterId = helicopterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public int getHelicopterId() {
        return helicopterId;
    }

    public void setHelicopterId(int helicopterId) {
        this.helicopterId = helicopterId;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    @Override
    public String toString() {
        return "Helicopter ID: " + id +
                "\nPart Name: " + partName +
                "\nCreated Type: " + partType +
                "\n";
    }
}
