package lab09_alg;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.UUID;

public class Heap { // This class is based in a class provided by Vicente
	private PriorityQueue<Node> nodes;
	private HashMap<UUID, Node> usedNodes;

	  public Heap() {
	    nodes = new PriorityQueue<Node>();
	    usedNodes = new HashMap<UUID, Node>();
	  }

	  public void createEmpty() {
	    nodes.clear();
	  }

	  public void insert(Node node) {
	    if (!nodeRepeated(node)) {
	      nodes.add(node);
	    }
	  }

	  private boolean nodeRepeated(Node node) {
	    for (Node n : usedNodes.values()) {
	      if (node.equals(n))
	        return true;
	    }
	    return false;
	  }

	  public boolean empty() {
	    return nodes.isEmpty();
	  }

	  public int estimateBest() {
	    return nodes.peek().getHeuristicValue();
	  }

	  public Node extractBestNode() {
	    Node node = nodes.poll();
	    return node;
	  }
}
