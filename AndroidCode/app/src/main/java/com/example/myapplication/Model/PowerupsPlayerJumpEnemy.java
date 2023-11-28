package com.example.myapplication.Model;

public class PowerupsPlayerJumpEnemy extends PowerUps {
    int X = 0;
    int Y = 0;
    boolean hasPowerUp;

    public PowerupsPlayerJumpEnemy(PowerUpInterface decoratedPlayer) {
       super(decoratedPlayer);
    }


    @Override
    public void setPowerUps(boolean havePowerUps) {
        this.hasPowerUp = havePowerUps;
    }

    @Override
    public boolean getPowerUps() {
        return hasPowerUp;
    }

    @Override
    public int powerUp() {
        return 0;
    }
}

