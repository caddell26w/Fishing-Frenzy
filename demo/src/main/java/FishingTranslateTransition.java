import javafx.scene.shape.Line;
import javafx.util.Duration;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

import javafx.animation.Interpolator;
import javafx.animation.Transition;

public class FishingTranslateTransition extends Transition {
    private Node node;
    private Duration duration;
    private double startX;
    private double startY;
    private double endX;
    private double endY;
    private Image hookImg;
    private ImageView hookIV;
    private Hook hook;
    private Line fishingLine;
    private ArrayList<Fish> fishArray;
    private double initalFishingLineLength;
    

    public FishingTranslateTransition(Duration duration, Node node, double startX, double startY, double endX, double endY, Image hookImg, ImageView hookIV, Hook hook, ArrayList<Fish> fishArray, Line fishingLine) {
        setCycleDuration(duration);
        this.node = node;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.hookImg = hookImg;
        this.hookIV = hookIV;
        this.hook = hook;
        this.fishArray = fishArray;
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
        this.hook.setTopOfHook(this.hookIV.getLayoutY() + 73.5 + -currentY);
        this.hook.setBottomOfHook(this.hookIV.getLayoutY() + this.hookImg.getHeight() - 20.5 + -currentY);
    }
}