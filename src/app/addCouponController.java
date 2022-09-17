package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class addCouponController extends mainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField expiryDate;

    @FXML
    private TextField discountAmount;

    @FXML
    private TextField couponCode;

    @FXML
    public void createCoupon() throws IOException{
        saveCouponInfo(couponCode.getText(), discountAmount.getText(), expiryDate.getText());
    }

    @FXML
    public void verifyCoupon() throws IOException{
        verifyCouponCode(couponCode.getText());
    }

    public void changeScreenEmployeeHome(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    void initialize() {
        assert expiryDate != null : "fx:id=\"expiryDate\" was not injected: check your FXML file 'addCoupon.fxml'.";
        assert discountAmount != null : "fx:id=\"discountAmount\" was not injected: check your FXML file 'addCoupon.fxml'.";
        assert couponCode != null : "fx:id=\"couponCode\" was not injected: check your FXML file 'addCoupon.fxml'.";

    }
}
