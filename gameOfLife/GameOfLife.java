package gameOfLife;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import static gameOfLife.Game.width;
import static gameOfLife.Game.height;
public class GameOfLife extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) {
        // initilazation of the rosa and cols.
        int rows =0,cols=0;
        // title of the scene.
        stage.setTitle("Game of Life");
        System.out.println("Read from file or generate? [f/g]");
        // this array is to store the information of the txt file.
        ArrayList<String> readArray = new ArrayList<>();
        // scanner to scan the input.
        Scanner sc = new Scanner(System.in);
        // to read the char at the index 0.
        char c = sc.next().charAt(0);
        if(c=='f') {
            System.out.println("Enter a file ptah : ");
            // this is to read the path of the file and read the rosa and colum and the grid.
            Scanner inScanner = new Scanner(System.in);
            String inFile = inScanner.next();
            BufferedReader reader;
            try {
                reader = new BufferedReader(new FileReader(inFile));
                String line = reader.readLine();
                while (line != null) {
                    readArray.add(line);
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String rowColum = readArray.get(0);
            readArray.remove(0);
            String[] rowColumnArray = rowColum.split(" ");
            rows = Integer.parseInt(rowColumnArray[0]);
            cols = Integer.parseInt(rowColumnArray[1]);
        }else if (c=='g'){
            // this is to generate the file asking for the row, column and the grid.
            System.out.println("Enter the number of rows: ");
            rows=sc.nextInt();
            System.out.println("Enter the number of colums: ");
            cols=sc.nextInt();
            System.out.println("Enter the grid :");
            int count = 0;
            // this is to ask the input of the number of the rows given by the user.
            while (count < (rows )) {
                if (sc.hasNext()) {
                    readArray.add(sc.next());
                    count++;
                } }
        }
        // grid plane
        GridPane root = new GridPane();
        Scene scene = new Scene(root, width, height );
        // creats the canvas to use graphics to get the two dimension.
        final Canvas canvas = new Canvas(4*width,4*height);
        root.getChildren().addAll(canvas);
        stage.setScene(scene);
        stage.show();
        // creates the graphics
        GraphicsContext graphics = canvas.getGraphicsContext2D();
        // this  creates a new game instance.
        Game game = new Game(rows, cols, graphics);
        // this fill the gmae with filGrid method.
        game.fillGrid( readArray);
        // this is for the  Animation timer.
        AnimationTimer runAnimation = new AnimationTimer() {
            private long lastUpdate = 0;
            @Override
            public void handle(long now) {
                if ((now - lastUpdate) >= TimeUnit.MILLISECONDS.toNanos(500)) {
                    game.nextGen();
                    lastUpdate = now;
                }
            }
        };
        runAnimation.start();
    }
}