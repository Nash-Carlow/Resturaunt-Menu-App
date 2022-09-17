package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class homepageController extends mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    public void guestLogin(ActionEvent event) throws IOException {
        
        tempEmail = "guest";
        tempPassword = "guest";

        System.out.println(tempEmail+tempPassword);
        
        Parent loginParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    void initialize() {

    }
}
