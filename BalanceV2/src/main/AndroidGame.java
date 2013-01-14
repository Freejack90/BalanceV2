package main;

import main.levels.AbstractScreen;
import main.levels.IntroScreen;

import com.badlogic.gdx.ApplicationListener;
import main.levels.LevelBuilder;

public class AndroidGame implements ApplicationListener {
	
	private AbstractScreen currentLevel;
	public void setLevel(AbstractScreen level) {
		currentLevel = level;
		currentLevel.create(this);
	}
	
	@Override
	public void create() {	
		System.out.println("AndroidGame");
		setLevel(new IntroScreen());
	}

	@Override
	public void dispose() {
		currentLevel.dispose();
	}

	@Override
	public void render() {	
		currentLevel.render();
	}

	@Override
	public void resize(int width, int height) {
		currentLevel.resize(width, height);
	}

	@Override
	public void pause() {
		currentLevel.pause();
	}

	@Override
	public void resume() {
		currentLevel.resume();
	}
}
