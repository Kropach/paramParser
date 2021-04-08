package KR;

import KR.Parsers.Parser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends DrawPanel {
    public static void main(String[] args)  {
        String eq = "3+4*(5-sin(3*t))+3*725+4";
        String eq1 = "pow(sin(t),2)";
        String eq2 = "5-78";
        Parser pr = new Parser();
        try {
            pr.parse(eq1);

            for (double t = 0; t <= 1; t+=0.1){
                double e = pr.comp(t);
//                System.out.println(e);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        JFrame w = new JFrame();
        JFrame control = new JFrame();
        final Main m = new Main();
        m.graphic = 0;
        w.add(m);
        w.setVisible(true);
        w.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        w.setSize(1000, 800);
        control.setLayout(new GridLayout(3, 1, 0, 0));


        JPanel frame5=new JPanel();
        frame5.setLayout(new GridLayout(2,2,0,0));
        frame5.add(new JLabel("x(t):"));
        final JTextField tStart=new JTextField("");
        frame5.add(tStart);
        frame5.add(new JLabel("y(t):"));
        final JTextField tSpeed=new JTextField("");
        frame5.add(tSpeed);
        control.add(frame5);

        JButton button = new JButton("Draw");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                m.xf=tStart.getText();
                m.yf=tSpeed.getText();
                m.repaint();
            }
        });
        control.add(button);
        control.setVisible(true);
        control.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        control.setSize(300, 300);
    }

}
