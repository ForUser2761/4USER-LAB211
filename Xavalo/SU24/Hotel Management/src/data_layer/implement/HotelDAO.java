/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data_layer.implement;

import business_layer.config.CommonConfig;
import business_layer.entity.Hotel;
import data_layer.IHotelDAO;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class HotelDAO implements IHotelDAO<Hotel>{

    private List<Hotel> hotelList = new ArrayList<>();

    /**
     * Retrieves the list of hotels.
     *
     * @return The list of hotels.
     */
    public List<Hotel> getHotelList() {
        return hotelList;
    }

    public HotelDAO() {
        loadDataFromFile();
    }

    /**
     * Loads data from a file and populates the hotelList. The file should be in
     * the format: id|name|roomAvailable|address|phone|rating Each line in the
     * file represents a hotel.
     *
     * @throws IOException if there is an error reading the file
     */
    @Override
    public void loadDataFromFile() {
        hotelList.clear();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(CommonConfig.FILE_NAME); // replace with your file path
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split("[|]"); // assuming data is comma-separated
                String id = data[0].trim();
                String name = data[1].trim();
                int roomAvailable = Integer.parseInt(data[2].trim());
                String address = data[3].trim();
                String phone = data[4].trim();
                int rating = Integer.parseInt(data[5].trim());
                Hotel hotel = new Hotel(id, name, roomAvailable, address, phone, rating);
                hotelList.add(hotel);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    /**
     * Writes the hotel data to a file.
     *
     * @throws Exception if an error occurs while writing to the file.
     */
    @Override
    public void writeToFile() throws Exception {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(CommonConfig.FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Hotel hotel : hotelList) {
                String line = hotel.getId()
                        + " | " + hotel.getName()
                        + " | " + hotel.getRoom_available()
                        + " | " + hotel.getAddress()
                        + " | " + hotel.getPhone()
                        + " | " + hotel.getRating();
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }

    /**
     * Adds a hotel to the hotel list and writes the updated list to a file.
     *
     * @param hotel the hotel to be added
     * @throws Exception if an error occurs while adding the hotel or writing to
     * the file
     */
    public void addHotel(Hotel hotel) throws Exception {
        hotelList.add(hotel);
        writeToFile();
    }

    /**
     * Deletes a hotel from the hotel list and updates the data file.
     *
     * @param hotel the hotel to be deleted
     * @throws Exception if an error occurs while deleting the hotel or updating
     * the data file
     */
    public void delete(Hotel hotel) throws Exception {
        hotelList.remove(hotel);
        writeToFile();
    }

    /**
     * Finds hotels by their ID.
     * 
     * @param id the ID to search for
     * @return a list of hotels matching the given ID
     */
    /**
     * Finds hotels by ID.
     * 
     * @param id the ID to search for
     * @return a list of hotels matching the given ID
     */
    public List<Hotel> findById(String id) {
        List<Hotel> list = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            // Add hotel to the list if the ID contains the given ID
            if (hotel.getId().toUpperCase().contains(id.toUpperCase())) {
                list.add(hotel);
            }
        }
        return list;
    }

    /**
     * Finds hotels by name.
     * 
     * @param name the name to search for
     * @return a list of hotels matching the given name
     */
    public List<Hotel> findByName(String name) {
        List<Hotel> list = new ArrayList<>();
        for (Hotel hotel : hotelList) {
            // Add hotel to the list if the name contains the given name
            if (hotel.getName().toUpperCase().contains(name.toUpperCase())) {
                list.add(hotel);
            }
        }
        return list;
    }
}
