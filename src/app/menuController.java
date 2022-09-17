package app;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.collections.FXCollections;
import javafx.scene.Node;

public class menuController {
    // list to put menu item strings into
    ObservableList<items> oList= FXCollections.observableArrayList();
    //menuList = new ListView<String>(list);

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea TEXTAREA;
    @FXML
    public ImageView menuImage = new ImageView("F:\\VSCode\\Java Projects\\loginTest\\src\\Images\\ceasar.png");
    
    public ListView menuList;
    public List<items> initialList = new ArrayList<>(10);


    public void addItem(String name, String ingredients, String imagePath, int price){
        items newItem = new items(name, ingredients, imagePath, price);
        initialList.add(newItem);
        oList.setAll(initialList);
        menuList.setItems(oList);
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


    //menu stuff
    @FXML
    public void itemSelected(MouseEvent event) throws IOException {
        items selectedItem = (items) menuList.getSelectionModel().getSelectedItem();
        TEXTAREA.setText("Item Name: " + selectedItem.getName()
                        +"\nIngredients: " + selectedItem.getIngredients()
                        +"\nItem Price: " + selectedItem.getPrice());
                        menuImage.setImage(new Image(selectedItem.getPicture()));
    }

    @FXML
    public void changeScreenCart(ActionEvent event) throws IOException {
        Parent loginParent2 = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
        Scene loginScene2 = new Scene(loginParent2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene2);
        window.show();
    }

    @FXML
    public void changeSreenMain(ActionEvent event) throws IOException {
        Parent loginParent2 = FXMLLoader.load(getClass().getResource("homepageScene.fxml"));
        Scene loginScene2 = new Scene(loginParent2);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene2);
        window.show();
    }
    
    @FXML
    void initialize() {
        // runs function on initialization?
        //loadMenu();
        assert TEXTAREA != null : "fx:id=\"TEXTAREA\" was not injected: check your FXML file 'menu.fxml'.";
        assert menuList != null : "fx:id=\"menuList\" was not injected: check your FXML file 'menu.fxml'.";

        createMenuList();
    }

    public void changeScreen(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("homepageScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }
}