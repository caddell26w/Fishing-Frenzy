import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.layout.Pane;
import javafx.animation.Interpolator;
import javafx.animation.Transition;

public class FishingTranslateTransition extends Transition{
    private Pane node;
    private Duration duration;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Line fishingLine;
    private double initalFishingLineLength;
    

    public FishingTranslateTransition(Duration duration, Pane node, double startX, double startY, double endX, double endY, Line fishingLine) {
        setCycleDuration(duration);
        this.node = node;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.fishingLine = fishingLine;
        this.initalFishingLineLength = fishingLine.getEndY();
        setInterpolator(Interpolator.LINEAR);
    }

    @Override
    protected void interpolate(double frac) {
        double currentX = startX + (endX - startX) * frac;
        double currentY = startY + (endY - startY) * frac;
        this.fishingLine.setEndY(this.initalFishingLineLength + -currentY);
        this.node.setTranslateX(currentX);
        this.node.setTranslateY(currentY);

    }

}