package entities ;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.lang.reflect.Array;
import java.util.ArrayList;

import gamestates.Playing;
import utilz.LoadSave;
import static utilz.Constatnts.EnemyConstants.*;

public class EnemyManager {

    private  Playing playing;
    private BufferedImage[][] crabbyArr;
    private ArrayList<Crabby> crabbies = new ArrayList<>();

    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {
        crabbies = LoadSave.GetCrabs();
    }

    public void update(){
        for (Crabby c : crabbies)
            c.update();
    }
    public void draw(Graphics g, int xlvlOffset){
        drawCrabs(g, xlvlOffset);
    }

    private void drawCrabs(Graphics g, int xlvlOffset) {
        for (Crabby c : crabbies)
            g.drawImage(crabbyArr[c.getEnemyState()][c.getAniIndex()], (int)c.getHitbox().x - xlvlOffset, (int)c.getHitbox().y ,CRABBY_WIDTH ,CRABBY_HEIGHT ,null);
    }


    private void loadEnemyImgs() {
    crabbyArr= new BufferedImage[5][9];
    BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
    for (int j; j<crabbyArr.length; j++)
        for(int i; i<crabbyArr[j].length; i++)
            crabbyArr[j][i] = temp.getSubimage(i * CRABBY_WIDTH_DEFAULT, J*CRABBY_HEIGHT_DEFAULT, CRABBY_WIDTH_DEFAULT,CRABBY_HEIGHT_DEFAULT);

    }
}
