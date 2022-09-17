package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class checkout_controller extends mainController{
	public String numPeopleString = "error";
    public String waitTime = "error";
    public String orderTotal = "0";
    public boolean couponEntered = false;
    String currentEmail = "";
    String currentPassword = "";
    public boolean couponSaucy = false;

	public void switchToOrderPlaced(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("orderPlaced.fxml"));
		Parent root = (Parent) loader.load();

		checkout_controller controller = loader.getController();
        controller.setWait(this.numPeopleString, this.waitTime, this.orderTotal);
        
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        
        //if they checked to save info
        if (saveInfo.isSelected() == true) {
        	//saveLogin()
        	storeBillingInfo(phoneNum.getText(), firstName.getText(), lastName.getText(), cardNum.getText(), expDate.getText(), securityCode.getText(), billingAddress.getText());
        }		
	}
	
	public void switchToCart(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("shoppingcart.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	

	public void setWait(String num, String num2, String totalCost) {
		numAhead.setText("Currently in the line ahead of you: " + num);
		expectedWait.setText("Expected wait: " + num2 + " min");
		this.numPeopleString = num;
		this.waitTime = num2;
		this.orderTotal = totalCost;
		total.setText("Total cost: $" + totalCost);
	}

    @FXML
    public void changeSreenMain(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("homepageScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(loginScene);
        window.show();
    }
	
	//first two entries should be email and password
	public void saveLogin (String email, String password) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("BillingInfo.txt", true));
        out.append(email + "," + password + ",");
        //out.newLine();
        out.close();
	}
	//rest of the entries in the line are the billing info
	public void storeBillingInfo(String phoneNum, String firstName, String lastName, String cardNum, String expDate, String secCode, String address) throws IOException {
		BufferedWriter out = new BufferedWriter(new FileWriter("BillingInfo.txt", true));
		out.append(currentEmail + "," + currentPassword);
		out.newLine();
        out.append(phoneNum + "," + firstName + "," + lastName + "," + cardNum + "," + expDate + "," + secCode + "," + address);
        out.newLine();
        out.close();
	}
    public void doIHaveCoup() {
        if(couponSaucy == true) {
            try {
                Scanner file = new Scanner(new File("couponInfo.txt"));
                file.useDelimiter("[,\r]");
                String Cline = "";
                Cline = file.next();
                couponCode.setText(Cline);
                file.close();
            } catch (Exception e) {
                System.out.print("Sorry No Coupon!");
            }
        } else {
            couponCode.setText("Sorry No Coupon!");
        }
    }
	
	public void checkForPrevInfo() throws IOException{
        boolean empty = false;
  
        Scanner fileScanner2 = new Scanner(new File("CurrentlogIn.txt"));
        fileScanner2.useDelimiter("[,\r]");
        while(fileScanner2.hasNext() && !empty){
            currentEmail = fileScanner2.next();
            currentPassword = fileScanner2.next();
            fileScanner2.nextLine();
        }
        
        String testEmail = "";
        String testPassword = "";
        Scanner fileScanner = new Scanner(new File("BillingInfo.txt"));
        fileScanner.useDelimiter("[,\n]");        
        while(fileScanner.hasNext()){
            couponSaucy = false;
            testEmail = fileScanner.next();
            testPassword = fileScanner.next();
            fileScanner.nextLine();
            if(testEmail.trim().equals(currentEmail.trim()) && testPassword.trim().equals(currentPassword.trim())){
                phoneNum.setText(fileScanner.next());
                firstName.setText(fileScanner.next());
                lastName.setText(fileScanner.next());
                cardNum.setText(fileScanner.next());
                expDate.setText(fileScanner.next());
                securityCode.setText(fileScanner.next());
                billingAddress.setText(fileScanner.next());
                couponSaucy = true;
                return;
            }
            fileScanner.nextLine();
        }
        fileScanner.close();  
        
    }
	
	public void checkCoupon(ActionEvent event) throws FileNotFoundException {
		checkValidCoupon(couponCode.getText());
	}
	
	public void checkValidCoupon(String coupon) throws FileNotFoundException {
		//valid coupon
		if((verifyCouponCode(coupon) == true) && couponEntered == false) {
			int discountInt = 0;
			float discountFloat = (float) discountInt;
			Scanner fileScanner = new Scanner(new File("couponInfo.txt"));
            fileScanner.useDelimiter("[,\n]");
            boolean success = false;
            String tempCouponCode = "";
            String tempDiscount = "";
            int discount = 0;

                Scanner fileScanner2 = new Scanner(new File("couponInfo.txt"));
                fileScanner.useDelimiter("[,\n]");
                while(fileScanner.hasNext() && !success){
                    tempCouponCode = fileScanner.next();
                    tempDiscount = fileScanner.next();
                    fileScanner.nextLine();
                    if(tempCouponCode.trim().equals(coupon.trim())){
                        success = true;
                        discountFloat = Float.parseFloat(tempDiscount);
                        discountInt = (int) discountFloat;
                    }
                }
                fileScanner.close();
			
			//Float newTotalCost = Float.parseFloat(coupon);
            couponValid.setText(discountInt + "% coupon applied!");
			discountFloat = (float) (1.00 - (discountFloat / 100));
			float newTotalCost = (float) ((discountFloat * Float.parseFloat(orderTotal)));
			this.orderTotal = orderTotal.valueOf(newTotalCost).format("%.2f", newTotalCost);
			total.setText("Total cost: $" + this.orderTotal);
			
			couponEntered = true;
		}
		//invalid coupon
		else if (couponEntered == false){
			couponValid.setText("That is not a valid coupon");
		}
	}
	
	
    @FXML
    private TextField firstName;

    @FXML
    private TextField lastName;

    @FXML
    private TextField cardNum;

    @FXML
    private Button placeOrder;

    @FXML
    private Button cartButton;

    @FXML
    private TextField securityCode;

    @FXML
    private TextField phoneNum;

    @FXML
    private CheckBox saveInfo;

    @FXML
    private TextField billingAddress;

    @FXML
    private TextField couponCode;

    @FXML
    private TextField expDate;
    
    @FXML
    private Label numAhead;
    
    @FXML
    private Label expectedWait;
    
    @FXML
    private Label total;
    
    @FXML
    private Label couponValid;
    
    @FXML
    private Button enterCoupon;


}
