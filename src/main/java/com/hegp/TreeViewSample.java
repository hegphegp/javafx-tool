package com.hegp;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TreeViewSample extends Application {

    private final Node rootIcon = new ImageView(
        new Image(getClass().getResourceAsStream("/setting.png"))
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tree View Sample");        

        TreeItem rootItem = new TreeItem("Inbox", rootIcon);
        rootItem.setExpanded(true);
        for (int i = 0; i < 3; i++) {
            TreeItem item = new TreeItem("Message" + i);
            rootItem.getChildren().add(item);
            for (int j = 0; j < 3; j++) {
                TreeItem sonItem = new TreeItem("Message" + i+j);
                item.getChildren().add(sonItem);
            }
        }
        StackPane root = new StackPane();
        root.getChildren().add(new TreeView(rootItem));
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }
}