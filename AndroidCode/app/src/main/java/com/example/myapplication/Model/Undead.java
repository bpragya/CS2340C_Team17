package com.example.myapplication.Model;

public class Undead implements Enemy {
    private int spriteId;
    private int x;
    private int y;
    private int healthPoints;
    private int attackDamage = 10;
    private int speed = 70;
    private int direction;
    private boolean reverseDirection;

    public int getSpriteId() {
        return spriteId;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getHealthPoints() {
        return healthPoints;
    }
    public int getSpeed() {
        return speed;
    }
    public int getAttackDamage() {
        return attackDamage;
    }
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSpriteId(int spriteId) {
        this.spriteId = spriteId;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public void setCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }
    @Override
    public void startPos() {
        reverseDirection = false;
        this.setX(590);
        this.setY(960);
    }

    // Enemy's attack pattern
    @Override
    public void attack() {
        // Attacks in a certain amount of tile.
    }

    @Override
    public void movement() {

        /*
        bot left:
        210, 2240

        bot right:
        1170, 2240

        top right
        1170, 634

        top left
        210, 634
         */
        if (getX() > 550) {
            reverseDirection = true;
        }
        if (getX() < 300) {
            reverseDirection = false;
        }
        if (reverseDirection) {
            setX(getX() - this.speed);
        } else {
            setX(getX() + this.speed);
        }
    }

    @Override
    public void noMovement() {
        speed = 0; //ToDo
    }
}
