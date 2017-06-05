package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Operaciones con matrices");
        primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
    }


    public static void main(String[] args) {
        //double [][] mat1 = {{1,2,3},{2,3,6}};
        double [][] mat2 = {{3,4,3},{2,5,1},{4,2,8}};
        //Matriz m1 = new Matriz(mat1.length, mat1[0].length,  mat1);
        Matriz m2 = new Matriz(mat2.length, mat2[0].length, mat2);
        //m1.toString();
        m2.toString();
        m2.inversa().toString();

        launch(args);
    }
}
