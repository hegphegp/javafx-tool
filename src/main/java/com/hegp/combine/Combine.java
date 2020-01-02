package com.hegp.combine;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.List;

public class Combine extends Application {
    private MenuBar menuBar = null;
    private TabPane mainTabPane = null;
    private BorderPane mainBorderPane = null;
    private StackPane leftMenuTreePane = null;
    private Stage primaryStage = null;
    @Override
    public void start(Stage primaryStage) {
        initBorderPane();
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        double screenWidth  = primaryScreenBounds.getWidth();
        double screenHeight = primaryScreenBounds.getHeight();
        double widthRatio = 0.5;  // 占屏幕总宽度的比例
        double heightRatio = 0.6; // 占屏幕总高度的比例
        Scene scene = new Scene(mainBorderPane, screenWidth * widthRatio, screenHeight * heightRatio);
        scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
        primaryStage.setScene(scene);
        this.primaryStage=primaryStage;
        primaryStage.show();
    }

    public void initMainTabPane() {
        mainTabPane = new TabPane();
        mainTabPane.setSide(Side.TOP);
        mainTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);

        // Internal Tabs
        Tab internalTab = new Tab();
        internalTab.setText("Internal Tabs");
        setupInternalTab(internalTab);
        mainTabPane.getTabs().add(internalTab);

        // Initial tab with buttons for experimenting
        Tab tab1 = new Tab();
        tab1.setText("Tab");
        tab1.setTooltip(new Tooltip("Tab Tooltip"));
        tab1.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/setting.png"))));

        final VBox vbox = getVBox();
        tab1.setContent(vbox);
        vbox.getChildren().add(new Label("11"));
        mainTabPane.getTabs().add(tab1);
    }

    private void setupInternalTab(Tab internalTab) {
        StackPane internalTabContent = new StackPane();
        final TabPane internalTabPane = new TabPane();
        internalTabPane.setTabClosingPolicy(TabPane.TabClosingPolicy.ALL_TABS);
        internalTabPane.setSide(Side.LEFT);

        internalTabPane.setPrefSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        final Tab innerTab = new Tab();
        innerTab.setText("Tab 1");
        final VBox innerVbox = getVBox();
        Button innerTabPosButton = new Button("Toggle Tab Position");
        innerTabPosButton.setText("Open Dialog");
        innerTabPosButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    final Stage dialog = new Stage();
                    dialog.setTitle("改变字体");
                    dialog.initModality(Modality.WINDOW_MODAL);
                    dialog.initOwner(((Node) event.getSource()).getScene().getWindow());
                    VBox dialogVbox = new VBox(20);
                    dialogVbox.getChildren().add(new Text("改变字体"));
                    Scene dialogScene = new Scene(dialogVbox, 300, 200);
                    dialog.setScene(dialogScene);
                    dialog.show();
                }
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

    private VBox getVBox() {
        final VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setTranslateX(10);
        vBox.setTranslateY(10);
        return vBox;
    }

    public void initBorderPane() {
        initMenuBar();
        initLeftMenuTreePane();
        initMainTabPane();
        mainBorderPane = new BorderPane();
        mainBorderPane.setTop(menuBar);
        mainBorderPane.setLeft(leftMenuTreePane);//放置在左边
        mainBorderPane.setCenter(mainTabPane);//放置在中间
    }

    public void initLeftMenuTreePane() {
        TreeItem leftMenuTree = new TreeItem("Inbox", new ImageView(new Image(getClass().getResourceAsStream("/setting.png"))));
        leftMenuTree.setExpanded(true);
        for (int i = 0; i < 3; i++) {
            TreeItem item = new TreeItem("Message" + i);
            leftMenuTree.getChildren().add(item);
            for (int j = 0; j < 3; j++) {
                TreeItem sonItem = new TreeItem("Message" + i + j);
                item.getChildren().add(sonItem);
            }
        }
        leftMenuTreePane = new StackPane();
        leftMenuTreePane.getChildren().add(new TreeView(leftMenuTree));
    }

    public void initMenuBar() {
        //创建MenuBar
        menuBar = new MenuBar();
//        menuBar.setStyle("-fx-background-color:red");

        //创建Menu
        Menu menu1 = new Menu("File");
        Menu menu2 = new Menu("Edit");
        Menu menu3 = new Menu("Project");

        //Menu键入到MenuBar
        menuBar.getMenus().addAll(menu1,menu2,menu3);

        //创建分割线
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        SeparatorMenuItem separator2 = new SeparatorMenuItem();

        //创建MenuItem类
        //还可以对MenuItem设置图标
        MenuItem menuItem1 = new MenuItem("New File", new ImageView(new Image(getClass().getResourceAsStream("/setting.png"))));
        MenuItem menuItem2 = new MenuItem("Open File");
        //设置menuItem的快捷键
        menuItem2.setAccelerator(KeyCombination.valueOf("SHIFT+M"));
        MenuItem menuItem3 = new MenuItem("Run As Admin");
        MenuItem menuItem4 = new MenuItem("ReBack");
        MenuItem menuItem6 = new MenuItem("New File6");

        MenuItem menuItem7 = new MenuItem("惊喜1");
        MenuItem menuItem8 = new MenuItem("惊喜2");

        //创建Menu
        Menu menuNode = new Menu("点我进入下一层");

        menuNode.getItems().addAll(menuItem7,menuItem8);

        //将MenuItem放在对应的Menu上
        menu1.getItems().addAll(menuItem1, separator1, menuItem2, separator2, menuNode);//将分割线加进来
        menu2.getItems().addAll(menuItem3, menuItem4);
        menu3.getItems().addAll(menuItem6);
    }

    public static void main(String[] args) {
        // new Font(20).getFamilies() 获取系统所有字体
        List<String> list = new Font(20).getFamilies();
        for(int i=0; i<list.size(); i++) {
            System.out.println(list.get(i));
        }

        launch(args);
    }

}
