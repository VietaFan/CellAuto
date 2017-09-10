import java.awt.*;
import java.awt.event.*;
public class GUIDriver implements MainClass{
    static GUISkeleton win;
    static GUIDriver c;
    static SimpleCellAuto automaton;
    static int frameCount;
    static int size;
    static boolean stopped;
    public GUIDriver() {}
    public static void main(String[] args) {
        size=200;
        stopped = false;
        frameCount = 0;
        automaton = new MultiColorCellAuto(size);
        c = new GUIDriver();
        win = new GUISkeleton("Cellular Automaton", new Color(255, 255, 255), 
                              new Dimension(600, 600), 100, c);
    }
    public void iterate() {
        if (!stopped)
        frameCount += 1;
        try {
            char c = win.getPressed().get(0).getKeyChar();
            if (c=='s') {
                stopped = true;
                frameCount = 1;
            } else if (c=='g') {
                stopped = false;
            }
        } catch(IndexOutOfBoundsException e) {}
        
        if (frameCount%40== 0)
        {
            
            automaton.iterate();
            while (automaton.wRatio < 0.05 || automaton.wRatio > 0.95)
            automaton.iterate();
        }
        if (frameCount%100 == 0)
            System.gc();
    }
    public void draw(Graphics g) {
        int incr = 600/size;
        for (int x = 1; x < size+1; x++) {
            for (int y = 1; y < size+1; y++) {
                g.setColor(automaton.getColor(x, y));
                g.fillRect(x*incr-incr, y*incr-incr, incr, incr);
            }
        }
        
    }
}
