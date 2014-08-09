// Written by Jasmine Vickery

// This is my enum for the constants that represent directions

public enum Direction {

	NORTH(1), SOUTH(-1), WEST(2), EAST(-2), UP(3), DOWN(-3);
	private int value;

	private Direction(int value) {// constructor must be private for an enum
		this.value = value;
	}

	public void reverseDirection() {// (this method doesn't work yet)
		value = value * -1;
	}
}
