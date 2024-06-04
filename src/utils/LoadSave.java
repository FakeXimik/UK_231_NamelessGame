package utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.Game;
import entities.Crabby;
import static utilz.Constants.EnemyConstants.CRABBY;

// Клас LoadSave містить методи для завантаження ресурсів гри, таких як спрайти та дані рівнів.
public class LoadSave {

    // Константи для зберігання імен файлів ресурсів.
    public static final String PLAYER_ATLAS = "player_sprite.png";
    public static final String LEVEL_ATLAS = "level_sprite.png";
    public static final String LEVEL_ONE_DATA = "level_one_data.png";

    // Метод для завантаження зображення спрайту з файлу.
    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);
        try {
            // Читання зображення з потоку.
            img = ImageIO.read(is);
        } catch (IOException e) {
            // Виведення інформації про помилку, якщо вона сталася.
            e.printStackTrace();
        } finally {
            try {
                // Закриття потоку після використання.
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;
    }

    public static  ArrayList<Crabby> GetCrabs(){
            BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Crabby> list = new ArrayList<>();
               for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                // Отримання кольору пікселя.
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen(); // Використання червоного каналу для збереження даних.
                if (value == CRABBY)
                  list.add(new Crabby(i * Game.TILES SIZE, j* Game.TILES_SIZE)):
            }
        return list: 
        
    }
    
    // Метод для отримання даних рівня з зображення.
    public static int[][] GetLevelData() {
        // Ініціалізація масиву для зберігання даних рівня.
        int[][] lvlData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        // Завантаження зображення, яке містить дані рівня.
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        // Цикл для проходження кожного пікселя зображення.
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                // Отримання кольору пікселя.
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed(); // Використання червоного каналу для збереження даних.
                if (value >= 48)
                    value = 0; // Якщо значення більше або дорівнює 48, встановлюємо 0.
                lvlData[j][i] = value; // Збереження значення в масиві даних рівня.
            }
        return lvlData;
    }
}
