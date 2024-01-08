
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author ADMIN
 */
public class LinearSearch {

    private int[] array;

    int inputNumberOfArray() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter number of array: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.err.println("Input cannot be empty");
                } else {
                    int number = Integer.parseInt(input);
                    //check number in range
                    if (number >= 0 && number <= Integer.MAX_VALUE) {
                        return number;
                    }
                }
            } catch (Exception e) {
                System.err.println("Must be number");
            }
        }
    }

    void createArray(int sizeArray) {
        array = new int[sizeArray];
    }

    void generateRandomNumber() {
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            //sinh ra cac phan tu ngau nhien
            int randomNumber = random.nextInt(array.length);
            //gan cac phan tu do vao ben trong mang
            array[i] = randomNumber;
        }
    }

    void displayArray(String message) {
        System.out.print(message + "[");
        for (int i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + ", ");
        }
        System.out.print(array[array.length - 1]);
        System.out.println("]");
    }

    int inputSearchValue() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter search number: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.err.println("Input cannot be empty");
                } else {
                    int number = Integer.parseInt(input);
                    //check number in range
                    if (number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE) {
                        return number;
                    }
                }
            } catch (Exception e) {
                System.err.println("Must be number");
            }
        }
    }

    void displayIndexOfSearchNumber(int searchValue) {
        //search
        List<Integer> list = searchByLinearSearch(searchValue);
        //display
        //if index = -1 <=> ko tim thay
        if (list.isEmpty()) {
            System.out.println("Not found");
        }else {
            System.out.println("Index is: " + list.toString());
        }
    }

    private List<Integer> searchByLinearSearch(int searchValue) {
        //tao ra 1 arraylist o day
        List<Integer> list = new ArrayList<>();
        //lap qua tung phan tu o ben trong mang
        for (int i = 0; i < array.length; i++) {
            int element = array[i];
            //kiem tra xem phan tu do co bang voi search value 
            if (element == searchValue) {
                //neu nhu bang search value => tra ve vi tri index
                list.add(i);
            }
        }
        return list;
    }
}
