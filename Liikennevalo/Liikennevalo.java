/**
 * Viikkoharjoitukset 3 - tehtävä 2 
 * 
 * Ohjelma, jossa liikennevalossa näkyy radiobuttonilla valittu valo
 */
package liikennevalo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Jenni Breite
 * @version 1.0 16.2.2021
 */
public class Liikennevalo extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("LiikennevaloView.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Näytä liikennevalot");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
