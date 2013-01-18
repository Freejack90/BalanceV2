package main.levels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import main.*;


public abstract class AbstractScreen {
	protected AndroidGame curGame;
	public GL10 gl;
	protected SpriteBatch batch;
	protected int mouseY;
	protected int mouseX;
	public static int screenHeight;
	public static int screenWidth;
    protected TextButton.TextButtonStyle buttonStyle;
    protected Label.LabelStyle labelStyle;
    protected Stage ui;
    protected Button resetBtn;
    protected Button resetBtnForm;
    protected Button exitBtn;
    protected Label scoreLabel;
    protected Button exitBtnForm;

	
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
        initStyles();

	}

    public void initStyles(){
        ui = new Stage(screenWidth, screenHeight, true);
        Gdx.input.setInputProcessor(ui);

        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont(
                Gdx.files.internal("data/arial.fnt"),
                Gdx.files.internal("data/arial.png"),
                false
        );
        buttonStyle.up = new NinePatchDrawable(new NinePatch(new TextureRegion(new Texture("data/images/button.png"), 109, 32)));
        buttonStyle.fontColor = Color.BLACK;
        buttonStyle.pressedOffsetY = 1f;
        buttonStyle.downFontColor = new Color(0.8f, 0.8f, 0.8f, 1f);

        labelStyle = new Label.LabelStyle();
        labelStyle.font = new BitmapFont(
                Gdx.files.internal("data/arial.fnt"),
                Gdx.files.internal("data/arial.png"),
                false
        );
        labelStyle.fontColor = Color.BLACK;
    }

    public void setCenter(Button button, int padY){
        float offsetX = button.getWidth();
        float offsetY = button.getHeight();
        button.translate(screenWidth/2 - offsetX/2, screenHeight/2 - offsetY/2 - padY);

    }
    public void setCenter(Label label, int padY){
        float offsetX = label.getWidth();
        float offsetY = label.getHeight();
        label.translate(screenWidth/2 - offsetX/2, screenHeight/2 - offsetY/2 - padY);

    }

	abstract public void dispose();

	abstract public void pause();

	public void render(){
            setColor();
			mouseY = Gdx.graphics.getHeight()-Gdx.input.getY();
			mouseX = Gdx.input.getX();
            ui.act();
            ui.draw();
			update();
	}
	
	public void update() {
		
	}

	public void resize(int arg0, int arg1){
        ui.setViewport(arg0, arg1, true);
    }

	abstract public void resume();	
}
