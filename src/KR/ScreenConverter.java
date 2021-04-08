package KR;
import java.awt.*;

public class ScreenConverter {
    private double rx, ry, rw, rh;
    private int sw, sh;

    public Point r2s(MyPoint p){
        int x=(int)(sw*(p.getX()-rx)/rw);
        int y=(int)(sh*(ry-p.getY())/rh);
        return new Point(x,y);
    }

    public MyPoint s2r(Point p){
        double x=rx+p.x*rw/sw;
        double y=ry-p.y*rh/sh;
        return new MyPoint(x,y);
    }

    public ScreenConverter(double rx, double ry, double rw, double rh, int sw, int sh) {
        this.rx = rx;
        this.ry = ry;
        this.rw = rw;
        this.rh = rh;
        this.sw = sw;
        this.sh = sh;
    }

    public double getRx() {
        return rx;
    }

    public double getRy() {
        return ry;
    }

    public double getRw() {
        return rw;
    }

    public double getRh() {
        return rh;
    }

    public int getSw() {
        return sw;
    }

    public int getSh() {
        return sh;
    }

    public void setRx(double rx) {
        this.rx = rx;
    }

    public void setRy(double ry) {
        this.ry = ry;
    }

    public void setRw(double rw) {
        this.rw = rw;
    }

    public void setRh(double rh) {
        this.rh = rh;
    }

    public void setSw(int sw) {
        this.sw = sw;
    }

    public void setSh(int sh) {
        this.sh = sh;
    }
}
