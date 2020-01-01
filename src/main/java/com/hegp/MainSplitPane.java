package com.hegp;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class MainSplitPane extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //创建一个滚动面板
        SplitPane splitPane = new SplitPane();

        //创建两个普通面板
        Pane leftPane = new Pane(new Button("左边的面板"));
        leftPane.setMinWidth(Region.USE_PREF_SIZE);
        leftPane.setMaxWidth(250);
        Pane centerPane = new Pane(new Button("中间的面板"));
        centerPane.setMinWidth(Region.USE_PREF_SIZE);
//        centerPane.setMaxWidth(200);
        Pane rightPane = new Pane(new Button("右边的面板"));
        rightPane.setMinWidth(Region.USE_PREF_SIZE);
//        rightPane.setMaxWidth(200);
        //添加到滚动面板中
        splitPane.getItems().addAll(leftPane, centerPane, rightPane);

        primaryStage.setScene(new Scene(splitPane, 900, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}