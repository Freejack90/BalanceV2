package main.levels;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap;
import main.AndroidGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
//import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


import javax.swing.*;

import static javax.swing.Timer.*;


public class LevelBuilder extends AbstractScreen{
	private LevelManager manager;
	private World world;
	//private Box2DDebugRenderer render;
	//private Matrix4 mtx = new Matrix4();
	private int clickTimes = 0;
	private Sprite currentSprite;
    private Label progressLab;
    private Label timer;
    private Label gamerOverLab;
    private Label timeCheckLab;
    private String levelProgress;
    private float timeCheck = 6;
    private boolean isLose = false;


    Date time = new Date();
    long startTime = time.getTime();
    SimpleDateFormat dateFormat = new SimpleDateFormat("mm : ss");

    public LevelBuilder(int level) {
        init(level);
    }
    private void init(int level) {
        manager = new LevelManager("data/levels.xml");
        manager.setLevel(level);
    }
    public LevelBuilder() {
        init(0);
    }

    public void create(AndroidGame game) {
        super.create(game);


        resetBtn = new TextButton("Reset", buttonStyle);
        exitBtn = new TextButton("Exit", buttonStyle);
        scoreLabel = new Label("Score: "+timer, labelStyle);
        progressLab = new Label("Level: "+levelProgress, labelStyle);
        gamerOverLab = new Label("", labelStyle);
        timeCheckLab = new Label("", labelStyle);
        //button = new Button(new TextureRegionDrawable(new TextureRegion(new Texture("data/images/button.png"), 109, 32)));
        ui.addActor(resetBtn);
        ui.addActor(exitBtn);
        ui.addActor(scoreLabel);
        ui.addActor(progressLab);
        ui.addActor(gamerOverLab);
        ui.addActor(timeCheckLab);
        exitBtn.translate(0, 50);
        scoreLabel.translate(50, 700);
        progressLab.translate(50, 650);
        timeCheckLab.translate(50, 600);
        setCenter(gamerOverLab, 100);

        //resetBtnForm = new Button(screenWidth/2, screenHeight/2, "Reset");
        //exitBtn = new Button(100, 50, "Exit");
        //exitBtnForm = new Button(screenWidth/2, screenHeight/2 + 50, "Exit");
        //progressLab = new Label(100, 150, "3/30");
		trace("LevelBuilder");



		world = new World(new Vector2(0,-15), true);
		//render = new Box2DDebugRenderer();
//		mtx.setToOrtho2D(0, 0, screenWidth/ModelHelper.meters, screenHeight/ModelHelper.meters);
		//Creating static objects
        destroyBodies();
        createStatics();


        resetBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                clickTimes = 0;
                destroyBodies();
                createStatics();
            }
        });
        exitBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                curGame.setLevel(new IntroScreen());
            }
        });
        ui.addListener(new ClickListener(){
            public boolean touchDown(InputEvent event, float x, float y,int pointer, int button){
                Actor curActor = ui.hit(x, y, true);

                if(clickTimes < manager.getCount(ObjectType.DYNAMICS) && curActor == null){
                        currentSprite = ModelHelper.getSprite(
                                manager.getObjects(ObjectType.DYNAMICS)
                                        .get(clickTimes)
                                        .getType()
                        );
                        changePosition();
                }else if(currentSprite != null){
                    currentSprite = null;
                }
                return true;
            }
            public void touchDragged (InputEvent event, float x, float y, int pointer) {
                if(currentSprite != null){
                    changePosition((int)x, (int)y);
                }
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                if(currentSprite != null){
                    ObjectType newBody = manager.getObjects(ObjectType.DYNAMICS).get(clickTimes);
                    newBody.setPosition((int)x, (int)y);
                   // System.out.print(mouseX+"Место клика");
                    ModelHelper.create(world, ObjectType.DYNAMICS, newBody);
                    currentSprite = null;
                    clickTimes++;
                }
            }
        });


	}

    public void createStatics() {

        Iterator<ObjectType> statics = manager.getObjects(ObjectType.STATICS).iterator();
        while (statics.hasNext()) {
            ObjectType stat = statics.next();
            ModelHelper.create(world, ObjectType.STATICS, stat);
        }

        System.out.print(manager.getCount(ObjectType.STATICS));
    }
	
	public void render() {
		super.render();
        levelProgress = (manager.getLevel()+1)+"/"+manager.getLevelCount();
        String timer = dateFormat.format(Calendar.getInstance().getTime().getTime() - startTime);

        progressLab.setText("Level: "+levelProgress);
        scoreLabel.setText("Score: "+timer);

		//render.render(world, mtx);
		batch.begin();		
			drawBar();
			drawBodies();

			if(currentSprite != null) 
				currentSprite.draw(batch);		

        /* Определение победы в уровне */
        batch.end();
        if(clickTimes == manager.getCount(ObjectType.DYNAMICS)){
            timeCheck -= Gdx.graphics.getDeltaTime();
            if(timeCheck > 0 && isLose == false){
                timeCheckLab.setText("Проверка : "+(int)timeCheck);
            }else if(isLose == false){
                manager.nextLevel();
                destroyBodies();
                createStatics();
                clickTimes = 0;
                timeCheck = 5;
                timeCheckLab.setText("");
            }


        }




       
	}


	
	public void update() {
		world.step(Gdx.graphics.getDeltaTime(), 6, 6);
		//onTouchAndMove();
	}


    private void destroyBodies() {
        Iterator<Body> bodies = world.getBodies();
            while (bodies.hasNext()) {
                Body curBody = bodies.next();
                if(curBody != null){
                    world.destroyBody(curBody);
                }
            }

    }

    private void changePosition(int x, int y){
		currentSprite.setPosition(
				x-currentSprite.getWidth()/2,
				y-currentSprite.getHeight()/2
		);
	}
    private void changePosition()  {
        changePosition(mouseX, mouseY);
    }
	
	private void drawBar(){
		if(clickTimes > manager.getCount(ObjectType.DYNAMICS) - 1) return;
		
		ArrayList<ObjectType> levelmap = manager.getObjects(ObjectType.DYNAMICS);
		int leftMargin = 32;
		int topMargin = 50;
		int offset = 42;
	
		for (int index = clickTimes; index < manager.getCount(ObjectType.DYNAMICS); index++){				
			String typePart = levelmap.get(index).getType();
			String[] parts = typePart.split("_");
			String firstPart = parts[0];
			Sprite tempSprite = ModelHelper.getSprite(firstPart);
			tempSprite.setPosition(leftMargin, Gdx.graphics.getHeight() - topMargin);
			leftMargin += offset;
			tempSprite.draw(batch);
		}			
	}
	
	public void drawBodies() {
		Iterator<Body> bodies = world.getBodies();		
		while(bodies.hasNext()){
            Body curBody = bodies.next();

            if(curBody != null){
                Sprite bodySprite = (Sprite)curBody.getUserData();
                bodySprite.setPosition(curBody.getPosition().x*ModelHelper.meters-bodySprite.getWidth()/2, curBody.getPosition().y*ModelHelper.meters-bodySprite.getHeight()/2);
                bodySprite.setRotation((float)Math.toDegrees(curBody.getAngle()));
                bodySprite.draw(batch);
                gamerOverLab.setText("");
                if((curBody.getPosition().y) < 0){
                    //resetBtnForm.draw(batch);
                    //exitBtnForm.draw(batch);
                    gamerOverLab.setText("GameOVER!");
                    timeCheckLab.setText("");
                    timeCheck = 0;
                    destroyBodies();
                    isLose = true;


                   /* if(Gdx.input.justTouched()){
                        clickTimes = 0;
                        destroyBodies();
                        createStatics();

                    }
                    if(Gdx.input.justTouched()){
                        this.curGame.setLevel(new IntroScreen());
                    }*/
                   // this.curGame.setLevel(new IntroScreen());
                }
            }else if(curBody == null && curBody.getPosition().y > 0){


            }

		}
	}


	public void dispose() {}

	public void pause() {}

	public void resize(int arg0, int arg1) {

        ui.setViewport(arg0, arg1, true);

    }

	public void resume() {}
}
