package com.gydoc.xmh.conf;

import java.io.Serializable;

/**
 * 
 */
public class UIConfig implements Serializable {

    private int positionX;
    private int positionY;
    private int windowWidth;
    private int windowHeight;
    private int ldPosX;
    private int ldPosY;
    private int ldWidth;
    private int ldHeight;

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public void setWindowWidth(int windowWidth) {
        this.windowWidth = windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }

    public void setWindowHeight(int windowHeight) {
        this.windowHeight = windowHeight;
    }

    public int getLdPosX() {
        return ldPosX;
    }

    public void setLdPosX(int ldPosX) {
        this.ldPosX = ldPosX;
    }

    public int getLdPosY() {
        return ldPosY;
    }

    public void setLdPosY(int ldPosY) {
        this.ldPosY = ldPosY;
    }

    public int getLdWidth() {
        return ldWidth;
    }

    public void setLdWidth(int ldWidth) {
        this.ldWidth = ldWidth;
    }

    public int getLdHeight() {
        return ldHeight;
    }

    public void setLdHeight(int ldHeight) {
        this.ldHeight = ldHeight;
    }
    
}
