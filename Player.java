// Written by Jasmine Vickery

//This is the class for the player which the user controls
//Extending monster will allow it to inherit all the basic code governing fair combat

import java.util.List;

public class Player extends Monster {

	private Node location;

	public Player(Node startingLocation) {
		// The numbers might need to be changed, but since there will
		// only be one instance it might not be a problem hard-coding here.
		super(10, 10);

		location = startingLocation;
	}

	public Node getLocation() {
		return location;
	}

	// Returns true if the player successfully moved in the direction given, and
	// checks if there is an edge moving from the player's location to the
	// direction specified
	public boolean move(Direction direction) {
		List<Edge> edges = location.getEdges();
		for (Edge edge : edges) {
			if (edge.getDirection() == direction) {
				location.setVisited(true);// old location is now visited
				location = edge.getNodeTo();// set new location
				location.printInfo();
				return true;
			}
		}
		return false;
	}

}
