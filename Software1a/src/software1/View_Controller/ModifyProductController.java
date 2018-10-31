package software1.View_Controller;

import software1.Model.Inventory;
import software1.Model.Part;
import software1.Model.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static software1.Model.Inventory.getPartInventory;
import static software1.Model.Inventory.getProductInventory;
import static software1.View_Controller.MainScreenController.productToModifyIndex;


public class ModifyProductController implements Initializable {

    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private int productID;
    private int productIndex = productToModifyIndex();

    @FXML
    private Label txtModifyProductIDNumber;
    @FXML
    private TextField txtModifyProductName;
    @FXML
    private TextField txtModifyProductInv;
    @FXML
    private TextField txtModifyProductPrice;
    @FXML
    private TextField txtModifyProductMin;
    @FXML
    private TextField txtModifyProductMax;
    @FXML
    private TextField txtModifyProductSearch;
    @FXML
    private TableView<Part> tableViewModifyProductAdd;
    @FXML
    private TableColumn<Part, Integer> tableViewModifyProductAddIDColumn;
    @FXML
    private TableColumn<Part, String> tableViewModifyProductAddNameColumn;
    @FXML
    private TableColumn<Part, Integer> tableViewModifyProductAddInvColumn;
    @FXML
    private TableColumn<Part, Double> tableViewModifyProductAddPriceColumn;
    @FXML
    private TableView<Part> tableViewModifyProductDelete;
    @FXML
    private TableColumn<Part, Integer> tableViewModifyProductDeleteIDColumn;
    @FXML
    private TableColumn<Part, String> tableViewModifyProductDeleteNameColumn;
    @FXML
    private TableColumn<Part, Integer> tableViewModifyProductDeleteInvColumn;
    @FXML
    private TableColumn<Part, Double> tableViewModifyProductDeletePriceColumn;

    public void handleBtnModifyProductAdd(ActionEvent actionEvent) {
        Part part = tableViewModifyProductAdd.getSelectionModel().getSelectedItem();
        currentParts.add(part);
        updateDeletePartsTableView();
    }

    public void handleBtnModifyProductDelete(ActionEvent actionEvent) {
        Part part = tableViewModifyProductDelete.getSelectionModel().getSelectedItem();
        currentParts.remove(part);
    }

    public void handleBtnModifyProductSave(ActionEvent actionEvent) {
        String productName = txtModifyProductName.getText();
        String productInv = txtModifyProductInv.getText();
        String productPrice = txtModifyProductPrice.getText();
        String productMin = txtModifyProductMin.getText();
        String productMax = txtModifyProductMax.getText();

        Product newProduct = new Product();
        newProduct.setProductID(productID);
        newProduct.setName(productName);
        newProduct.setInStock(Integer.parseInt(productInv));
        newProduct.setPrice(Double.parseDouble(productPrice));
        newProduct.setMin(Integer.parseInt(productMin));
        newProduct.setMax(Integer.parseInt(productMax));
        newProduct.setParts(currentParts);
        Inventory.updateProduct(productIndex, newProduct);
    }

    public void handleBtnModifyProductCancel(ActionEvent actionEvent) throws IOException {
        Parent modifyProductCancelParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(modifyProductCancelParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void handleBtnSearch(ActionEvent actionEvent) {
        String searchPart = txtModifyProductSearch.getText();
        int partIndex = -1;
        if (Inventory.lookupPart(searchPart) == -1) {

        }
        else {
            partIndex = Inventory.lookupPart(searchPart);
            Part tempPart = getPartInventory().get(partIndex);
            ObservableList<Part> tempPartList = FXCollections.observableArrayList();
            tempPartList.add(tempPart);
            tableViewModifyProductAdd.setItems(tempPartList);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Product product = getProductInventory().get(productIndex);
        productID = getProductInventory().get(productIndex).getProductID();
        txtModifyProductIDNumber.setText("Auto-Gen: " + productID);
        txtModifyProductName.setText(product.getName());
        txtModifyProductInv.setText(Integer.toString(product.getInStock()));
        txtModifyProductPrice.setText(Double.toString(product.getPrice()));
        txtModifyProductMin.setText(Integer.toString(product.getMin()));
        txtModifyProductMax.setText(Integer.toString(product.getMax()));
        currentParts = product.getParts();
        tableViewModifyProductAddIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDproperty().asObject());
        tableViewModifyProductAddNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        tableViewModifyProductAddInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        tableViewModifyProductAddPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        tableViewModifyProductDeleteIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDproperty().asObject());
        tableViewModifyProductDeleteNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        tableViewModifyProductDeleteInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        tableViewModifyProductDeletePriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        updateAddPartsTableView();
        updateDeletePartsTableView();
    }

    public void updateAddPartsTableView() {

        tableViewModifyProductAdd.setItems(getPartInventory());
    }

    public void updateDeletePartsTableView() {

        tableViewModifyProductDelete.setItems(currentParts);
    }
}
