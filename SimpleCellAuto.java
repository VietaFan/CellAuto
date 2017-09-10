import java.awt.Color;
public class SimpleCellAuto {
    protected byte[][] cells;
    public int size;
    public double wRatio;
    private static Color[] colors = {new Color(255, 255, 255),
                                     new Color(0, 0, 0), 
                                     new Color(255, 0, 0),
                                     new Color(0, 255, 0),
                                     new Color(0, 0, 255),
                                     new Color(255, 255, 0),
                                     new Color(255, 0, 255),
                                     new Color(0, 255, 255)};
    public SimpleCellAuto(int size) {
        this.size = size;
        cells = new byte[size+2][size+2];
        for (int a = 0; a < size+2; a++) {
            for (int b = 0; b < size+2; b++) {
                cells[a][b] = 0;
            }
        }
        initCells();
        wRatio = 1.0;
    }
    protected void initCells() {
        cells[13][13] = 1;
        cells[13][14] = 1;
        cells[13][15] = 1;
        cells[13][16] = 1;
        cells[14][13] = 1;
        cells[14][16] = 1;
        cells[15][13] = 1;
        cells[15][16] = 1;
        cells[16][13] = 1;
        cells[16][14] = 1;
        cells[16][15] = 1;
        cells[16][16] = 1;
    }
    protected byte f(int x, int y) {
        int sum=0;
        for (int a = -1; a < 2; a++) {
            for (int b = -1; b < 2; b++) {
                if (a == 0 && b == 0) continue;
                sum += cells[x+a][y+b];
            }
        }
        if ((sum == 1) || (sum == 8)) return 1;
        return 0;
    }
    public void iterate() {
        int wCount=0;
        byte[][] temp = new byte[size+2][size+2];
        for (int x = 1; x < size+1; x++) {
            for (int y = 1; y < size+1; y++) {
                temp[x][y] = f(x, y);
                if (temp[x][y]==0)wCount++;
            }
        }
        wRatio = ((double)(wCount))/((double)(size*size));
        cells = temp.clone();
    }
    public Color getColor(int x, int y) {
        return colors[cells[x][y]];
    }
}
