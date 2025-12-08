import java.util.ArrayList;

import javafx.animation.AnimationTimer;

public class FishingAnimationTimer extends AnimationTimer {
    private ArrayList<Fish> fishArray;
    private Hook hook;
    private long previous = 0;

    public FishingAnimationTimer(ArrayList<Fish> fishArray, Hook hook){
        this.fishArray = fishArray;
        this.hook = hook;
    }

    @Override
    public void handle(long n) {
        if (n - previous > 100_000_000) {
            previous = n;
            detectCollision(this.fishArray, this.hook);
        }
    }

    public void detectCollision(ArrayList<Fish> fishArray, Hook hook) {
        for (Fish fish : fishArray) {
            boolean horCollision = fish.leftOfFish < hook.getRightOfHook() && fish.rightOfFish > hook.getLeftOfHook();
            boolean verCollision = fish.topOfFish < hook.getBottomOfHook() && fish.bottomOfFish > hook.getTopOfHook();
            if (horCollision && verCollision) {
                fish.getImageView().setVisible(false);
            }
        }
    }
}
