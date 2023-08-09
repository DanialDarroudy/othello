package graphic;

import config.Config;
import logic.CreateBoard;
import logic.Piece;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ShowBoard extends JPanel {

    //---------------------------------------------------for singleton------------------------------------------------//
    private final static ShowBoard board = new ShowBoard();
    private ShowBoard(){
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                int x = me.getX() / (Config.getInstance().getWindowWidth() / Config.getInstance().getRowCount());
                int y = me.getY() / (Config.getInstance().getWindowHeight() / Config.getInstance().getColumnCount());
                CreateBoard.getInstance().clickMouse(x , y);
            }
        });
    }
    public static ShowBoard getInstance() {
        return board;
    }
    //---------------------------------------------------for singleton------------------------------------------------//
    //---------------------------------------------------methods------------------------------------------------------//
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackGround();
        int windowHeight = Config.getInstance().getWindowHeight() - 20;
        int windowWidth = Config.getInstance().getWindowWidth() - 5;
        if (Config.getInstance().isFinish()) {
            finish(windowHeight , g);
        } else {
            showTurn(windowHeight , g);
        }
        lining(windowHeight , g , windowWidth);
        drawPieces(windowHeight , g , windowWidth);
    }
    public void setBackGround(){
        getInstance().setBackground(new Color(0, 106, 65));
    }
    public void finish(int windowHeight , Graphics g){
        int p1Score = 0;
        int p2Score = 0;
        for (Piece piece : CreateBoard.getInstance().getPieces()) {
            p1Score += piece.isColor() ? 0 : 1;
            p2Score += piece.isColor() ? 1 : 0;
        }
        String gameEndStr;
        if (p1Score > p2Score)
            gameEndStr = Config.getInstance().getWinner1();
        else if (p2Score > p1Score)
            gameEndStr = Config.getInstance().getWinner2();
        else
            gameEndStr = Config.getInstance().getTie();
        g.drawString(gameEndStr, 0, windowHeight + 15);
    }
    public void showTurn(int windowHeight , Graphics g){
        g.drawString(Config.getInstance().getTurn() + (CreateBoard.getInstance().isPlayerTurn() ? "white" : "black"), 0, windowHeight + 15);
    }
    public void lining(int windowHeight , Graphics g , int windowWidth){
        g.setColor(Color.WHITE);
        for (int i = 0; i <= Config.getInstance().getRowCount(); i++) {
            g.drawLine(0, i * windowHeight / Config.getInstance().getRowCount(), windowWidth, i * windowHeight / Config.getInstance().getRowCount());
            g.drawLine(i * windowWidth / Config.getInstance().getColumnCount(), 0, i * windowWidth / Config.getInstance().getRowCount(), windowHeight);
        }
    }
    public void drawPieces(int windowHeight , Graphics g , int windowWidth){
        for (Piece piece : CreateBoard.getInstance().getPieces()) {
            int x = piece.getX() * (windowWidth / Config.getInstance().getColumnCount()) + (windowWidth / Config.getInstance().getColumnCount()) / 2 - piece.getSize() / 2;
            int y = piece.getY() * (windowHeight / Config.getInstance().getRowCount()) + (windowHeight / Config.getInstance().getRowCount()) / 2 - piece.getSize() / 2;
            g.setColor(piece.isColor() ? Color.WHITE : Color.BLACK);
            g.drawOval(x, y, piece.getSize(), piece.getSize());
            g.fillOval(x, y, piece.getSize(), piece.getSize());
        }
    }
    //---------------------------------------------------methods------------------------------------------------------//
}
