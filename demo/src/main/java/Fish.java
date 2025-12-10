import javafx.scene.image.ImageView;

public class Fish{

    final int reward;
    final String species;
    final double minLivingDepth;
    final double maxLivingDepth;
    final boolean isLegendary;
    final String spritePath;
    double depth;
    ImageView fishIV;
    double leftOfFish;
    double rightOfFish;
    double topOfFish;
    double bottomOfFish;

    public Fish(int reward, String species, double minLivingDepth, double maxLivingDepth, boolean isLegendary, String spritePath){
        this.reward = reward;
        this.species = species;
        this.minLivingDepth = minLivingDepth;
        this.maxLivingDepth = maxLivingDepth;
        this.isLegendary = isLegendary;
        this.spritePath = spritePath;
    }

    public double getLeftOfFish() {
        return this.leftOfFish;
    }

    public void setLeftOfFish(double leftOfFish) {
        this.leftOfFish = leftOfFish;
    }

    public double getRightOfFish() {
        return this.rightOfFish;
    }

    public void setRightOfFish(double rightOfFish) {
        this.rightOfFish = rightOfFish;
    }

    public double getTopOfFish() {
        return this.topOfFish;
    }

    public void setTopOfFish(double topOfFish) {
        this.topOfFish = topOfFish;
    }

    public double getBottomOfFish() {
        return this.bottomOfFish;
    }

    public void setBottomOfFish(double bottomOfFish) {
        this.bottomOfFish = bottomOfFish;
    }

    public int getReward(){
        return this.reward;
    }

    public String getSpecies() {
        return this.species;
    }

    public double getMinLivingDepth(){
        return this.minLivingDepth;
    }

    public double getMaxLivingDepth(){
        return this.maxLivingDepth; 
    }

    public boolean isLegendary(){
        return this.isLegendary;
    }

    public String getSpritePath(){
        return this.spritePath;
    }

    public ImageView getImageView(){
        return this.fishIV;
    }

    public void setImageView(ImageView fishIV){
        this.fishIV = fishIV;
    }

    public Double getDepth() {
        return this.depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

}

