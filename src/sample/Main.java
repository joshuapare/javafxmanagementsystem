package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static int idproductcount = 4;
    public static int idpartcount = 4;
    public static Inventory inventory = new Inventory();

    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        Scene main = new Scene(root, 1100, 350);

        window.setScene(main);
        window.show();
    }




    public static void main(String[] args) {
        // LOAD DUMMY DATA

        ObservableList<Part> dummydata = FXCollections.observableArrayList();

        Outsourced part1 = new Outsourced(1, "Part 1", 5.00, 5, 5,5,"Joshua");
        Outsourced part2 = new Outsourced(2, "Part 2", 10.00, 10, 5,5,"Joshua");
        Outsourced part3 = new Outsourced(3, "Part 3", 15.00, 15, 5,5,"Joshua");

        inventory.addPart(part1);
        inventory.addPart(part2);
        inventory.addPart(part3);

        Product product1 = new Product(dummydata,1, "Product 1", 5.00, 5, 5, 5);
        Product product2 = new Product(dummydata,2, "Product 2", 10.00, 5, 5, 5);
        Product product3 = new Product(dummydata,3, "Product 3", 15.00, 5, 5, 5);

        inventory.addProduct(product1);
        inventory.addProduct(product2);
        inventory.addProduct(product3);

        launch(args);
    }
}
