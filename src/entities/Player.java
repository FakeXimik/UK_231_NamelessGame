package entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.PlayerConstants.IDLE;

public class Player extends Entity {

    private int playerAction = IDLE;
    private BufferedImage img;
    private boolean left, up, right, down;
    private float playerSpeed = 2.0f;

    public Player(float x, float y) {
        super(x, y);
        loadAnimations();
    }

    public void update() {
        updatePos();
    }

    public void render(Graphics g) {
        g.drawImage(img, (int) x, (int) y, 128, 80, null);
    }

    private void loadAnimations() {
        InputStream is = getClass().getResourceAsStream("/res/player_sprite.png");
        try {
            img = ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    private void updatePos() {

        if (left && !right) {
            x -= playerSpeed;
        } else if (right && !left) {
            x += playerSpeed;
        }
        if (up && !down) {
            y -= playerSpeed;
        } else if (down && !up) {
            y += playerSpeed;
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setDown(boolean down) {
        this.down = down;
    }
}