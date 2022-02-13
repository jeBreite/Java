/**
 * Liikennevalo -ohjelma
 * View Controller
 */
package liikennevalo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 *
 * @author Jenni Breite
 * @version 1.0 16.2.2021
 */
public class LiikennevaloViewController implements Initializable {

    @FXML
    private RadioButton radioButtonRed;
    @FXML
    private ToggleGroup colorGroup;
    @FXML
    private RadioButton radioButtonYellow;
    @FXML
    private RadioButton radioButtonGreen;
    @FXML
    private Circle circleRed;
    @FXML
    private Circle circleYellow;
    @FXML
    private Circle circleGreen;

    @FXML
    private Text txtToDo;
    @FXML
    private Pane labelWhatToDo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     *
     * @param event valitaan punainen radioBtn
     */
    @FXML
    private void onClickedPutRedOn(MouseEvent event) {
        circleRed.setFill(Color.RED);
        circleYellow.setFill(Color.BLACK);
        circleGreen.setFill(Color.BLACK);
        
        txtToDo.setText(" \"Kettu? Voiko mennä...?\" ");

    }

    /**
     *
     * @param event valitaan keltainen radioBtn
     */
    @FXML
    private void onClickedPutYellowOn(MouseEvent event) {
        circleYellow.setFill(Color.YELLOW);
        circleRed.setFill(Color.BLACK);
        circleGreen.setFill(Color.BLACK);
        
        txtToDo.setText(" \"EI vielä...\" ");
    }

    /**
     *
     * @param event valitaan vihreä radioBtn
     */
    @FXML
    private void onClickedPutGreenOn(MouseEvent event) {
        circleGreen.setFill(Color.GREEN);
        circleRed.setFill(Color.BLACK);
        circleYellow.setFill(Color.BLACK);
        
        txtToDo.setText(" \"NYT!\" ");
    }

}
