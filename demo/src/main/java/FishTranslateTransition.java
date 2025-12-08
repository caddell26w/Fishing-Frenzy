import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.shape.Line;
import javafx.animation.Interpolator;
import javafx.animation.Transition;

public class FishTranslateTransition extends Transition {
    private ImageView iv;
    private Image image;
    private Fish fish;
    private Duration duration;
    private double startX;
    private double startY;
    private double endX;
    private double endY;

    public FishTranslateTransition (Duration duration, ImageView iv, Image image, Fish fish) {
        setCycleDuration(duration);
        this.iv = iv;
        this.image = image;
        setInterpolator(Interpolator.LINEAR);
        this.fish = fish;
    }

    public void setStartX(double x) {
        this.startX = x;
    }

    public void setEndX(double x) {
        this.endX = x;
    }

    @Override
    protected void interpolate(double frac) {
        if (this.iv.getTranslateX() == 495) {
            this.iv.setScaleX(-1);
        }
        else if(this.iv.getTranslateX() == 225) {
            this.iv.setScaleX(1);
        }
        double currentX = startX + (endX - startX) * frac;
        this.iv.setTranslateX(currentX);
        if (this.fish.species == "Salmon") {
            this.fish.leftOfFish = currentX + 7;
            this.fish.rightOfFish = currentX + this.image.getWidth() - 6.75;
        }
        else if (this.fish.species == "Tuna") {
            this.fish.leftOfFish = currentX;
            this.fish.rightOfFish = currentX + this.image.getWidth();
        }
    }
}
