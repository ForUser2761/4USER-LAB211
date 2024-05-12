package business_layer;

import business_layer.validate.Validate;

import java.util.List;

import business_layer.entity.Equipment;
import business_layer.validate.Constant;
import data_layer.EquipmentDAO;

public class EquipmentUI {
    EquipmentDAO equipmentDAO = new EquipmentDAO();

    /**
     * Input equipment information
     */
    public void input() {
        //input equipment
        //id, name, type, quantity, condition
        String id = Validate.getString("Enter id: ", "Id must be a string, in format Exxxxxx", Constant.EQUIPMENT_ID);
        String name = Validate.getString("Enter name: ", "Name must be a string", Constant.STRING_REGEX);        
        int type = Validate.getInteger("1. Cardio\n2. Strength\n3. Accessories\nEnter equipment type: ", "Equipment type must be a number", 1, 3);
        int quantity = Validate.getInteger("Enter quantity: ", "Quantity must be a number", 0, Integer.MAX_VALUE);
        int condition = Validate.getInteger("1. New\n2. Good\n3. Worn\n4. Damaged\nEnter equipment condition: ", "Equipment condition must be a number", 1, 4);        
        
        //create equipment object
        Equipment equipment = new Equipment(id, name, type, quantity, condition);
        //add equipment to database
        try {
            equipmentDAO.addEquipment(equipment);
            System.out.println("Equipment added successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
    }

    /**
     * Sort and print the list of equipment ascending by name
     */
    public void sortAscendingByName() {
        //get list of equipment
        List<Equipment> equipmentList = equipmentDAO.getEquipmentList();
        //check list empty
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment to sort");
            return;
        }
        //clone equipment list
        List<Equipment> cloneList = List.copyOf(equipmentList);
        //sort list of equipment by name
        cloneList.sort((e1, e2) -> e1.getName().compareTo(e2.getName()));
        //display format
        System.out.println(String.format("%-15s %-15s %-15s %-15s %-15s ", "Id", "Name", "Type", "Quantity", "Condition"));
        //print list of equipment
        cloneList.forEach(System.out::println);        
    }

    /**
     * View and update existing equipment information
     */
    public void viewAndUpdate() {
        List<Equipment> equipmentList = equipmentDAO.getEquipmentList();
        //check list empty
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment to view and update");
            return;
        }
        //get equipment id
        String equipmentId = Validate.getString("Enter equipment id: ", "Equipment id must be a string, in format Exxxxxx", Constant.EQUIPMENT_ID);
        //get equipment by id
        Equipment equipment = equipmentDAO.getEquipmentById(equipmentId);
        //check equipment null
        if (equipment == null) {
            System.out.println("Equipment not found");
            return;
        }
        //display equipment
        System.out.println(equipment);
        //input new equipment
        //name, type, quantity, condition
        String name = Validate.getString("Enter name: ", "Name must be a string", Constant.STRING_REGEX);
        String type = Validate.getString("1. Cardio\n2. Strength\n3. Accessories\nEnter equipment type: ", "Equipment type must be a number", "([1-3]|\\s*)");
        String quantity = Validate.getString("Enter quantity: ", "Quantity must be a number", "([0-9]|\\s*)");
        String condition = Validate.getString("1. New\n2. Good\n3. Worn\n4. Damaged\nEnter equipment condition: ", "Equipment condition must be a number", "([1-4]|\\s*)");
        //update equipment
        if (name != null || !name.isEmpty()) {
            equipment.setName(name);
        }
        if (type != null || !type.isEmpty()) {
            equipment.setType(Integer.parseInt(type));
        }
        if (quantity != null || !quantity.isEmpty()) {
            equipment.setQuantity(Integer.parseInt(quantity));
        }
        if (condition != null || !condition.isEmpty()) {
            equipment.setCondition(Integer.parseInt(condition));
        }
        //update equipment to database
        try {
            equipmentDAO.updateEquipment(equipment);
            System.out.println("Equipment updated successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Delete equipment
     */
    public void delete() {
        //get list of equipment
        List<Equipment> equipmentList = equipmentDAO.getEquipmentList();
        //check list empty
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment to delete");
            return;
        }
        //get equipment id
        String equipmentId = Validate.getString("Enter equipment id: ", "Equipment id must be a string, in format Exxxxxx", Constant.EQUIPMENT_ID);
        //delete equipment
        try {
            equipmentDAO.deleteEquipment(equipmentId);
            System.out.println("Equipment deleted successfully !!");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
