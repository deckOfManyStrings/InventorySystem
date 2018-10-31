package software1.Model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private static ObservableList<Part> parts = FXCollections.observableArrayList();
    private final IntegerProperty productID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    //Constructor
    public Product() {
        productID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

    public void setParts(ObservableList<Part> parts) {
        this.parts = parts;
    }
    public void setProductID(int productID) {
        this.productID.set(productID);
    }
    public void setName(String name) {
        this.name.set(name);
    }
    public void setPrice(double price) {
        this.price.set(price);
    }
    public void setInStock(int inStock) {
        this.inStock.set(inStock);
    }
    public void setMin(int min) {
        this.min.set(min);
    }
    public void setMax(int max) {
        this.max.set(max);
    }

    public static ObservableList<Part> getParts() {
        return parts;
    }

    public double getPrice() {
        return price.get();
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public int getInStock() {
        return inStock.get();
    }

    public IntegerProperty inStockProperty() {
        return inStock;
    }

    public int getMin() {
        return min.get();
    }

    public IntegerProperty minProperty() {
        return min;
    }

    public int getMax() {
        return max.get();
    }

    public IntegerProperty maxProperty() {
        return max;
    }

    public int getProductID() {
        return productID.get();
    }

    public IntegerProperty productIDProperty() {
        return productID;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

}
