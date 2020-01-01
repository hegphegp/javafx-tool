package com.hegp;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainBorderPane extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        pane.setTop(getCenterVBox("Top")); //放置在上面
        pane.setRight(getCenterVBox("Right"));//放置在右边
        pane.setBottom(getCenterVBox("Bottom"));//放置在上面
        pane.setLeft(getCenterVBox("Left"));//放置在左边
        pane.setCenter(getCenterVBox("Center"));//放置在中间
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private VBox getCenterVBox(String labelText) {
        final VBox vBox = new VBox();
        Button button = new Button(labelText);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-padding: 1;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 1;" +
                "-fx-border-radius: 1;" +
                "-fx-border-color: blue;");
        vBox.getChildren().add(button);
        return vBox;
    }

    private VBox getVBox(String labelText) {
        final VBox vBox = new VBox();
        vBox.setTranslateX(0);
        vBox.setTranslateY(0);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-padding: 1;" +
                "-fx-border-style: solid inside;" +
                "-fx-border-width: 1;" +
                "-fx-border-insets: 1;" +
                "-fx-border-radius: 1;" +
                "-fx-border-color: blue;");
        Text title = new Text(labelText);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        vBox.getChildren().add(title);
        return vBox;
    }
}