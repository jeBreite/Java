package hirsipuupeli;

import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 *
 * @author Jenni Breite
 */
public class Hirsipuupeli extends Application {

    int i = 0;
    int cal = 0;

    @Override
    public void start(Stage primaryStage) {

        //hirttopuun pohja
        Ellipse groundEllipse = new Ellipse(40, 450, 200, 80);

        // taulukko johon tallennetaan "hirttoukon" osat
        ArrayList<Shape> hangman = new ArrayList<>();

        
        // luodaan hirttoukon palat ja lisätään taulukkoon
        Line verticalLine = new Line(40, 40, 40, 450);
        verticalLine.setVisible(false);
        hangman.add(verticalLine);

        Line horisontalLine = new Line(40, 40, 150, 40);
        horisontalLine.setVisible(false);
        hangman.add(horisontalLine);

        Line rope = new Line(150, 40, 150, 80);
        rope.setVisible(false);
        hangman.add(rope);

        Circle head = new Circle(150, 110, 30);
        head.setFill(Color.WHITE);
        head.setStroke(Color.BLACK);
        head.setVisible(false);
        hangman.add(head);

        Line body = new Line(150, 140, 150, 280);
        body.setVisible(false);
        hangman.add(body);

        Line leftArm = new Line(150, 180, 130, 250);
        leftArm.setVisible(false);
        hangman.add(leftArm);

        Line rightArm = new Line(150, 180, 170, 250);
        rightArm.setVisible(false);
        hangman.add(rightArm);

        Line leftLeg = new Line(150, 280, 130, 350);
        leftLeg.setVisible(false);
        hangman.add(leftLeg);

        Line rightLeg = new Line(150, 280, 170, 350);
        rightLeg.setVisible(false);
        hangman.add(rightLeg);

        
        // luodaan kirjainpaikat
        Text letterJ = new Text(400, 400, "_");
        letterJ.setFont(Font.font("Arial", 24));

        Text letterA = new Text(420, 400, "_");
        letterA.setFont(Font.font("Arial", 24));

        Text letterV = new Text(440, 400, "_");
        letterV.setFont(Font.font("Arial", 24));

        Text letterA2 = new Text(460, 400, "_");
        letterA2.setFont(Font.font("Arial", 24));

        // häviö- ja voittoteksti
        Text loss = new Text(300, 80, "GAME OVER!");
        loss.setFont(Font.font("Arial", 32));
        loss.setVisible(false);

        Text win = new Text(300, 80, "YOU WIN!");
        win.setFont(Font.font("Arial", 32));
        win.setVisible(false);

        
        Pane root = new Pane();

        root.getChildren().addAll(groundEllipse, verticalLine, horisontalLine, rope, head, body, leftArm, rightArm, leftLeg, rightLeg, letterJ, letterA, letterV, letterA2, loss, win);

        Scene scene = new Scene(root, 600, 600);

        primaryStage.setTitle("Hirsipuupeli");
        primaryStage.setScene(scene);
        primaryStage.show();

        //näppäimistötapahtumien käsittely
        letterJ.setOnKeyPressed((KeyEvent event) -> {

            //oikeaa kirjainta painettaessa muutetaan _-merkki kirjaimeksi
            // lisätään voitto -laskuriin yksi
            switch (event.getCode()) {
                case J: {
                    letterJ.setText(event.getText().toUpperCase());
                    cal++;
                } break;
                
                case A: {
                    letterA.setText(event.getText().toUpperCase());
                    letterA2.setText(event.getText().toUpperCase());
                    cal++;
                    cal++;
                } break;
                
                case V: {
                    letterV.setText(event.getText().toUpperCase());
                    cal++;
                } break;
                   
                // jos oikeaa näppäintä ei paineta noudetaan listalta hirttoukon seuraava osa
                default:
                    if (Character.isLetter(event.getText().charAt(0))) {
                        while (i < 9) {
                            hangman.get(i).setVisible(true);
                            i++;
                            break;
                        } 
                        // jos kaikki hirttoukon osat on paljatettu, näytetään häviö-viesti
                        if (i > 8) {
                            loss.setVisible(true);
                        }
                    }
            } 
            //jos kaikki kirjaimet on arvattu näytetään voitto-viesti
            if (cal == 4) {
                win.setVisible(true);
            } 

        });

        //näppäimistön "kytkentä"
        letterJ.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
