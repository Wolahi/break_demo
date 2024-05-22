package com.example.break_house_demo;

import javafx.animation.Transition;
import javafx.scene.image.Image;
import javafx.util.Duration;


public class SpriteAnimation extends Transition {

    private final Image[] images;

    private final int count;

    public SpriteAnimation(Image[] imageViews, Duration duration, int count) {
        this.images = imageViews;
        this.count = count;
        setCycleDuration(duration);
    }

    @Override
    protected void interpolate(double v) {
        int index = Math.min((int) Math.floor(v * count), count - 1);
        GameController.player.setImageView(images[index]);
    }
}
