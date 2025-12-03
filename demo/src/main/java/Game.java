import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;

public class Game extends Application {

    private double depth = 200.0;
    private int hookCapacity = 10;

    double width = 800;
    double height = 1600;

    Fish tunaFish = new Fish(20, "Tuna", 100.0, 500.0, false, ".Sprites/tuna-fish.jpg");
    Fish salmonFish = new Fish (40, "Salmon", 200.0, 800.0, false, ".Sprites/salmon-fish.png");

    ArrayList<Fish> fishArray = new ArrayList<>();



    @Override
    public void start(Stage primaryStage) {

        fishArray.add(tunaFish);
        fishArray.add(salmonFish);

        // Circle and buttons
        Circle circle = new Circle(100, 100, 50, Color.ORANGE);
        Button startButton = new Button("Start Game!");
        Button depthUpgradeButton = new Button("Depth");
        Button hookCapacityUpgradeButton = new Button("Hook Capacity");
        double depthIncreaseAmount = 50.0;
        int hookCapacityIncreaseAmount = 2;

        // Background images
        Image background = new Image("./Sprites/longerBG.png", width, height, true, true);
        ImageView bgIV = new ImageView(background);

        Image fisherman = new Image("./Sprites/Fisherman.png", 32, 32, true, true);
        ImageView fishermanIV = new ImageView(fisherman);

        Pane backgroundPane = new Pane(bgIV, fishermanIV); // background moves up and down
        Pane gameObjectsPane = new Pane(circle, startButton); // other things on the screen
        Pane gamePane = new Pane(backgroundPane, gameObjectsPane);
        Circle depthCircle = new Circle(50.0, Color.ORANGE);
        Circle hookCapCircle = new Circle(50.0, Color.ORANGE);
        Pane depthPane = new Pane(depthCircle, depthUpgradeButton);
        Pane hookCapPane = new Pane(hookCapCircle, hookCapacityUpgradeButton);
        HBox upgradeButtons = new HBox(depthPane, hookCapPane);

        spawnFish(fishArray);

        gameObjectsPane.getChildren().add(tunaFish.getImageView());
        gameObjectsPane.getChildren().add(salmonFish.getImageView());


        startButton.layoutXProperty().bind(circle.centerXProperty().subtract(startButton.widthProperty().divide(2)));
        startButton.layoutYProperty().bind(circle.centerYProperty().subtract(startButton.heightProperty().divide(2)));

        depthUpgradeButton.layoutXProperty().bind(
                depthCircle.centerXProperty().subtract(depthUpgradeButton.widthProperty().divide(2)));
        depthUpgradeButton.layoutYProperty().bind(
                depthCircle.centerYProperty().subtract(depthUpgradeButton.heightProperty().divide(2)));
        hookCapacityUpgradeButton.layoutXProperty().bind(
                hookCapCircle.centerXProperty().subtract(hookCapacityUpgradeButton.widthProperty().divide(2)));
        hookCapacityUpgradeButton.layoutYProperty().bind(
                hookCapCircle.centerYProperty().subtract(hookCapacityUpgradeButton.heightProperty().divide(2)));

        BorderPane root = new BorderPane();
        root.setCenter(gamePane);

        // Scene
        Scene scene = new Scene(root, width, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fishing Frenzy");
        primaryStage.show();

        circle.setCenterX(400);
        circle.setCenterY(400);

        // Button click moves background up and back down
        startButton.setOnMouseClicked(e -> {
            startButton.setVisible(false);
            circle.setVisible(false);
            upgradeButtons.setVisible(false);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), bgIV);
            tt.setOnFinished(event -> {
                circle.setVisible(true);
                startButton.setVisible(true);
                upgradeButtons.setVisible(true);
                if (gamePane.getChildren().size() == 2) {
                gamePane.getChildren().add(upgradeButtons);
                upgradeButtons.setSpacing(110.0);
                upgradeButtons.setLayoutX(295);
                upgradeButtons.setLayoutY(500);
                }
            });
            tt.setByY(-depth); // move up
            tt.setAutoReverse(true); // then move back down
            tt.setCycleCount(2); // up + down
            tt.play();

        });

        depthUpgradeButton.setOnMouseClicked(e -> {
            setDepth(depthIncreaseAmount);
        });

        depthCircle.setOnMouseClicked (e -> {
            setDepth(depthIncreaseAmount);
            System.out.println("Circle clicked");
        });

        hookCapacityUpgradeButton.setOnMouseClicked(e -> {
            setHookCapacity(hookCapacityIncreaseAmount);
        });

        hookCapCircle.setOnMouseClicked (e -> {
            setHookCapacity(hookCapacityIncreaseAmount);
        });

    }

    public void setDepth(double depthIncreaseAmount) {
        depth += depthIncreaseAmount;
    }

    public void setHookCapacity(int hookCapacityIncreaseAmount) {
        hookCapacity += hookCapacityIncreaseAmount;
    }

    public void spawnFish(ArrayList<Fish> fishArray){
        
        for (Fish fishType : fishArray){

            double maxDepth = fishType.getMaxLivingDepth();
            double minDepth = fishType.getMinLivingDepth();

            double spawnY = minDepth + Math.random() * (maxDepth - minDepth);

            Image fishImg = new Image(fishType.getSpritePath(), 80, 50, true, true);
            ImageView fishIV = new ImageView(fishImg);

            boolean startOnLeft = Math.random() < 0.5;

            fishType.setImageView(fishIV);

            TranslateTransition move = new TranslateTransition(Duration.seconds(5 + Math.random() * 3), fishIV);

            if (startOnLeft){
                move.setFromX(200);
                move.setToX(400);
            }
            else{
                move.setFromX(600);
                move.setToX(400);
            }

            move.setCycleCount(TranslateTransition.INDEFINITE);
            move.setAutoReverse(true);
            move.play();
        }
    }

    public static void main(String[] args) {
        
        launch(args);
    }

}