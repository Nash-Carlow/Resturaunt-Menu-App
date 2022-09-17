package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController extends mainController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField username;

    @FXML
    private TextField password;

    @FXML
    public void verifyLogin(ActionEvent event) throws IOException {
        boolean asd = verifyUserInfo(username.getText(), password.getText());
        if (this.tempEmail.equals("admin") && this.tempPassword.equals("admin")) {
            Parent loginParent = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        } else if (asd == true) {
            Parent loginParent = FXMLLoader.load(getClass().getResource("menu.fxml"));
            Scene loginScene = new Scene(loginParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(loginScene);
            window.show();
        }
    }

    @FXML
    void initialize() {

    }
}
