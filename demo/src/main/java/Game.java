import java.util.ArrayList;

import javafx.animation.Animation.Status;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    private double depth = 200.0;
    private int hookCapacity = 10;
    final double maxDepth = 5600.0;
    final int maxHookCapacity = 20;

    int collectedFish = 0;

    double width = 800;
    double height = 25600;
    double sceneHeight = 800;
    double waterSurfaceY = sceneHeight / 2 + 85.0;
    double waterBottomPadding = 60.0;
    int amountOfTuna = (int) (Math.random() * 10) + 10;
    int amountOfSalmon = (int) (Math.random() * 10) + 10;
    int amountOfButterflyFish = (int) (Math.random() * 10) + 10;;
    int amountOfYellowTang = (int) (Math.random() * 10) + 10;;
    int amountOfAnglerFish = (int) (Math.random() * 10) + 10;;

    int money = 0;
    Text moneyText = new Text("Money: $0");
    Font statFont = new Font("Comic Sans MS", 40);

    Text fishStorage;
    FishingTranslateTransition upTransition;

    int depthUpgradeCost = 50;
    int hookUpgradeCost = 100;
    

    ArrayList<Fish> fishArray = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        for (int i = 1; i <= amountOfTuna; i++) {
            Fish tunaFish = new Fish(20, "Tuna", 25.0, 1145.0, false, "./Sprites/Fish/tuna-fish.png");
            fishArray.add(tunaFish);
        }
        for (int j = 1; j <= amountOfButterflyFish; j++) {
            Fish butterflyFish = new Fish(30, "Butterfly Fish", 1145.0, 2265.0, false, "./Sprites/Fish/butterflyFish.png");
            fishArray.add(butterflyFish);
        }
        for (int k = 1; k <= amountOfSalmon; k++) {
            Fish salmonFish = new Fish (40, "Salmon", 2265.0, 3385.0, false, "./Sprites/Fish/salmon-fish.png");
            fishArray.add(salmonFish);
        }
        for (int l = 1; l <= amountOfYellowTang; l++) {
            Fish yellowTang = new Fish(45, "Yellow Tang", 3385.0, 4505.0, false, "./Sprites/Fish/yellowTang.png");
            fishArray.add(yellowTang);
        }
        for (int m = 1; m <= amountOfAnglerFish; m++) {
            Fish anglerFish = new Fish(60, "Angler Fish", 4505.0, 5625.0, false, "./Sprites/Fish/anglerFish.png");
            fishArray.add(anglerFish);
        }

        // Circle and buttons
        Circle circle = new Circle(50, 50, 50, Color.ORANGE);
        Button startButton = new Button("Start Game!");
        Button depthUpgradeButton = new Button("Depth ($" + depthUpgradeCost + ")");
        Button hookCapacityUpgradeButton = new Button("Hook Cap ($" + hookUpgradeCost + ")");
        double depthIncreaseAmount = 50.0;
        int hookCapacityIncreaseAmount = 2;

        Text statText = new Text(0,150,"Max Depth: "+depth+" \nHook Capacity: "+hookCapacity+" Fish");
        statText.setFont(statFont);
        moneyText.setLayoutY(50);
        moneyText.setLayoutX(0);
        moneyText.setFont(statFont);

        fishStorage = new Text(width-175,sceneHeight-25, collectedFish+ " / " + hookCapacity + " Fish collected");
        fishStorage.setVisible(false);
        fishStorage.setFill(Paint.valueOf("White"));

        // Background images
        Image background = new Image("./Sprites/longerBG.png", width, height, true, true);
        ImageView bgIV = new ImageView(background);

        Image fisherman = new Image("./Sprites/Fisherman.png", 120, 120, true, true);
        ImageView fishermanIV = new ImageView(fisherman);
        fishermanIV.setLayoutX(width / 2 - fishermanIV.getImage().getWidth() / 2 + 55);
        fishermanIV.setLayoutY(waterSurfaceY - fishermanIV.getImage().getHeight());

        Image hookImg = new Image("./Sprites/Hook.png", 120, 120, true, true);
        ImageView hookIV = new ImageView(hookImg);
        Hook hook = new Hook();
        hookIV.setLayoutX(-15.0);

        Line fishingLine = new Line();
        fishingLine.setStrokeWidth(3.0);
        fishingLine.setStroke(Color.WHITE);

        Pane backgroundPane = new Pane(bgIV, fishermanIV, fishingLine); // background moves up and down
        Pane gameObjectsPane = new Pane(hookIV, circle, startButton); // other things on the screen
        Pane gamePane = new Pane(backgroundPane, gameObjectsPane, statText, moneyText, fishStorage);
        Circle depthCircle = new Circle(50.0, Color.ORANGE);
        Circle hookCapCircle = new Circle(50.0, Color.ORANGE);
        Pane depthPane = new Pane(depthCircle, depthUpgradeButton);
        Pane hookCapPane = new Pane(hookCapCircle, hookCapacityUpgradeButton);
        HBox upgradeButtons = new HBox(depthPane, hookCapPane);

        spawnFish(fishArray, backgroundPane);

        updateMoneyText();

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
        fishingLine.setEndX(gameObjectsPane.getLayoutX() + gameObjectsPane.getMaxWidth()/2 + 0.5);
        fishingLine.setEndY(gameObjectsPane.getLayoutY() + gameObjectsPane.getMaxHeight() + 2.5 - hookImg.getHeight()/4);

        // Button click moves background up and back down
        startButton.setOnMouseClicked(e -> {
            scene.getRoot().requestFocus();
            startButton.setVisible(false);
            circle.setVisible(false);
            upgradeButtons.setVisible(false);
            statText.setVisible(false);
            fishStorage.setVisible(true);
            collectedFish = 0;
            updateCollectedText(fishStorage);

            hook.setLeftOfHook(hookIV.getLayoutX() + 51.5);
            hook.setRightOfHook(hookIV.getLayoutX() + hookImg.getWidth() - 51.5);
            hook.setTopOfHook(hookIV.getLayoutY() + 73.5);
            hook.setBottomOfHook(hookIV.getLayoutY() + hookImg.getHeight() - 20.5);

            FishingTranslateTransition downTransition = new FishingTranslateTransition(Duration.seconds(1), backgroundPane, 0, 0, 0, -maxDepth, hookImg, hookIV, hook, fishArray, fishingLine);
            upTransition = new FishingTranslateTransition(Duration.seconds(maxDepth/200), backgroundPane, 0, -maxDepth, 0, 0, hookImg, hookIV, hook, fishArray, fishingLine);
            FishingAnimationTimer ft = new FishingAnimationTimer(fishArray, hook, this);
            scene.setOnKeyPressed((KeyEvent event) -> {
                if (upTransition.getStatus() == Status.RUNNING) {
                    if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                        if (hookIV.getLayoutX() >= -169.0) {
                            hookIV.setLayoutX(hookIV.getLayoutX() - 7);
                            hook.setLeftOfHook(hookIV.getLayoutX() + 51.5);
                            hook.setRightOfHook(hookIV.getLayoutX() + hookImg.getWidth() - 51.5);
                            fishingLine.setEndX(fishingLine.getEndX() - 7);
                        }
                    }
                    else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                        if (hookIV.getLayoutX() <= 152.0) {
                            hookIV.setLayoutX(hookIV.getLayoutX() + 7);
                            hook.setLeftOfHook(hookIV.getLayoutX() + 51.5);
                            hook.setRightOfHook(hookIV.getLayoutX() + hookImg.getWidth() - 51.5);
                            fishingLine.setEndX(fishingLine.getEndX() + 7);
                        }
                    }
                }
            });
            hookIV.setOnMouseDragged((MouseEvent event) -> {
                if (upTransition.getStatus() == Status.RUNNING && event.getButton() == MouseButton.PRIMARY) {
                    if(-175.0 <= hookIV.getLayoutX() && hookIV.getLayoutX() <= 152){
                        double hookBounds = Math.max(-175, Math.min(152, event.getSceneX() - 400));
                        hookIV.setLayoutX(hookBounds);
                        hook.setLeftOfHook(hookIV.getLayoutX() + 51.5);
                        hook.setRightOfHook(hookIV.getLayoutX() + hookImg.getWidth() - 51.5);
                        double hookCenterX = gameObjectsPane.getLayoutX() + hookIV.getLayoutX() + hookImg.getWidth() / 2;
                        fishingLine.setEndX(hookCenterX+7);
                    }

                }
            });
            downTransition.setOnFinished(event -> {
                ft.start();
                upTransition.play();
            });
            upTransition.setOnFinished(event -> {
                ft.stop();
                circle.setVisible(true);
                startButton.setVisible(true);
                startButton.requestFocus();
                upgradeButtons.setVisible(true);
                statText.setVisible(true);
                hookIV.setLayoutX(-15.0);
                fishingLine.setEndX(400.5);
                fishStorage.setVisible(false);
                collectedFish = 0;
                updateCollectedText(fishStorage);
                for (Fish fish : fishArray) {
                    if (fish.getImageView().isVisible() == false){
                        money += fish.getReward();
                        fish.getImageView().setVisible(true);
                    }
                }
                updateMoneyText();
                if (gamePane.getChildren().size() == 4) { //Update if elements are added to gamePane -> Prevents upgrade buttons from being added multiple times
                    gamePane.getChildren().add(upgradeButtons);
                    upgradeButtons.setSpacing(110.0);
                    upgradeButtons.setLayoutX(295);
                    upgradeButtons.setLayoutY(350);
                }
            });
            downTransition.play();
        });

        depthUpgradeButton.setOnMouseClicked(e -> {
            if (money >= depthUpgradeCost) {
                money -= depthUpgradeCost;
                setDepth(depthIncreaseAmount);
                depthUpgradeCost = (int) Math.round(depthUpgradeCost * 1.5);
                statText.setText("Max Depth: "+depth+" \nHook Capacity: "+hookCapacity+" Fish");
                updateMoneyText();
                updateUpgradeButtonLabels(depthUpgradeButton, hookCapacityUpgradeButton);
            }
        });


        hookCapacityUpgradeButton.setOnMouseClicked(e -> {
            if (money >= hookUpgradeCost) {
                money -= hookUpgradeCost;
                setHookCapacity(hookCapacityIncreaseAmount);
                hookUpgradeCost = (int) Math.round(hookUpgradeCost * 1.5);
                statText.setText("Max Depth: "+depth+" \nHook Capacity: "+hookCapacity+" Fish");
                updateMoneyText();
                updateUpgradeButtonLabels(depthUpgradeButton, hookCapacityUpgradeButton);
            }
        });

    }

    public void setDepth(double depthIncreaseAmount) {
        if (depth + depthIncreaseAmount != maxDepth) {
            depth += depthIncreaseAmount;
        }
    }

    public void setHookCapacity(int hookCapacityIncreaseAmount) {
        if (hookCapacity + hookCapacityIncreaseAmount != maxHookCapacity) {
            hookCapacity += hookCapacityIncreaseAmount;
        }
    }

    public void spawnFish(ArrayList<Fish> fishArray, Pane parent){
        for (Fish fishType : fishArray){

            double maxLivingDepth = fishType.getMaxLivingDepth();
            double minLivingDepth = fishType.getMinLivingDepth();

            double availableDepth = maxDepth;
            double depthRange = maxLivingDepth - minLivingDepth;
            double depthFromSurface = Math.min(minLivingDepth + Math.random() * depthRange, availableDepth);
            double spawnY = waterSurfaceY + depthFromSurface;
            fishType.depth = spawnY;

            Image fishImg = new Image(fishType.getSpritePath(), 80, 50, true, true);
            ImageView fishIV = new ImageView(fishImg);

            if (fishType.species == "Salmon") {
                fishType.topOfFish = fishType.depth + 6;
                fishType.bottomOfFish = fishType.depth + fishImg.getHeight() - 7;
            }
            else if (fishType.species == "Tuna") {
                fishType.topOfFish = fishType.depth + 10.5;
                fishType.bottomOfFish = fishType.depth + fishImg.getHeight() - 12;
            }
            else if (fishType.species == "Angler Fish") {
                fishType.topOfFish = fishType.depth + 5;
                fishType.bottomOfFish = fishType.depth + fishImg.getHeight() - 5;
            }
            else if (fishType.species == "Butterfly Fish") {
                fishType.topOfFish = fishType.depth + 1;
                fishType.bottomOfFish = fishType.depth + fishImg.getHeight();
            }
            else if (fishType.species == "Yellow Tang") {
                fishType.topOfFish = fishType.depth + 5;
                fishType.bottomOfFish = fishType.depth + fishImg.getHeight() - 4;
            }

            fishIV.setY(spawnY);

            boolean startOnLeft = Math.random() < 0.5;

            //Line line = new Line(0, fishType.depth + fishImg.getHeight() - 4, 1000, fishType.depth + fishImg.getHeight() - 4);

            fishType.setImageView(fishIV);
            parent.getChildren().add(fishIV);
            //parent.getChildren().add(line);

            FishTranslateTransition fish = new FishTranslateTransition(Duration.seconds(2), fishIV, fishImg, fishType);

            if (startOnLeft) {
                // fish.setStartX(225);
                fish.setStartX(225);
                fish.setEndX(500);
            }
            else {
                fish.setStartX(500);
                fish.setEndX(225);
                fishIV.setScaleX(-1);
            }


            fish.setCycleCount(TranslateTransition.INDEFINITE);
            fish.setAutoReverse(true);
            fish.play();
        }
    }

    public void addMoney(int amount) {
        money += amount;
        updateMoneyText();
    }

    public boolean isCapacityReached() {
        return collectedFish >= hookCapacity;
    }

    public void onFishCaught(Fish fish) {
        if (isCapacityReached()) {
            return;
        }
        fish.getImageView().setVisible(false);
        collectedFish++;
        updateCollectedText(fishStorage);
        if (isCapacityReached() && upTransition != null) {
            upTransition.setRate(15.0);
        }
    }

    public void updateMoneyText() {
        moneyText.setText("Money: $" + money);
        moneyText.toFront();
    }

    public void updateCollectedText(Text fishStorage){
        fishStorage.setText(collectedFish+ " / " + hookCapacity + " Fish collected");
        fishStorage.toFront();
    }

    public void updateUpgradeButtonLabels(Button depthUpgradeButton, Button hookCapacityUpgradeButton) {
        depthUpgradeButton.setText("Depth ($" + depthUpgradeCost + ")");
        hookCapacityUpgradeButton.setText("Hook Cap ($" + hookUpgradeCost + ")");
    }

    public static void main(String[] args) {
        
        launch(args);
    }

}
