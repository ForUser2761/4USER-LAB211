package sample.controllers;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Hoa Doan
 */
import java.util.ArrayList;
import java.util.Collections;
import sample.dto.I_List;
import sample.dto.Product;
import sample.utils.Utils;

public class ProductList extends ArrayList<Product> implements I_List {

    @Override
    public int find(String code) {
        int index = -1;
        index = this.indexOf(new Product(code));

        return index;
    }

    @Override
    public boolean add() {
        boolean check = true;
        String code = "";
        do {
            code = Utils.getString("Input code: ");
            int index = this.find(code);
            if (index == -1) {
                check = false;
            }
        } while (check);
        String name = Utils.getString("Input name: ");
        int size = Utils.getInt("Input size: ", 1, 1000);
        int price = Utils.getInt("Input price: ", 1, 1000);
        this.add(new Product(code, name, size, price));
        return check;
    }

    @Override
    public boolean remove() {
        boolean check = false;
        String code = Utils.getString("Input code that you want to remove:");
        boolean confirmRemove = Utils.confirmYesNo("Ban co muon xoa hay ko?");
        if (confirmRemove) {
            this.remove(new Product(code));
            check = true;
        }
        return check;
    }

    @Override
    public boolean update() {
        boolean check = false;
        try {
            String code = Utils.getString("Input code that you want to update:");
            int index = this.find(code);
            if (index < 0 || index >= this.size()) {
                check = false;
            } else {
                String newName = Utils.getString("input new name: ", this.get(index).getName());
                this.get(index).setName(newName);

                int newSize = Utils.getInt("input new size: ", 1, 100, this.get(index).getSize());
                this.get(index).setSize(newSize);

                int newPrice = Utils.getInt("input new price: ", 1, 100, this.get(index).getPrice());
                this.get(index).setPrice(newPrice);
                check = true;
            }
        } catch (Exception e) {
        }

        return check;

    }

    @Override
    public void sort() {
        Collections.sort(this);
    }

    @Override
    public void output() {
        System.out.println(this.toString());
    }

    @Override
    public boolean remove(String code) {
        boolean check = false;
        boolean confirmRemove = Utils.confirmYesNo("Ban co muon xoa hay ko?");
        if (confirmRemove) {
            this.remove(new Product(code));
            check = true;
        }
        return check;
    }

    @Override
    public boolean update(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void output2() {
//        yoủ code hểgenerated methods, choose Tools | Templates.
    }

}
