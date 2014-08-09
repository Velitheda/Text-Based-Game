// Written by Jasmine Vickery

// This is a text-based adventure game inspired by the game Zork.  

// This class contains the main game loop, and also handles moving, fighting, and iterating over dialogues trees
// The fighting is not functional yet, and there are a few bugs in the dialogue tree iteration.

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class GameLoop {

	private Graph map;
	private Player player;

	// The tree won't normally be stored here, it is just here for testing
	// purposes at the moment
	private DialogueTree dialogueTree;
	private boolean foundDialogue;

	public GameLoop(String adjacencyListName, String nodeDescriptionsName) {

		File adjacencyList = new File(adjacencyListName);
		File nodeDescriptions = new File(nodeDescriptionsName);
		map = new Graph(adjacencyList, nodeDescriptions);

		dialogueTree = new DialogueTree();

		// node 0 is the starting node for the game
		Node startingLocation = map.getNodes().get(0);

		player = new Player(startingLocation);

	}

	public void mainLoop() {

		String input;

		do {
			// show the player where they are to start off
			player.getLocation().printInfo();

			input = Printer.getInput();

			if (move(input))
				continue;

			switch (input) {
			case "look":
				// don't know if title should be included
				player.getLocation().printDescription();
				break;
			case "talk":
				talk();
				break;
			case "quit":
				break;
			case "fight":
				fight(new Monster(10, 10));
			default:
				Printer.print("Enter a direction, \"talk\" to have a conversation, \"fight\" to have a battle or \"quit\" to leave");
			}
		} while (!input.equals("quit"));// this ends the game

	}

	public void fight(Monster monster) {
		Printer.print("Bam! A terrifying monster jumps out of nowhere!");
		while (player.getHealth() >= 0 || monster.getHealth() >= 0) {// both are
																		// alive

			Printer.print("The super terrifing monster tries to attack you!! Oh no, what will you do?");
			String action = Printer.getInput();
			if (action == "attack")
				;
			// compute result
			// print result
			// go around again...
		}
	}

	public void talk() {
		Printer.print("A person inexplicitly materalises in front of you purely for you to converse with."); // :P

		TreeNode currentNode = dialogueTree.getRoot();
		String input = null;// could be buggy with the space

		do {

			foundDialogue = false;
			Printer.print(currentNode.getDialogue());
			currentNode.setVisited(true);// probably don't need this
			input = Printer.getInput();// could break here
			Scanner inputScanner = new Scanner(input);

			// checking if any of the words the player entered match any of the
			// keywords for the children of the current node.
			while (inputScanner.hasNext()) {
				String currentWord = inputScanner.next();
				TreeNode newNode = recursiveIterator(currentNode, currentWord);
				if (newNode != null) {
					currentNode = newNode;
				}
				for (TreeNode node : dialogueTree.getNodeArray()) {
					node.setTempVisited(false);
				}
			}
			inputScanner.close();

			// goodbye should probably be in the text file too
			if (!foundDialogue && input != null && !input.equals("goodbye")) {

				// this string should possibly be in the textfile
				Printer.print("Find something better to talk about or say \"goodbye\"");
			}

		} while (!input.equals("goodbye"));

	}

	// finish at top of tree if no dialogue found
	private TreeNode recursiveIterator(TreeNode currentNode, String currentWord) {
		if (foundDialogue || currentNode == null || currentNode.isTempVisited()) {
			return currentNode;
		}
		List<TreeNode> children = currentNode.getChildren();
		currentNode = checkChildrenForKeyWords(currentNode, currentWord);

		for (TreeNode child : children) {
			// accessible children
			if ((!child.isTempVisited()) && child.isVisited()) {
				currentNode = recursiveIterator(child, currentWord);
			}
		}
		if (!foundDialogue) {
			currentNode = recursiveIterator(currentNode.getParent(),
					currentWord);
		}
		return currentNode;
	}

	private TreeNode checkChildrenForKeyWords(TreeNode currentNode,
			String currentWord) {
		List<TreeNode> children = currentNode.getChildren();
		for (TreeNode child : children) {
			if (child.isTempVisited())
				continue;
			child.setTempVisited(true);
			if (child.isAccessible()) {
				List<String> keyWords = child.getKeywords();
				for (String keyWord : keyWords)
					if (keyWord.equals(currentWord)) {
						currentNode = child;
						currentNode.setVisited(true);
						foundDialogue = true;
					}
			}
		}
		// this checks the currentNode for its own keywords, which is a way of
		// checking the root for keywords, since it is the child of nothing
		if (!foundDialogue) {
			List<String> keyWords = currentNode.getKeywords();
			for (String keyWord : keyWords) {
				if (keyWord.equals(currentWord)) {
					foundDialogue = true;
				}
			}
		}
		return currentNode;
	}

	public boolean move(String input) {
		// logically what is happening is this variable starts out false,
		// gets changed to true if the player moved successfully, then is
		// send back and it tells the program if the player has moved or not.
		boolean moved = false;
		switch (input) {
		case "north":
			moved = player.move(Direction.NORTH);
			break;
		case "south":
			moved = player.move(Direction.SOUTH);
			break;
		case "west":
			moved = player.move(Direction.WEST);
			break;
		case "east":
			moved = player.move(Direction.EAST);
			break;
		case "up":
			moved = player.move(Direction.UP);
			break;
		case "down":
			moved = player.move(Direction.DOWN);
			break;
		}
		return moved;
	}
}
