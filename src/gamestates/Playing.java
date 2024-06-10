 package gamestates;

import  entities.Player;
import  levels.LevelManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

 public class Playing extends State implements  Statemethods{
    private Player player; // Гравець у грі.
    private LevelManager levelManager; // Менеджер рівнів.
    private entities.EnemyManager enemyManager;


    public Playing(Game game) {
        super(game);
        initClasses()
    }

    // Метод для ініціалізації класів гравця та менеджера рівнів.
    private void initClasses() {
        levelManager = new LevelManager(game);
        enemyManager = new entities.EnemyManager(this);
        player = new Player(200, 200, (int) (64 * game.SCALE), (int) (40 * game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
    }



     @Override
     public void update() {

     }

     @Override
     public void draw(Graphics g) {
        enemyManager.draw(g, xLvlOffset);
     }

     @Override
     public void mouseClicked(MouseEvent e) {

     }

     @Override
     public void mousePressed(MouseEvent e) {

     }

     @Override
     public void mouseReleased(MouseEvent e) {

     }

     @Override
     public void mouseMoved(MouseEvent e) {

     }

     @Override
     public void keyPressed(KeyEvent e) {
         switch (e.getKeyCode()) {
             case KeyEvent.VK_A:
                 player.setLeft(true);
                 break;
             case KeyEvent.VK_D:
                 player.setRight(true);
                 break;
             case KeyEvent.VK_SPACE:
                 player.setJump(true);
                 break;
             case KeyEvent.VK_BACK_SPACE:
                 Gamestate.state =  Gamestate.MENU;
         }
     }

     @Override
     public void keyReleased(KeyEvent e) {
         switch (e.getKeyCode()) {

             case KeyEvent.VK_A:
                 player.setLeft(false);
                 break;
             case KeyEvent.VK_D:
                 player.setRight(false);
                 break;
             case KeyEvent.VK_SPACE:
                 player.setJump(false);
                 break;
         }
     }
 }
 public Player getPlayer(){
     return  player
 }
