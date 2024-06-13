package entities;

import static main.Game.SCALE;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import utils.LoadSave;

// Клас Player відповідає за управління гравцем у грі.
public class Player extends Entity {
    private BufferedImage[][] animations; // Масив анімаційних спрайтів гравця.
    private int aniTick, aniIndex, aniSpeed = 25; // Змінні для управління анімацією гравця.
    private int playerAction = IDLE; // Поточна дія гравця.
    private boolean moving = false, attacking = false; // Флаги для визначення стану гравця.
    private boolean left, up, right, down, jump; // Флаги для керування рухом гравця.
    private float playerSpeed = 2.0f * SCALE; // Швидкість руху гравця.
    private int[][] lvlData; // Дані рівня, на якому перебуває гравець.
    private float xDrawOffset = 21 * SCALE; // Зміщення по осі X для відображення гравця.
    private float yDrawOffset = 4 * SCALE; // Зміщення по осі Y для відображення гравця.

    // Параметри стрибка / гравітації.
    private float airSpeed = 0f; // Швидкість гравця у повітрі.
    private float gravity = 0.04f * SCALE; // Значення гравітації.
    private float jumpSpeed = -2.25f * SCALE; // Швидкість стрибка.
    private float fallSpeedAfterCollision = 0.5f * SCALE; // Швидкість падіння після зіткнення з підлогою.
    private boolean inAir = false; // Флаг для визначення, чи перебуває гравець у повітрі.

    // Конструктор класу Player, приймає початкові координати та розміри гравця.
    public Player(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations(); // Завантаження анімацій гравця.
        initHitbox(x, y, 20 * SCALE, 27 * SCALE); // Ініціалізація хітбокса гравця.
    }

    // Метод для оновлення стану гравця.
    public void update() {
        updatePos(); // Оновлення позиції гравця.
        updateAnimationTick(); // Оновлення таймера анімації.
        setAnimation(); // Вибір анімації гравця.
    }

    // Метод для відображення гравця на екрані.
    public void render(Graphics g) {
        // Відображення поточного спрайту гравця згідно з анімацією.
        g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
   // drawHitbox(g, xLvlOffset);
    }

    // Метод для оновлення таймера анімації.
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)) {
                aniIndex = 0;
                attacking = false;
            }
        }
    }

    // Метод для вибору анімації гравця.
    private void setAnimation() {
        int startAni = playerAction;

        // Визначення стану гравця для вибору потрібної анімації.
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (inAir) {
            if (airSpeed < 0)
                playerAction = JUMP;
            else
                playerAction = FALLING;
        }

        if (attacking)
            playerAction = ATTACK_1;

        if (startAni != playerAction)
            resetAniTick();
    }

    // Метод для скидання таймера анімації до початкового значення.
    private void resetAniTick() {
        aniTick = 0;
        aniIndex = 0;
    }

    // Метод для оновлення позиції гравця.
    private void updatePos() {
        moving = false;

        if (jump)
            jump();
        if (!left && !right && !inAir)
            return;

        float xSpeed = 0;

        if (left)
            xSpeed -= playerSpeed;
        if (right)
            xSpeed += playerSpeed;

        if (!inAir)
            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;

        if (inAir) {
            if (CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
                hitbox.y += airSpeed;
                airSpeed += gravity;
                updateXPos(xSpeed);
            } else {
                hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }

        } else
            updateXPos(xSpeed);
        moving = true;
    }

    // Метод для стрибка гравця.
    private void jump() {
        if (inAir)
            return;
        inAir = true;
        airSpeed = jumpSpeed;
    }

    // Метод для скидання флагу inAir після завершення стрибка.
    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    // Метод для оновлення позиції гравця по осі X.
    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = GetEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    // Метод для завантаження анімацій гравця.
    private void loadAnimations() {

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);

        // Ініціалізація масиву анімаційних спрайтів гравця.
        animations = new BufferedImage[9][6];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * 64, j * 40, 64, 40);
    }

    // Метод для завантаження даних рівня, на якому перебуває гравець.
    public void loadLvlData(int[][] lvlData) {
        this.lvlData = lvlData;
        if (!IsEntityOnFloor(hitbox, lvlData))
            inAir = true;
    }

    // Метод для скидання флагів напрямків руху.
    public void resetDirBooleans() {
        left = false;
        right = false;
        up = false;
        down = false;
    }

    // Геттери та сеттери для деяких змінних стану гравця.
    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }}
