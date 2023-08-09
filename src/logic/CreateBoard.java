package logic;

import config.Config;

import java.util.ArrayList;

public class CreateBoard {
    private boolean playerTurn = false;
    private final ArrayList<Piece> pieces = new ArrayList<>();
    //---------------------------------------------------methods------------------------------------------------------//
    public void clickMouse(int x , int y){
        if (x >= Config.getInstance().getRowCount() || y >= Config.getInstance().getColumnCount()) {
            return;
        }
        boolean doesFlip = false;
        Piece[][] board = getInstance().getCellState();
        if (board[x][y] != null) {
            return;
        }
        computing(x , y , doesFlip , board);
    }
    public Piece[][] getCellState() {
        Piece[][] board = new Piece[Config.getInstance().getRowCount()][Config.getInstance().getColumnCount()];
        for (Piece piece : getInstance().pieces) {
            board[piece.getX()][piece.getY()] = piece;
        }
        return board;
    }
    public boolean playerHasMove(boolean player) {
        for (int i = 0; i < Config.getInstance().getRowCount(); i++)
            for (int j = 0; j < Config.getInstance().getColumnCount(); j++) {
                Piece[][] board = getInstance().getCellState();
                if (board[i][j] != null) {
                    continue;
                }
                int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
                int[] dy = {-1, 1, 1, -1, 1, -1, 0, 0};
                boolean doesFlip = false;
                mainLoop:
                for (int k = 0; k < dx.length; k++) {
                    ArrayList<Piece> flipRow = new ArrayList<>();
                    for (int l = 1; l <= Math.max(Config.getInstance().getRowCount(), Config.getInstance().getColumnCount()); l++) {
                        int nx = i + l * dx[k], ny = j + l * dy[k];
                        if (nx >= Config.getInstance().getColumnCount() || nx < 0 || ny >= Config.getInstance().getRowCount() || ny < 0 || board[nx][ny] == null)
                            continue mainLoop;
                        if (board[nx][ny].isColor() == player)
                            break;
                        flipRow.add(board[nx][ny]);
                    }
                    doesFlip = !flipRow.isEmpty();
                }
                if (doesFlip)
                    return true;
            }
        return false;
    }
    public void computing(int x , int y, boolean doesFlip , Piece[][] board){
        int[] dx = {1, 1, -1, -1, 0, 0, 1, -1};
        int[] dy = {-1, 1, 1, -1, 1, -1, 0, 0};
        mainLoop:
        for (int k = 0; k < dx.length; k++) {
            ArrayList<Piece> flipRow = new ArrayList<>();
            for (int i = 1; i <= Math.max(Config.getInstance().getRowCount(), Config.getInstance().getColumnCount()); i++) {
                int nx = x + i * dx[k], ny = y + i * dy[k];
                if (nx >= Config.getInstance().getColumnCount() || nx < 0 ||
                       ny >= Config.getInstance().getRowCount() || ny < 0 || board[nx][ny] == null)
                    continue mainLoop;
                if (board[nx][ny].isColor() == playerTurn)
                    break;
                flipRow.add(board[nx][ny]);
            }
            for (Piece piece : flipRow) {
                doesFlip = true;
                piece.setColor(playerTurn);
            }
        }
        if (doesFlip) {
            getInstance().pieces.add(new Piece(x, y, playerTurn));
        }
        else {
            return;
        }
        playerTurn = !playerTurn;
        if (!getInstance().playerHasMove(playerTurn))
            playerTurn = !playerTurn;
    }
    //---------------------------------------------------methods------------------------------------------------------//
    //---------------------------------------------------for singleton------------------------------------------------//
    private final static CreateBoard board = new CreateBoard();
    private CreateBoard(){}
    public static CreateBoard getInstance() {
        return board;
    }
    //---------------------------------------------------for singleton------------------------------------------------//
    //---------------------------------------------------getters------------------------------------------------------//
    public ArrayList<Piece> getPieces() {
        return pieces;
    }
    public boolean isPlayerTurn() {
        return playerTurn;
    }
    //---------------------------------------------------getters------------------------------------------------------//
}
