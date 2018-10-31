package software1.View_Controller;

import software1.Model.InHousePart;
import software1.Model.Inventory;
import software1.Model.OutsourcedPart;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AddPartController {

    @FXML
    private RadioButton radioAddPartInHouse;
    @FXML
    private RadioButton radioAddPartOutsourced;
    @FXML
    private TextField txtAddPartName;
    @FXML
    private TextField txtAddPartInventory;
    @FXML
    private TextField txtAddPartCost;
    @FXML
    private TextField txtAddPartMax;
    @FXML
    private TextField txtAddPartMin;
    @FXML
    private Label labelAddPartIdNumber;
    @FXML
    private TextField txtAddPartMachineID;
    @FXML
    private TextField txtAddPartID;

    private boolean isOutsourced;
    private int partID;

    public void initialize() {
        partID = Inventory.getPartIDCount();
        txtAddPartID.setText("Auto-Gen: " + partID);
    }

    @FXML
    void handleRadioAddPartInHouse(ActionEvent actionEvent) {
        isOutsourced = false;
        labelAddPartIdNumber.setText("Machine ID");
        radioAddPartOutsourced.setSelected(false);
    }

    @FXML
    void handleRadioAddPartOutsourced(ActionEvent actionEvent) {
        isOutsourced = true;
        labelAddPartIdNumber.setText("Company Name");
        radioAddPartInHouse.setSelected(false);
    }


    public void handleBtnAddPartCancel(ActionEvent actionEvent) throws IOException {
        Parent addPartCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(addPartCancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void handleBtnAddPartSave(ActionEvent actionEvent) throws IOException {
        String partName = txtAddPartName.getText();
        String partInv = txtAddPartInventory.getText();
        String partPrice = txtAddPartCost.getText();
        String partMin = txtAddPartMin.getText();
        String partMax = txtAddPartMax.getText();
        String partDyn = txtAddPartMachineID.getText();

        if (isOutsourced == false) {
            System.out.println("Part name: " + partName);
            InHousePart Part = new InHousePart();
            Part.setName(partName);
            Part.setPrice(Double.parseDouble(partPrice));
            Part.setInStock(Integer.parseInt(partInv));
            Part.setMin(Integer.parseInt(partMin));
            Part.setMax(Integer.parseInt(partMax));
            Part.setPartMachineID(Integer.parseInt(partDyn));
            Inventory.addPart(Part);
        } else {
            System.out.println("Part name: " + partName);
            OutsourcedPart Part = new OutsourcedPart();
            Part.setName(partName);
            Part.setPrice(Double.parseDouble(partPrice));
            Part.setInStock(Integer.parseInt(partInv));
            Part.setMin(Integer.parseInt(partMin));
            Part.setMax(Integer.parseInt(partMax));
            Part.setPartCompanyName(partDyn);
            Inventory.addPart(Part);
        }

        Parent addPartSave = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(addPartSave);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
