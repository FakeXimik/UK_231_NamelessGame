package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

// Абстрактний клас Entity є базовим класом для усіх об'єктів у грі.
public abstract class Entity {

    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox; // Хітбокс об'єкта.

    // Конструктор класу Entity, приймає початкові координати та розміри об'єкта.
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Метод для відображення хітбоксу об'єкта (для відладки).
    protected void drawHitbox(Graphics g) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // Метод для ініціалізації хітбоксу об'єкта з вказаними параметрами.
    protected void initHitbox(float x, float y, float width, float height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    // Геттер для отримання хітбоксу об'єкта.
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

}