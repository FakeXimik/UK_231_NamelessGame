package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

// Клас LevelManager відповідає за управління рівнями гри.
public class LevelManager {

    private Game game; // Посилання на об'єкт гри.
    private BufferedImage[] levelSprite; // Масив зображень для рівнів.
    private Level levelOne; // Об'єкт першого рівня.

    // Конструктор класу LevelManager, приймає об'єкт гри як параметр.
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites(); // Імпорт зовнішніх спрайтів.
        levelOne = new Level(LoadSave.GetLevelData()); // Створення об'єкта першого рівня.
    }

    // Приватний метод для імпортування зовнішніх спрайтів для рівнів.
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS); // Завантаження атласу спрайтів рівнів.
        levelSprite = new BufferedImage[48]; // Ініціалізація масиву зображень рівнів.
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                // Виділення підзображення з атласу спрайтів та збереження його в масиві.
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
    }

    // Метод для відображення рівня на екрані.
    public void render(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < Game.TILES_IN_WIDTH; i++) {
                int index = levelOne.getSpriteIndex(i, j); // Отримання індексу спрайта для даної плитки рівня.
                // Відображення спрайта на екрані.
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    // Метод для оновлення стану рівня (поки що пустий).
    public void update() {

    }

    // Метод для отримання поточного рівня.
    public Level getCurrentLevel() {
        return levelOne;
    }

}