package KR;
import javax.swing.*;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import KR.Parsers.Parser;

public class DrawPanel extends JPanel implements MouseWheelListener, MouseInputListener {
    private ScreenConverter sc;
    public int graphic;
    String xf="";
    String yf="";
    MyPoint currentPoint;
    Parser px, py;

    public DrawPanel() {
        super();
        sc = new ScreenConverter(-50, 50, 100, 100, 1000, 800);
        px = new Parser();
        py = new Parser();
        this.addMouseWheelListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2=(Graphics2D)g;
        g2.setColor(Color.WHITE);
        g2.fillRect(0,0,1000,1000);
        g2.setColor(Color.BLACK);
        drawCoordLine(g2);
        try {
            drawGraphic(g2);
        } catch (Exception e) {
            e.printStackTrace();
        }
//            drawGraphic(pd,fx1,ex,A,stepx,fy1,ey,B,stepy,a,b,c,d);

        //if(graphic!=0)Drawer.drawGraphic(sc,ld,pd,function,t,timer);


    }

    void drawGraphic(Graphics2D g2) throws Exception {
        if (!xf.equals("") && !yf.equals("")) {
            px.parse(xf);
            double prevX = px.comp(0);
            py.parse(yf);
            double prevY = py.comp(0);
            double x, y;
            MyPoint mp1 = new MyPoint(prevX, prevY);
            MyPoint mp2;
            for (double i = 0; i < 10 * Math.PI; i += 0.001) {
                x = px.comp(i);
                y = py.comp(i);
                mp2 = new MyPoint(x, y);
                g2.drawLine(sc.r2s(mp1).x, sc.r2s(mp1).y, sc.r2s(mp2).x, sc.r2s(mp2).y);
                mp1.setX(x);
                mp1.setY(y);
            }
        }
    }

    void drawCoordLine(Graphics2D g2) {
        MyPoint mp1 = new MyPoint(sc.getRx()-1,0);
        MyPoint mp2 = new MyPoint(sc.getRx()+sc.getRw()+1,0);
        MyPoint mp3 = new MyPoint(0, (sc.getRy() + 1));
        MyPoint mp4 = new MyPoint(0, (sc.getRy() - sc.getRh() - 1));
        g2.drawLine(sc.r2s(mp1).x, sc.r2s(mp1).y, sc.r2s(mp2).x, sc.r2s(mp2).y);
        g2.drawLine(sc.r2s(mp3).x, sc.r2s(mp3).y, sc.r2s(mp4).x, sc.r2s(mp4).y);
        for (int i = (int) sc.getRx() - 10; i < sc.getRx() + sc.getRw() + 1; i++) {
            MyPoint px = new MyPoint(i, 0);
            if (i % (int) (sc.getRw() / 10) == 0) {
                Point p1 = sc.r2s(new MyPoint(i, 0));
                p1.y = p1.y - 5;
                Point p2 = sc.r2s(new MyPoint(i, 0));
                p2.y = p2.y + 5;
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                g2.drawString("" + i, sc.r2s(px).x, sc.r2s(px).y);
            }
        }
        for (int i = (int) sc.getRy() + 1; i > (sc.getRy() - sc.getRh()) - 1; i--) {
            MyPoint py = new MyPoint(0, i);
            if (i % (int) (sc.getRh() / 10) == 0) {
                Point p1 = sc.r2s(new MyPoint(0, i));
                p1.x = p1.x - 5;
                Point p2 = sc.r2s(new MyPoint(0, i));
                p2.x = p2.x + 5;
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
                g2.drawString("" + i, sc.r2s(py).x, sc.r2s(py).y);
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        //System.out.println(e.getPreciseWheelRotation());
        if (e.getPreciseWheelRotation() < 0&&sc.getRw()>10&&sc.getRh()>10) {
            sc.setRw(sc.getRw() - 2);
            sc.setRh(sc.getRh() - 2);
            sc.setRx(sc.getRx() + 1);
            sc.setRy(sc.getRy() - 1);
        }
        if (e.getPreciseWheelRotation() > 0){
            sc.setRw(sc.getRw() + 2);
            sc.setRh(sc.getRh() + 2);
            sc.setRx(sc.getRx() - 1);
            sc.setRy(sc.getRy() + 1);
        }
        repaint();
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        currentPoint=sc.s2r(e.getPoint());
        System.out.println(currentPoint);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        sc.setRx(sc.getRx()+currentPoint.getX()-sc.s2r(e.getPoint()).getX());
        sc.setRy(sc.getRy()+currentPoint.getY()-sc.s2r(e.getPoint()).getY());
        currentPoint=sc.s2r(e.getPoint());
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
