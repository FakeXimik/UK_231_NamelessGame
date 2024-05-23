package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

// Клас GameWindow відповідає за створення і налаштування ігрового вікна.
public class GameWindow {
    private JFrame jframe; // Головне вікно гри.

    // Конструктор класу GameWindow, приймає GamePanel як параметр.
    public GameWindow(GamePanel gamePanel) {

        jframe = new JFrame(); // Створення нового вікна.

        // Налаштування поведінки вікна при закритті - завершення програми.
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Додавання панелі гри до вікна.
        jframe.add(gamePanel);

        // Розміщення вікна по центру екрану.
        jframe.setLocationRelativeTo(null);

        // Заборона зміни розміру вікна.
        jframe.setResizable(false);

        // Встановлення оптимального розміру вікна відповідно до його вмісту.
        jframe.pack();

        // Відображення вікна.
        jframe.setVisible(true);

        // Додавання обробника фокусу вікна.
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowLostFocus(WindowEvent e) {
                // Виклик методу при втраті фокусу вікна.
                gamePanel.getGame().windowFocusLost();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Метод для обробки отримання фокусу вікном (залишений пустим).
                // TODO Auto-generated method stub
            }
        });

    }

}