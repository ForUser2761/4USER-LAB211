package data_layer.implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import business_layer.entity.Order;
import data_layer.IGenericDAO;

public class OrderDAO implements IGenericDAO<Order> {
    private static List<Order> orderList = new ArrayList<>();

    public OrderDAO() {
        try {
            loadDataFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void loadDataFromFile() throws Exception {
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            File file = new File(IGenericDAO.ORDER_FILE_NAME); // replace with your file path
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split("\\|");
                Order order = new Order();
                order.setCode(parts[0].trim());
                order.setDrinkCode(parts[1].trim());
                order.setQuantity(Integer.parseInt(parts[2].trim()));
                orderList.add(order);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile() throws Exception {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(IGenericDAO.ORDER_FILE_NAME);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Order order : orderList) {
                // write in format code|drinkCode|quantity
                String line = order.getCode() + "|" + order.getDrinkCode() + "|" + order.getQuantity() + "|";
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                bufferedWriter.close();
                fileWriter.close();
            }
        }
    }

    @Override
    public void insert(Order t) throws Exception {
        // set code for order
        t.setCode("O" + String.format("%03d", orderList.size() + 1));
        orderList.add(t);
        writeToFile();
    }

    @Override
    public void clear() {
        orderList.clear();
    }

}
