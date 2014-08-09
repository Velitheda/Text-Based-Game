// Written by Jasmine Vickery

//This is a class for the the routes that exist between nodes

public class Edge {

	private Node from;
	private Node to;
	private Direction direction;//the direction from the first node (called 'from) to the second node (called 'to').
	
	public Edge(Node from, Node to, Direction direction){
		this.from = from;
		this.to = to;
		this.direction = direction;
	}

	public Node getNodeFrom() {
		return from;
	}

	public void setNodeFrom(Node from) {
		this.from = from;
	}

	public Node getNodeTo() {
		return to;
	}

	public void setNodeTo(Node to) {
		this.to = to;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
