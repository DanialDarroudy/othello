package config;

import logic.CreateBoard;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Config {
    private final int screenWidth;
    private final int screenHeight;
    private int windowWidth;
    private int windowHeight;
    private int rowCount;
    private int columnCount;
    private String winner1;
    private String winner2;
    private String tie;
    private String turn;
    private int sizeOfPieces;
    private final ArrayList<Integer> x = new ArrayList<>();
    private final ArrayList<Integer> y = new ArrayList<>();
    private final ArrayList<Boolean> booleans = new ArrayList<>();
    //---------------------------------------------------methods------------------------------------------------------//
    public void setConfig() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("./Config.txt"));
        setCoordinates(reader);
        setTexts(reader);
        setPieces(reader);
        reader.close();
    }
    public void setCoordinates(BufferedReader reader) throws IOException {
        //for width
        String input = reader.readLine();
        int value = Integer.parseInt(input.split(" ")[1]);
        this.windowWidth = value;
        //for height
        input = reader.readLine();
        value = Integer.parseInt(input.split(" ")[1]);
        this.windowHeight = value;
        //for row
        input = reader.readLine();
        value = Integer.parseInt(input.split(" ")[1]);
        this.rowCount = value;
        //for column
        input = reader.readLine();
        value = Integer.parseInt(input.split(" ")[1]);
        this.columnCount = value;
    }
    public void setTexts(BufferedReader reader) throws IOException {
        //for texts
        String input = reader.readLine();
        this.winner1 = input;
        input = reader.readLine();
        this.winner2 = input;
        input = reader.readLine();
        this.tie = input;
        input = reader.readLine();
        this.turn = input;
    }
    public void setPieces(BufferedReader reader) throws IOException {
        //for size
        String input = reader.readLine();
        this.sizeOfPieces = Integer.parseInt(input);
        //for pieces
        input = reader.readLine();
        while (input != null){
            this.x.add(Integer.parseInt(input.split(" ")[0]));
            this.y.add(Integer.parseInt(input.split(" ")[1]));
            this.booleans.add(Boolean.parseBoolean(input.split(" ")[2]));
            input = reader.readLine();
        }
    }
    public boolean isFinish(){
        return !(CreateBoard.getInstance().playerHasMove(false) || CreateBoard.getInstance().playerHasMove(true));
    }
    //---------------------------------------------------methods------------------------------------------------------//
    //---------------------------------------------------for singleton------------------------------------------------//
    private final static Config config = new Config();
    private Config(){
        try {
            setConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        this.screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }
    public static Config getInstance(){
        return config;
    }
    //---------------------------------------------------for singleton------------------------------------------------//
    //---------------------------------------------------getters------------------------------------------------------//
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public ArrayList<Integer> getX() {
        return x;
    }

    public ArrayList<Integer> getY() {
        return y;
    }

    public ArrayList<Boolean> getBooleans() {
        return booleans;
    }

    public String getWinner1() {
        return winner1;
    }

    public String getWinner2() {
        return winner2;
    }

    public String getTie() {
        return tie;
    }

    public String getTurn() {
        return turn;
    }

    public int getSizeOfPieces() {
        return sizeOfPieces;
    }
    //---------------------------------------------------getters------------------------------------------------------//
}
