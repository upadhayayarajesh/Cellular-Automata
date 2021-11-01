package gameOfLife;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.util.ArrayList;
public class Game {
    // width of the scene
    public  static final int width = 1000;
    // height of the scene
    public  static final int height = 1000;
    // size of cell
    public static final int cellSize = 30;
    // rows
    private final int rows;
    private final int cols;
    private int[][] grid;
    // to create 2d grid  and for the filling of the grid  graphics
    private final GraphicsContext graphics;
    // constructor game
    public Game(int rows, int cols, GraphicsContext graphics) {
        this.rows = rows;
        this.cols = cols;
        this.graphics = graphics;
        grid = new int[rows][cols];
    }
    // to fill the grid with the given filearray which contain the  initial state of the given file.
    public void fillGrid(ArrayList<String> fileArray) {
        for(int r=0 ;r<fileArray.size();r++){
            for(int c= 0; c <fileArray.get(r).length();c++){
                grid[r][c]=Integer.parseInt(String.valueOf(fileArray.get(r).charAt(c)));
            }
        }

        sketch();
    }
    // this method is to fill the grid with the cell state condition.
    private void sketch() {
        // this fill the graphics  starting form( 0,0) to  the given height and width.
        graphics.fillRect(0, 0, width, height);
        // iterating over the grid to fill it with  the given condition
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // it checks for the grid condition and checks for the graphics with its neightbour cells.
                if (grid[i][j] == 1) {
                    graphics.setFill(Color.BLACK);
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }else {
                    graphics.setFill(Color.WHITE);
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                    graphics.fillRect((i * cellSize) + 1, (j * cellSize) + 1, cellSize - 2, cellSize - 2);
                }
            }
        }
    }
// this is for the next generation.
    public void nextGen() {
        // creating a tempGrid  to store the result based on the nubmer of neighbour cells.
        int[][] tempGrid = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = numNeighbor(i, j);

                if (neighbors == 3) {
                    tempGrid[i][j] = 1;
                }else if (neighbors < 2 || neighbors > 3) {
                    tempGrid[i][j] = 0;
                }else {
                    tempGrid[i][j] = grid[i][j];
                }
            }
        }
        grid = tempGrid;
        sketch();
    }
// this method is to count the number of neighbor cells.
    private int numNeighbor(int row, int column) {
        int sum = 0;
        int rowStart,rowEnd,columStart,columnEnd;
        if(row==0){ rowStart=0; }else{ rowStart=-1; }

        if(row==grid.length-1){ rowEnd=0;}else{ rowEnd=1;}

        if(column==0){columStart=0;} else{columStart=-1;}
        if(column==grid[0].length-1){columnEnd=0;} else{columnEnd=1;}

        for (int k = rowStart; k <= rowEnd; k++) {
            for (int l = columStart; l <= columnEnd; l++) {
                sum += grid[row + k][l + column]; } }
        sum -= grid[row][column];
        return sum;
    }
}
