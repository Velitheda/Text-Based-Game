// Written by Jasmine Vickery

//I wanted to call this an Item class, or Object, except that is illegal in java.
//It will be the parent class for a lot of other classes in the game.
//Items, rooms and people will all inherit from it.


import java.util.ArrayList;

public class Thing {

	String title;
	String description;
	String textFileName;// this is how we will read in all its stuff
						// if we choose to do that here.
	private ArrayList<State> States;

	public Thing() {
		
	}

}
