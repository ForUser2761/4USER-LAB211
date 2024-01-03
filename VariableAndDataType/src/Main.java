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
        int age = 18;

        if (age >= 18) {
            int a;
            String status = "Adult"; // Khai báo biến 'status' trong khối mã if
            System.out.println("You are an " + status);
        } else {
            String status = "Minor"; // Khai báo biến 'status' trong khối mã else
            System.out.println("You are a " + status);
        }

        // Biến 'status' không thể truy cập ở đây vì nó chỉ tồn tại trong khối if hoặc else
    }
}
