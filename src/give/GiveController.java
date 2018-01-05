/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package give;

import java.io.File;
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
import javafx.scene.control.CheckBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author noahwebb
 */
public class GiveController implements Initializable {
    @FXML private MediaView mediaView;
    @FXML private CheckBox muted;
    
    private MediaPlayer curMediaPlayer;
    private int curVid;
    private File[] vidFiles;
    private boolean isPaused;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vidFiles = new File("./src/give/media").listFiles();
        curVid = 0;
        isPaused = false;
        if (vidFiles == null || vidFiles.length == 0) {
            //handle error
            System.exit(1);
        }
        initMediaPlayer();
    }    
    
    public void initMediaPlayer() {
 
        String curVidName = vidFiles[curVid].getName();
        Media media = new Media(getClass().getResource("media/" + curVidName).toExternalForm());
        if (curMediaPlayer != null) {
            curMediaPlayer.stop();
        }
        curMediaPlayer = new MediaPlayer(media);
//        curMediaPlayer.setStartTime(javafx.util.Duration.ZERO);
        curMediaPlayer.setOnReady(() -> {
            double len = media.getDuration().toSeconds();
            System.out.println(len);
            curMediaPlayer.setStartTime(Duration.seconds(0));
            curMediaPlayer.setStopTime(Duration.seconds(len - 1));
        });
        curMediaPlayer.setOnEndOfMedia(() -> {
            System.out.println("initializing new media player");
            initMediaPlayer();
        });
        curVid = (curVid + 1) % vidFiles.length;
        mediaView.setMediaPlayer(curMediaPlayer);
        curMediaPlayer.play();
    }
    
    public void backButtonPressed(ActionEvent event) throws IOException {
        Parent homeParent = FXMLLoader.load(getClass().getResource("fxml_home.fxml"));
        Scene giveScene = new Scene(homeParent);
        mediaView.getMediaPlayer().stop();
        // Get stage and transfer to give scene
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(giveScene);
        window.show();
    }
    
    public void pauseButtonPressed(ActionEvent event) {
        if (isPaused) {
            mediaView.getMediaPlayer().play();
            isPaused = false;
        } else {
            mediaView.getMediaPlayer().stop();
            isPaused = true;
        }
    }
    
    public void muteCheckboxPressed(ActionEvent event) throws IOException {
        if (muted.isSelected()) {
            mediaView.getMediaPlayer().setMute(true);
        } else {
            mediaView.getMediaPlayer().setMute(false);
        }
        
        
    }
}
