package com.example.myapplication.Model;

import android.view.KeyEvent;

public class Player {
    private String name;
    private int healthPoints; // 75 for easy, 65 for medium, 55 for hard
    private int image; // 1 for sprite_1, 2 for sprite_2, 3 for sprite_3

    private int score;

    private int x = 0;
    private int y = 0;

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    private Player() {
        this.name = null;
        this.healthPoints = -1;
        this.image = -1;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public int getXCoordinate() {
        return x;
    }

    public int getYCoordinate() {
        return y;
    }

    private static Player instance = null;

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void moveRight() {
        int x = Player.getInstance().getXCoordinate();
        x+=0.25;
    }

    public void moveLeft() {
        int x = Player.getInstance().getXCoordinate();
        x+=-0.25;
    }

    public void moveUp() {
        int y = Player.getInstance().getYCoordinate();
        y+=1;
    }

    public void moveDown() {
        int y = Player.getInstance().getYCoordinate();
        y=-1;
    }

    public void doNothing() {
        int x = Player.getInstance().getXCoordinate();
        int y = Player.instance.getYCoordinate();
    }

    public boolean onKeyUp(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_DPAD_LEFT:
                moveLeft();
                return true;

            case KeyEvent.KEYCODE_DPAD_RIGHT:
                moveRight();
                return true;

            case KeyEvent.KEYCODE_DPAD_UP:
                moveUp();
                return true;

            case KeyEvent.KEYCODE_DPAD_DOWN:
                moveDown();
                return true;
            default:
                doNothing();
                return true;
        }



        }
    }


