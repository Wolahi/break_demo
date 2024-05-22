package com.example.break_house_demo;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.util.List;

public class Coins implements Collision {

    private Label coinsCounter;

    private Pane coins;


    public Coins(Pane coins, Label coinsCounter) {
        this.coins = coins;
        this.coinsCounter = coinsCounter;
    }

    public List<Node> getChildren() {
        return coins.getChildren();
    }

    @Override
    public boolean collision(List<Node> colArray, Pane collObj, boolean options) {
        for (int i = 0; i < colArray.size(); i++) {
            int coinsCountTemp = Integer.parseInt(coinsCounter.getText());
            Node node = colArray.get(i);
            if (node.getBoundsInParent().intersects(collObj.localToScene(collObj.getBoundsInLocal()))) {
                colArray.remove(node);
                coinsCountTemp++;
                coinsCounter.setText("" + coinsCountTemp);
                return true;
            }
        }
        return false;
    }
}
