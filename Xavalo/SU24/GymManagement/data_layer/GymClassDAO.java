package data_layer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import business_layer.entity.GymClass;
import business_layer.validate.Constant;

public class GymClassDAO {
    public static List<GymClass> gymClassList = new ArrayList<>();

    public void addGymClass(GymClass gymClass) {
        gymClassList.add(gymClass);
        writeToFile();
    }

    private void writeToFile() {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(Constant.GYM_CLASS_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (GymClass gymClass : gymClassList) {
                bufferedWriter.write(gymClass.stringToFile());
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

    private void loadDataFromFile() {
        gymClassList.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(Constant.GYM_CLASS_FILE_NAME);
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("\\|");
                GymClass gymClass = new GymClass(data[0].trim(),
                        data[1].trim(),
                        data[2].trim(),
                        Integer.parseInt(data[3].trim()));
                // Add members
                String[] memberIds = data[4].split(",");
                for (String memberId : memberIds) {
                    gymClass.addMember(memberId);
                }
                // Add equipment
                String[] equipmentIds = data[5].split(",");
                for (String equipmentId : equipmentIds) {
                    gymClass.addEquipment(equipmentId);
                }
                gymClassList.add(gymClass);
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

    public List<GymClass> getGymClassList() {
        loadDataFromFile();
        return gymClassList;
    }

    public void updateGymClass(GymClass gymClass) {
        writeToFile();
    }

    public void deleteGymClass(GymClass gymClass) {
        gymClassList.remove(gymClass);
        writeToFile();
    }

}
