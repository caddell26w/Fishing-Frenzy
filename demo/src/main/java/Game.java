import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        Scene scene = new Scene(root, 800, 600);
        
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.ORANGE);
        Button button1 = new Button("BUTTON");
        
        button1.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.out.println("CLICKED");
                
            }
        });
        

        Image image = new Image("./Sprites/test.png", 800, 800, true, true);
        ImageView iv = new ImageView();
        iv.setImage(image);

        iv.setTranslateZ(-1.0);

        StackPane stack = new StackPane();
        stack.getChildren().addAll(iv, circle, button1);

        

        root.getChildren().add(stack);

        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Fishing Frenzy");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
