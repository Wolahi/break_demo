package com.example.break_house_demo;
import javafx.animation.AnimationTimer;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class MovedBlocks implements Collision {

    private Pane movedBlocks;
    private DirectionBlock direction;

    private final int blockSpeed = 1;

    private double maxBlocksTemp;

    private boolean startDirect = true;

    private List<Double> startCoordsBlocksY = new ArrayList<Double>();
    private List<Double> startCoordsBlocksX = new ArrayList<Double>();

    // Инициализация старотового положения блоков
    private void initStartCoords(Pane movedBlocksPane) {
        movedBlocksPane.getChildren().forEach(node -> {
            startCoordsBlocksY.add(node.getLayoutY());
            startCoordsBlocksX.add(node.getLayoutX());
        });
    }

    public MovedBlocks(Pane movedBlocksPane, DirectionBlock directionBlock, double maxBlocksTemp ) {
        this.movedBlocks = movedBlocksPane;
        this.direction = directionBlock;
        this.maxBlocksTemp = maxBlocksTemp;
        initStartCoords(movedBlocksPane);
    }

    public ObservableList<Node> getChildrenBlocks() {
        return movedBlocks.getChildren();
    }

    public boolean getDirectionUpBool() {
        return startDirect;
    }

    public int getBlockSpeed() {
        return blockSpeed;
    }

    // Движения блока вверх-вниз
    private void moveBlocksUp() {
        for(int i = 0; i< movedBlocks.getChildren().size() ; i++) {
            Node node = movedBlocks.getChildren().get(i);
            double startPos = startCoordsBlocksY.get(i);
            if(node.getLayoutY() > startPos - maxBlocksTemp && startDirect) {
                node.setLayoutY(node.getLayoutY() - blockSpeed);
            }
            else if(node.getLayoutY() < startPos) {
                startDirect = false;
                node.setLayoutY(node.getLayoutY() + blockSpeed);
            }
            else {
                startDirect = true;
            }
        }
    }

    // Движения блока вправо-влево
    private void moveBlocksHor() {
        for(int i = 0; i< movedBlocks.getChildren().size() ; i++) {
            Node node = movedBlocks.getChildren().get(i);
            double startPos = startCoordsBlocksX.get(i);
            if(node.getLayoutX() > startPos - maxBlocksTemp && startDirect) {
                node.setLayoutX(node.getLayoutX() - blockSpeed);
            }
            else if(node.getLayoutX() < startPos) {
                startDirect = false;
                node.setLayoutX(node.getLayoutX() + blockSpeed);
            }
            else {
                startDirect = true;
            }
        }
    }

    // Движение блока на основе Enum
    public void move() {
        switch (direction) {
            case UP -> moveBlocksUp();
            case HORIZONTAL -> moveBlocksHor();
        }
    }


    // Таймер блоков
    public AnimationTimer timer = new AnimationTimer() {
        @Override
        public void handle(long l) {
            move();
        }
    };

    // Имплементпция интерфейса Collision
    @Override
    public boolean collision(List<Node> colArray, Pane collObj, boolean options) {
        for (Node node : colArray) {
            if (node.getBoundsInParent().intersects(collObj.localToScene(collObj.getBoundsInLocal())) && options) {
                return true;
            }
        }
        return false;
    }
}
