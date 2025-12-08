public class Hook {
    private double leftOfHook;
    private double rightOfHook;
    private double topOfHook;
    private double bottomOfHook;

    public Hook() {
    }

    public double getLeftOfHook() {
        return this.leftOfHook;
    }

    public void setLeftOfHook(double leftOfHook) {
        this.leftOfHook = leftOfHook + 350.0;
    }

    public double getRightOfHook() {
        return this.rightOfHook;
    }

    public void setRightOfHook(double rightOfHook) {
        this.rightOfHook = rightOfHook + 350.0;
    }

    public double getTopOfHook() {
        return this.topOfHook;
    }

    public void setTopOfHook(double topOfHook) {
        this.topOfHook = topOfHook + 400.0;
    }

    public double getBottomOfHook() {
        return this.bottomOfHook;
    }

    public void setBottomOfHook(double bottomOfHook) {
        this.bottomOfHook = bottomOfHook + 400.0;
    }
}
