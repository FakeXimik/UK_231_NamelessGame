package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_HEIGHT; // Імпортуємо висоту гри з класу Game.
import static main.Game.GAME_WIDTH;  // Імпортуємо ширину гри з класу Game.

// Клас GamePanel наслідує JPanel та відповідає за відображення ігрового вікна та обробку вводу.
public class GamePanel extends JPanel {

    // Змінні для обробки вводу миші та гри.
    private MouseInputs mouseInputs;
    private Game game;

    // Конструктор класу GamePanel, приймає об'єкт гри як параметр.
    public GamePanel(Game game) {
        // Ініціалізація обробки вводу миші та гри.
        mouseInputs = new MouseInputs(this);
        this.game = game;

        // Встановлення розміру панелі.
        setPanelSize();

        // Додавання обробників клавіатури та миші.
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    // Метод для встановлення розміру панелі відповідно до розміру гри.
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    // Метод для оновлення стану гри. (Поки що порожній)
    public void updateGame() {

    }

    // Метод для відображення графіки гри.
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    // Метод для отримання об'єкту гри.
    public Game getGame() {
        return game;
    }

}