package elementaryCA.backend;
import elementaryCA.backend.cell.Cell;
import elementaryCA.backend.grid.Grid1D;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ElementaryCA extends Application {
    public static void main(String[] args) {
        launch(args); }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("1D Cellular Automata");
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        double width = 1000;
        double height = 1000;
        Scene scene = new Scene(root, width, height);
        primaryStage.setScene(scene);
        String behaviour="";String initialGeneration= "";
        System.out.println("Read from file or generate? [f/g]");
        Scanner sc = new Scanner(System.in);
        char c = sc.next().charAt(0);
        if(c=='f'){
            System.out.println("Enter a file path:");
            Scanner inScanner = new Scanner(System.in);
            String inFile = inScanner.next();
            ArrayList<String> content = new ArrayList<>();
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(inFile));
                String line = reader.readLine();
                while (line != null) {
                    content.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) { e.printStackTrace(); }
            behaviour= content.get(0);
            initialGeneration=content.get(1);

        }else if(c=='g'){
            System.out.println("Enter a rule number:");
            Scanner scanner = new Scanner(System.in);
            int number= sc.nextInt();
            String ruleStr = Integer.toBinaryString(number);
             behaviour = String.format("%8s", ruleStr).replaceAll(" ", "0"); // 8bit binary
            System.out.println("ruleStr = "+ behaviour);
            System.out.println("Enter initial generation: ");
            initialGeneration= scanner.nextLine();
            System.out.println("your initial generation is: "+initialGeneration);
        }

        String firstGenStr =initialGeneration;//"00000000100000000";
        List<Cell> firstGen = new ArrayList<>();
        for( int i =0 ; i<firstGenStr.length();i++){
            firstGen.add(Cell.fromChar(firstGenStr.charAt(i)));

        }
        Grid1D grid = new Grid1D(root,behaviour, firstGen, 50);
        primaryStage.show();
        Runner.run(grid);

    }
}
