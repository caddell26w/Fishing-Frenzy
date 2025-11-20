public abstract class Fish{

    final int reward;
    final String species;
    final double livingDepth;
    final boolean isLegendary;

    public Fish(int reward, String species, double livingDepth, boolean isLegendary){
        this.reward = reward;
        this.species = species;
        this.livingDepth = livingDepth;
        this.isLegendary = isLegendary;
    }

    public abstract int getReward();


}