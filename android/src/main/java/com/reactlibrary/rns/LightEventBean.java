package com.reactlibrary.rns;

/**
 * Name:
 * <p>
 * 2019/7/16 by StoneWay
 * <p>
 * Outline:
 */
public class LightEventBean {
    public LightEventBean(int lightOn) {
        this.lightOn = lightOn;
    }

    public LightEventBean() {
    }

    public int getLightOn() {
        return lightOn;
    }

    public void setLightOn(int lightOn) {
        this.lightOn = lightOn;
    }

    private int lightOn;
}
