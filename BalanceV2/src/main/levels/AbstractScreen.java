package main.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import main.*;


public abstract class AbstractScreen {
	protected AndroidGame curGame;
	public GL10 gl;
	protected SpriteBatch batch;
	protected int mouseY;
	protected int mouseX;
	public static int screenHeight;
	public static int screenWidth;
	
	public void trace(Object mixed) {
		System.out.println(mixed);
	}
	
	public void setColor(float red, float green, float blue) {
		gl.glClearColor(red, green, blue, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
	}
	
	public void setColor(){
		gl.glClearColor(1, 1, 1, 1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	
	}
	
	public void create(AndroidGame gameObject) {
		this.curGame = gameObject;
		gl = Gdx.graphics.getGL10 ();
		batch = new SpriteBatch();
		screenHeight = Gdx.graphics.getHeight();
		screenWidth = Gdx.graphics.getWidth();
	}
	
	abstract public void dispose();

	abstract public void pause();

	public void render(){
			mouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
			mouseX = Gdx.input.getX();
			update();
	}
	
	public void update() {
		
	}

	abstract public void resize(int arg0, int arg1);

	abstract public void resume();	
}
