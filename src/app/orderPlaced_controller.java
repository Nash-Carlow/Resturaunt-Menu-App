package app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Node;

public class orderPlaced_controller {

	
	public void setWait(String num, String num2) {
		numAhead.setText("Currently in the line ahead of you: " + num);
		expectedWait.setText("Expected wait: " + num2 + " min");
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label numAhead;

    @FXML
    private Label expectedWait;
    
    @FXML
    private Label total;

    @FXML
    void initialize() {
        assert numAhead != null : "fx:id=\"numAhead\" was not injected: check your FXML file 'orderPlaced.fxml'.";
        assert expectedWait != null : "fx:id=\"expectedWait\" was not injected: check your FXML file 'orderPlaced.fxml'.";

    }
}
