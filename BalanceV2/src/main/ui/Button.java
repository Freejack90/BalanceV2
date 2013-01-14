package main.ui;

import main.levels.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Button {
	
	private BitmapFont font;
	private int posX;
	private int posY;
	private String text;
	private Sprite sprite;
	private Sprite defSprite;
	
	private Point halfSprite;
	private Point halfFont;
	private int offset = 2;
	
	public Button(int posX, int posY, String text, Sprite sprite){
		createButton(posX, posY, text, sprite);
	}
	
	public Button(int posX, int posY, String text) {
		defSprite = new Sprite(new TextureRegion(new Texture("data/images/button.png"),109, 32));
		createButton(posX, posY, text, defSprite);
	}

    private void createButton(int posX, int posY, String text, Sprite sprite) {
		this.posX = (int) Math.floor(posX - sprite.getWidth()/2);
		this.posY = (int) Math.floor(posY - sprite.getHeight()/2);	
		this.text = text;
		this.sprite = sprite;		
		this.sprite.setPosition(posX, posY);
		
		font = new BitmapFont(
				Gdx.files.internal("data/arial.fnt"), 
				Gdx.files.internal("data/arial.png"), 
				false
		);
		
		font.setColor(Color.BLACK);	
		
		this.sprite.setX(this.posX);
		this.sprite.setY(this.posY);	
		
		halfSprite = new Point(
				(int) Math.floor(this.sprite.getWidth()/2),     //width
				(int) Math.floor(this.sprite.getHeight()/2)     //height
		);	
		halfFont = new Point(
				(int)Math.floor(font.getBounds(text).width/2),  //width
				(int)Math.floor(font.getBounds(text).height/2)  //height
		);
	}

	public void draw (SpriteBatch batch){	
		sprite.draw(batch);
		int fontX = posX - halfFont.x + halfSprite.x;
		int fontY = posY + halfFont.y + halfSprite.y+offset;
		font.draw(batch, text, fontX, fontY);
	}
	
	public boolean hitTest (){	
		if ((Gdx.input.getX() > posX && Gdx.input.getX() < posX + sprite.getWidth())&&(Gdx.input.getY() > Gdx.graphics.getHeight() - posY - sprite.getHeight() && Gdx.input.getY() < Gdx.graphics.getHeight() - posY ))
			return true;			
		return false;		
	}


    public String getText() {
        return text;
    }
}
