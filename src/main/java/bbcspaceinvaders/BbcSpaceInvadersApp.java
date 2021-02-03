package bbcspaceinvaders;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class BbcSpaceInvadersApp extends Application {

    private Canvas canvas;
    private GraphicsContext gc;
    private Image spaceshipImage = new Image(this.getClass().getResourceAsStream("/spaceship.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/background_game.png"));


    // Instanzvariable
    private long lastTimeInNanoSec;
    // Wir wollen das Spaceship 100 Pixel in der Sekunde bewegen.
    private static final double SPEED_OF_SPACESHIP = 100;
    private double spaceshipPosX = 150;
    private boolean isLeftKeyPressed = false;
    private boolean isRightKeyPressed = false;



    @Override
    public void start(Stage stage) throws Exception {

        // Gibt die aktuelle Systemzeit in Nanosekunden zurück.
        lastTimeInNanoSec = System.nanoTime();
        new AnimationTimer() {
            @Override
            public void handle(long currentTimeInNanoSec) {


                // currentTimeInNanoSec:
                // Der Wert entspricht der Anzahl vergangennen Nanosekunden
                // seit einem fixen Datum. (Meistens: 1.1.1970)

                long deltaInNanoSec = currentTimeInNanoSec - lastTimeInNanoSec;
                double deltaInSec = deltaInNanoSec / 1000000000d; //oder: 1e9;

                lastTimeInNanoSec = currentTimeInNanoSec;

                update(deltaInSec);
                paint();
            }
        }.start();


        Group root = new Group();
        canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);

        gc = canvas.getGraphicsContext2D();


        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Bbc SpaceInvaders");
        scene.setOnKeyPressed((e) -> onKeyPressed(e));
        scene.setOnKeyReleased((e) -> onKeyReleased(e));
        stage.show();

    }

    private void paint() {
        gc.drawImage(backgroundImage, 0, 0);
        gc.drawImage(spaceshipImage, spaceshipPosX, 400);
    }

    private void update(double deltaInSec) {
        double distanceToMove = SPEED_OF_SPACESHIP * deltaInSec;
        if (isLeftKeyPressed){
            spaceshipPosX -= distanceToMove;
        }
        else if (isRightKeyPressed){
            spaceshipPosX += distanceToMove;
        }
    }
    private void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.LEFT){
            isLeftKeyPressed = true;
        }
        else if (keyEvent.getCode() == KeyCode.RIGHT){
            isRightKeyPressed = true;
        }
        }
    private void onKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.RIGHT){
            isRightKeyPressed = false;
        }
        else if (keyEvent.getCode() == KeyCode.LEFT){
            isLeftKeyPressed = false;
        }
    }
}
