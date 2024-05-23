package levels;

// Клас Level представляє окремий рівень гри.
public class Level {

    private int[][] lvlData; // Масив, що зберігає дані про рівень.

    // Конструктор класу Level, приймає масив даних рівня як параметр.
    public Level(int[][] lvlData) {
        this.lvlData = lvlData; // Ініціалізація масиву даних рівня.
    }

    // Метод для отримання індексу спрайту для певної плитки рівня по координатах (x, y).
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x]; // Повертає значення з масиву даних рівня за вказаними координатами.
    }

    // Метод для отримання всіх даних рівня.
    public int[][] getLevelData() {
        return lvlData; // Повертає всі дані рівня.
    }

}