package main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {

    public GameWindow(GamePanel gamePanel) {
        // Берет размеры окна из других компонентов
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Закрыть по нажатию на клавишу закрытия окна
        add(gamePanel);
        setLocationRelativeTo(null);
        setResizable(false);
        pack();
        setVisible(true);
        addWindowFocusListener(new WindowAdapter() {

            public void windowGainedFocus(WindowEvent e) {

            }
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost();
                System.out.println("windowLostFocus");
            }
        });
    }
}