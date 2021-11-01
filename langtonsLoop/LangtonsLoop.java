package langtonsLoop;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class LangtonsLoop extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        // this intialization for rows and colums
        int rows =0,cols=0;
        stage.setTitle("Langton's Loop");
        GridPane root = new GridPane();
        List<String> fileArray = new ArrayList<>();
        Scene scene = new Scene(root,1000,1000);
        stage.setScene(scene);
        stage.show();
        // this is to read the file path.
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader("resources\\langtonsLoop\\init_config.txt"));
            String line = reader.readLine();
            while (line != null) {
                fileArray.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) { e.printStackTrace(); }

        String rowColm=fileArray.get(0);
        String[] rowColumnArray = rowColm.split(" ");
        rows = Integer.parseInt(rowColumnArray[0]);
        cols = Integer.parseInt(rowColumnArray[1]);
        fileArray.remove(0);
        // this add the content form the file and add it to  rowArray.
        List<List<LCell>> firstGen = new ArrayList<>();
        for(int i=0; i<fileArray.get(0).length();i++){
            List<LCell> rowArray = new ArrayList<>();
            for(int j=0; j<fileArray.size();j++){
                rowArray.add(LCell.statechar(fileArray.get(i).charAt(j)));
            }
            firstGen.add(rowArray); }
        List<String> rule =new ArrayList<>();
        // this is to read the rule file.
        try {
            reader = new BufferedReader(new FileReader("resources\\langtonsLoop\\rule_table.txt"));
            String line = reader.readLine();
            while (line != null) {
                rule.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // this create the grid
        LGrid grid = new LGrid(root,firstGen,10);
        // this runs the timer
        Timer.run(grid);
    }


}
