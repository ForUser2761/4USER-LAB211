package sample.dto;

/* Interface for a group of objects
 */

/**
 *
 * @author Hoa Doan
 */
public interface I_List {
  // Find the position of element which has code equal parameter coe
  int find(String code);
  // add new element( input from scanner) to I_List
  boolean add(); 
  // Input the code wanna remove
  boolean remove();
  boolean remove(String code);
  // input the code want to update, after that update other information--> use set method
  boolean update();
  boolean update(String id);
  // sort list use Collections.sort(this, new Comparator<Clock>()..., sort based price or make
  void sort();
  // show detail of each element of List
  void output();
  void output2();
}
