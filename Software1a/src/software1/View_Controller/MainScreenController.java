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


public class MainScreenController implements Initializable {

    @FXML
    private TableView<Part> tableViewParts;
    @FXML
    private TableColumn<Part, Integer> tableViewPartsIDColumn;
    @FXML
    private TableColumn<Part, String> tableViewPartsNameColumn;
    @FXML
    private TableColumn<Part, Integer> tableViewPartsInvColumn;
    @FXML
    private TableColumn<Part, Double> tableViewPartsPriceColumn;
    @FXML
    private TableView<Product> tableViewProducts;
    @FXML
    private TableColumn<Product, Integer> tableViewProductsIDColumn;
    @FXML
    private TableColumn<Product, String> tableViewProductsNameColumn;
    @FXML
    private TableColumn<Product, Integer> tableViewProductsInvColumn;
    @FXML
    private TableColumn<Product, Double> tableViewProductsPriceColumn;
    @FXML
    private TextField txtSearchParts;
    @FXML
    private TextField txtSearchProducts;

    private static Part modifyPart;
    private static int modifyPartIndex;
    private static Product modifyProduct;
    private static int modifyProductIndex;

    @FXML
    public void handleBtnExitButton(ActionEvent actionEvent) {
        System.exit(0);
    }

    //products
    @FXML
    public void handleBtnAddProducts(ActionEvent actionEvent) throws IOException {
        Parent addProducts = FXMLLoader.load(getClass().getResource("AddProduct.fxml"));
        Scene addPartScene = new Scene(addProducts);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    public void handleBtnModifyProductScreen(ActionEvent actionEvent) throws IOException {
        modifyProduct = tableViewProducts.getSelectionModel().getSelectedItem();
        modifyProductIndex = Inventory.getProductInventory().indexOf(modifyProduct);
        Parent modifyProductParent = FXMLLoader.load(getClass().getResource("ModifyProduct.fxml"));
        Scene modifyProductScene = new Scene(modifyProductParent);
        Stage modifyProductStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        modifyProductStage.setScene(modifyProductScene);
        modifyProductStage.show();
    }

    @FXML
    public void handleBtnDeleteProducts(ActionEvent actionEvent) {
        Product product = tableViewProducts.getSelectionModel().getSelectedItem();
        Inventory.removeProduct(product);
        System.out.println("Product " + product.getName() + " was removed.");
    }

    @FXML
    public void handleBtnSearchProducts(ActionEvent actionEvent) {
        int productIndex = -1;
        String searchProduct = txtSearchProducts.getText();
        productIndex = Inventory.lookupProduct(searchProduct);
        Product tempProduct = Inventory.getProductInventory().get(productIndex);
        ObservableList<Product> tempProductList = FXCollections.observableArrayList();
        tempProductList.add(tempProduct);
        tableViewProducts.setItems(tempProductList);
    }

    //parts
    @FXML
    public void handleBtnAddPart(ActionEvent actionEvent) throws IOException {
        Parent addPart = FXMLLoader.load(getClass().getResource("AddPart.fxml"));
        Scene addPartScene = new Scene(addPart);
        Stage addPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        addPartStage.setScene(addPartScene);
        addPartStage.show();
    }

    @FXML
    public void handleBtnModifyParts(ActionEvent actionEvent) throws IOException {
        modifyPart = tableViewParts.getSelectionModel().getSelectedItem();
        modifyPartIndex = Inventory.getPartInventory().indexOf(modifyPart);
        Parent modifyPartParent = FXMLLoader.load(getClass().getResource("ModifyPart.fxml"));
        Scene modifyPartScene = new Scene(modifyPartParent);
        Stage modifyPartStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        modifyPartStage.setScene(modifyPartScene);
        modifyPartStage.show();
    }

    @FXML
    public void handleBtnPartsDelete(ActionEvent actionEvent) {
        Part part = tableViewParts.getSelectionModel().getSelectedItem();
        Inventory.deletePart(part);
    }

    @FXML
    public void handleBtnPartsSearch(ActionEvent actionEvent) {
        String searchPart = txtSearchParts.getText();
        int partIndex = -1;
        partIndex = Inventory.lookupPart(searchPart);
        Part tempPart = Inventory.getPartInventory().get(partIndex);
        ObservableList<Part> tempPartList = FXCollections.observableArrayList();
        tempPartList.add(tempPart);
        tableViewParts.setItems(tempPartList);
    }

    public static int productToModifyIndex() {
        return modifyProductIndex;
    }

    public void updatePartsTableView() { tableViewParts.setItems(Inventory.getPartInventory()); }

    public void updateProductsTableView() { tableViewProducts.setItems(Inventory.getProductInventory()); }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewPartsIDColumn.setCellValueFactory(cellData -> cellData.getValue().partIDproperty().asObject());
        tableViewPartsNameColumn.setCellValueFactory(cellData -> cellData.getValue().partNameProperty());
        tableViewPartsInvColumn.setCellValueFactory(cellData -> cellData.getValue().partInvProperty().asObject());
        tableViewPartsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().partPriceProperty().asObject());
        tableViewProductsIDColumn.setCellValueFactory(cellData -> cellData.getValue().productIDProperty().asObject());
        tableViewProductsNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        tableViewProductsInvColumn.setCellValueFactory(cellData -> cellData.getValue().inStockProperty().asObject());
        tableViewProductsPriceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        updatePartsTableView();
        updateProductsTableView();
    }

}
