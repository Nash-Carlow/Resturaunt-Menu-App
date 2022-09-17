package app;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class employeeAddController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea ListTheIngredients;

    @FXML
    private TextField NewMenuItemN;

    @FXML
    private Button CreateNewItem;

    @FXML
    private TextField NewItemPrice;

    @FXML
    private TextField NewItemFilepath;


    @FXML
    public void changeScreen(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    public void createNewItem(ActionEvent event) throws IOException {
        String name = NewMenuItemN.getText();
        String ingredients = ListTheIngredients.getText();
        int price = Integer.parseInt(NewItemPrice.getText());
        String filepath = NewItemFilepath.getText();

        BufferedWriter out = new BufferedWriter(new FileWriter("menuInfo.txt", true));
        out.append("\n" + name + "#" + ingredients + "#" + price + "#" + filepath);
        out.close();

        Parent loginParent = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
        
        
    }

    @FXML
    void initialize() {
        assert ListTheIngredients != null : "fx:id=\"ListTheIngredients\" was not injected: check your FXML file 'EmployeeAdd.fxml'.";
        assert NewMenuItemN != null : "fx:id=\"NewMenuItemN\" was not injected: check your FXML file 'EmployeeAdd.fxml'.";
        assert CreateNewItem != null : "fx:id=\"CreateNewItem\" was not injected: check your FXML file 'EmployeeAdd.fxml'.";
        assert NewItemPrice != null : "fx:id=\"NewItemPrice\" was not injected: check your FXML file 'EmployeeAdd.fxml'.";
        assert NewItemFilepath != null : "fx:id=\"NewItemFilepath\" was not injected: check your FXML file 'EmployeeAdd.fxml'.";

    }

}
