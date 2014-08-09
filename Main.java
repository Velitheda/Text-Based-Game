// Written by Jasmine Vickery

// The class that starts the program, with the names of the text files currently 
// hard-coded which pass the connections between locations and their descriptions
// to the game loop.

// The first text file containing an adjacency list representing all 
// the connections between the nodes
// The node number comes first, followed by a line of all the nodes they join up
// to with each node having its direction to it straight afterwards, with a blank line
// separating all the nodes.

// The second argument is a text file containing the number of the node, 
// the title of the node and then its description.  There must be a blank line between nodes
// and at the end of the file for both files.

//I have only written sample files for now, without any game content yet.

public class Main {

	public static void main(String[] args) {

		GameLoop game = new GameLoop("adjencyList1.txt", "Descriptions1.txt");
		game.mainLoop();
	}

}
