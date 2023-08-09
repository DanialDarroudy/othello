package graphic;

import config.Config;
import logic.CreateBoard;
import logic.Piece;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ShowBoard panel = ShowBoard.getInstance();
        CreateBoard board = CreateBoard.getInstance();
        initializeFrame(frame);
        initializePanel(panel, frame);
        startGame(board, frame);
    }
    public static void startGame(CreateBoard board, JFrame frame) {
        initializeBoard(board);
        while (true) {
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            frame.repaint();
            frame.revalidate();
            if (Config.getInstance().isFinish()) {
                finish(frame , board);
            }
        }
    }

    public static void initializeFrame(JFrame frame) {
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(Config.getInstance().getWindowWidth() + 15
                , Config.getInstance().getWindowHeight() + 50));
        frame.setLocation((Config.getInstance().getScreenWidth() - Config.getInstance().getWindowWidth()) / 2
                , (Config.getInstance().getScreenHeight() - Config.getInstance().getWindowHeight()) / 2);
        frame.setVisible(true);
    }

    public static void initializePanel(ShowBoard panel, JFrame frame) {
        frame.setContentPane(panel);
        panel.setLayout(null);
    }

    public static void initializeBoard(CreateBoard board) {
        board.getPieces().add(new Piece(Config.getInstance().getX().get(0), Config.getInstance().getY().get(0)
                , Config.getInstance().getBooleans().get(0)));
        board.getPieces().add(new Piece(Config.getInstance().getX().get(1), Config.getInstance().getY().get(1)
                , Config.getInstance().getBooleans().get(1)));
        board.getPieces().add(new Piece(Config.getInstance().getX().get(2), Config.getInstance().getY().get(2)
                , Config.getInstance().getBooleans().get(2)));
        board.getPieces().add(new Piece(Config.getInstance().getX().get(3), Config.getInstance().getY().get(3)
                , Config.getInstance().getBooleans().get(3)));
    }
    public static void finish(JFrame frame , CreateBoard board){
        JOptionPane.showMessageDialog(frame, "somebody win the game!");
        board.getPieces().clear();
        startGame(board, frame);
    }
}