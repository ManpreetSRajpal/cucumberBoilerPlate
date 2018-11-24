package com.project.cucumber.utils;

import org.openqa.selenium.Dimension;

public enum FormFactor {

    TINY(320, 500),
    MOBILE(600, 500),
    TABLET(900, 700),
    DESKTOP(1200, 800);

    private final int width;
    private final int height;

    FormFactor(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Dimension asDimension() {
        return new Dimension(width, height);
    }
}


