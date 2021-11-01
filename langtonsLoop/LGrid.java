package langtonsLoop;
import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.shape.Rectangle;
// constructor LGrid
public class LGrid {
    private final GridPane GRID_PANE;
    private final double CELL_SIZE;
    // 2D list cueentGen
    List<List<LCell>> currentGen;
    private int currentGenIndex;

    public LGrid(GridPane gridpane, List<List<LCell>> currentGen,
                 double cellsize){
        this.GRID_PANE = gridpane;
        this.CELL_SIZE = cellsize;
        this.currentGen = currentGen;
        this.currentGenIndex=0;
        gridDisplay(); }
        // this gridDisplay is to display the grid based on its initial condition.
    private void gridDisplay(){
        int rIndex=0,cIndex=0;
        List<List<LCell>> cellArray = new ArrayList<>();
        for(List<LCell> row: currentGen){
            List<LCell> rowCellArray = new ArrayList<>();
            for(LCell cell: row){
                rowCellArray.add(cell);
                Rectangle rect = new Rectangle(CELL_SIZE,CELL_SIZE,cell.getColor());
                GRID_PANE.add(rect,cIndex,rIndex);
                cIndex++; }
            cellArray.add(rowCellArray);
            cIndex=0;
            rIndex++;
        }
    }

}
