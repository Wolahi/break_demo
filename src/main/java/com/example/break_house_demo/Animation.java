package com.example.break_house_demo;

import javafx.scene.image.Image;
import javafx.util.Duration;

public abstract class Animation {


    private SpriteAnimation animation;

    public void initAnimation(Image[] images, Duration duration) {
        animation = new SpriteAnimation(images, duration, images.length);
        animation.setCycleCount(javafx.animation.Animation.INDEFINITE);
    }

    public void start() {
        animation.play();
    }

    public void stop() {
        animation.stop();
    }
}
