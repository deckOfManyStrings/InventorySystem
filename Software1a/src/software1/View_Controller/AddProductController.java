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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static software1.Model.Inventory.getPartInventory;


public class AddProductController implements Initializable {

    private ObservableList<Part> currentParts = FXCollections.observableArrayList();
    private int productID;

    @FXML
    private TextField txtAddProductSearch;
    @FXML
    private TextField txtAddProductName;
    @FXML
    private TextField txtAddProductInventory;
    @FXML
    private TextField txtAddProductCost;
    @FXML
    private TextField txtAddProductMin;
    @FXML
    private TextField txtAddProductMax;
    @FXML
    private TableView tableViewProductAdd;
    @FXML
    private TableView tableViewProductDelete;
    @FXML
    private TableColumn<Part, Integer> tableViewAddProductAddIDColumn;
    @FXML
    private TableColumn<Part, String> tableViewAddProductAddNameColumn;
    @FXML
    private TableColumn<Part, Integer> tableViewAddProductAddInvColumn;
    @FXML
    private TableColumn<Part, Double> tableViewAddProductAddPriceColumn;
    @FXML
    private TableColumn<Part, Integer> tableViewAddProductDeleteIDColumn;
    @FXML
    private TableColumn<Part, String> tableViewAddProductDeleteNameColumn;
    @FXML
    private TableColumn<Part, Integer> tableViewAddProductDeleteInvColumn;
    @FXML
    private TableColumn<Part, Double> tableViewAddProductDeletePriceColumn;
    @FXML
    private TextField lblAddProductIdNumber;


    @FXML
    public void handleBtnAddProductSearch(ActionEvent actionEvent) {
        String searchPart =  txtAddProductSearch.getText();
        int partIndex = -1;
        if (Inventory.lookupPart(searchPart) == -1){

        } else {
            partIndex = Inventory.lookupPart(searchPart);
            Part tempPart = getPartInventory().get(partIndex);
            ObservableList<Part> tempPartList = FXCollections.observableArrayList();
            tempPartList.add(tempPart);
            tableViewProductAdd.setItems(tempPartList);
        }
    }

    @FXML
    public void handleBtnAddProductAdd(ActionEvent actionEvent) {
        Part part = (Part) tableViewProductAdd.getSelectionModel().getSelectedItem();
        currentParts.add(part);

    }

    @FXML
    public void handleBtnAddProductDelete(ActionEvent actionEvent) {
        Part part = (Part) tableViewProductAdd.getSelectionModel().getSelectedItem();
        currentParts.remove(part);
    }

    @FXML
    public void handleBtnAddProductSave(ActionEvent actionEvent) throws IOException {
        String productName = txtAddProductName.getText();
        String productInv = txtAddProductInventory.getText();
        String productPrice = txtAddProductCost.getText();
        String productMin = txtAddProductMin.getText();
        String productMax = txtAddProductMax.getText();

        System.out.println("Product name: " + productName);
        Product newProduct = new Product();
        newProduct.setProductID(productID);
        newProduct.setName(productName);
        newProduct.setInStock(Integer.parseInt(productInv));
        newProduct.setPrice(Double.parseDouble(productPrice));
        newProduct.setMax(Integer.parseInt(productMax));
        newProduct.setMin(Integer.parseInt(productMin));
        newProduct.setParts(currentParts);
        Inventory.addProduct(newProduct);

        Parent addProductSaveParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(addProductSaveParent);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();

    }

    @FXML
    public void handleBtnAddProductCancel(ActionEvent actionEvent) throws IOException {
        Parent addProductCancel = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene scene = new Scene(addProductCancel);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tableViewAddProductAddIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDproperty().asObject());
        tableViewAddProductAddNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        tableViewAddProductAddInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        tableViewAddProductAddPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        tableViewAddProductDeleteIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDproperty().asObject());
        tableViewAddProductDeleteNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        tableViewAddProductDeleteInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        tableViewAddProductDeletePriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        updateAddPartTableView();
        updateDeletePartTableView();
        productID = Inventory.getProductIDCount();
        lblAddProductIdNumber.setText("Auto-Gen: " + productID);
    }

    public void updateAddPartTableView() {
        tableViewProductAdd.setItems(getPartInventory());
    }

    public void updateDeletePartTableView() {
        tableViewProductDelete.setItems(currentParts);
    }
}
