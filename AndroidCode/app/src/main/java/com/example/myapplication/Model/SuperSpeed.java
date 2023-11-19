package com.example.myapplication.Model;

public class SuperSpeed extends PowerUps{

    int X = 0;
    int Y = 0;
    boolean havePowerUps;

    public SuperSpeed(int X, int Y) {
        this.X = X;
        this.Y = Y;
        havePowerUps = false;
    }
    @Override
    void setPosition(int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    int getX() {
        return X;
    }

    @Override
    int getY() {
        return Y;
    }
    @Override
    void action() {
        Player.getInstance().setSpeed(30);
    }

    @Override
    void setPowerUps(boolean havePowerUps) {
        havePowerUps = true;
    }

    @Override
    boolean getPowerUps() {
        return havePowerUps;
    }
}
