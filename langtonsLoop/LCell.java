package langtonsLoop;
import javafx.scene.paint.Color;
// this create the class of enum of LCell with eight color
public enum LCell {
    WHITE(0),
    BLACK(1),
    RED(2),
    GREEN(3),
    BLUE(4),
    INDIGO(5),
    PURPLE(6),
    VIOLET(7);
    private int numState;
    LCell(int numState){ this.numState=numState; }
// thei findState to find the state of the cell
    public int findState(){ return numState; }
// thsi method is to get the color the current cell.
    public Color getColor(){
        if (this == WHITE){ return Color.WHITE; }
        else if(this==BLACK){ return Color.BLACK; }
        else if(this==RED){ return Color.RED; }
        else if(this== GREEN){ return Color.GREEN; }
        else if(this==BLUE){ return Color.BLUE; }
        else if(this== INDIGO){ return Color.INDIGO; }
        else if(this==PURPLE){ return Color.PURPLE; }
        else  if(this==VIOLET){return Color.VIOLET;}
        else{ return Color.PINK; }
    }
// this method is to find the  char of the cell
    public static LCell statechar(char c) throws IllegalArgumentException {
        if (c == '0') { return WHITE; }
        else if (c == '1') { return BLACK; }
        else if (c == '2') { return RED; }
        else if (c == '3') { return GREEN; }
        else if (c == '4') { return BLUE; }
        else if (c == '5') { return INDIGO; }
        else if (c == '6') { return PURPLE; }
        else if (c == '7') { return VIOLET; }
        else { throw new IllegalArgumentException("INVALID CHARACTER"); }
    }

}
