package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class editItemController {

    ObservableList<items> oList= FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField editFilePath;

    @FXML
    private Button CreateNewItem;

    @FXML
    private TextArea editIngredients;

    @FXML
    private Button GoBackToEm;

    @FXML
    private TextField editPrice;

    @FXML
    private TextField editName;

    public ListView listView;
    public List<items> initialList = new ArrayList<>(10);

    @FXML
    void changeScreen(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    void editMenuItem(ActionEvent event) throws IOException {
        items selectedItem = (items) listView.getSelectionModel().getSelectedItem();
        String oldItem = selectedItem.getName() + "#" + selectedItem.getIngredients() + "#" + selectedItem.getPrice() + "#" + selectedItem.getPicture();
        String editedItem = editName.getText() + "#" + editIngredients.getText() + "#" + editPrice.getText() + "#" + editFilePath.getText();
        
        editItem(oldItem, editedItem);

        Parent loginParent = FXMLLoader.load(getClass().getResource("employeeHome.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    public void addItem(String name, String ingredients, String imagePath, int price){
        items newItem = new items(name, ingredients, imagePath, price);
        initialList.add(newItem);
        oList.setAll(initialList);
        listView.setItems(oList);
    }

    public void createMenuList(){
        String name = "";
        String ingredients = "";
        String priceString = "";
        String filepath = "";

        try{
            Scanner fileScanner = new Scanner(new File("menuInfo.txt"));
            fileScanner.useDelimiter("[#\n]");
            while(fileScanner.hasNext()){
                name = fileScanner.next();
                ingredients = fileScanner.next();
                priceString = fileScanner.next();
                filepath = fileScanner.next();
                int price = Integer.parseInt(priceString);
                addItem(name, ingredients, filepath, price);
            }
            fileScanner.close();
        } catch (Exception e){
            System.out.println("Menu Does Not Exist");
        }
    }

    public void itemSelected(MouseEvent event) throws IOException {
        items selectedItem = (items) listView.getSelectionModel().getSelectedItem();
        editName.setText(selectedItem.getName());
        editIngredients.setText(selectedItem.getIngredients());
        editPrice.setText(Integer.toString(selectedItem.getPrice()));
        editFilePath.setText(selectedItem.getPicture());

    }

    public void editItem(String item, String editedItem){
        try {
            // input the file content to the StringBuffer "input"
            BufferedReader file = new BufferedReader(new FileReader("menuInfo.txt"));
            StringBuffer inputBuffer = new StringBuffer();
            String line = file.readLine();
            while (line != null ) {
                inputBuffer.append(line);
                if((line = file.readLine()) != null) {
                    inputBuffer.append('\n');
                }
            }
            file.close();
            String inputStr = inputBuffer.toString();
            
            inputStr = inputStr.replace(item, editedItem);
            
            FileOutputStream fileOut = new FileOutputStream("menuInfo.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
    
        } catch (Exception e) {
            System.out.println("Error reading menuInfo");
        }
    }

    @FXML
    void initialize() {
        assert editFilePath != null : "fx:id=\"editFilePath\" was not injected: check your FXML file 'EditMenuItem.fxml'.";
        assert listView != null : "fx:id=\"listView\" was not injected: check your FXML file 'EditMenuItem.fxml'.";
        assert CreateNewItem != null : "fx:id=\"CreateNewItem\" was not injected: check your FXML file 'EditMenuItem.fxml'.";
        assert editIngredients != null : "fx:id=\"editIngredients\" was not injected: check your FXML file 'EditMenuItem.fxml'.";
        assert GoBackToEm != null : "fx:id=\"GoBackToEm\" was not injected: check your FXML file 'EditMenuItem.fxml'.";
        assert editPrice != null : "fx:id=\"editPrice\" was not injected: check your FXML file 'EditMenuItem.fxml'.";
        assert editName != null : "fx:id=\"editName\" was not injected: check your FXML file 'EditMenuItem.fxml'.";

        createMenuList();

    }
}
