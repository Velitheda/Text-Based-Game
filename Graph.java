import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Written by Jasmine Vickery
 * 
 * This is a class for the graph that will represent all the locations and the links between them.
 *
 */

public class Graph {

	private List<Node> nodes;
	private List<Edge> edges;

	public Graph(File adjacencyList, File nodeDescriptions) {
		nodes = new ArrayList<Node>();
		edges = new ArrayList<Edge>();
		readFiles(adjacencyList, nodeDescriptions);

	}

	private void readFiles(File adjacencyList, File nodeDescriptions) {

		try {
			readDescriptions(nodeDescriptions);
			readAdjacencyList(adjacencyList);
		} catch (FileNotFoundException e) {
			Printer.print("Can't find both files");
		}
		// I need to close the scanners somehow
	}

	private void readDescriptions(File nodeDescriptions)
			throws FileNotFoundException {
		Scanner descriptionScanner = new Scanner(nodeDescriptions);

		while (descriptionScanner.hasNextLine()) {
			// iterate through the descriptions and generate a list of nodes
			// with no connections between them yet.

			// this line just skips the scanner past the index
			// that is just there to help tell what node it is
			int index = Integer.parseInt(descriptionScanner.nextLine());
			String nodeTitle = descriptionScanner.nextLine();

			String nodeDescription = descriptionScanner.nextLine();

			Node node = new Node(nodeTitle, nodeDescription);
			nodes.add(node);
			// skips the blank line for readability
			descriptionScanner.nextLine();
		}
	}

	// this algorithm will create two edges between each node, each in different
	// directions
	private void readAdjacencyList(File adjacencyList)
			throws FileNotFoundException {
		Scanner listScanner = new Scanner(adjacencyList);

		while (listScanner.hasNext()) {

			// gets the index of the current node
			int index = Integer.parseInt(listScanner.nextLine());
			Node from = nodes.get(index);

			// gets all the nodes it connects to
			String listString = listScanner.nextLine();
			Scanner edgeScanner = new Scanner(listString);

			while (edgeScanner.hasNext()) {

				int nodeToIndex = Integer.parseInt(edgeScanner.next());
				Node to = nodes.get(nodeToIndex);
				String directionString = edgeScanner.next();
				Direction direction = convertToDirection(directionString);

				Edge edge = new Edge(from, to, direction);
				edges.add(edge);// adds edge to global list of edges
				from.getEdges().add(edge);// adds edge to node specific to it
			}
			edgeScanner.close();

			// skips the blank line used for readability, there must be one at
			// the end of the text file
			listScanner.nextLine();
		}
	}

	// this method should possibly be in the enum direction class as another
	// constructor
	private Direction convertToDirection(String direction) {
		switch (direction) {
		case "north":
			return Direction.NORTH;
		case "south":
			return Direction.SOUTH;
		case "west":
			return Direction.WEST;
		case "east":
			return Direction.EAST;
		case "up":
			return Direction.UP;
		default:
			return Direction.DOWN;
		}
	}

	public List<Node> getNodes() {
		return nodes;
	}

}
