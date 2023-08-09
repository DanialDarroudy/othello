package test;


import config.Config;
import graphic.Main;
import graphic.ShowBoard;
import logic.CreateBoard;
import logic.Piece;
import org.junit.*;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestOtello {
    //---------------------------------------------------tests--------------------------------------------------------//
    @Test
    public void testCoordinates() throws IOException {
        Config config = Config.getInstance();
        config.setCoordinates(new BufferedReader(new FileReader("./Config.txt")));
        assertEquals(config.getWindowHeight() , 500);
        assertEquals(config.getWindowWidth() , 500);
        assertEquals(config.getRowCount() , 8);
        assertEquals(config.getColumnCount() , 8);
    }
    @Test
    public void testTexts() throws IOException{
        Config config = Config.getInstance();
        config.setCoordinates(new BufferedReader(new FileReader("./Config.txt")));
        assertEquals(config.getTie() , "Tie");
        assertEquals(config.getTurn() , "Player turn : ");
        assertEquals(config.getWinner1() , "Winner: Player 1");
        assertEquals(config.getWinner2() , "Winner: Player 2");
    }
    @Test
    public void testPieces() throws IOException{
        Config config = Config.getInstance();
        config.setCoordinates(new BufferedReader(new FileReader("./Config.txt")));
        assertEquals(config.getSizeOfPieces() , 50);
        ArrayList<Integer> x = new ArrayList<>();
        ArrayList<Integer> y = new ArrayList<>();
        ArrayList<Boolean> booleans = new ArrayList<>();
        initializeArrays(x , y , booleans);
        assertArrayEquals(config.getX().toArray() , x.toArray());
        assertArrayEquals(config.getY().toArray() , y.toArray());
        assertArrayEquals(config.getBooleans().toArray() , booleans.toArray());
    }
    @Test
    public void testIsFinish(){
        Config config = Config.getInstance();
        assertFalse(config.isFinish());
    }
    @Test
    public void testFrame(){
        JFrame frame = new JFrame();
        Main.initializeFrame(frame);
        assertNotNull(frame.getLayout());
        assertEquals(frame.getDefaultCloseOperation() , 3);
        assertEquals(frame.getSize() , new Dimension(515 , 550));
        assertTrue(frame.isVisible());
    }
    @Test
    public void testPanel(){
        JFrame frame = new JFrame();
        ShowBoard panel = ShowBoard.getInstance();
        Main.initializePanel(panel , frame);
        assertNull(panel.getLayout());
        assertEquals(frame.getContentPane() , panel);
    }
    @Test
    public void testBoard(){
        CreateBoard board = CreateBoard.getInstance();
        Main.initializeBoard(board);
        ArrayList<Piece> pieces = new ArrayList<>();
        initializeBoard(pieces);
        for (int i = 0; i < pieces.size(); i++) {
            assertEquals(board.getPieces().get(i).getSize() , pieces.get(i).getSize());
            assertEquals(board.getPieces().get(i).getX() , pieces.get(i).getX());
            assertEquals(board.getPieces().get(i).getY() , pieces.get(i).getY());
            assertEquals(board.getPieces().get(i).isColor() , pieces.get(i).isColor());
        }
    }
    @Test
    public void testBackGround(){
        ShowBoard board = ShowBoard.getInstance();
        board.setBackGround();
        assertEquals(board.getBackground() , new Color(0, 106, 65));
    }
    @Test
    public void testPiece(){
        Piece piece = new Piece(2 , 2 , true);
        assertTrue(piece.isColor());
        assertEquals(piece.getY() , 2);
        assertEquals(piece.getX() , 2);
        assertEquals(piece.getSize() , 50);
    }
    @Test
    public void testCellState(){
        CreateBoard createBoard = CreateBoard.getInstance();
        Piece[][] board = new Piece[Config.getInstance().getRowCount()][Config.getInstance().getColumnCount()];
        for (Piece piece : createBoard.getPieces()) {
            board[piece.getX()][piece.getY()] = piece;
        }
        assertArrayEquals(board , createBoard.getCellState());
    }
    @Test
    public void testGame(){
        CreateBoard board = CreateBoard.getInstance();
        board.clickMouse(4 , 2);
        board.clickMouse(5 , 2);
        board.clickMouse(6 ,2);
        board.clickMouse(5 ,4);
        board.clickMouse(4 ,5);
        board.clickMouse(3 ,2);
        board.clickMouse(2 ,2);
        board.clickMouse(4 ,1);
        board.clickMouse(2 ,3);
        board.clickMouse(2 , 4);
        board.clickMouse(5 ,3);
        board.clickMouse(5 ,1);
        for (int i = 0; i < 16; i++) {
            assertNotNull(board.getPieces().get(i));
        }
        assertFalse(board.isPlayerTurn());
        assertTrue(board.getCellState()[5][2].isColor());
        assertTrue(board.playerHasMove(true));
    }

    //---------------------------------------------------tests--------------------------------------------------------//
    public static void initializeArrays(ArrayList<Integer> x , ArrayList<Integer> y , ArrayList<Boolean> booleans){
        x.add(3);
        x.add(4);
        x.add(3);
        x.add(4);
        y.add(3);
        y.add(4);
        y.add(4);
        y.add(3);
        booleans.add(false);
        booleans.add(false);
        booleans.add(true);
        booleans.add(true);
    }
    public static void initializeBoard(ArrayList<Piece> pieces){
        pieces.add(new Piece(Config.getInstance().getX().get(0), Config.getInstance().getY().get(0)
                , Config.getInstance().getBooleans().get(0)));
        pieces.add(new Piece(Config.getInstance().getX().get(1), Config.getInstance().getY().get(1)
                , Config.getInstance().getBooleans().get(1)));
        pieces.add(new Piece(Config.getInstance().getX().get(2), Config.getInstance().getY().get(2)
                , Config.getInstance().getBooleans().get(2)));
        pieces.add(new Piece(Config.getInstance().getX().get(3), Config.getInstance().getY().get(3)
                , Config.getInstance().getBooleans().get(3)));
    }
}
