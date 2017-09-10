import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
class MouseDetector implements MouseListener{
    GUISkeleton master;
    public MouseDetector(GUISkeleton g) {
        master = g;
    }
    public void mouseExited(MouseEvent e) {
        master.mouseQueue.add(0, e);
    }
    public void mouseEntered(MouseEvent e) {
        master.mouseQueue.add(0, e);
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        master.mouseQueue.add(0, e);
    }
    @Override
    public void mousePressed(MouseEvent e) {
        master.mouseQueue.add(0, e);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        master.mouseQueue.add(0, e);
    }
}
class KeyDetector implements KeyListener {
    GUISkeleton master;
    public KeyDetector(GUISkeleton g) {
        master = g;
    }
    public void keyReleased(KeyEvent e) {
        master.releasedQueue.add(0, e);
    }
    @Override
    public void keyPressed(KeyEvent e) {
        master.pressedQueue.add(0, e);
    }
    @Override
    public void keyTyped(KeyEvent e) {
        master.typedQueue.add(0, e);
    }
}
public class GUISkeleton extends JPanel implements Runnable {
    ArrayList<KeyEvent> pressedQueue;
    ArrayList<MouseEvent> mouseQueue;
    ArrayList<KeyEvent> typedQueue;
    ArrayList<KeyEvent> releasedQueue;
    JFrame win;
    int delayTime;
    MainClass main;
    public GUISkeleton(String title, Color bgColor, Dimension size, int framesPerSecond, MainClass caller) {
        this.setPreferredSize(size);
        this.setBackground(bgColor);
        this.setVisible(true);
        win = new JFrame(title); //switch to desired title
        pressedQueue = new ArrayList();
        releasedQueue = new ArrayList();
        typedQueue = new ArrayList();
        mouseQueue = new ArrayList();
        win.setResizable(false);
        win.setBackground(new Color(255, 255, 255));
        win.setContentPane(this);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.pack();
        //Add listeners for input events
        this.addMouseListener(new MouseDetector(this));
        this.addKeyListener(new KeyDetector(this));
        new Thread(this).start();
        win.setVisible(true);
        this.requestFocus();
        delayTime = 1000 / framesPerSecond;
        main = caller;
    }
    public GUISkeleton() {
        this.setPreferredSize(new Dimension(640, 480));
        this.setBackground(new Color(255, 255, 255));
        this.setVisible(true);
        win = new JFrame("Default_Title"); //switch to desired title
        pressedQueue = new ArrayList();
        releasedQueue = new ArrayList();
        typedQueue = new ArrayList();
        mouseQueue = new ArrayList();
        win.setResizable(false);
        win.setBackground(new Color(255, 255, 255));
        win.setContentPane(this);
        win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        win.pack();
        //Add listeners for input events
        this.addMouseListener(new MouseDetector(this));
        this.addKeyListener(new KeyDetector(this));
        new Thread(this).start();
        win.setVisible(true);
        this.requestFocus();
    }
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        try {
            main.draw(g);
        } catch (Exception e) {
            
        }
    }
    public ArrayList<KeyEvent> getPressed() throws IndexOutOfBoundsException {
        if (pressedQueue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        ArrayList<KeyEvent> i = (ArrayList<KeyEvent>) pressedQueue.clone();
        pressedQueue.clear();
        return i;
    }
    public ArrayList<KeyEvent> getTyped() throws IndexOutOfBoundsException {
        if (typedQueue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        ArrayList<KeyEvent> i = (ArrayList<KeyEvent>) typedQueue.clone();
        typedQueue.clear();
        return i;
    }
    public ArrayList<KeyEvent> getReleased() throws IndexOutOfBoundsException {
        if (releasedQueue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        ArrayList<KeyEvent> i = (ArrayList<KeyEvent>) releasedQueue.clone();
        releasedQueue.clear();
        return i;
    }
    public MouseEvent getMouseEvent() throws IndexOutOfBoundsException {
        if (mouseQueue.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return mouseQueue.remove(0);
    }
    public void run(){
        while (true) {
            repaint();
            try {
                main.iterate();
                Thread.sleep(delayTime); //replace with your desired delay time
            } catch (Exception e) {
            }
        }
    }
}
