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

public class ModifyPartController {

    /*
     * radioBtnModifyPartInHouse
     * radioBtnModifyPartOutsourced
     * lblModifyPartIDNumber
     * txtModifyPartName
     * txtModifyPartInv
     * txtModifyPartPrice
     * txtModifyPartMin
     * txtModifyPartMax
     * txtModifyPart
     *
     */

    private boolean isOutsourced;
    private static int modifyPartIndex;
    private int partID;
    int partIndex = partToModifyIndex();

    public static int partToModifyIndex() {
        return modifyPartIndex;
    }

    @FXML
    private RadioButton radioBtnModifyPartInhouse;
    @FXML
    private RadioButton radioBtnModifyPartOutsourced;
    @FXML
    private TextField txtModifyPartIDNumber;
    @FXML
    private TextField txtModifyPartName;
    @FXML
    private TextField txtModifyPartInv;
    @FXML
    private TextField txtModifyPartPrice;
    @FXML
    private TextField txtModifyPartMin;
    @FXML
    private TextField txtModifyPartMax;
    @FXML
    private TextField txtModifyPart;
    @FXML
    private Label lblModifyPart;


    @FXML
    public void handleBtnModifyPartSave(ActionEvent actionEvent) throws IOException {
        String partName = txtModifyPartName.getText();
        String partInv = txtModifyPartInv.getText();
        String partPrice = txtModifyPartPrice.getText();
        String partMin = txtModifyPartMin.getText();
        String partMax = txtModifyPartMax.getText();
        String partDyn = txtModifyPart.getText();

        if (isOutsourced == false) {
            System.out.println("Part name: " + partName);
            InHousePart inPart = new InHousePart();
            inPart.setPartID(partID);
            inPart.setName(partName);
            inPart.setInStock(Integer.parseInt(partInv));
            inPart.setPrice(Double.parseDouble(partPrice));
            inPart.setMin(Integer.parseInt(partMin));
            inPart.setMax(Integer.parseInt(partMax));
            inPart.setPartMachineID(Integer.parseInt(partDyn));
            Inventory.updatePart(partIndex, inPart);
        }
        else {
            System.out.println("Part name: " + partName);
            OutsourcedPart outPart = new OutsourcedPart();
            outPart.setPartID(partID);
            outPart.setName(partName);
            outPart.setInStock(Integer.parseInt(partInv));
            outPart.setPrice(Double.parseDouble(partPrice));
            outPart.setMin(Integer.parseInt(partMin));
            outPart.setMax(Integer.parseInt(partMax));
            outPart.setPartCompanyName(partDyn);
            Inventory.updatePart(partIndex, outPart);
        }


    }

    @FXML
    public void handleBtnModifyPartCancel(ActionEvent actionEvent) throws IOException {
        Parent modifyPartCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(modifyPartCancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @FXML
    public void handleRadioBtnModifyPartOutsourced(ActionEvent actionEvent) {
        isOutsourced = true;
        radioBtnModifyPartInhouse.setSelected(false);
        lblModifyPart.setText("Company Name");
        txtModifyPart.setText("");
    }

    @FXML
    public void handleRadioBtnModifyPartInHouse(ActionEvent actionEvent) {
        isOutsourced = false;
        radioBtnModifyPartOutsourced.setSelected(false);
        lblModifyPart.setText("Machine ID");
        txtModifyPart.setText("");
    }
}
