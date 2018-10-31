package software1.Model;

import javafx.beans.property.*;

public abstract class Part {
    private final IntegerProperty partID;
    private final StringProperty name;
    private final DoubleProperty price;
    private final IntegerProperty inStock;
    private final IntegerProperty min;
    private final IntegerProperty max;

    //Constructor
    public Part() {
        partID = new SimpleIntegerProperty();
        name = new SimpleStringProperty();
        price = new SimpleDoubleProperty();
        inStock = new SimpleIntegerProperty();
        min = new SimpleIntegerProperty();
        max = new SimpleIntegerProperty();
    }

    public IntegerProperty partIDproperty() {
        return partID;
    }

    public StringProperty partNameProperty() {
        return name;
    }
    public DoubleProperty partPriceProperty() {
        return price;
    }
    public IntegerProperty partInvProperty() {
        return inStock;
    }

    public int getPartID() {
        return partID.get();
    }
    public String getName() {
        return name.get();
    }
    public double getPrice() {
        return price.get();
    }
    public int getInStock() {
        return inStock.get();
    }
    public int getMin() { return min.get(); }
    public int getMax() {
        return max.get();
    }

    public IntegerProperty partIDProperty() {
        return partID;
    }
    public StringProperty nameProperty() {
        return name;
    }
    public DoubleProperty priceProperty() {
        return price;
    }
    public IntegerProperty inStockProperty() { return inStock; }
    public IntegerProperty minProperty() {
        return min;
    }
    public IntegerProperty maxProperty() {
        return max;
    }

    public void setPartID(int partID) {
        this.partID.set(partID);
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
}
