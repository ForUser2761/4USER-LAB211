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
        LinearSearch linearSearch = new LinearSearch();
        //allows users to input the number of array
        int sizeArray = linearSearch.inputNumberOfArray();

        //create array
        linearSearch.createArray(sizeArray);

        //Generate random integer in number range input
        linearSearch.generateRandomNumber();
        
        //Display unsorted array 
        linearSearch.displayArray("Array: ");
        //enter search value
        int searchValue = linearSearch.inputSearchValue();
        
        //display index of search number
        linearSearch.displayIndexOfSearchNumber(searchValue);
    }
}
