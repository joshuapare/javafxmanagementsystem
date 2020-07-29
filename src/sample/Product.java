package sample;

import javafx.collections.ObservableList;

public class Product {
    // ATTRIBUTES

    private ObservableList<Part> associatedParts;
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    // CONSTRUCTOR

    public Product(ObservableList<Part> parts, int id, String name, double price, int stock, int min, int max) {
        this.associatedParts = parts;
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    // GETTERS AND SETTERS


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    // METHODS

    public void addAssociatedPart(Part part){
        this.associatedParts.addAll(part);
    }

    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        boolean b = this.associatedParts.removeAll(selectedAssociatedPart);
        return b;
    }

    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }

}
