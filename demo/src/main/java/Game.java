import java.util.ArrayList;

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
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    private double depth = 200.0;
    private int hookCapacity = 10;

    double width = 800;
    double height = 1600;
    double sceneHeight = 800;
    double waterSurfaceY = sceneHeight / 2 + 85.0;
    double waterBottomPadding = 60.0;
    
    Fish tunaFish = new Fish(20, "Tuna", 50.0, 100.0, false, "./Sprites/tuna-fish.jpg");
    Fish salmonFish = new Fish (40, "Salmon", 50.0, 100.0, false, "./Sprites/salmon-fish.png");

    ArrayList<Fish> fishArray = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        fishArray.add(tunaFish);
        fishArray.add(salmonFish);

        // Circle and buttons
        Circle circle = new Circle(50, 50, 50, Color.ORANGE);
        Button startButton = new Button("Start Game!");
        Button depthUpgradeButton = new Button("Depth");
        Button hookCapacityUpgradeButton = new Button("Hook Capacity");
        double depthIncreaseAmount = 50.0;
        int hookCapacityIncreaseAmount = 2;

        Text statText = new Text(550,50,"Max Depth: "+depth+" \nHook Capacity: "+hookCapacity+" Fish");

        // Background images
        Image background = new Image("./Sprites/longerBG.png", width, height, true, true);
        ImageView bgIV = new ImageView(background);

        Image fisherman = new Image("./Sprites/Fisherman.png", 120, 120, true, true);
        ImageView fishermanIV = new ImageView(fisherman);
        fishermanIV.setLayoutX(width / 2 - fishermanIV.getImage().getWidth() / 2 + 55);
        fishermanIV.setLayoutY(waterSurfaceY - fishermanIV.getImage().getHeight());

        Image hook = new Image("./Sprites/Hook.png", 120, 120, true, true);
        ImageView hookIV = new ImageView(hook);
        System.out.println("\n" + hookIV.getFitHeight());
        hookIV.setLayoutX(-15.0);

        Line fishingLine = new Line();
        fishingLine.setStrokeWidth(3.0);
        fishingLine.setStroke(Color.WHITE);

        Pane backgroundPane = new Pane(bgIV, fishermanIV, fishingLine); // background moves up and down
        Pane gameObjectsPane = new Pane(hookIV, circle, startButton); // other things on the screen
        Pane gamePane = new Pane(backgroundPane, gameObjectsPane, statText);
        Circle depthCircle = new Circle(50.0, Color.ORANGE);
        Circle hookCapCircle = new Circle(50.0, Color.ORANGE);
        Pane depthPane = new Pane(depthCircle, depthUpgradeButton);
        Pane hookCapPane = new Pane(hookCapCircle, hookCapacityUpgradeButton);
        HBox upgradeButtons = new HBox(depthPane, hookCapPane);

        spawnFish(fishArray, backgroundPane);

        gameObjectsPane.setLayoutY(400.0);

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
        Scene scene = new Scene(root, width, sceneHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fishing Frenzy");
        primaryStage.show();

        
        fishingLine.setStartX(400.5);
        fishingLine.setStartY(389.5);
        gameObjectsPane.setMaxSize(100, 100);
        gameObjectsPane.setLayoutX(400.0 - gameObjectsPane.getMaxWidth()/2);
        System.out.println(gameObjectsPane.getLayoutX());
        System.out.println(gameObjectsPane.getLayoutY());
        System.out.println(hook.getHeight());
        fishingLine.setEndX(gameObjectsPane.getLayoutX() + gameObjectsPane.getMaxWidth()/2 + 0.5);
        fishingLine.setEndY(gameObjectsPane.getLayoutY() + gameObjectsPane.getMaxHeight() + 2.5 - hook.getHeight()/4);

        // Button click moves background up and back down
        startButton.setOnMouseClicked(e -> {
            startButton.setVisible(false);
            circle.setVisible(false);
            upgradeButtons.setVisible(false);
            statText.setVisible(false);

            FishingTranslateTransition ftt = new FishingTranslateTransition(Duration.seconds(depth/100), backgroundPane, 0, 0, 0, -depth, fishingLine);
            ftt.setOnFinished(event -> {
                circle.setVisible(true);
                startButton.setVisible(true);
                upgradeButtons.setVisible(true);
                statText.setVisible(true);
                if (gamePane.getChildren().size() == 3) { //Update if elements are added to gamePane -> Prevents upgrade buttons from being added multiple times
                gamePane.getChildren().add(upgradeButtons);
                upgradeButtons.setSpacing(110.0);
                upgradeButtons.setLayoutX(295);
                upgradeButtons.setLayoutY(350);
                }
            });
            ftt.setAutoReverse(true);
            ftt.setCycleCount(2);
            ftt.play();

        });

        depthUpgradeButton.setOnMouseClicked(e -> {
            setDepth(depthIncreaseAmount);
            statText.setText("Max Depth: "+depth+" \nHook Capacity: "+hookCapacity+" Fish");
        });

        depthCircle.setOnMouseClicked (e -> {
            setDepth(depthIncreaseAmount);
        });

        hookCapacityUpgradeButton.setOnMouseClicked(e -> {
            setHookCapacity(hookCapacityIncreaseAmount);
            statText.setText("Max Depth: "+depth+" \nHook Capacity: "+hookCapacity+" Fish");
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

    public void spawnFish(ArrayList<Fish> fishArray, Pane parent){
        
        for (Fish fishType : fishArray){

            double maxDepth = fishType.getMaxLivingDepth();
            double minDepth = fishType.getMinLivingDepth();

            double availableDepth = Math.max(0, sceneHeight - waterSurfaceY - waterBottomPadding);
            double depthRange = maxDepth - minDepth;
            double depthFromSurface = Math.min(minDepth + Math.random() * depthRange, availableDepth);
            double spawnY = waterSurfaceY + depthFromSurface;

            Image fishImg = new Image(fishType.getSpritePath(), 80, 50, true, true);
            ImageView fishIV = new ImageView(fishImg);

            fishIV.setY(spawnY);

            boolean startOnLeft = Math.random() < 0.5;

            fishType.setImageView(fishIV);
            parent.getChildren().add(fishIV);

            TranslateTransition move = new TranslateTransition(Duration.seconds(2), fishIV);

            if (startOnLeft){
                move.setFromX(225);
                move.setToX(495);
            }
            else{
                move.setFromX(495);
                move.setToX(225);
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
