package com.example.break_house_demo;

import javafx.scene.Node;
import javafx.scene.layout.Pane;

import java.util.List;

public interface Collision {

    boolean collision(List<Node> colArray, Pane collObj, boolean options);

}
