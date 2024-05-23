package utils;

import java.awt.geom.Rectangle2D;

import main.Game;

// Клас HelpMethods містить корисні методи для перевірки умов в грі, таких як можливість руху, перевірка твердості, позиціонування об'єктів.
public class HelpMethods {

    // Метод перевіряє, чи можна рухатись до вказаної позиції, враховуючи рівень даних.
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData) {
        // Перевірка чотирьох кутів прямокутника на твердість.
        if (!IsSolid(x, y, lvlData))
            if (!IsSolid(x + width, y + height, lvlData))
                if (!IsSolid(x + width, y, lvlData))
                    if (!IsSolid(x, y + height, lvlData))
                        return true; // Якщо жоден з кутів не твердий, рух можливий.
        return false; // Якщо хоча б один кут твердий, рух неможливий.
    }

    // Метод перевіряє, чи є конкретна позиція твердою (недоступною для руху).
    private static boolean IsSolid(float x, float y, int[][] lvlData) {
        // Перевірка, чи координати знаходяться за межами гри.
        if (x < 0 || x >= Game.GAME_WIDTH)
            return true;
        if (y < 0 || y >= Game.GAME_HEIGHT)
            return true;

        // Обчислення індексів плиток.
        float xIndex = x / Game.TILES_SIZE;
        float yIndex = y / Game.TILES_SIZE;

        // Отримання значення з масиву рівня для даних індексів.
        int value = lvlData[(int) yIndex][(int) xIndex];

        // Перевірка значення на твердість.
        if (value >= 48 || value < 0 || value != 11)
            return true;
        return false;
    }

    // Метод повертає позицію об'єкта поруч зі стіною, враховуючи швидкість по осі X.
    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / Game.TILES_SIZE);
        if (xSpeed > 0) {
            // Рух вправо.
            int tileXPos = currentTile * Game.TILES_SIZE;
            int xOffset = (int) (Game.TILES_SIZE - hitbox.width);
            return tileXPos + xOffset - 1;
        } else
            // Рух вліво.
            return currentTile * Game.TILES_SIZE;
    }

    // Метод повертає позицію об'єкта під дахом або над підлогою, враховуючи швидкість по осі Y.
    public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / Game.TILES_SIZE);
        if (airSpeed > 0) {
            // Падіння - дотик до підлоги.
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int) (Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else
            // Стрибок.
            return currentTile * Game.TILES_SIZE;
    }

    // Метод перевіряє, чи знаходиться об'єкт на підлозі.
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] lvlData) {
        // Перевірка пікселів під нижніми лівим та правим кутами хітбокса.
        if (!IsSolid(hitbox.x, hitbox.y + hitbox.height + 1, lvlData))
            if (!IsSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, lvlData))
                return false; // Якщо під об'єктом немає твердої поверхні, він не на підлозі.

        return true; // Якщо під об'єктом є тверда поверхня, він на підлозі.
    }

}