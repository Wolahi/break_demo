package com.example.break_house_demo;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final Pane playerPane;
    private final Pane jumpPane;

    private final Pane deathPane;

    private final ImageView playerImg;

    private final Menu menu;

    private boolean onMovedPanelVertical = false;

    private boolean onMovedPanelHorizontal= false;

    private List<Node> blocks = new ArrayList<Node>();
    private MovedBlocks movedBlocksVertical;

    private MovedBlocks movedBlocksHorizontal;


    private final Coins coins;

    private final AnchorPane container;


    public final PlayerIdleAnimation animationIdle = new PlayerIdleAnimation(Duration.millis(450));
    public final PlayerRunAnimation animationRun = new PlayerRunAnimation(Duration.millis(300));

    // Иницилизация блоков с которыми игрок может взаимодействовать
    public void initBlocks(Pane block, MovedBlocks mBlocksVer, MovedBlocks mBlocksHor) {
        blocks.addAll(mBlocksVer.getChildrenBlocks());
        blocks.addAll(mBlocksHor.getChildrenBlocks());
        blocks.addAll(block.getChildren());
    }

    public Player(ImageView playerImg, Pane playerPane, Pane jumpPane,
                  Pane deathPane,
                  Pane block, MovedBlocks movedBlocksVertical,
                  MovedBlocks movedBlocksHorizontal, Coins coins,
                  AnchorPane container, Menu menu) {
        this.playerPane = playerPane;
        this.playerImg = playerImg;
        this.coins = coins;
        this.deathPane = deathPane;
        this.movedBlocksVertical = movedBlocksVertical;
        this.movedBlocksHorizontal = movedBlocksHorizontal;
        this.jumpPane = jumpPane;
        this.container = container;
        this.menu = menu;
        initBlocks(block, movedBlocksVertical, movedBlocksHorizontal);
    };


    public boolean isJump = false;
    public boolean isMoveRight = false;
    public boolean isMoveLeft = false;

    private final int playerSpeed = 5, jumpDownSpeed = 14, jumpUpSpeed = 10;
    private double yJump;

    public void setImageView(Image image) {
        playerImg.setImage(image);
    }


    public void setIsJump(boolean isJump) {
        this.isJump = isJump;
    }

    public void setIsMoveRight(boolean isMoveRight) {
        this.isMoveRight = isMoveRight;
    }

    public void setIsMoveLeft(boolean isMoveLeft) {
        this.isMoveLeft = isMoveLeft;
    }


    // Поиск максимального y на основе положения игрока
    private void checkMaxJumpDistance() {
        if(!isJump) {
            for (int i = 0; i < blocks.size(); i++) {
                Node node = blocks.get(i);
                if (node.getBoundsInParent().intersects(jumpPane.localToScene(jumpPane.getBoundsInLocal()))) {
                    yJump = node.getLayoutY() - 50f;
                    if (!movedBlocksVertical.getChildrenBlocks().contains(node)) {
                        onMovedPanelVertical = false;
                    }
                    if (!movedBlocksHorizontal.getChildrenBlocks().contains(node)) {
                        onMovedPanelHorizontal = false;
                    }
                    break;
                } else {
                    yJump = blocks.get(blocks.size() - 1).getLayoutY() - 50f;
                }
            }
        }
    }
    // Прыжок игрока
    private void jump() {
        if(isJump && playerPane.getLayoutY() > yJump - 200f) {
            playerPane.setLayoutY(playerPane.getLayoutY() - jumpUpSpeed);
        } else if(playerPane.getLayoutY() <= yJump) {
            isJump = false;
            playerPane.setLayoutY(playerPane.getLayoutY() + jumpDownSpeed);
        }
    }


    // Соприкосновение с движущемся блоком
    private void checkCollisionMovedBlocks() {
        if( movedBlocksHorizontal.collision(movedBlocksHorizontal.getChildrenBlocks(), jumpPane, isJump)) {
            onMovedPanelHorizontal = true;
        }
        if (movedBlocksVertical.collision(movedBlocksVertical.getChildrenBlocks(), jumpPane, isJump)) {
            onMovedPanelVertical = true;
        }
    }

    // Движения игрока
    private void move() {
        if (isMoveRight && playerPane.getLayoutX() < container.getWidth() - 40f) {
            animationIdle.stop();
            playerImg.setRotate(0);
            playerPane.setLayoutX(playerPane.getLayoutX() + playerSpeed);
        }

        if (isMoveLeft && playerPane.getLayoutX() > -5f) {
            animationIdle.stop();
            playerImg.setRotate(180);
            playerPane.setLayoutX(playerPane.getLayoutX() - playerSpeed);
        }

        if (onMovedPanelVertical) {
            if(movedBlocksVertical.getDirectionUpBool()) {
                playerPane.setLayoutY(playerPane.getLayoutY() - movedBlocksVertical.getBlockSpeed());
            }
            else {
                playerPane.setLayoutY(playerPane.getLayoutY() + movedBlocksVertical.getBlockSpeed());
            }
        }

        if (onMovedPanelHorizontal) {
            if(movedBlocksHorizontal.getDirectionUpBool()) {
                playerPane.setLayoutX(playerPane.getLayoutX() - movedBlocksVertical.getBlockSpeed());
            }
            else {
                playerPane.setLayoutX(playerPane.getLayoutX() + movedBlocksVertical.getBlockSpeed());
            }
        }
    }

    // Таймер игрока
   public AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            animationIdle.start();

            menu.deathPlayer(blocks, deathPane, playerPane);

            checkCollisionMovedBlocks();

            checkMaxJumpDistance();

            coins.collision(coins.getChildren(), deathPane, false);

            menu.playerWin(timer, coins.getChildren(), playerPane);

            jump();

            move();
        }
    };

}
