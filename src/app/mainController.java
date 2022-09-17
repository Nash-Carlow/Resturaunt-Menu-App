package app;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import javafx.scene.Node;

public class mainController extends App{
    public String tempEmail = "";
    public String tempPassword = "";
    @FXML
    public void changeScreenLogin(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("loginScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    public void changeScreenHomepage(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("homepageScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    @FXML
    public void changeScreenSignup(ActionEvent event) throws IOException {
        Parent loginParent = FXMLLoader.load(getClass().getResource("signupScene.fxml"));
        Scene loginScene = new Scene(loginParent);
        
        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(loginScene);
        window.show();
    }

    public void saveUserInfo(String email, String password, String firstName, String lastName) throws IOException {
        BufferedWriter out = new BufferedWriter(new FileWriter("loginInfo.txt", true));
        out.append(email + "," + password + "," + firstName + "," + lastName);
        out.newLine();
        out.close();
    }
    public boolean verifyUserInfo(String email, String password){
        boolean success = false;
        String tempEmail = "";
        String tempPassword = "";

        try{
            Scanner fileScanner = new Scanner(new File("loginInfo.txt"));
            fileScanner.useDelimiter("[,\n]");
            while(fileScanner.hasNext() && !success){
                tempEmail = fileScanner.next();
                tempPassword = fileScanner.next();
                fileScanner.nextLine();
                if(tempEmail.trim().equals(email.trim()) && tempPassword.trim().equals(password.trim())){
                    success = true;
                    this.tempEmail = tempEmail;
                    this.tempPassword = tempPassword;


                    File temp = new File("CurrentlogIn.txt");
                    temp.delete();
                    BufferedWriter out = new BufferedWriter(new FileWriter("CurrentlogIn.txt", true));
                    out.append(email + "," + password);
                    out.newLine();
                    out.close();


                    return success;
                }
            }
            fileScanner.close();
            return success;
        } catch (Exception e){
            System.out.println("Verify Login Error");
            return success;
        }
    }

    public void saveCouponInfo(String couponCode, String discount, String expiryDate) throws IOException {
        String coupPath = "F:\\VSCode\\Java Projects\\loginTest\\couponInfo.txt";
        java.nio.file.Path couponPath = Paths.get(coupPath);
        try {
            Files.delete(couponPath);
        } catch (Exception e) {
            System.out.println("No Files Were Deleted");
        }
        BufferedWriter out = new BufferedWriter(new FileWriter("couponInfo.txt", true));
        out.append(couponCode + "," + discount + "," + expiryDate);
        out.close();
    }

    public boolean verifyCouponCode(String couponCode){
        boolean success = false;
        String tempCouponCode = "";
        String tempDiscount = "";
        int discount = 0;

        try{
            Scanner fileScanner = new Scanner(new File("couponInfo.txt"));
            fileScanner.useDelimiter("[,\n]");
            while(fileScanner.hasNext() && !success){
                tempCouponCode = fileScanner.next();
                tempDiscount = fileScanner.next();
                fileScanner.nextLine();
                if(tempCouponCode.trim().equals(couponCode.trim())){
                    success = true;
                    discount = Integer.parseInt(tempDiscount);
                    System.out.println("Valid Coupon" + discount);
                    return success;
                }
            }
            fileScanner.close();
            return success;
        } catch (Exception e){
            System.out.println("Coupon Does Not Exist");
            return success;
        }
    }
    

    @FXML
    void initialize() {

    }
}