package main;

import java.awt.Graphics;

import entities.Player;
import levels.LevelManager;

import gamestates.Menu;
import gamestates.Playing;

// Клас Game реалізує інтерфейс Runnable, що дозволяє запускати його в окремому потоці.
public class Game implements Runnable {


    
    // Змінні для вікна гри, панелі гри, потоку гри, FPS та UPS.
    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 144; // Фіксована кількість кадрів на секунду.
    private final int UPS_SET = 200; // Фіксована кількість оновлень на секунду.
  /*  private Player player; // Гравець у грі.
    private LevelManager levelManager; // Менеджер рівнів. */
private Playing  playing;
private Menu menu;

    // Константи, що визначають розмір плиток та розмір гри.
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    // Конструктор класу Game.
    public Game() {
        // Ініціалізація ігрових класів.
        initClasses();

        // Створення панелі гри та вікна гри, і фокусування на панелі.
        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();

        // Запуск ігрового циклу.
        startGameLoop();
    }

    // Метод для ініціалізації класів гравця та менеджера рівнів.
    private void initClasses() {
    /*    levelManager = new LevelManager(this);
        player = new Player(200, 200, (int) (64 * SCALE), (int) (40 * SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLevelData());*/
    }

    // Метод для запуску ігрового циклу в окремому потоці.
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // Метод для оновлення стану гри.
    public void update() {
      /*  levelManager.update();
        player.update();
        switch(Gamestate.state){
            case MENU:
                menu.update();
                break
            case PLAYING :
                 levelManager.update();
                 player.update();
            dafeult :
            break;*/
        }
    }

    // Метод для відображення графіки гри.
    public void render(Graphics g) {
      /*  levelManager.render(g);
        player.render(g);
       switch(Gamestate.state){
            case MENU:
                menu.update();
                break
            case PLAYING :
                 levelManager.render(g);
                 player.render(g);
            dafeult :
            break;*/
    }

    // Метод run, який реалізує ігровий цикл.
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET; // Час на один кадр.
        double timePerUpdate = 1000000000.0 / UPS_SET; // Час на одне оновлення.

        long previousTime = System.nanoTime(); // Час початку циклу.

        int frames = 0; // Лічильник кадрів.
        int updates = 0; // Лічильник оновлень.
        long lastCheck = System.currentTimeMillis(); // Час останньої перевірки.

        double deltaU = 0; // Зміна для оновлень.
        double deltaF = 0; // Зміна для кадрів.

        // Нескінченний цикл гри.
        while (true) {
            long currentTime = System.nanoTime(); // Поточний час.

            deltaU += (currentTime - previousTime) / timePerUpdate; // Обчислення зміни для оновлень.
            deltaF += (currentTime - previousTime) / timePerFrame; // Обчислення зміни для кадрів.
            previousTime = currentTime; // Оновлення попереднього часу.

            // Якщо накопичено достатньо часу для оновлення, виконуємо оновлення.
            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            // Якщо накопичено достатньо часу для малювання кадру, виконуємо малювання.
            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // Перевірка раз на секунду для виведення FPS та UPS.
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
            }
        }

    }

    // Метод, який викликається, коли вікно гри втрачає фокус.
    public void windowFocusLost() {
//        player.resetDirBooleans();
    }

    // Метод для отримання об'єкту гравця.
  /*  public Player getPlayer() {
        return player;*/
    }

}
