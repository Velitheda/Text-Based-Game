//Written by Jasmine Vickery

//This is a class for the locations the player can visit
//It is a node that will be placed within a graph data structure

import java.util.ArrayList;
import java.util.List;


public class Node extends Thing{

	private boolean visited;
	private boolean accessible;//this is for unlocking a room, 
	
	private List<Edge> edges;
	
	//need array of items
	
	public Node(String title, String description){
		this.title = title;
		this.description = description;
		edges = new ArrayList<Edge>();
		visited = false;
	}
	
	public void setEdges(List<Edge> edges){
		this.edges = edges;
	}
	
	public List<Edge> getEdges(){
		return edges;
	}
	
	public void printInfo(){
		Printer.print(title);
		if(!visited)
			Printer.print(description);
	}

	//This method is used if the player typed 'look'
	//Should possibly combine it with a boolean parameter
	public void printDescription(){
		Printer.print(description);
	}
	
	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
