package KR;

import java.awt.geom.Point2D;

public class MyPoint extends Point2D {
    private double x,y;

    public MyPoint(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public double getX() {
        return x;
    }

    @Override
    public double getY() {
        return y;
    }

    @Override
    public void setLocation(double x, double y) {
        this.x=x;
        this.y=y;
    }
}
