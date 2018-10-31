package software1.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class InHousePart extends Part{
    private final IntegerProperty partMachineID;

    public InHousePart() {
        super();
        partMachineID = new SimpleIntegerProperty();
    }

    public int getPartMachineID() {
        return partMachineID.get();
    }

    public IntegerProperty partMachineIDProperty() {
        return partMachineID;
    }

    public void setPartMachineID(int partMachineID) {
        this.partMachineID.set(partMachineID);
    }
}
