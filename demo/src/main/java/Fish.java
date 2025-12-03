import javafx.scene.image.ImageView;

public class Fish{

    final int reward;
    final String species;
    final double minLivingDepth;
    final double maxLivingDepth;
    final boolean isLegendary;
    final String spritePath;
    ImageView fishIV;

    public Fish(int reward, String species, double minLivingDepth, double maxLivingDepth, boolean isLegendary, String spritePath){
        this.reward = reward;
        this.species = species;
        this.minLivingDepth = minLivingDepth;
        this.maxLivingDepth = maxLivingDepth;
        this.isLegendary = isLegendary;
        this.spritePath = spritePath;
    }

    public int getReward(){
        return this.reward;
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


}

