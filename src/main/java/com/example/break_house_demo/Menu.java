package com.example.break_house_demo;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class Menu {

    private Pane menu;

    private Label headerText;

    public Menu(Pane menu, Label headerText) {
        this.menu = menu;
        this.headerText = headerText;
    }

    private void showMenu(String headerText) {
        this.headerText.setText(headerText);
        menu.setLayoutY(0f);
    }

    public void deathPlayer(List<Node> blocks, Pane deathPane, Pane playerPane) {
        List<Node> collisionBlocks = blocks.stream().filter(node ->
                node.getBoundsInParent().intersects(deathPane.localToScene(deathPane.getBoundsInLocal()))
                        && node != blocks.get(blocks.size() - 1)).toList();
        if(collisionBlocks.size() >= 2) {
            playerPane.getChildren().remove(0);
            showMenu("You lose!!!");
        }
    }

    public void playerWin(AnimationTimer timer, List<Node> coins, Pane playerPane)  {
        if(coins.isEmpty()) {
            playerPane.getChildren().remove(0);
            showMenu("You win");
            timer.stop();
        }
    }

}
