package app;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

public class shopcart {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button backbtn;

    @FXML
    private Text bwAmount;

    @FXML
    private Spinner<Integer> bwSpin;

    @FXML
    private Text cartLabel;

    @FXML
    private Button checkoutBtn;

    @FXML
    private Text cpAmount;

    @FXML
    private Spinner<Integer> cpSpin;

    @FXML
    private Text csAmount;

    @FXML
    private Spinner<Integer> csSpin;

    @FXML
    private Text ftAmount;

    @FXML
    private Spinner<Integer> ftSpin;

    @FXML
    private Text ordertotal;

    @FXML
    private Text pizzaAmount;

    @FXML
    private Spinner<Integer> pizzaSpin;

    @FXML
    private Label totalCost;

    @FXML
    private Text twAmount;

    @FXML
    private Spinner<Integer> twSPin;

    @FXML
    private Button updateBtn;

    @FXML
    private Label waitTime;


    @FXML
    public void updateTotal(){
        
        totalCost.setText(String.valueOf(pizzaSpin.getValue()*8 + twSPin.getValue()*7 + bwSpin.getValue()*8 + cpSpin.getValue()*12 + csSpin.getValue()*5 + ftSpin.getValue()*5));
        waitTime.setText(String.valueOf(3*(pizzaSpin.getValue() + twSPin.getValue() + bwSpin.getValue() + cpSpin.getValue() + csSpin.getValue() + ftSpin.getValue())) + " min");
    }

    @FXML
    void custHome(ActionEvent event) {

    }

    @FXML
    public void changeScreenCheck(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("checkout.fxml"));
        Parent root = (Parent) loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();

        Random rand = new Random();
        int numPeople = rand.nextInt(6);
        String numPeopleString = null;
        String waitTimes = null;
        int numPeopleTime = numPeople * 7;
        numPeopleString = numPeopleString.valueOf(numPeople);
        waitTimes = (String.valueOf(numPeopleTime+(3*(pizzaSpin.getValue() + twSPin.getValue() + bwSpin.getValue() + cpSpin.getValue() + csSpin.getValue() + ftSpin.getValue()))));
        checkout_controller checkout_controller = loader.getController();
        checkout_controller.setWait(numPeopleString, waitTimes, totalCost.getText());
        checkout_controller.checkForPrevInfo();
    }

    @FXML
    public void changeScreenMenu(ActionEvent event) throws IOException {
        Parent loginParent2 = FXMLLoader.load(getClass().getResource("menu.fxml"));
        Scene loginScene2 = new Scene(loginParent2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene2);
        window.show();
    }

    @FXML
    void initialize() {
        pizzaSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        twSPin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        bwSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        cpSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        csSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        ftSpin.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
        ArrayList<Integer> numItems = new ArrayList<Integer>();
        for(int i=0; i<6; i++){
            numItems.add(i);
    }
    
    totalCost.setText(String.valueOf(pizzaSpin.getValue() + twSPin.getValue() + bwSpin.getValue() + cpSpin.getValue() + csSpin.getValue() + ftSpin.getValue()));
    
    
}

}
