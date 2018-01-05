/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author noahwebb
 */
public class HomeController implements Initializable {
    @FXML private Label greeting;
    private User user;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
    public void setUser(User user) {
        this.user = user;
        this.greeting.setText("Hey, " + user.getUsername());
    }
    
    public void giveButtonPressed(ActionEvent event) throws IOException {
        Parent giveParent = FXMLLoader.load(getClass().getResource("fxml_give.fxml"));
        Scene giveScene = new Scene(giveParent);
        // Get stage and transfer to give scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(giveScene);
        window.show();
    }
    
    public void accountButtonPressed(ActionEvent event) throws IOException {
        Parent accountParent = FXMLLoader.load(getClass().getResource("fxml_account.fxml"));
        Scene accountScene = new Scene(accountParent);
        // Get stage and transfer to give scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(accountScene);
        window.show();
    }
    
    public void charityButtonPressed(ActionEvent event) throws IOException {
        Parent charityParent = FXMLLoader.load(getClass().getResource("fxml_charity.fxml"));
        Scene charityScene = new Scene(charityParent);
        // Get stage and transfer to charity scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(charityScene);
        window.show();
    }
}
