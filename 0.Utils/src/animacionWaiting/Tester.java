package animacionWaiting;
import javax.swing.*;
import java.awt.*;

final public class Tester {

    JFrame frame;
    DrawPanel drawPanel;

    private int oneX = 7;
    private int oneY = 7;

    boolean up = false;
    boolean down = true;
    boolean left = false;
    boolean right = true;

    public static void main(String[] args) {
        new Tester().go();
    }

    private void go() {
        frame = new JFrame("Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new DrawPanel();

        frame.getContentPane().add(BorderLayout.CENTER, drawPanel);

        frame.setVisible(true);
        frame.setResizable(true);
        frame.setSize(300, 300);
        frame.setLocation(375, 55);
        moveIt();
    }

    class DrawPanel extends JPanel {
        public void paintComponent(Graphics g) {
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
            g.setColor(Color.RED);
            g.fillRect(3, 3, this.getWidth()-6, this.getHeight()-6);
            g.setColor(Color.WHITE);
            g.fillRect(6, 6, this.getWidth()-12, this.getHeight()-12);
            g.setColor(Color.BLACK);
            g.fillRect(oneX, oneY, 6, 6);
        }
    }

    private void moveIt() {
        while(true){
            if(oneX >= drawPanel.getWidth()){
                right = false;
                left = true;
            }
            if(oneX <= 7){
                right = true;
                left = false;
            }
            if(oneY >= drawPanel.getHeight()){
                up = true;
                down = false;
            }
            if(oneY <= 7){
                up = false;
                down = true;
            }
            if(up){
                oneY--;
            }
            if(down){
                oneY++;
            }
            if(left){
                oneX--;
            }
            if(right){
                oneX++;
            }
            try{
                Thread.sleep(5);
            } catch (Exception exc){}
            frame.repaint();
        }
    }
}