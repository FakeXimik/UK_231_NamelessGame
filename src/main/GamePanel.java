package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;
import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private Game game;

    public GamePanel(Game game){
        mouseInputs = new MouseInputs();
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setFocusable(true);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 720);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Calls the paintComponent method from JPanel
        game.render(g);
        }

    public void updateGame() {
    }

    public Game getGame() {
        return game;
    }
}