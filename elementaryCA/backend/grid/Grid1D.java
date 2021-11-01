package elementaryCA.backend.grid;
import elementaryCA.backend.cell.Cell;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import java.util.List;

public class Grid1D {
    // Used by JavaFX to display the visualization
    private final GridPane gridPane;
    // 8 bit string representing the behavior of the CA
    private final String behavior;
    // Width/height of cell
    private final double cellSize;

    // Current generation of cells
    private List<Cell> currentGen;
    // Current generation (row) being shown to the screen
    private int currentGenIndex;

    public Grid1D(GridPane gridPane,
                  String behavior,
                  List<Cell> currentGen,
                  double cellSize) {
        this.gridPane = gridPane;
        this.behavior = behavior;
        this.currentGen = currentGen;
        this.cellSize = cellSize;
        this.currentGenIndex = 0;
        show();

    }
    private void evolve() {
        // to find the length of currentGen
        int length = currentGen.size() ;
        // length of Cell
        Cell[] tempGen= new Cell[length];
        //to find the state of left, center and right cell.
        for (int i=0; i<length;i++ ){
            Cell left, center, right;
            if(i==0){
                left=currentGen.get(length-1);
            }else {
                left = currentGen.get(i - 1);
            }
            center=currentGen.get(i);
            if(i==length-1){
                right=currentGen.get(0);
            }else {
                right = currentGen.get(i + 1);
            }
            // checking the behaviour of the cells based on the given comndition
            if((left.equals(Cell.WHITE))&&(center.equals(Cell.WHITE) )&& (right.equals(Cell.WHITE))) {
                // it assigh the tempGen to whit or black based on the cell state.
                tempGen[i]=behavior.charAt(7)=='0'?Cell.WHITE :Cell.BLACK;
            } else if((left.equals(Cell.WHITE))&&(center.equals(Cell.WHITE) )&& (right.equals(Cell.BLACK))){
                tempGen[i]=behavior.charAt(6)=='0'?Cell.WHITE :Cell.BLACK;
            }else if((left.equals(Cell.WHITE))&&(center.equals(Cell.BLACK) )&& (right.equals(Cell.WHITE))){
                tempGen[i]=behavior.charAt(5)=='0'?Cell.WHITE :Cell.BLACK;
            }else if((left.equals(Cell.WHITE))&&(center.equals(Cell.BLACK) )&& (right.equals(Cell.BLACK))){
                tempGen[i]=behavior.charAt(4)=='0'?Cell.WHITE :Cell.BLACK;
            }else if((left.equals(Cell.BLACK))&&(center.equals(Cell.WHITE) )&& (right.equals(Cell.WHITE))){
                tempGen[i]=behavior.charAt(3)=='0'?Cell.WHITE :Cell.BLACK;
            }else if((left.equals(Cell.BLACK))&&(center.equals(Cell.WHITE) )&& (right.equals(Cell.BLACK))){
                tempGen[i]=behavior.charAt(2)=='0'?Cell.WHITE :Cell.BLACK;
            }else if((left.equals(Cell.BLACK))&&(center.equals(Cell.BLACK) )&& (right.equals(Cell.WHITE))){
                tempGen[i]=behavior.charAt(1)=='0'?Cell.WHITE :Cell.BLACK;
            }else if((left.equals(Cell.BLACK))&&(center.equals(Cell.BLACK) )&& (right.equals(Cell.BLACK))){
                tempGen[i]=behavior.charAt(0)=='0'?Cell.WHITE :Cell.BLACK;
            }
        }
        // after checking all the condition it now assigns temGen to currentGen
        int j=0;
        for(Cell cell :tempGen){
            currentGen.set(j,cell);
            j++;
        }




    }
    /**
     * This function shows the current generation to the JavaFX window
     */
    private void show() {
        int colIndex = 0;
        // Create new rectangles to show for the current generation
        for (Cell cell : currentGen) {
            // Create a rectangle to represent the cell
            Rectangle rect = new Rectangle(cellSize, cellSize, cell.getColor());
            // Add it to the JavaFX graph
            gridPane.getChildren().add(rect);
            // Tell it where to show it on the screen
            GridPane.setConstraints(rect, colIndex, currentGenIndex);
            // Go to the next cell
            colIndex++;
        }
        currentGenIndex++;
    }

    /**
     * This function advances the state of the class to the next generation.
     * It then shows this new generation to the Java FX window.
     */
    public void nextGeneration() {
        evolve();
        show();
    }
}
