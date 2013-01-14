package main.levels;

import java.util.Hashtable;


public class ObjectType {
	
	//Const
	public static final int DYNAMICS = 1;
	public static final int STATICS = 0;
	
	public enum Shapes {CUBE, TRIANGLE, RECT, CIRCLE}

	//Vars
	private String type;
	private Shapes shape;
	private int angle;
	private Point pos;
	private static Hashtable<String, Point> sizes = createSizes();
	private static Hashtable<String, Shapes> strToShape = createShape();
	
	ObjectType(String type, int angle, Point position) {		
		this.type = type;
		this.angle = angle;
		this.pos = position;
		this.shape = strToShape.get(type.split("_")[0]);

	}
	

	private static Hashtable<String, Shapes> createShape() {
		Hashtable<String, Shapes> tmp = new Hashtable<String, Shapes>();		
		tmp.put("cube", Shapes.CUBE);
		tmp.put("triangle", Shapes.TRIANGLE);
		tmp.put("rect", Shapes.RECT);
		tmp.put("circle", Shapes.CIRCLE);
		return tmp;
	}


	private static Hashtable<String, Point> createSizes() {
		Hashtable<String, Point> tmp = new Hashtable<String, Point>();
		
		tmp.put("cube_s", new Point(48,48));
		tmp.put("cube_m", new Point(64,64));
		tmp.put("cube_l", new Point(96,96));

		tmp.put("triangle_s", new Point(50,0));
		tmp.put("triangle_m", new Point(75,0));
		tmp.put("triangle_l", new Point(100,0));

		tmp.put("circle_s", new Point(48,0));
		tmp.put("circle_m", new Point(64,0));
		tmp.put("circle_l", new Point(96,0));

		tmp.put("rect_s", new Point(128,24));
		tmp.put("rect_m", new Point(192,32));
		tmp.put("rect_l", new Point(256,48));	
		return tmp;
	}


	public Point getSize(){	return sizes.get(type);	}
	public Point getPosition() {return this.pos;}
	public int getAngle() {return this.angle;}
	public String getType(){return type;}
	public Shapes getShape() {return shape;}


	public void setPosition(int x, int y) {
		pos = new Point(x, y);
	}
	
}
