package com.hegp;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class TabPaneApp extends Application {

    private TabPane tabPane;
    private Tab internalTab;
    private ImageView imageView = new ImageView(new Image(getClass().getResourceAsStream("/setting.png")));

    public Parent createContent() {
        //Each tab illustrates different capabilities
        tabPane = new TabPane();
        setTabPaneCenter();
        tabPane.setSide(Side.TOP);
        tabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        Tab tab1 = new Tab();
        internalTab = new Tab();

        final VBox vbox = getVBox();
        // Initial tab with buttons for experimenting
        tab1.setText("Tab 1");
        tab1.setTooltip(new Tooltip("Tab 1 Tooltip"));
        tab1.setGraphic(imageView);

        setUpControlButtons(vbox);
        tab1.setContent(vbox);
        tabPane.getTabs().add(tab1);

        // Internal Tabs
        internalTab.setText("Internal Tabs");
        setupInternalTab();
        tabPane.getTabs().add(internalTab);
        return tabPane;
    }

    private void setTabPaneCenter() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth  = primaryScreenBounds.getWidth();
        double screenHeight = primaryScreenBounds.getHeight();
        double widthRatio = 0.5;  // 占屏幕总宽度的比例
        double heightRatio = 0.6; // 占屏幕总高度的比例
        tabPane.setPrefSize(screenWidth * widthRatio, screenHeight * heightRatio);
        tabPane.setMinSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
        tabPane.setMaxSize(TabPane.USE_PREF_SIZE, TabPane.USE_PREF_SIZE);
    }

    private VBox getVBox() {
        final VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setTranslateX(10);
        vBox.setTranslateY(10);
        return vBox;
    }

    private void toggleTabPosition(TabPane tabPane) {
        Side pos = tabPane.getSide();
        if (pos == Side.TOP) {
            tabPane.setSide(Side.RIGHT);
        } else if (pos == Side.RIGHT) {
            tabPane.setSide(Side.BOTTOM);
        } else if (pos == Side.BOTTOM) {
            tabPane.setSide(Side.LEFT);
        } else {
            tabPane.setSide(Side.TOP);
        }
    }

    private void setupInternalTab() {
        StackPane internalTabContent = new StackPane();
        final TabPane internalTabPane = new TabPane();
        internalTabPane.setSide(Side.LEFT);

        internalTabPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        final Tab innerTab = new Tab();
        innerTab.setText("Tab 1");
        final VBox innerVbox = getVBox();
        Button innerTabPosButton = new Button("Toggle Tab Position");
        innerTabPosButton.setOnAction((ActionEvent e) -> {
            toggleTabPosition(internalTabPane);
        });
        innerVbox.getChildren().add(innerTabPosButton);
        innerTab.setContent(innerVbox);
        internalTabPane.getTabs().add(innerTab);

        for (int i = 1; i < 5; i++) {
            Tab tab = new Tab();
            tab.setText("Tab " + i);
            tab.setContent(new Region());
            internalTabPane.getTabs().add(tab);
        }
        internalTabContent.getChildren().add(internalTabPane);
        internalTab.setContent(internalTabContent);
    }

    private void setUpControlButtons(VBox vbox) {
        final Button tabPositionButton = new Button("Toggle Tab Position");
        tabPositionButton.setOnAction((ActionEvent e) -> {
            toggleTabPosition(tabPane);
        });
        // Add tab and switch to it
        final Button newTabButton = new Button("Switch to New Tab");
        newTabButton.setOnAction((ActionEvent e) -> {
            Tab t = new Tab("Testing");
            t.setContent(new Button("Howdy"));
            tabPane.getTabs().add(t);
            tabPane.getSelectionModel().select(t);
        });
        vbox.getChildren().add(newTabButton);
        // Add tab
        final Button addTabButton = new Button("Add Tab");
        addTabButton.setOnAction((ActionEvent e) -> {
            Tab t = new Tab("New Tab");
            t.setContent(new Region());
            tabPane.getTabs().add(t);
        });
        vbox.getChildren().add(addTabButton);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}