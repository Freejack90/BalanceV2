package main.levels;

import java.util.ArrayList;

public class LevelMap {
	private ArrayList<ObjectType> statics = new ArrayList<ObjectType>();
	private ArrayList<ObjectType> dynamics = new ArrayList<ObjectType>();
	
	LevelMap() {
		
	}
	
	public void add(ObjectType object, int type) {
		if (type == ObjectType.DYNAMICS) 
			dynamics.add(object);
		else 
			statics.add(object);
	}
	
	public ArrayList<ObjectType> get(int type) {
		if (type == ObjectType.DYNAMICS) 
			return dynamics;
		else
			return statics;
			
	}
}
