package data_layer.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import business_layer.entity.User;
import data_layer.IGenericDAO;

public class UserDAO implements IGenericDAO<User> {
    private List<User> listUser;

    public UserDAO() {
        listUser = new ArrayList<>();
    }

    @Override
    public void loadDataFromFile() throws Exception {
        BufferedReader reader = null;
        try {
            File file = new File(USER_FILE);
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("New user file created: " + USER_FILE);
            }
            reader = new BufferedReader(new FileReader(USER_FILE));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split("\\|");
                if (userData.length == 6) {
                    User user = new User(userData[0].trim(), userData[1].trim(), userData[2].trim(),
                            userData[3].trim(), userData[4].trim(), Boolean.parseBoolean(userData[5].trim()));
                    listUser.add(user);
                } else {
                    System.out.println("Incorrect user data format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading user data from file: " + e.getMessage());
            throw e;
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Error closing the BufferedReader: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void writeToFile() throws Exception {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(USER_FILE));
            for (User user : listUser) {
                writer.write(user.getUserProperties());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error " + e.getMessage());
            throw e;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.out.println("Error closing the BufferedWriter: " + e.getMessage());
                }
            }
        }
    }

    @Override
    public void insert(User user) throws Exception {
        listUser.add(user);
        writeToFile();
    }

    @Override
    public void clear() {
        listUser.clear();
    }

    public User getUserByID(String userId) {
        clear();
        try {
            loadDataFromFile();
            for (User user : listUser) {
                if (user.getUserId().equals(userId)) {
                    return user;
                }
            }
            // If user is not found, return null
            return null;
        } catch (Exception e) {
            System.out.println("Error getting user by ID: " + e.getMessage());
            return null;
        }
    }

    public void deleteUser(String userId) throws Exception {
        boolean found = false;
        for (User user : listUser) {
            if (user.getUserId().equals(userId)) {
                user.setActiveUser(false);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("User not found.");
        }
        writeToFile();
    }

    public List<User> getListUser() {
        clear();
        try {
            loadDataFromFile();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return listUser;
    }

    public void updateUser(User user) throws Exception {
        boolean found = false;
        for (int i = 0; i < listUser.size(); i++) {
            if (listUser.get(i).getUserId().equals(user.getUserId())) {
                listUser.set(i, user);
                found = true;
                break;
            }
        }
        if (!found) {
            throw new Exception("User not found.");
        }
        writeToFile();
    }
}
