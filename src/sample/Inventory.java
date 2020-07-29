package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    // ATTRIBUTES
    private ObservableList<Part> allParts = FXCollections.observableArrayList();
    private ObservableList<Product> allProducts = FXCollections.observableArrayList();

    // METHODS

    public void addPart(Part newPart){

        this.allParts.addAll(newPart);
        Main.idpartcount++;
    }

    public void addProduct(Product newProduct){

        this.allProducts.addAll(newProduct);
        Main.idproductcount++;
    }

    public Part lookupPart(int partID){
        Part found = null;
        for (Part part : allParts){
            if (partID == part.getId()){
                found = part;
            } else {}
        }
        return found;
    } // NOT TESTED

    public Product lookupProduct(int productID){
        Product found = null;
        for (Product product : allProducts){
            if (productID == product.getId()){
                found = product;
            } else {}
        }
        return found;
    } // NOT TESTED

    public ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> found = null;
        for (Part part : allParts){
            if (partName == part.getName()){
                found.add(part);
            } else {}
        }
        return found;
    } // NOT TESTED

    public ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> found = null;
        for (Product product : allProducts){
            if (productName == product.getName()){
                found.add(product);
            } else {}
        }
        return found;
    } // NOT TESTED

    public void updatePart(int index, Part selectedPart){
        allParts.set(index, selectedPart);
    } // NOT TESTED

    public void updateProduct(int index, Product newProduct){
        allProducts.set(index, newProduct);
    } // NOT TESTED

    public boolean deletePart(Part selectedPart){
        boolean deleted = false;
        for (Part part : allParts){
            if (part.getId() == selectedPart.getId()){
                allParts.remove(part);
                deleted = true;
            }
        }
        return deleted;
    } // NOT TESTED

    public boolean deleteProduct(Product selectedProduct){
        boolean deleted = false;
        for (Product product : allProducts){
            if (product.getId() == selectedProduct.getId()){
                allProducts.remove(product);
                deleted = true;
            }
        }
        return deleted;
    } // NOT TESTED

    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
