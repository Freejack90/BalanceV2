package main.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import main.AndroidGame;


import com.badlogic.gdx.Gdx;

public class IntroScreen extends AbstractScreen {
	private Button playbtn;
	private Button levelsbtn;

	public void create(AndroidGame game) {
		super.create(game);
		//UI Init
        playbtn = new TextButton("Play", buttonStyle);
        levelsbtn = new TextButton("Levels", buttonStyle);
        setCenter(playbtn, -50);
        setCenter(levelsbtn, 0);
        playbtn.addListener(new ClickListener(){
           public void clicked(InputEvent event, float x, float y) {
               curGame.setLevel(new LevelBuilder(0));
           }
        });
        levelsbtn.addListener(new ClickListener(){
            public void clicked(InputEvent event, float x, float y) {
                curGame.setLevel(new SelectLevel());
            }
        });
        ui.addActor(playbtn);
        ui.addActor(levelsbtn);

        trace("Intro");
    }
	
	public void render() {
        super.render();
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
		super.resize(arg0, arg1);
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

}
