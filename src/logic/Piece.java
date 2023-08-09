package logic;

import config.Config;

public class Piece {
    private final int x;
    private final int y;
    private final int size;
    private boolean color;

    public Piece(int x, int y, boolean color) {
        this.size = Config.getInstance().getSizeOfPieces();
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }
}
