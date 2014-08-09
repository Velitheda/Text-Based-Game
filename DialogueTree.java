// Written by Jasmine Vickery

// This class, combined with the TreeNode class, together form a tree for dialogue.
// This is where the reading in of the text file that contains the dialogue and the
// assigning of it to and creating of the children of the tree happens.  

// The way dialogue happens is that the computer prints the dialogue in its current
// node, and if the player types back one of the key-words corresponding to one any
// visited node or their children, that node becomes the current node and the computer 
// responds with dialogue from there.

// The text file is structure with the number of the node first,
// then the number of its children each with a space after them,
// then the dialogue for that node, then a blank line to separate 
// it from the next node.  Currently I am using a very simple example.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DialogueTree {

	private TreeNode root;

	private List<TreeNode> nodeArray;

	public DialogueTree() {
		nodeArray = new ArrayList<TreeNode>();
		readTextFile();
	}

	private void readTextFile() {
		// reads text file, and sets up tree with each node containing the
		// integer indexes of its children,
		// rather than the references first

		// hard-coded for now, but this can be changed to load a different text
		// file
		File dialogueFile = new File("Dialogue1.txt");
		Scanner s;
		TreeNode currentNode = null;
		TreeNode parent = null;

		try {
			s = new Scanner(dialogueFile);

			while (s.hasNext()) {

				int index = Integer.parseInt(s.nextLine());

				// helper method to read all the number indexes for the children
				// nodes
				ArrayList<Integer> childIndexes = readChildIndexes(s.nextLine());

				// helper method again to read all the keywords that trigger
				// that node's dialogue
				ArrayList<String> keyWords = readKeyWords(s.nextLine());

				// the actual dialogue that the person says
				String dialogue = s.nextLine(); 
				if (currentNode != null)
					parent = currentNode;

				currentNode = new TreeNode(index, true, dialogue, keyWords,
						childIndexes, parent);
				nodeArray.add(index, currentNode);

				s.nextLine();// skips the blank line between node info
			}

			// gives each node a direct reference to its children, rather than
			// an index of an address stored in an array outside of itself
			setReferencesToChildren();

			root = nodeArray.get(0); // 0 should be the root

		} catch (FileNotFoundException e) {
			Printer.print("Can't find Dialogue file");
		}
	}

	private void setReferencesToChildren() {
		for (TreeNode node : nodeArray) {
			ArrayList<Integer> childrenIndexes = node.getChildrenIndexes();
			List<TreeNode> children = node.getChildren();

			for (int i : childrenIndexes)
				children.add(nodeArray.get(i));
		}
	}

	private ArrayList<Integer> readChildIndexes(String childIndexesString) {
		Scanner childIndexesScanner = new Scanner(childIndexesString);
		ArrayList<Integer> childIndexes = new ArrayList<Integer>();
		while (childIndexesScanner.hasNext()) {
			childIndexes.add(childIndexesScanner.nextInt());
		}
		return childIndexes;
	}

	private ArrayList<String> readKeyWords(String keyWordsString) {
		Scanner keyWordsScanner = new Scanner(keyWordsString);
		ArrayList<String> keyWords = new ArrayList<String>();
		while (keyWordsScanner.hasNext()) {
			keyWords.add(keyWordsScanner.next());
		}
		return keyWords;
	}

	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public List<TreeNode> getNodeArray() {
		return nodeArray;
	}

	public void setNodeArray(List<TreeNode> nodeArray) {
		this.nodeArray = nodeArray;
	}

}
