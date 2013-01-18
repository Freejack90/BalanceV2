package main.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import main.AndroidGame;
import main.ui.Button;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Freejack
 * Date: 14.01.13
 * Time: 11:20
 * To change this template use File | Settings | File Templates.
 */
public class SelectLevel extends AbstractScreen{

    private ArrayList<Button> levelsbtn = new ArrayList<Button>();
    LevelManager manager = new LevelManager("data/levels.xml");
    private int formOffsetX = 100;
    private int formOffsetY = 400;


    public void create(AndroidGame game) {
        super.create(game);
        trace("SelectLevels");
        int offsetX = 64;
        int offsetY = 96;
        int perLine = 5;
        for(int i = perLine; i < manager.getLevelCount() + perLine; i++){
            int posX = formOffsetX + i%perLine * offsetX;
            int posY = formOffsetY - (int)i/perLine * offsetY;
            String num = ""+(i-(perLine-1));
            levelsbtn.add(new Button(posX, posY, num, new Sprite(new TextureRegion(new Texture("data/images/levelbtn.png"),52, 52))));
        }

    }

    public void render() {
        //Drawing
        setColor();
        batch.begin();
        for (Button btn : levelsbtn){
             btn.draw(batch);
             if (Gdx.input.justTouched()) {
                if (btn.hitTest()) {
                    trace(Integer.parseInt(btn.getText()));
                    this.curGame.setLevel(new LevelBuilder(Integer.parseInt(btn.getText())-1));
                }
             }
        }
        batch.end();


        //OnClick Event
      /*  if (Gdx.input.justTouched()) {
            if (playbtn.hitTest()) {
                this.curGame.setLevel(new LevelBuilder());
            }
        }     */
    }

    @Override
    public void dispose() {
        // TODO Auto-generated method stub

    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub

    }

    @Override
    public void resize(int arg0, int arg1) {
        // TODO Auto-generated method stub

    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub

    }
}
