public class SimpleCellAuto {
    private byte[][] cells, shift;
    private final byte size = 30;
    public SimpleCellAuto(int n) {
        cells = new byte[32][32];
        shift = new byte[3][3];
        for (byte a = 0; a < 3; a++) {
            for (byte b = 0; b < 3; b++) {
                if (a == 1 && b == 1)
                    continue;
                shift[a][b] = n%2;
                n >>= 1;
            }
        }
        for (byte a = 0; a < 32; a++) {
            for (byte b = 0; b < 32; b++) {
                cells[a][b] = 0;
            }
        }
    }
    public iterate() {
        byte sum;
        for (byte x = 1; x < size+1; x++) {
            for (byte y = 1; y < size+1; y++) {
                sum = 0;
                for (byte dx = 0; dx < 3; ++dx) {
                    for (byte dy = y-1; dy < y+2; ++dy) {
                        sum += cells[x+dx][y+dy]*shift[dx][dy];
                    }
                }
                cells[x][y] = sum%2;
            }
        }
    }
