/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ADMIN
 */
public class Main {

    public static void main(String[] args) {
        BubbleSort bubbleSort = new BubbleSort();
        //allows users to input the number of array
        int sizeArray = bubbleSort.inputNumberOfArray();

        //create array
        bubbleSort.createArray(sizeArray);

        //Generate random integer in number range input
        bubbleSort.generateRandomNumber();

        //Display unsorted array 
        bubbleSort.displayArray("Unsorted array: ");

        //sort by bubble sort
        bubbleSort.sortByBubbleSort();
        //Display sorted array 
        bubbleSort.displayArray("Sorted array: ");

    }
}
