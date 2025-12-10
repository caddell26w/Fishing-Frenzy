import java.util.ArrayList;

import javafx.animation.AnimationTimer;

public class FishingAnimationTimer extends AnimationTimer {
    private ArrayList<Fish> fishArray;
    private Hook hook;
    private long previous = 0;
    private Game game;

    public FishingAnimationTimer(ArrayList<Fish> fishArray, Hook hook, Game game){
        this.fishArray = fishArray;
        this.hook = hook;
        this.game = game;
    }

    @Override
    public void handle(long n) {
        if (n - previous > 100_000_000) {
            previous = n;
            detectCollision(this.fishArray, this.hook);
        }
    }

    public void detectCollision(ArrayList<Fish> fishArray, Hook hook) {
        if (game.isCapacityReached()) {
            return;
        }
        for (Fish fish : fishArray) {
            if (game.isCapacityReached()) {
                return;
            }
            boolean horCollision = fish.leftOfFish < hook.getRightOfHook() && fish.rightOfFish > hook.getLeftOfHook();
            boolean verCollision = fish.topOfFish < hook.getBottomOfHook() && fish.bottomOfFish > hook.getTopOfHook();
            if (horCollision && verCollision && fish.getImageView().isVisible()) {
                game.onFishCaught(fish);
            }
        }
    }
}
