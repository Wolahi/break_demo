package com.example.break_house_demo;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerRunAnimation extends Animation{

    private final Image[] imagesRun = {
            new Image("file:src/main/resources/assets/bk/player/adventurer-run-00.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-run-01.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-run-02.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-run-03.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-run-04.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-run-05.png"),
    };

    public PlayerRunAnimation(Duration duration) {
        initAnimation(imagesRun, duration);
    }
}
