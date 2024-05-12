package business_layer.entity;

public class Equipment {
    private String equipmentId;
    private String name;
    private int type; // Example: "Cardio", "Strength", "Accessories"
    private int quantity;
    private int condition;  // Example: "New", "Good", "Worn", "Damaged"

    // Constructor to initialize Equipment objects
    public Equipment(String equipmentId, String name, int type, int quantity, int condition) {
        this.equipmentId = equipmentId;
        this.name = name;
        this.type = type;
        this.quantity = quantity;
        this.condition = condition;
    }

    // Getters and Setters
    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCondition() {
        return condition;
    }

    public void setCondition(int condition) {
        this.condition = condition;
    }

    public String getConditionString() {
        switch (condition) {
            case 1:
                return "New";
            case 2:
                return "Good";
            case 3:
                return "Worn";
            case 4:
                return "Damaged";
            default:
                return "Unknown";
        }
    }

    public String getTypeString() {
        switch (type) {
            case 1:
                return "Cardio";
            case 2:
                return "Strength";
            case 3:
                return "Accessories";
            default:
                return "Unknown";
        }
    }

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s", equipmentId, name, getTypeString(), quantity, getConditionString());
    }

    public String StringToFile() {
        return String.format("%s|%s|%s|%s|%s", equipmentId, name, type, quantity, condition);
    }
}
