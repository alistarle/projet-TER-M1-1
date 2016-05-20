package enums;

/**
 * Created by alistarle on 20/05/2016.
 */
public enum Direction {
    FORWARD(100,100),
    BACK(-100,-100),
    LEFT(-175,175),
    RIGHT(175,-175);

    private int left;
    private int right;

    Direction(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft(){
        return left;
    }

    public int getRight(){
        return right;
    }
}
