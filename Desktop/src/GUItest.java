import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/**
 * Created with IntelliJ IDEA.
 * User: Feax
 * Date: 16.01.13
 * Time: 12:14
 * To change this template use File | Settings | File Templates.
 */
public class GUItest implements ApplicationListener {
    private Stage ui;

    @Override
    public void create() {
        ui = new Stage();
        Gdx.input.setInputProcessor(ui);
        Button button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("data/images/button.png"), 109, 32)));
        ui.addActor(button);
        // tbl.getTableLayout();
        button.addListener( new ClickListener() {
            public void clicked (InputEvent event, float x, float y) {
                System.out.println("DES");
            }
        });
    }

    @Override
    public void resize(int width, int height) {
        ui.setViewport(width, height, true);
    }

    @Override
    public void render() {
        ui.act();
        ui.draw();
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

