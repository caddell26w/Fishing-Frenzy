import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {

    private double depth = 100.0;
    private int hookCapacity = 10;

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();

        // Circle and buttons
        Circle circle = new Circle(100, 100, 50, Color.ORANGE);
        Button startButton = new Button("Start Game!");
        Button depthUpgradeButton = new Button("Depth");
        Button hookCapacityUpgradeButton = new Button("Hook Capacity");
        double depthIncreaseAmount = 2.0;
        int hookCapacityIncreaseAmount = 2;

        // Background images
        Image background = new Image("./Sprites/test.png", 800, 800, true, true);
        ImageView bgIV = new ImageView(background);

        Image chunk = new Image("./Sprites/chunk.png", 800, 800, true, true);
        ImageView chunkIV = new ImageView(chunk);

        // Put background images into a Pane so they can move freely
        Pane backgroundPane = new Pane(bgIV, chunkIV);

        // StackPane to layer everything
        HBox upgradeButtons = new HBox(depthUpgradeButton, hookCapacityUpgradeButton);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(backgroundPane, circle, startButton);

        root.getChildren().add(stack);

        // Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fishing Frenzy");
        primaryStage.show();

        // Button click moves background up and back down
        startButton.setOnMouseClicked(e -> {
            startButton.setVisible(false);
            circle.setVisible(false);
            stack.getChildren().add(upgradeButtons);

            TranslateTransition tt = new TranslateTransition(Duration.seconds(1), backgroundPane);
            tt.setByY(-100); // move up
            tt.setAutoReverse(true); // then move back down
            tt.setCycleCount(2); // up + down
            tt.play();
        });

        depthUpgradeButton.setOnMouseClicked(e -> {
            setDepth(depthIncreaseAmount);
        });

        hookCapacityUpgradeButton.setOnMouseClicked(e -> {
            setHookCapacity(hookCapacityIncreaseAmount);
        });

    }

    public void setDepth(double depthIncreaseAmount) {
        depth += depthIncreaseAmount;
    }

    public void setHookCapacity(int hookCapacityIncreaseAmount) {
        hookCapacity += hookCapacityIncreaseAmount;
    }

    public static void main(String[] args) {
        launch(args);
    }

}