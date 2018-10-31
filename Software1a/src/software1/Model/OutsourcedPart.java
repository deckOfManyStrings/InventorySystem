package software1.Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OutsourcedPart extends Part{
    private final StringProperty partCompanyName;

    public OutsourcedPart() {
        super();
        partCompanyName = new SimpleStringProperty();
    }

    public String getPartCompanyName() {
        return partCompanyName.get();
    }

    public StringProperty partCompanyNameProperty() {
        return partCompanyName;
    }

    public void setPartCompanyName(String partCompanyName) {
        this.partCompanyName.set(partCompanyName);
    }
}
