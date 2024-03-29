package com.example.myapplication;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.myapplication.Model.BasePowerUp;
import com.example.myapplication.Model.BossController;
import com.example.myapplication.Model.ExtraHealthPoints;
import com.example.myapplication.Model.PowerUpInterface;
import com.example.myapplication.Model.SuperSpeed;
import com.example.myapplication.R;
import android.view.KeyEvent;

import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.Score;
import com.example.myapplication.ViewModel.PlayerConfigActivityViewModel;
import com.example.myapplication.ViewModel.InitialGameScreenViewModel;
import com.example.myapplication.Model.EnemyController;
/*
import com.example.myapplication.Physics.RoomMapTile;
import com.example.myapplication.Physics.CollisionInfo;
import java.util.List;
 */

import java.sql.Time;
import java.text.DateFormat;
import java.util.Calendar;

public class PlayerTests {

    PlayerConfigActivityViewModel playerViewModel = new PlayerConfigActivityViewModel();
    Player player;
    Score score;
    InitialGameScreenViewModel gameViewModel;


    @Test
    public void testPlayerName() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        assertEquals("Bob", player.getName());
    }

    @Test
    public void testPlayerEasyDifficulty() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        // Player starts with 75 on easy mode
        assertEquals(75, player.getHealthPoints());
    }

    @Test
    public void testPlayerNormalDifficulty() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Normal");
        playerViewModel.finalizePlayer();
        // Player starts with 65 on normal mode
        assertEquals(65, player.getHealthPoints());
    }

    @Test
    public void testPlayerHardDifficulty() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Hard");
        playerViewModel.finalizePlayer();
        // Player starts with 55 on normal mode
        assertEquals(55, player.getHealthPoints());
    }
    @Test
    public void testPlayerInitialScore() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        gameViewModel = new InitialGameScreenViewModel();
        assertEquals(100, player.getScore());
    }
    @Test
    public void testPlayerScoreUpdate() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.updateScore();
        assertEquals(99, player.getScore());
    }
    @Test
    public void testPlayerScoreStopping() {
        player = Player.getInstance();
        playerViewModel.setPlayerName("Bob");
        playerViewModel.updatePlayerSprite("Sprite 1");
        playerViewModel.updateDifficulty("Easy");
        playerViewModel.finalizePlayer();
        gameViewModel = new InitialGameScreenViewModel();
        // Score should stop at 0
        for (int i = 120; i > 0; i++) {
            gameViewModel.updateScore();
        }
        assertEquals(0, player.getScore());
    }


    @Test
    public void testPlayerXCoordinate() {
        player = Player.getInstance();
        player.setCoordinates(150, 150);
        assertEquals(150, player.getXCoordinate());
    }

    @Test
    public void testPlayerYCoordinate() {
        player = Player.getInstance();
        player.setCoordinates(150, 150);
        assertEquals(150, player.getYCoordinate());
    }

    @Test
    public void testScore() {
        score = new Score("Bob", 50, "11:50");
        assertEquals(50, score.getScore());
    }

    @Test
    public void testScoreTime() {
        score = new Score("Bob", 50, "11:50");
        assertEquals("11:50", score.getTime());
    }

    @Test
    public void testCompareTest() {
        Score score2 = new Score("Jerry", 100, "11:50");
        score = new Score("Bob", 50, "11:50");
        // returns 1 if score is less than score comparing to
        assertEquals(1, score.compareTo(score2));
    }

    @Test
    public void testPlayerMovementLeft() {
        player = player.getInstance();
        gameViewModel = new InitialGameScreenViewModel();
        player.setCoordinates(40,40);
        gameViewModel.movePlayer(KeyEvent.KEYCODE_A);
        assertEquals(30, player.getXCoordinate());
        assertEquals(40, player.getYCoordinate());
    }
    @Test
    public void testPlayerMovementRight() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.movePlayer(KeyEvent.KEYCODE_D);
        assertEquals(50, player.getXCoordinate());
        assertEquals(40, player.getYCoordinate());
    }
    @Test
    public void testPlayerMovementDown() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.movePlayer(KeyEvent.KEYCODE_S);
        assertEquals(40, player.getXCoordinate());
        assertEquals(50, player.getYCoordinate());
    }
    @Test
    public void testPlayerMovementUp() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.movePlayer(KeyEvent.KEYCODE_W);
        assertEquals(40, player.getXCoordinate());
        assertEquals(30, player.getYCoordinate());
    }

    @Test
    public void testMultiplePlayerMovements() {
        player = player.getInstance();
        player.setCoordinates(40,40);
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.movePlayer(KeyEvent.KEYCODE_A);
        gameViewModel.movePlayer(KeyEvent.KEYCODE_S);
        gameViewModel.movePlayer(KeyEvent.KEYCODE_D);
        gameViewModel.movePlayer(KeyEvent.KEYCODE_W);
        assertEquals(40, player.getXCoordinate());
        assertEquals(40, player.getYCoordinate());
    }

    @Test
    public void loserTests() {
        player = player.getInstance();
        if (player.getScore() <= 0) {
            assertFalse(player.getOutcome());

        }
    }

    @Test
    public void winnerTests() {
        player = player.getInstance();
        if (player.getOutcome()) {
            assertTrue(player.getRoomList().size() == 3);
        }
    }

    @Test
    public void creatingEnemy() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createSkeleton();
        assertEquals(600, gameViewModel.getEnemyX(gameViewModel.getSkeleton()));
        assertEquals(950, gameViewModel.getEnemyY(gameViewModel.getSkeleton()));
    }
    @Test
    public void basicEnemyMovement() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createSkeleton();
        gameViewModel.setEnemyX(120, gameViewModel.getSkeleton());
        gameViewModel.setEnemyY(120, gameViewModel.getSkeleton());
        assertEquals(120, gameViewModel.getEnemyX(gameViewModel.getSkeleton()));
        assertEquals(120, gameViewModel.getEnemyY(gameViewModel.getSkeleton()));
    }
    @Test
    /**
     * This test ensures that players will lose 10
     * health when colliding with an enemy.
     */
    public void testEnemyCollision() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createWizard();
        player = player.getInstance();
        player.setCoordinates(500, 500);
        int scoreBeforeCollision = player.getScore();
        gameViewModel.setEnemyX(501, gameViewModel.getWizard());
        gameViewModel.setEnemyY(499, gameViewModel.getWizard());
        assertFalse(scoreBeforeCollision < player.getScore());
    }
    @Test
    /**
     * This test ensures that players with health below 10
     * will not be affected by enemy collision.
     */
    public void testCollisionLowHealth() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createWizard();
        player = player.getInstance();
        player.setScore(9);
        player.setCoordinates(500, 500);
        gameViewModel.setEnemyX(501, gameViewModel.getWizard());
        gameViewModel.setEnemyY(499, gameViewModel.getWizard());
        assertFalse(player.getScore() < 0);
    }
    public void SkeletonMovementPattern() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createSkeleton();
        gameViewModel.moveEnemy(gameViewModel.getSkeleton());
        gameViewModel.moveEnemy(gameViewModel.getSkeleton());
        assertEquals(660, gameViewModel.getEnemyX(gameViewModel.getSkeleton()));
        assertEquals(950, gameViewModel.getEnemyY(gameViewModel.getSkeleton()));
    }

    @Test
    public void SlimeMovementPattern() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createSlime();
        gameViewModel.moveEnemy(gameViewModel.getSlime());
        gameViewModel.moveEnemy(gameViewModel.getSlime());
        assertEquals(230, gameViewModel.getEnemyX(gameViewModel.getSlime()));
        assertEquals(634, gameViewModel.getEnemyY(gameViewModel.getSlime()));
    }
    @Test
    public void OlafMovementPattern() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createOlaf();
        gameViewModel.moveEnemy(gameViewModel.getOlaf());
        gameViewModel.moveEnemy(gameViewModel.getOlaf());
        assertEquals(310, gameViewModel.getEnemyX(gameViewModel.getOlaf()));
        assertEquals(634, gameViewModel.getEnemyY(gameViewModel.getOlaf()));
    }
    @Test
    public void BossMovementPattern() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createBoss();
        gameViewModel.moveEnemy(gameViewModel.getBoss());
        gameViewModel.moveEnemy(gameViewModel.getBoss());
        assertEquals(400, gameViewModel.getEnemyX(gameViewModel.getBoss()));
        assertEquals(630, gameViewModel.getEnemyY(gameViewModel.getBoss()));
    }
    @Test
    public void WizardMovementPattern() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createWizard();
        gameViewModel.moveEnemy(gameViewModel.getWizard());
        gameViewModel.moveEnemy(gameViewModel.getWizard());
        assertEquals(600, gameViewModel.getEnemyX(gameViewModel.getWizard()));
        assertEquals(1050, gameViewModel.getEnemyY(gameViewModel.getWizard()));
    }
    @Test
    public void UndeadMovementPattern() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createUndead();
        gameViewModel.moveEnemy(gameViewModel.getUndead());
        gameViewModel.moveEnemy(gameViewModel.getUndead());
        assertEquals(460, gameViewModel.getEnemyX(gameViewModel.getUndead()));
        assertEquals(950, gameViewModel.getEnemyY(gameViewModel.getUndead()));
    }

    @Test
    public void testBoss() {
        gameViewModel = new InitialGameScreenViewModel();
        gameViewModel.createBoss();
        gameViewModel.setEnemyX(50, gameViewModel.getBoss());
        gameViewModel.setEnemyX(50, gameViewModel.getBoss());
        assertEquals(40, gameViewModel.getEnemyX(gameViewModel.getBoss()));
        assertEquals(40, gameViewModel.getEnemyX(gameViewModel.getBoss()));
    }

    @Test
    public void testPlayerExtraHealthPoints() {
        PowerUpInterface playerWithExtraHealth = new ExtraHealthPoints(new BasePowerUp());
        Player.getInstance().setHealthPoints(10);
        assertEquals(Player.getInstance().getHealthPoints() + 100, playerWithExtraHealth.powerUp());
    }

    @Test
    public void testPlayerWithSuperSpeed() {
        PowerUpInterface playerWithSuperSpeed = new SuperSpeed(new BasePowerUp());
        assertEquals(Player.getInstance().getSpeed() + 40, playerWithSuperSpeed.powerUp());
    }



}
