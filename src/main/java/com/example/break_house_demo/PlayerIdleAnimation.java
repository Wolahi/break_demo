package com.example.break_house_demo;

import javafx.scene.image.Image;
import javafx.util.Duration;

public class PlayerIdleAnimation extends Animation{

    private final Image[] imagesIdle = {
            new Image("file:src/main/resources/assets/bk/player/adventurer-idle-00.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-idle-01.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-idle-02.png"),
            new Image("file:src/main/resources/assets/bk/player/adventurer-idle-03.png")
    };

    public PlayerIdleAnimation(Duration duration) {
        initAnimation(imagesIdle, duration);
    }


}
