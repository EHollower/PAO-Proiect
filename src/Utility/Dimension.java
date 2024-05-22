package Utility;

public abstract class Dimension {
    private int length;
    private int width;
    private int height;

    public Dimension(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    @Override
    public String toString() {
        return length + "x" + width + "x" + height + " cm";
    }
}
