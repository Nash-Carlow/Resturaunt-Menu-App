package app;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;

public class employeeController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddMenuItem;

    @FXML
    private Button EditMenuItem;

    @FXML
    private Button GiveACoupon;

    @FXML
    private Button ReturnToMain;

    @FXML
    public void changeScreen(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("EmployeeAdd.fxml"));
        Scene loginScene = new Scene(loginParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    public void changeScreenedit(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("EditMenuItem.fxml"));
        Scene loginScene = new Scene(loginParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene);
        window.show();
    }

    @FXML
    public void changeScreen2(ActionEvent event) throws IOException {
        Parent loginParent2 = FXMLLoader.load(getClass().getResource("homepageScene.fxml"));
        Scene loginScene2 = new Scene(loginParent2);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene2);
        window.show();
    }

    @FXML
    public void changeScreenCoupon(ActionEvent event) throws IOException {
        Parent loginParent2 = FXMLLoader.load(getClass().getResource("addCoupon.fxml"));
        Scene loginScene2 = new Scene(loginParent2);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(loginScene2);
        window.show();
    }
    
    @FXML
    void initialize() {
        assert AddMenuItem != null : "fx:id=\"AddMenuItem\" was not injected: check your FXML file 'Employee Abilities.fxml'.";
        assert EditMenuItem != null : "fx:id=\"EditMenuItem\" was not injected: check your FXML file 'Employee Abilities.fxml'.";
        assert GiveACoupon != null : "fx:id=\"GiveACoupon\" was not injected: check your FXML file 'Employee Abilities.fxml'.";
        assert ReturnToMain != null : "fx:id=\"ReturnToMain\" was not injected: check your FXML file 'Employee Abilities.fxml'.";

    }

}