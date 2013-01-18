package main.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class ModelHelper {
	public static int meters = 32;
	public static Texture map = new Texture("data/map.png");
	public static float friction = 0.5f;
	public static int density = 10;
	public static void create(World world, int type, ObjectType object) {
		switch (object.getShape()) {
			case CUBE: createCube(world, object.getSize().x, object.getType(), type, object.getAngle(), object.getPosition()); break;
			case CIRCLE: createCircle(world, (float)object.getSize().x, object.getType(), type, object.getAngle(), object.getPosition()); break;
			case RECT: createRect(world, object.getSize(), object.getType(), type, object.getAngle(), object.getPosition()); break;
			case TRIANGLE: createTriangle(world, (float)object.getSize().x/100, object.getType(), type, object.getAngle(), object.getPosition()); break;
		}
	}
	
	public static void createRect(World world, Point size, String stringType, int type, int angle, Point position) {
		//Shape
		PolygonShape shape = new PolygonShape();
		shape.setAsBox((float)size.x/meters/2, (float)size.y/meters/2);
		
		//BodyDef
		BodyDef bd = new BodyDef();		
		bd.angle = MathUtils.degreesToRadians * (-angle);
		bd.position.set((float)position.x /meters, (float)position.y/meters);
		//Type
		if(type == ObjectType.STATICS)
			bd.type = BodyType.StaticBody;
		else 
			bd.type = BodyType.DynamicBody;
		//Fixture
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.friction = friction;
		fixture.density = density;
		//Body
		Body body = world.createBody(bd);		
		body.createFixture(fixture);
		body.setUserData(getSprite(stringType));
	}
	
	public static void createCube(World world, int size, String stringType, int type, int angle, Point position) {
		createRect(world, new Point(size, size), stringType, type, angle, position);
	}
	
	public static void createTriangle(World world, float coef, String stringType, int type, int angle, Point position) {
		PolygonShape shape = new PolygonShape();
		Vector2[] verticles = new Vector2[3];
	    verticles[0] = new Vector2(2*coef  , -1*coef);
	    verticles[1] = new Vector2(0 , 1*coef);
	    verticles[2] = new Vector2(-2*coef , -1*coef);
		shape.set(verticles); 
		BodyDef bd = new BodyDef();
		float setAngle = MathUtils.degreesToRadians*(-angle);
		bd.angle = setAngle;

		//bd.linearDamping = 1f;
		bd.position.set((float)position.x/meters, (float)position.y/meters);
        System.out.print((float)(position.x/meters));
		if(type == ObjectType.STATICS){
            bd.type = BodyType.StaticBody;
        }else{
			bd.type = BodyType.DynamicBody;
		}
		Body body = world.createBody(bd);
		
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.friction = friction;
		fixture.density = density;
		body.createFixture(fixture);
		body.setUserData(getSprite(stringType));
	}
	
	public static void createCircle(World world, float radius, String stringType, int type, int angle, Point position){
		
		CircleShape shape = new CircleShape();
		shape.setRadius((float)radius/meters/2);
		BodyDef bd = new BodyDef();
		bd.position.set((float)position.x/meters, (float)position.y/meters);
		if(type == ObjectType.STATICS){
			bd.type = BodyType.StaticBody;
		}else{
			bd.type = BodyType.DynamicBody;
		}
		Body body = world.createBody(bd);
		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.friction = friction;
		fixture.density = density;
		body.createFixture(fixture);
		body.setUserData(getSprite(stringType));

	}
	
	
	public static Sprite getSprite(String currentShape){
		//return new Sprite(map, 0,0,48,48);

        if (currentShape.equals("cube_s")) {
            return new Sprite(map, 0, 0, 48, 48);
        } else if (currentShape.equals("cube_m")) {
            return new Sprite(map, 48, 0, 64, 64);
        } else if (currentShape.equals("cube_l")) {
            return new Sprite(map, 112, 0, 96, 96);
        } else if (currentShape.equals("rect_s")) {
            return new Sprite(map, 208, 0, 128, 24);
        } else if (currentShape.equals("rect_m")) {
            return new Sprite(map, 208, 24, 192, 32);
        } else if (currentShape.equals("rect_l")) {
            return new Sprite(map, 208, 56, 256, 48);
        } else if (currentShape.equals("triangle_s")) {
            return new Sprite(map, 48, 128, 64, 32);
        } else if (currentShape.equals("triangle_m")) {
            return new Sprite(map, 336, 104, 96, 48);
        } else if (currentShape.equals("triangle_l")) {
            return new Sprite(map, 208, 104, 128, 64);
        } else if (currentShape.equals("circle_s")) {
            return new Sprite(map, 0, 48, 48, 48);
        } else if (currentShape.equals("circle_m")) {
            return new Sprite(map, 48, 64, 64, 64);
        } else if (currentShape.equals("circle_l")) {
            return new Sprite(map, 112, 96, 96, 96);
        } else if (currentShape.equals("cube")) {
            return new Sprite(map, 64, 224, 32, 32);
        } else if (currentShape.equals("rect")) {
            return new Sprite(map, 32, 224, 32, 32);
        } else if (currentShape.equals("triangle")) {
            return new Sprite(map, 96, 224, 32, 32);
        } else if (currentShape.equals("circle")) {
            return new Sprite(map, 0, 224, 32, 32);
        } else {
            return new Sprite();
        }

	}
	
	
}
