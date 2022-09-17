package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class signUpController extends mainController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField userPassword;

    @FXML
    private TextField userLastName;

    @FXML
    private Button signUpButton;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userFirstName;

    @FXML
    public void createUser(ActionEvent event) throws IOException {
        saveUserInfo(userEmail.getText(), userPassword.getText(), userFirstName.getText(), userLastName.getText());
    
        Parent loginParent = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    void initialize() {
        assert userPassword != null : "fx:id=\"userPassword\" was not injected: check your FXML file 'signupScene.fxml'.";
        assert userLastName != null : "fx:id=\"userLastName\" was not injected: check your FXML file 'signupScene.fxml'.";
        assert signUpButton != null : "fx:id=\"signUpButton\" was not injected: check your FXML file 'signupScene.fxml'.";
        assert userEmail != null : "fx:id=\"userEmail\" was not injected: check your FXML file 'signupScene.fxml'.";
        assert userFirstName != null : "fx:id=\"userFirstName\" was not injected: check your FXML file 'signupScene.fxml'.";

    }
}
