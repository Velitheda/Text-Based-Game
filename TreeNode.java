// Written by Jasmine Vickery
// This is a class for the nodes in a tree for having dialogue

import java.util.*;

public class TreeNode {

	private int index;//this is the index of the node when the tree is stored in an array, stored here too as a useful check
	private String dialogue;
	private List<String> keywords;// the keywords in the dialogue, checked in the parent that will take the player to this node
	private TreeNode parent;
	private List<TreeNode> children;
	private ArrayList<Integer> childrenIndexes; 
	private boolean visited;
	private boolean tempVisited; //this is just to help with iterating over the tree
	private boolean accessible;
	
	public TreeNode(int index, boolean accessible, String dialogue, List<String> keywords, ArrayList<Integer> childrenIndexes, TreeNode parent) {
		
		children = new ArrayList<TreeNode>();
		
		visited = false;
		this.accessible = accessible;
		this.dialogue = dialogue;
		this.keywords = keywords;
		this.childrenIndexes = childrenIndexes;
		this.parent = parent;
		
	}

	public String getDialogue() {
		return dialogue;
	}

	public void setDialogue(String dialogue) {
		this.dialogue = dialogue;
	}

	public List<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isAccessible() {
		return accessible;
	}

	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public ArrayList<Integer> getChildrenIndexes() {
		return childrenIndexes;
	}

	public void setChildrenIndexes(ArrayList<Integer> childrenIndexes) {
		this.childrenIndexes = childrenIndexes;
	}

	public TreeNode getParent() {
		return parent;
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	public boolean isTempVisited() {
		return tempVisited;
	}

	public void setTempVisited(boolean tempVisited) {
		this.tempVisited = tempVisited;
	}

	
}
