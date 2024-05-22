package com.example.break_house_demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class GameController {
    @FXML
    private Pane jumpPane;
    @FXML
    private Pane menuPane;
    @FXML
    private Label menuHeaderText;
    @FXML
    private Pane playerPane;
    @FXML
    private Pane deathPane;
    @FXML
    private Pane movedBlocksPaneVertical;
    @FXML
    private Pane movedBlocksPaneHorizontal;
    @FXML
    private Label coinsCounter;
    @FXML
    private Pane coinsPane;
    @FXML
    private ImageView playerImg;
    @FXML
    private Pane block;
    @FXML
    private AnchorPane container;


    public static Player player;

    public Menu menu;

    public Coins coins;

    public MovedBlocks movedBlocksVertical;
    public MovedBlocks movedBlocksHorizontal;

    // Иницализация игрока
    private void initPlayer() {
        player = new Player(playerImg, playerPane,
                jumpPane, deathPane , block, movedBlocksVertical,
                movedBlocksHorizontal, coins, container, menu);
    }

    // Иницализация блоков и монет
    private void initBlocks() {
        menu = new Menu(menuPane, menuHeaderText);
        coins = new Coins(coinsPane, coinsCounter);
        movedBlocksVertical = new MovedBlocks(movedBlocksPaneVertical, DirectionBlock.UP, 200f);
        movedBlocksHorizontal = new MovedBlocks(movedBlocksPaneHorizontal, DirectionBlock.HORIZONTAL, 350f);
    }

    // Добавления обработчиков событий к сцене
    public void startScene(Scene scene, Stage stage) {
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.A) {
                player.setIsMoveLeft(true);
                player.animationRun.start();
            }
            if(e.getCode() == KeyCode.D) {
                player.setIsMoveRight(true);
                player.animationRun.start();
            }
            if(e.getCode() == KeyCode.SPACE) {
                player.setIsJump(true);
            }
            if(e.getCode() == KeyCode.R) {
                stage.close();
                try {
                    new BreakHouse().start(new Stage());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        scene.setOnKeyReleased(e -> {
            if(e.getCode() == KeyCode.A) {
                player.setIsMoveLeft(false);
                player.animationRun.stop();
                player.animationIdle.start();
            }
            if(e.getCode() == KeyCode.D) {
                player.setIsMoveRight(false);
                player.animationRun.stop();
                player.animationIdle.start();
            }
        });

    };

    // Функция инициализация от JavaFX, срабатывает при иницилизации сцены
    @FXML
    void initialize() {
        assert block != null : "fx:id=\"block\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert container != null : "fx:id=\"container\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert playerImg != null : "fx:id=\"playerImg\" was not injected: check your FXML file 'hello-view.fxml'.";
        initBlocks();
        initPlayer();
        movedBlocksVertical.timer.start();
        movedBlocksHorizontal.timer.start();
        player.timer.start();
    }

}