package data_layer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import business_layer.entity.Equipment;
import business_layer.validate.Constant;

public class EquipmentDAO {
    List<Equipment> equipmentList = new ArrayList<Equipment>();

    public EquipmentDAO() {
        loadDataFromFile();
    }

    /**
     * Add equipment to list
     * @param equipment
     */
    public void addEquipment(Equipment equipment) {
        //check equipment id exists
        Equipment equipmentById = getEquipmentById(equipment.getEquipmentId());
        if (equipmentById != null) {
            throw new IllegalArgumentException("Equipment id already exists");
        }

        //add equipment to list
        equipmentList.add(equipment);
        writeToFile();
    }

    /**
     * Write data to file
     */
    private void writeToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(Constant.EQUIPMENT_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Equipment equipment : equipmentList) {
                bufferedWriter.write(equipment.StringToFile());                    
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * Load data from file
     */
    private void loadDataFromFile() {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(Constant.EQUIPMENT_FILE_NAME);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                Equipment equipment = new Equipment(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
                equipmentList.add(equipment);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }
    /**
     * Get equipment by id
     * @param equipmentId
     * @return equipment
     */
    public Equipment getEquipmentById(String equipmentId) {
        //search equipment by id
        for (Equipment equipment : equipmentList) {
            if (equipment.getEquipmentId().equals(equipmentId)) {
                return equipment;
            }
        }
        return null;
    }

    /**
     * Get list of equipment
     * @return list of equipment
     */
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    /**
     * Delete equipment by id
     * @param equipmentId
     */
    public void deleteEquipment(String equipmentId) {
        //search equipment by id
        Equipment equipment = getEquipmentById(equipmentId);
        if (equipment == null) {
            throw new IllegalArgumentException("Equipment not found");
        }
        //remove equipment
        equipmentList.remove(equipment);
        writeToFile();
    }

    public void updateEquipment(Equipment equipment) {
        writeToFile();
    }
}
