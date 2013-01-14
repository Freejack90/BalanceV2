package main.levels;

import java.util.ArrayList;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.XmlReader;
import com.badlogic.gdx.utils.XmlReader.Element;

public class LevelManager {	
	//Private vars
	private String filepath;
	private int level = 5;
    private int levelCount = 0;

	private int countDyn = 0;
	private int countStat = 0;
	
	private LevelMap map;
	
	LevelManager(String path) {
		this.filepath = path;
		map = new LevelMap();
		parseFile();
	}
	
	private void parseFile() {
		//File Reading
		XmlReader reader = new XmlReader();
		Element root = reader.parse(Gdx.files.internal(this.filepath).readString());
        this.levelCount = root.getChildCount();
		Element levelNode = root.getChild(this.level);
		Element dynObjects = levelNode.getChild(ObjectType.DYNAMICS);
		Element statObjects = levelNode.getChild(ObjectType.STATICS);
		this.countDyn = dynObjects.getChildCount();
		this.countStat = statObjects.getChildCount();
		
		//Get Dynamics
		for (int i = 0; i < this.countDyn; i++){
			Element currentNode = dynObjects.getChild(i);
			
			int angle = currentNode.getAttribute("angle") != null ? Integer.parseInt(currentNode.getAttribute("angle")): 0;
			String type = currentNode.getAttribute("type") != null ?  currentNode.getAttribute("type") : "cube";
			
			ObjectType object = new ObjectType(type, angle, null);
			map.add(object, ObjectType.DYNAMICS);
		}
		
		//Get Statics
		for (int i = 0; i < this.countStat; i++){
			Element currentNode = statObjects.getChild(i);
			int angle = currentNode.getAttribute("angle") != null ? Integer.parseInt(currentNode.getAttribute("angle")): 0;
			String type = currentNode.getAttribute("type") != null ?  currentNode.getAttribute("type") : "cube";
			int posx = getX(currentNode.getAttribute("x"));
			int posy = getY(currentNode.getAttribute("y"));
			Point position = new Point(posx, posy);
			ObjectType object = new ObjectType(type, angle, position);
			map.add(object, ObjectType.STATICS);
		}
	}
	
	private int getY(String coord){ return AbstractScreen.screenHeight - AbstractScreen.screenHeight/100 * Integer.parseInt(coord); }
	
	private int getX(String coord){ return AbstractScreen.screenWidth/100 * Integer.parseInt(coord);}
	
	public int getCount(int type) {	return type == ObjectType.DYNAMICS ? countDyn : countStat; }
	
	public ArrayList<ObjectType> getObjects(int type) { return map.get(type); }

    public void setLevel(int level) {
        if (this.levelCount <= level && level >= 0)
            this.level = level;
        else this.level = 0;
        parseFile();
    }

    public int getLevel(){return this.level;}
    public void nextLevel() {
        this.setLevel(this.level+1);
    }
}
